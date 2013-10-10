package cn.seu.cose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.filter.SecurityContextHolder;
import cn.seu.cose.service.AdminService;
import cn.seu.cose.service.ArticleService;

@Controller
public class IndexController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private AdminService adminService;

	@RequestMapping("/")
	public String newBlog() {
		adminService.logon("admin", "admin");
		SecurityContextHolder.getSecurityContext();
		return "index";
	}

	@RequestMapping("/index")
	public String newBloga() {
		return "index";
	}
}
