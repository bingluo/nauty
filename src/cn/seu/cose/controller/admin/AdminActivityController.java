package cn.seu.cose.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import cn.seu.cose.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AdminActivityController {

	@Autowired
	ActivityService actyService;
	
	
	//*********************activity begin*********************//
	@RequestMapping(value="/admin/activity_list", method=RequestMethod.GET)
	public String getActyList() {
		return "admin_actys";
	}
	
	@RequestMapping(value="/admin/add_activity", method=RequestMethod.GET)
	public String getAdd() {
		return "admin_acty_add";
	}
	
	@RequestMapping(value="/admin/add_activity", method=RequestMethod.POST)
	public void postAdd() {
		
	}
	
	@RequestMapping(value="/admin/del_activity-{activityId}", method=RequestMethod.POST)
	public void postDel() {
		
	}
	
	@RequestMapping(value="/admin/alt_activity-{activityId}", method=RequestMethod.GET)
	public String getAlt() {
		return "admin_acty_alt";
	}
	
	@RequestMapping(value="/admin/alt_activity", method=RequestMethod.POST)
	public void postAlt() {
		
	}
	//*********************activity end*********************//
	
	//*********************activity app begin*********************//
	@RequestMapping(value="/admin/applicationsOf-{activityId}", method=RequestMethod.GET)
	public String getApps() {
		return "admin_acty_apps";
	}
	
	//*********************activity app end*********************//
}
