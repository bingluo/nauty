package cn.seu.cose.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.seu.cose.entity.Activity;
import cn.seu.cose.entity.ActivityApplication;
import cn.seu.cose.entity.ActivityNews;
import cn.seu.cose.entity.ActivityPhoto;
import cn.seu.cose.entity.Designer;
import cn.seu.cose.entity.WorkPojo;
import cn.seu.cose.service.ActivityService;
import cn.seu.cose.service.DesignerService;
import cn.seu.cose.service.WorkService;
import cn.seu.cose.view.util.ViewUtil;

@Controller
public class ActivityController extends AbstractController {
	@Autowired
	ActivityService activityService;
	@Autowired
	WorkService workService;
	@Autowired
	DesignerService designerService;

	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String activity(Model model) {
		basicIssue(model);
		List<Activity> activities = activityService.getRecentActivities();
		List<ActivityNews> latestNews = activityService.getActivityLatestNews();
		List<WorkPojo> hotWorks = workService.getHotWorks();
		model.addAttribute("hotWorks", hotWorks);
		model.addAttribute("latestNews", latestNews);
		model.addAttribute("activities", activities);
		return "activity/index";
	}

	@RequestMapping(value = "/activity/current", method = RequestMethod.GET)
	public String currentActivity(Model model) {
		basicIssue(model);
		List<Activity> activities = activityService.getCurrentActivities();
		List<ActivityNews> latestNews = activityService.getActivityLatestNews();
		List<WorkPojo> hotWorks = workService.getHotWorks();
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
		List<WorkPojo> hotWorks = workService.getHotWorks();
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
		List<WorkPojo> hotWorks = workService.getHotWorks();
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
			List<WorkPojo> recentWorks = workService
					.getRecentWorksByActivityId(activityId);
			List<ActivityNews> recentActivityNews = activityService
					.getActivityLatestNewsByActivityId(activityId);
			List<ActivityPhoto> recentActivityPhotos = activityService
					.getRecentActivityPhotoByActivityId(activityId);
			Designer designer = designerService.getCurrentUser();
			if (designer != null) {
				ActivityApplication activityApplication = activityService
						.getActivityApplicationsByUserIdAndActivityId(
								designer.getId(), activityId);
				if (activityApplication != null) {
					model.addAttribute("activityApplied", true);
				}
			}
			model.addAttribute("activity", activity);
			model.addAttribute("recentWorks", recentWorks);
			model.addAttribute("recentNews", recentActivityNews);
			model.addAttribute("recentPhotos", recentActivityPhotos);
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
			return "activity/viewActivity";
		}
	}
}
