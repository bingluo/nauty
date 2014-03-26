package cn.seu.cose.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.entity.ActivityPojo;
import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.entity.SlidePojo;
import cn.seu.cose.service.ActivityService;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.SlideService;

@Controller
public class IndexController extends AbstractController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private ActivityService activityService;

	@RequestMapping("/")
	public String index(Model model) {
		List<SlidePojo> slides = slideService.getSlides();
		List<ArticlePojo> news = articleService.newCenterInIndex();
		List<ActivityPojo> activities = activityService.getIndexActivities();
		model.addAttribute("slides", slides);
		model.addAttribute("news", news);
		model.addAttribute("activities", activities);
		basicIssue(model);
		return "index";
	}

	@RequestMapping("/favicon.ico")
	public void favicon(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(
				request.getContextPath() + "/static/images/favicon.ico")
				.forward(request, response);
	}
}
