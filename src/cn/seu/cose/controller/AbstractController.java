package cn.seu.cose.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import cn.seu.cose.core.CategoryCache;
import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.entity.Link;
import cn.seu.cose.entity.Parameter;
import cn.seu.cose.entity.PublicationPojo;
import cn.seu.cose.service.DesignerService;
import cn.seu.cose.service.LinkService;
import cn.seu.cose.service.ParameterService;
import cn.seu.cose.service.PublicationService;
import cn.seu.cose.service.StatisticService;
import cn.seu.cose.util.Constant;

public class AbstractController {
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private PublicationService publicationService;
	@Autowired
	private DesignerService designerService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private StatisticService statisticService;

	protected void basicIssue(Model model) {
		List<CategoryPojo> cats = CategoryCache.getRootsWithChildren();
		String location = parameterService.getParameterByKey(
				Constant.PARAMETER_KEY_LOCATION).getParameterValue();
		Parameter emailParameter = parameterService
				.getParameterByKey(Constant.PARAMETER_KEY_EMAIL);
		Parameter phoneParameter = parameterService
				.getParameterByKey(Constant.PARAMETER_KEY_PHONE);
		String contactIntro = parameterService.getParameterByKey(
				Constant.PARAMETER_KEY_CONTACT).getParameterValue();
		String gpslocation = parameterService.getParameterByKey(
				Constant.PARAMETER_KEY_GPS_LOCATION).getParameterValue();

		statisticService.IncreasePV();// page views increase here
		long pageViews = statisticService.getPV();
		long ips = statisticService.getIP();

		model.addAttribute("pageViews", pageViews);
		model.addAttribute("ips", ips);

		List<Parameter> socialParameters = new ArrayList<Parameter>();
		for (String socialName : Constant.PARAMETER_KEY_SOCIALS) {
			Parameter socialParameter = parameterService
					.getParameterByKey(socialName);
			if (socialParameter.getParameterValue() != null
					&& !socialParameter.getParameterValue().trim().equals("")) {
				socialParameters.add(socialParameter);
			}
		}

		List<PublicationPojo> recentPublications = publicationService
				.getRecentPublications();

		List<Link> links = linkService.getAllLinks();

		model.addAttribute("location", location);
		model.addAttribute("socials", socialParameters);
		model.addAttribute("email", emailParameter.getParameterValue());
		model.addAttribute("phone", phoneParameter.getParameterValue());
		model.addAttribute("gpslocation", gpslocation);
		model.addAttribute("cats", cats);
		model.addAttribute("contactIntro", contactIntro);
		model.addAttribute("recentPublications", recentPublications);
		model.addAttribute("links", links);

		if (designerService.isSignIn()) {
			model.addAttribute("curUser", designerService.getCurrentUser());
		}
	}
}
