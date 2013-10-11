package cn.seu.cose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.service.AdminService;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.CategoryService;

@Controller
public class IndexController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/")
	public String newBlog(Model model) {
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
