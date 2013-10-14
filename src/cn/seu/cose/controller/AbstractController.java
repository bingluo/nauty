package cn.seu.cose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import cn.seu.cose.core.CategoryCache;
import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.service.CategoryService;

public class AbstractController {
	@Autowired
	private CategoryService categoryService;

	protected void addCategories(Model model) {
		List<CategoryPojo> cats = CategoryCache.getRootsWithChildren();
		model.addAttribute("cats", cats);
	}
}
