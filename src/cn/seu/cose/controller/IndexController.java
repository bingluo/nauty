package cn.seu.cose.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String newBlog() {
		return "index";
	}

	@RequestMapping("/index")
	public String newBloga() {
		return "index";
	}
}
