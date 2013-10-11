package cn.seu.cose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.entity.SlidePojo;
import cn.seu.cose.service.AdminService;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.CategoryService;
import cn.seu.cose.service.SlideService;

@Controller
public class IndexController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SlideService slideService;

	@RequestMapping("/")
	public String index(Model model) {
		List<SlidePojo> slides = slideService.getSlides();
		model.addAttribute("slides", slides);
		CommonIssues(model);
		return "index";
	}

	@RequestMapping("/index")
	public String newBloga() {
		return "index";
	}

	public void CommonIssues(Model model) {
		List<CategoryPojo> cats = categoryService.getRootsWithChildren();
		model.addAttribute("cats", cats);
	}
}
