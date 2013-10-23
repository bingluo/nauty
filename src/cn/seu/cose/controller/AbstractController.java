package cn.seu.cose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import cn.seu.cose.core.CategoryCache;
import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.entity.Parameter;
import cn.seu.cose.service.CategoryService;
import cn.seu.cose.service.ParameterService;
import cn.seu.cose.util.Constant;

public class AbstractController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ParameterService parameterService;

	protected void addCategories(Model model) {
		List<CategoryPojo> cats = CategoryCache.getRootsWithChildren();
		Parameter emailParameter = parameterService
				.getParameterByKey(Constant.PARAMETER_KEY_EMAIL);
		Parameter phoneParameter = parameterService
				.getParameterByKey(Constant.PARAMETER_KEY_PHONE);
		model.addAttribute("email", emailParameter.getParameterValue());
		model.addAttribute("phone", phoneParameter.getParameterValue());
		model.addAttribute("cats", cats);
	}
}
