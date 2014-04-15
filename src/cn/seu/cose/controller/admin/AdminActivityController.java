package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.Activity;
import cn.seu.cose.entity.ActivityApplication;
import cn.seu.cose.entity.ActivityNews;
import cn.seu.cose.entity.ActivityPhoto;
import cn.seu.cose.entity.ActivityVideo;
import cn.seu.cose.service.ActivityService;
import cn.seu.cose.view.util.ViewUtil;


@Controller
public class AdminActivityController extends AbstractController {

	@Autowired
	ActivityService actyService;
	private DateFormat format = new SimpleDateFormat("yyyyy-MM-dd HH:mm");
	
	
	//*********************activity begin*********************//
	@RequestMapping(value="/admin/activity_list", method=RequestMethod.GET)
	public String getActyList(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<Activity> list = actyService.getAllActivities();
		model.addAttribute("activity_list", list);
		return "admin_actys";
	}
	@RequestMapping(value="/admin/activity_list_search-{searchInput}", method=RequestMethod.GET)
	public String searchActysByTitle(@PathVariable("searchInput") String searchTitle, Model model,
			HttpServletResponse response) {
		putAdmin(model, response);
		List<Activity> list = actyService.searchActivityByTitle(searchTitle);
		model.addAttribute("activity_list", list);
		return "admin_actys";
	}
	
	@RequestMapping(value="/admin/add_activity", method=RequestMethod.GET)
	public String getAddActy(Model model, HttpServletResponse response) {
		putAdmin(model, response);		
		return "admin_actys_add";
	}
	
	@RequestMapping(value="/admin/add_activity", method=RequestMethod.POST)
	public void postAddActy(@RequestParam("title") String title, @RequestParam("intro") String intro, 
			@RequestParam("titlePic") String titlePicPath, @RequestParam("appBeginTime") String appBeginTime,
			@RequestParam("appEndTime") String appEndTime, @RequestParam("actBeginTime") String actBeginTime,
			@RequestParam("actEndTime") String actEndTime, HttpServletResponse response) {
		Activity acty = new Activity();
		acty.setTitle(title);
		acty.setIntro(intro);
		acty.setTitlePic(titlePicPath);
		try {
			acty.setAppBeginTime(new java.sql.Timestamp(format.parse(appBeginTime).getTime()));
			acty.setAppEndTime(new java.sql.Timestamp(format.parse(appEndTime).getTime()));
			acty.setActBeginTime(new java.sql.Timestamp(format.parse(actBeginTime).getTime()));
			acty.setActEndTime(new java.sql.Timestamp(format.parse(actEndTime).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		actyService.addActivity(acty);
	}
	
	@RequestMapping(value="/admin/del_activity", method=RequestMethod.POST)
	public void postDelActy(@RequestParam("id") int id, HttpServletResponse response) {
		actyService.deleteActivityById(id);
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/activity_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/admin/alt_activity-{id}", method=RequestMethod.GET)
	public String getAltActy(@PathVariable("id") int id,
			Model model, HttpServletResponse response) {
		putAdmin(model, response);
		Activity acty = actyService.getActivityById(id);
		model.addAttribute("activity", acty);
		
		return "admin_actys_alt";
	}
	
	@RequestMapping(value="/admin/alt_activity", method=RequestMethod.POST)
	public void postAltActy(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("intro") String intro, 
			@RequestParam("titlePic") String titlePicPath, @RequestParam("appBeginTime") String appBeginTime,
			@RequestParam("appEndTime") String appEndTime, @RequestParam("actBeginTime") String actBeginTime,
			@RequestParam("actEndTime") String actEndTime, HttpServletResponse response) {
		Activity acty = new Activity();
		acty.setId(id);
		acty.setTitle(title);
		acty.setIntro(intro);
		acty.setTitlePic(titlePicPath);
		try {
			acty.setAppBeginTime(new java.sql.Timestamp(format.parse(appBeginTime).getTime()));
			acty.setAppEndTime(new java.sql.Timestamp(format.parse(appEndTime).getTime()));
			acty.setActBeginTime(new java.sql.Timestamp(format.parse(actBeginTime).getTime()));
			acty.setActEndTime(new java.sql.Timestamp(format.parse(actEndTime).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		actyService.updateActivity(acty);
	}
	//*********************activity end*********************//
	
	//*********************activity application begin*********************//
	@RequestMapping(value="/admin/applicationsOf-{id}", method=RequestMethod.GET)
	public String getApplications(@PathVariable("id") int id,
			Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<ActivityApplication> list = actyService.getActivityApplicationsByActivityId(id);
		model.addAttribute("applications_list", list);
		model.addAttribute("appCount", list.size());
		model.addAttribute("activityId", id);
		model.addAttribute("activityTitle", actyService.getActivityById(id).getTitle());
		return "admin_actyapps";
	}
	//*********************activity application end*********************//
	
	//*********************activity news begin*********************//
	@RequestMapping(value="/admin/acty-{id}news_list", method=RequestMethod.GET)
	public String getActyNewsByActyId(@PathVariable("id") int id, 
			Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<ActivityNews> list = actyService.getActivityNewsByActivityId(id);
		model.addAttribute("activityId", id);
		model.addAttribute("news_list", list);
		model.addAttribute("activityTitle", actyService.getActivityById(id).getTitle());
		return "admin_actynews";
	}
	
	@RequestMapping(value="/admin/acty-{id}news_search-{searchInput}", method=RequestMethod.GET)
	public String searchActyNews(@PathVariable("id") int id, @PathVariable("searchInput") String searchInput, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<ActivityNews> list = actyService.searchActivityNewsByTitle(searchInput);
		model.addAttribute("activityId", id);
		model.addAttribute("news_list", list);
		model.addAttribute("activityTitle", actyService.getActivityById(id).getTitle());
		return "admin_actynews";
		
	}
	
	@RequestMapping(value="/admin/add_acty-{id}news", method=RequestMethod.GET)
	public String getAddActyNews(@PathVariable("id") int id, 
			Model model, HttpServletResponse response) {
		putAdmin(model, response);
		model.addAttribute("activityId", id);
		model.addAttribute("activityTitle", actyService.getActivityById(id).getTitle());
		return "admin_actynews_add";
	}
	
	@RequestMapping(value="/admin/add_actynews", method=RequestMethod.POST)
	public void postAddActyNews(@RequestParam("title") String title, @RequestParam("content") String content, 
			@RequestParam("activityId") int actyId, @RequestParam("postUser") String postUser,
			HttpServletResponse response) {
		ActivityNews news = new ActivityNews();
		news.setTitle(title);
		news.setContent(content);
		news.setActivityId(actyId);
		news.setPostUser(postUser);
		actyService.addActivityNews(news);
	}
	
	@RequestMapping(value="/admin/del_actynews", method=RequestMethod.POST)
	public void postDelActyNews(@RequestParam("id")int id, @RequestParam("activityId") int activityId, HttpServletResponse response) {
		actyService.deleteActivityNews(id);
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/acty-"+ activityId +"news_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/admin/alt_actynews-{id}", method=RequestMethod.GET)
	public String getAltActyNews(@PathVariable("id") int id, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		ActivityNews news = actyService.getActivityNewsById(id);
		model.addAttribute("news", news);
		model.addAttribute("activityTitle", actyService.getActivityById(news.getActivityId()).getTitle());
		return "admin_actynews_alt";
	}
	
	@RequestMapping(value="/admin/alt_actynews", method=RequestMethod.POST)
	public void postAltActyNews(@RequestParam("id") int id, @RequestParam("title") String title, 
			@RequestParam("content") String content, @RequestParam("activityId") int actyId, 
			@RequestParam("postUser") String postUser,
			HttpServletResponse response) {
		ActivityNews news = new ActivityNews();
		news.setId(id);
		news.setTitle(title);
		news.setContent(content);
		news.setActivityId(actyId);
		news.setPostUser(postUser);
		actyService.updateActivityNews(news);
	}
	//*********************activity news end*********************//
	
	//*********************activity photo start*********************//
	@RequestMapping(value="/admin/acty-{id}photos_list", method=RequestMethod.GET)
	public String getActyPhotosByActyId(@PathVariable("id") int id, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<ActivityPhoto> list = actyService.getActivityPhotoByActivityId(id);
		model.addAttribute("activityId", id);
		model.addAttribute("photo_list", list);
		model.addAttribute("activityTitle", actyService.getActivityById(id).getTitle());
		return "admin_actyphotos";
	}
	
	@RequestMapping(value="/admin/add_acty-{id}photo", method=RequestMethod.GET)
	public String getAddActyPhoto(@PathVariable("id") int id, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		model.addAttribute("activityId", id);
		model.addAttribute("activityTitle", actyService.getActivityById(id).getTitle());
		return "admin_actyphotos_add";
	}
	
	@RequestMapping(value="/admin/add_actyphoto", method=RequestMethod.POST)
	public void postAddActyPhoto(@RequestParam("picUri") String picUri, @RequestParam("activityId") int activityId,
			@RequestParam("intro") String intro, HttpServletResponse response) {
		ActivityPhoto photo = new ActivityPhoto();
		photo.setPicUri(picUri);
		photo.setActivityId(activityId);
		photo.setIntro(intro);
		actyService.addActivityPhoto(photo);
	}
	
	@RequestMapping(value="/admin/del_actyphoto", method=RequestMethod.POST)
	public void postDelActyPhoto(@RequestParam("activityId") int activityId, @RequestParam("id") int id, HttpServletResponse response) {
		actyService.deleteActivityPhoto(id);
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/acty-"+ activityId +"photos_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/admin/alt_actyphoto-{id}", method=RequestMethod.GET)
	public String getAltActyPhoto(@PathVariable("id") int id, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		ActivityPhoto photo = actyService.getActivityPhotoById(id);
		model.addAttribute("photo", photo);
		model.addAttribute("activityTitle", actyService.getActivityById(photo.getActivityId()).getTitle());
		return "admin_actyphotos_alt";
	}
	
	
	@RequestMapping(value="/admin/alt_actyphoto", method=RequestMethod.POST)
	public void postAltActyPhoto(@RequestParam("id") int id, @RequestParam("picUri") String picUri,
			@RequestParam("activityId") int activityId, @RequestParam("intro") String intro) {
		ActivityPhoto photo = new ActivityPhoto();
		photo.setId(id);
		photo.setActivityId(activityId);
		photo.setPicUri(picUri);
		photo.setIntro(intro);
		actyService.updateActivityPhoto(photo);
	}
	//*********************activity photo end*********************//
	
	
	//*********************activity video start*********************//
		@RequestMapping(value="/admin/acty-{id}videos_list", method=RequestMethod.GET)
		public String getActyVideosByActyId(@PathVariable("id") int id, Model model, HttpServletResponse response) {
			putAdmin(model, response);
			List<ActivityVideo> list = actyService.getActivityVideoByActivityId(id);
			model.addAttribute("activityId", id);
			model.addAttribute("el_list", list);
			model.addAttribute("activityTitle", actyService.getActivityById(id).getTitle());
			return "admin_actyvideos";
		}
		
		@RequestMapping(value="/admin/add_acty-{id}video", method=RequestMethod.GET)
		public String getAddActyVideo(@PathVariable("id") int id, Model model, HttpServletResponse response) {
			putAdmin(model, response);
			model.addAttribute("activityId", id);
			model.addAttribute("activityTitle", actyService.getActivityById(id).getTitle());
			return "admin_actyvideo_add";
		}
		
		@RequestMapping(value="/admin/add_actyvideo", method=RequestMethod.POST)
		public void postAddActyPhoto(@RequestParam("videoUri") String videoUri, @RequestParam("activityId") int activityId,
				@RequestParam("videoTitle") String title,  @RequestParam("videoDesc")String desc, HttpServletResponse response) {
			ActivityVideo video = new ActivityVideo();
			video.setVideoUri(videoUri);
			video.setActivityId(activityId);
			video.setVideoTitle(title);
			video.setVideoDesc(desc);
			actyService.insertActivityVideo(video);
		}
		
		@RequestMapping(value="/admin/del_actyvideo", method=RequestMethod.POST)
		public void postDelActyVideo(@RequestParam("activityId") int activityId, @RequestParam("id") int id, HttpServletResponse response) {
			actyService.deleteActivityPhoto(id);
			try {
				response.sendRedirect(ViewUtil.getContextPath() + "/admin/acty-"+ activityId +"videos_list");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping(value="/admin/alt_actyvideo-{id}", method=RequestMethod.GET)
		public String getAltActyVideo(@PathVariable("id") int id, Model model, HttpServletResponse response) {
			putAdmin(model, response);
			ActivityVideo video = actyService.getActivityVideoById(id);
			model.addAttribute("el", video);
			model.addAttribute("activityTitle", actyService.getActivityById(video.getActivityId()).getTitle());
			return "admin_actyvideo_alt";
		}
		
		
		@RequestMapping(value="/admin/alt_actyvideo", method=RequestMethod.POST)
		public void postAltActyVideo(@RequestParam("id") int id, @RequestParam("videoUri") String videoUri, @RequestParam("activityId") int activityId,
				@RequestParam("videoTitle") String title,  @RequestParam("videoDesc")String desc) {
			ActivityVideo video = new ActivityVideo();
			video.setId(id);
			video.setVideoUri(videoUri);
			video.setActivityId(activityId);
			video.setVideoTitle(title);
			video.setVideoDesc(desc);
			actyService.updateActivityVideo(video);
		}
		//*********************activity photo end*********************//
}
