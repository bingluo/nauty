package cn.seu.cose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.service.CategoryService;

@Controller
public class CatController extends AbstractController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping("/about/{catId}/")
	public String viewAboutCat(Model model) {
		addCategories(model);
		return "viewCat";
	}
}
