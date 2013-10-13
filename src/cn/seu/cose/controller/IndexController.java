package cn.seu.cose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.entity.SlidePojo;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.SlideService;

@Controller
public class IndexController extends AbstractController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private SlideService slideService;

	@RequestMapping("/")
	public String index(Model model) {
		List<SlidePojo> slides = slideService.getSlides();
		List<ArticlePojo> news = articleService.newCenterInIndex();
		model.addAttribute("slides", slides);
		model.addAttribute("news", news);
		addCategories(model);
		return "index";
	}
}
