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

import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.entity.Blog;
import cn.seu.cose.entity.SlidePojo;
import cn.seu.cose.service.ActivityService;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.BlogService;
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
	@Autowired
	private BlogService blogService;

	@RequestMapping("/")
	public String index(Model model) {
		List<SlidePojo> slides = slideService.getSlides();
		List<ArticlePojo> news = articleService.newCenterInIndex();
		// List<ActivityPojo> activities = activityService.getIndexActivities();
		List<ArticlePojo> memberStyleList = articleService
				.getArticleByCatIdAndPnAndPsBrief(4, 1, 12);
		List<ArticlePojo> activityArticleList = articleService
				.getArticleByCatIdAndPnAndPsBrief(5, 1, 12);
		List<ArticlePojo> workshops = articleService
				.getArticleByCatIdAndPnAndPsBrief(3, 1, 10);
		List<ArticlePojo> policies = articleService
				.getArticleByCatIdAndPnAndPsBrief(6, 1, 10);
		List<ArticlePojo> trains = articleService
				.getArticleByCatIdAndPnAndPsBrief(61, 1, 10);
		List<Blog> indexBlogs = blogService
				.getRecentBlogsByPnAndPageSize(1, 10);
		model.addAttribute("slides", slides);
		model.addAttribute("news", news);
		// model.addAttribute("activities", activities);
		model.addAttribute("memberStyleList", memberStyleList);
		model.addAttribute("activityArticleList", activityArticleList);
		model.addAttribute("workshops", workshops);
		model.addAttribute("policies", policies);
		model.addAttribute("trains", trains);
		model.addAttribute("indexBlogs", indexBlogs);
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
