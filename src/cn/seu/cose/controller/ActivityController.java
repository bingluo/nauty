package cn.seu.cose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.seu.cose.entity.Activity;
import cn.seu.cose.service.ActivityService;

@Controller
public class ActivityController extends AbstractController {
	@Autowired
	ActivityService activityService;

	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String activity(Model model) {
		basicIssue(model);
		List<Activity> activities = activityService.getAllActivities();
		model.addAttribute("activities",activities);
		return "activity/index";
	}
}
