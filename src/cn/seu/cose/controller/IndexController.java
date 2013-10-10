package cn.seu.cose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.service.ArticleService;

@Controller
public class IndexController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/")
	public String newBlog() {
		articleService.getArticleById(1);
		return "index";
	}

	@RequestMapping("/index")
	public String newBloga() {
		return "index";
	}
}
