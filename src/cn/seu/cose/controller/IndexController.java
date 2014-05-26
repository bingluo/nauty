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
import cn.seu.cose.entity.WorkPojo;
import cn.seu.cose.service.ActivityService;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.SlideService;
import cn.seu.cose.service.WorkService;

@Controller
public class IndexController extends AbstractController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private WorkService workService;

	@RequestMapping("/")
	public String index(Model model) {
		List<SlidePojo> slides = slideService.getSlides();
		List<ArticlePojo> news = articleService.newCenterInIndex();
		List<ActivityPojo> activities = activityService.getIndexActivities();
		List<WorkPojo> works = workService.getRecentWorks(6);
		model.addAttribute("slides", slides);
		model.addAttribute("news", news);
		model.addAttribute("activities", activities);
		model.addAttribute("works", works);
		model.addAttribute(
				"meta",
				"<meta name='Keywords' content='设计,工业设计,设计师,设计作品,工业设计协会,设计新闻,设计赛事,设计活动'/>  <meta name='Description' content='长三角（嘉兴）工业设计致力于工业设计发展，为优秀设计师与设计公司提供机遇。'/>");
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
