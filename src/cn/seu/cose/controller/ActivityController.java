package cn.seu.cose.controller;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.seu.cose.entity.Activity;
import cn.seu.cose.entity.ActivityApplication;
import cn.seu.cose.entity.ActivityNews;
import cn.seu.cose.entity.ActivityPhoto;
import cn.seu.cose.entity.Comment;
import cn.seu.cose.entity.CommentPojo;
import cn.seu.cose.entity.Designer;
import cn.seu.cose.entity.Work;
import cn.seu.cose.entity.WorkPojo;
import cn.seu.cose.service.ActivityService;
import cn.seu.cose.service.CommentService;
import cn.seu.cose.service.DesignerService;
import cn.seu.cose.service.WorkService;
import cn.seu.cose.util.Constant.CommentType;
import cn.seu.cose.view.util.ViewUtil;

@Controller
public class ActivityController extends AbstractController {
	@Autowired
	ActivityService activityService;
	@Autowired
	WorkService workService;
	@Autowired
	DesignerService designerService;
	@Autowired
	CommentService commentService;

	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String activity(Model model) {
		basicIssue(model);
		List<Activity> activities = activityService.getRecentActivities();
		List<ActivityNews> latestNews = activityService.getActivityLatestNews();
		List<WorkPojo> hotWorks = workService.getHotWorksWithVoteCount(4);
		model.addAttribute("hotWorks", hotWorks);
		model.addAttribute("latestNews", latestNews);
		model.addAttribute("activities", activities);
		return "activity/index";
	}

	private void activityBasicIssue(Model model, Activity activity) {
		List<ActivityNews> recentActivityNews = activityService
				.getActivityLatestNewsByActivityId(activity.getId());
		Designer designer = designerService.getCurrentUser();
		if (designer != null) {
			ActivityApplication activityApplication = activityService
					.getActivityApplicationsByUserIdAndActivityId(
							designer.getId(), activity.getId());
			if (activityApplication != null) {
				model.addAttribute("activityApplied", true);
			}
		}
		model.addAttribute("activity", activity);
		model.addAttribute("recentNews", recentActivityNews);
		Timestamp now = new Timestamp(new Date().getTime());
		if (activity.getActBeginTime().before(now)
				&& activity.getActEndTime().after(now)) {
			model.addAttribute("activityStatus", 1);// on
		} else if (activity.getActEndTime().before(now)) {
			model.addAttribute("activityStatus", 2);// off
		} else {
			model.addAttribute("activityStatus", 3);// wait
		}
		if (activity.getAppBeginTime().before(now)
				&& activity.getAppEndTime().after(now)) {
			model.addAttribute("applyStatus", 1);// on
		} else if (activity.getAppEndTime().before(now)) {
			model.addAttribute("applyStatus", 2);// off
		} else {
			model.addAttribute("applyStatus", 3);// wait
		}
	}

	@RequestMapping(value = "/activity/current", method = RequestMethod.GET)
	public String currentActivity(Model model) {
		basicIssue(model);
		List<Activity> activities = activityService.getCurrentActivities();
		List<ActivityNews> latestNews = activityService.getActivityLatestNews();
		List<WorkPojo> hotWorks = workService.getHotWorksWithVoteCount(4);
		model.addAttribute("hotWorks", hotWorks);
		model.addAttribute("latestNews", latestNews);
		model.addAttribute("activities", activities);
		model.addAttribute(
				"activityType",
				"<span> / <a href='"
						+ ViewUtil.getContextPath()
						+ "/activity/current' style='color:#aaa'>正在进行</a></span>");
		return "activity/index";
	}

	@RequestMapping(value = "/activity/advance", method = RequestMethod.GET)
	public String advanceActivity(Model model) {
		basicIssue(model);
		List<Activity> activities = activityService.getAdvanceActivities();
		List<ActivityNews> latestNews = activityService.getActivityLatestNews();
		List<WorkPojo> hotWorks = workService.getHotWorksWithVoteCount(4);
		model.addAttribute("hotWorks", hotWorks);
		model.addAttribute("latestNews", latestNews);
		model.addAttribute("activities", activities);
		model.addAttribute(
				"activityType",
				"<span> / <a href='"
						+ ViewUtil.getContextPath()
						+ "/activity/advance' style='color:#aaa'>即将开始</a></span>");
		return "activity/index";
	}

	@RequestMapping(value = "/activity/review", method = RequestMethod.GET)
	public String reviewActivity(Model model) {
		basicIssue(model);
		List<Activity> activities = activityService.getReviewActivities();
		List<ActivityNews> latestNews = activityService.getActivityLatestNews();
		List<WorkPojo> hotWorks = workService.getHotWorksWithVoteCount(4);
		model.addAttribute("hotWorks", hotWorks);
		model.addAttribute("latestNews", latestNews);
		model.addAttribute("activities", activities);
		model.addAttribute(
				"activityType",
				"<span> / <a href='"
						+ ViewUtil.getContextPath()
						+ "/activity/review' style='color:#aaa'>往期回顾</a></span>");
		return "activity/index";
	}

	@RequestMapping(value = "/activity/{activityId}", method = RequestMethod.GET)
	public String viewActivity(Model model,
			@PathVariable("activityId") int activityId) {
		basicIssue(model);
		Activity activity = activityService.getActivityById(activityId);
		if (activity == null) {
			return "index";
		} else {
			activityBasicIssue(model, activity);
			List<WorkPojo> recentWorks = workService
					.getRecentWorksByActivityId(activityId);
			List<ActivityPhoto> recentActivityPhotos = activityService
					.getRecentActivityPhotoByActivityId(activityId);
			model.addAttribute("recentWorks", recentWorks);
			model.addAttribute("recentPhotos", recentActivityPhotos);
			return "activity/viewActivity";
		}
	}

	@RequestMapping(value = "/activity/{activityId}/works", method = RequestMethod.GET)
	public String viewActivityWorks(Model model,
			@PathVariable("activityId") int activityId,
			@RequestParam(value = "pn", required = false) Integer pn) {
		basicIssue(model);
		Activity activity = activityService.getActivityById(activityId);
		if (activity == null) {
			return "index";
		} else {
			activityBasicIssue(model, activity);
			pn = pn == null || pn <= 0 ? 1 : pn;
			int pageSize = 9;
			List<WorkPojo> works = workService
					.getWorksByActivityIdAndPnAndSize(activityId, pn, pageSize);
			int totalCount = workService.getWorksCountByActivityId(activityId);
			model.addAttribute("works", works);
			model.addAttribute("pageIndex", pn);
			model.addAttribute("pageCount",
					(int) Math.ceil((double) totalCount / pageSize));
			model.addAttribute("totalCount", totalCount);
			StringBuilder sb = new StringBuilder();
			sb.append(ViewUtil.getContextPath()).append("/activity/")
					.append(activityId).append("/works");
			model.addAttribute("uri", sb.toString());
			return "activity/activityWorks";
		}
	}

	@RequestMapping("/activity/{activityId}/apply")
	public void applyActivity(HttpServletResponse response,
			@PathVariable("activityId") int activityId) {
		try {
			Designer designer = designerService.getCurrentUser();
			if (designer == null) {
				response.sendRedirect(ViewUtil.getContextPath() + "/sign-in");
			} else {
				ActivityApplication app = activityService
						.getActivityApplicationsByUserIdAndActivityId(
								designer.getId(), activityId);
				if (app == null) {
					activityService.applyActivity(designer.getId(), activityId);
				}
				response.sendRedirect(ViewUtil.getContextPath() + "/activity/"
						+ activityId);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/activity/{activityId}/works/{workId}/vote", method = RequestMethod.POST)
	public void voteWork(HttpServletResponse response,
			@PathVariable("activityId") int activityId,
			@PathVariable("workId") int workId, Model model) {
		try {
			Designer designer = designerService.getCurrentUser();
			Writer writer = response.getWriter();
			if (designer == null) {
				writer.write("0");// 未登录
				writer.flush();
				return;
			}
			Activity activity = activityService.getActivityById(activityId);
			if (activity == null) {
				writer.write("1");// 无此活动
				writer.flush();
				return;
			}
			WorkPojo work = workService.getWorkViaId(workId);
			if (work == null || activityId != work.getActivityId()) {
				writer.write("2");// 无此作品
				writer.flush();
				return;
			} else {
				workService.updateWork(workId);
				writer.write("3");// 成功投票
				writer.flush();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/activity/{activityId}/works/{workId}", method = RequestMethod.GET)
	public String viewActivityWork(@PathVariable("activityId") int activityId,
			@PathVariable("workId") int workId, Model model,
			@RequestParam(value = "pn", required = false) Integer pn) {
		basicIssue(model);
		Activity activity = activityService.getActivityById(activityId);
		if (activity == null) {
			return "index";
		} else {
			activityBasicIssue(model, activity);
			WorkPojo work = workService.getWorkViaId(workId);
			if (work == null || work.getActivityId() != activityId) {
				return "index";
			} else {
				pn = pn == null || pn <= 0 ? 1 : pn;
				List<CommentPojo> comments = commentService
						.getCommentsByRefAndTypeAndPnAndSize(workId,
								CommentType.WORK.ordinal(), pn, 10);
				model.addAttribute("work", work);
				model.addAttribute("comments", comments);

				int totalCount = commentService.getCommentCountByRefAndType(
						workId, CommentType.WORK.ordinal());
				model.addAttribute("pageIndex", pn);
				model.addAttribute("pageCount",
						(int) Math.ceil((double) totalCount / 10));
				model.addAttribute("totalCount", totalCount);
				StringBuilder sb = new StringBuilder();
				sb.append(ViewUtil.getContextPath()).append("/activity/")
						.append(activityId).append("/works/").append(workId);
				model.addAttribute("uri", sb.toString());
				return "activity/viewActivityWork";
			}
		}
	}

	@RequestMapping(value = "/activity/{activityId}/works/{workId}/comment", method = RequestMethod.POST)
	public void commentWork(HttpServletResponse response,
			@PathVariable("activityId") int activityId,
			@PathVariable("workId") int workId,
			@RequestParam("message") String message) {

		try {
			Activity activity = activityService.getActivityById(activityId);
			WorkPojo work = workService.getWorkViaId(workId);
			if (activity == null || work == null
					|| work.getActivityId() != activityId) {
				response.getWriter().write("0");// 活动、作品错误
			} else {
				Designer designer = designerService.getCurrentUser();
				if (designer == null) {
					response.getWriter().write("1");// 未登录
				} else {
					Comment comment = new Comment();
					comment.setCommentType(CommentType.WORK.ordinal());
					comment.setContent(message);
					comment.setReferenceId(workId);
					comment.setUserId(designer.getId());
					commentService.insertComment(comment);
					response.getWriter().write("2");// 成功
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/activity/{activityId}/new-work", method = RequestMethod.GET)
	public String newWork(HttpServletResponse response,
			@PathVariable("activityId") int activityId, Model model) {
		try {
			Designer designer = designerService.getCurrentUser();
			if (designer == null) {
				response.sendRedirect(ViewUtil.getContextPath() + "/sign-in");
				return null;
			} else {
				Activity activity = activityService.getActivityById(activityId);
				if (activity == null) {
					response.sendRedirect(ViewUtil.getContextPath()
							+ "/activity");
					return null;
				}
				ActivityApplication app = activityService
						.getActivityApplicationsByUserIdAndActivityId(
								designer.getId(), activityId);
				if (app == null) {
					response.sendRedirect(ViewUtil.getContextPath()
							+ "/activity/" + activityId);
					return null;
				}
				basicIssue(model);
				activityBasicIssue(model, activity);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "activity/newWork";
	}

	@RequestMapping(value = "/activity/{activityId}/new-work", method = RequestMethod.POST)
	public void postWork(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@RequestParam MultipartFile[] file,
			@RequestParam("title") String title,
			@RequestParam("intro") String intro,
			@PathVariable("activityId") int activityId) throws IOException {
		basicIssue(model);
		Designer designer = designerService.getCurrentUser();
		if (designer == null) {
			response.sendRedirect(ViewUtil.getContextPath() + "/sign-in");
			return;
		}
		Activity activity = activityService.getActivityById(activityId);
		if (!designer.isCertificated() || activity == null) {
			response.sendRedirect(ViewUtil.getContextPath() + "/activity");
			return;
		}
		Work work = new Work();
		work.setWorkName(title);
		work.setIntro(intro);
		work.setUserId(designer.getId());
		work.setActivityId(activityId);
		StringBuilder workPics = new StringBuilder();
		String path = request.getSession().getServletContext()
				.getRealPath("static/works");
		boolean notFirst = false;
		for (MultipartFile f : file) {
			DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = format.format(new Date())
					+ f.getOriginalFilename();
			File targetFile = new File(path, fileName);
			String contentType = f.getContentType();
			if (contentType.equals("image/jpeg")
					|| contentType.equals("image/jpg")
					|| contentType.equals("image/bmp")
					|| contentType.equals("image/png")
					|| contentType.equals("image/gif") && !targetFile.exists()) {
				// 保存
				try {
					f.transferTo(targetFile);
					if (notFirst) {
						workPics.append(";");
					}
					workPics.append(fileName);
					notFirst = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		work.setWorkPics(workPics.toString());
		workService.insertWork(work);
		response.sendRedirect(ViewUtil.getContextPath() + "/activity/"
				+ activityId + "/works");
	}

	@RequestMapping(value = "/activity/{activityId}/photos", method = RequestMethod.GET)
	public String viewActivityPhotos(Model model,
			@PathVariable("activityId") int activityId,
			@RequestParam(value = "pn", required = false) Integer pn) {
		basicIssue(model);
		Activity activity = activityService.getActivityById(activityId);
		if (activity == null) {
			return "index";
		} else {
			activityBasicIssue(model, activity);
			pn = pn == null || pn <= 0 ? 1 : pn;
			int pageSize = 9;
			List<ActivityPhoto> photos = activityService
					.getActivityPhotoByActivityIdAndPnAndSize(activityId, pn,
							pageSize);// 分页

			int totalCount = activityService
					.getActivityPhotosCountByActivityId(activityId);
			model.addAttribute("photos", photos);
			model.addAttribute("pageIndex", pn);
			model.addAttribute("pageCount",
					(int) Math.ceil((double) totalCount / pageSize));
			model.addAttribute("totalCount", totalCount);
			StringBuilder sb = new StringBuilder();
			sb.append(ViewUtil.getContextPath()).append("/activity/")
					.append(activityId).append("/photos");
			model.addAttribute("uri", sb.toString());
			return "activity/activityPhotos";
		}
	}

	@RequestMapping(value = "/activity/{activityId}/photos/{photoId}", method = RequestMethod.GET)
	public String viewActivityPhoto(@PathVariable("activityId") int activityId,
			@PathVariable("photoId") int photoId, Model model,
			@RequestParam(value = "pn", required = false) Integer pn,
			HttpServletResponse response) {
		try {
			basicIssue(model);
			Activity activity = activityService.getActivityById(activityId);
			ActivityPhoto photo = activityService.getActivityPhotoById(photoId);
			if (activity == null || photo == null
					|| photo.getActivityId() != activityId) {
				response.sendRedirect(ViewUtil.getContextPath() + "/activity");
				return null;
			} else {
				activityBasicIssue(model, activity);
				pn = pn == null || pn <= 0 ? 1 : pn;
				int pageSize = 10;
				List<CommentPojo> comments = commentService
						.getCommentsByRefAndTypeAndPnAndSize(photoId,
								CommentType.ACTIVITY_PHOTO.ordinal(), pn,
								pageSize);
				model.addAttribute("photo", photo);
				model.addAttribute("comments", comments);

				int totalCount = commentService.getCommentCountByRefAndType(
						photoId, CommentType.ACTIVITY_PHOTO.ordinal());
				model.addAttribute("pageIndex", pn);
				model.addAttribute("pageCount",
						(int) Math.ceil((double) totalCount / pageSize));
				model.addAttribute("totalCount", totalCount);
				StringBuilder sb = new StringBuilder();
				sb.append(ViewUtil.getContextPath()).append("/activity/")
						.append(activityId).append("/photos/").append(photoId);
				model.addAttribute("uri", sb.toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "activity/viewActivityPhoto";
	}

	@RequestMapping(value = "/activity/{activityId}/photos/{photoId}/comment", method = RequestMethod.POST)
	public void commentPhoto(HttpServletResponse response,
			@PathVariable("activityId") int activityId,
			@PathVariable("photoId") int photoId,
			@RequestParam("message") String message) {

		try {
			Activity activity = activityService.getActivityById(activityId);
			ActivityPhoto photo = activityService.getActivityPhotoById(photoId);
			if (activity == null || photo == null
					|| photo.getActivityId() != activityId) {
				response.getWriter().write("0");// 活动、照片错误
			} else {
				Designer designer = designerService.getCurrentUser();
				if (designer == null) {
					response.getWriter().write("1");// 未登录
				} else {
					Comment comment = new Comment();
					comment.setCommentType(CommentType.ACTIVITY_PHOTO.ordinal());
					comment.setContent(message);
					comment.setReferenceId(photoId);
					comment.setUserId(designer.getId());
					commentService.insertComment(comment);
					response.getWriter().write("2");// 成功
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/activity/{activityId}/news")
	public String activityNews(Model model, HttpServletResponse response,
			@PathVariable("activityId") int activityId,
			@RequestParam(value = "pn", required = false) Integer pn) {
		try {
			basicIssue(model);
			Activity activity = activityService.getActivityById(activityId);
			if (activity == null) {
				response.sendRedirect(ViewUtil.getContextPath() + "/activity");
				return null;
			} else {
				activityBasicIssue(model, activity);
				pn = pn == null || pn <= 0 ? 1 : pn;
				int pageSize = 10;
				List<ActivityNews> activityNews = activityService
						.getActivityNewsByIdAndPnAndSize(activityId, pn,
								pageSize);
				model.addAttribute("activityNews", activityNews);

				int totalCount = activityService
						.getActivityNewsCountByActivityId(activityId);
				model.addAttribute("pageIndex", pn);
				model.addAttribute("pageCount",
						(int) Math.ceil((double) totalCount / pageSize));
				model.addAttribute("totalCount", totalCount);
				StringBuilder sb = new StringBuilder();
				sb.append(ViewUtil.getContextPath()).append("/activity/")
						.append(activityId).append("/news");
				model.addAttribute("uri", sb.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "activity/activityNews";
	}

	@RequestMapping(value = "/activity/{activityId}/news/{newsId}", method = RequestMethod.GET)
	public String viewActivityNews(Model model, HttpServletResponse response,
			@PathVariable("activityId") int activityId,
			@PathVariable("newsId") int newsId,
			@RequestParam(value = "pn", required = false) Integer pn) {
		try {
			basicIssue(model);
			Activity activity = activityService.getActivityById(activityId);
			ActivityNews activityNews = activityService
					.getActivityNewsById(newsId);
			if (activity == null || activityNews == null
					|| activityNews.getActivityId() != activityId) {
				response.sendRedirect(ViewUtil.getContextPath() + "/activity");
				return null;
			} else {
				activityBasicIssue(model, activity);
				pn = pn == null || pn <= 0 ? 1 : pn;
				int pageSize = 10;
				List<CommentPojo> comments = commentService
						.getCommentsByRefAndTypeAndPnAndSize(newsId,
								CommentType.ACTIVITY_NEWS.ordinal(), pn,
								pageSize);
				model.addAttribute("activityNews", activityNews);
				model.addAttribute("comments", comments);

				int totalCount = commentService.getCommentCountByRefAndType(
						newsId, CommentType.ACTIVITY_NEWS.ordinal());
				model.addAttribute("pageIndex", pn);
				model.addAttribute("pageCount",
						(int) Math.ceil((double) totalCount / pageSize));
				model.addAttribute("totalCount", totalCount);
				StringBuilder sb = new StringBuilder();
				sb.append(ViewUtil.getContextPath()).append("/activity/")
						.append(activityId).append("/news/").append(newsId);
				model.addAttribute("uri", sb.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "activity/viewActivityNews";
	}

	@RequestMapping(value = "/activity/{activityId}/news/{newsId}/comment", method = RequestMethod.POST)
	public void commentNews(HttpServletResponse response,
			@PathVariable("activityId") int activityId,
			@PathVariable("newsId") int newsId,
			@RequestParam("message") String message) {

		try {
			Activity activity = activityService.getActivityById(activityId);
			ActivityNews activityNews = activityService
					.getActivityNewsById(newsId);
			if (activity == null || activityNews == null
					|| activityNews.getActivityId() != activityId) {
				response.getWriter().write("0");// 活动、新闻错误
			} else {
				Designer designer = designerService.getCurrentUser();
				if (designer == null) {
					response.getWriter().write("1");// 未登录
				} else {
					Comment comment = new Comment();
					comment.setCommentType(CommentType.ACTIVITY_NEWS.ordinal());
					comment.setContent(message);
					comment.setReferenceId(newsId);
					comment.setUserId(designer.getId());
					commentService.insertComment(comment);
					response.getWriter().write("2");// 成功
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
