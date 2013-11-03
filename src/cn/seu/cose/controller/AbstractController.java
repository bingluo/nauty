package cn.seu.cose.controller;

import java.util.ArrayList;
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

	protected void basicIssue(Model model) {
		List<CategoryPojo> cats = CategoryCache.getRootsWithChildren();
		Parameter emailParameter = parameterService
				.getParameterByKey(Constant.PARAMETER_KEY_EMAIL);
		Parameter phoneParameter = parameterService
				.getParameterByKey(Constant.PARAMETER_KEY_PHONE);

		List<Parameter> socialParameters = new ArrayList<Parameter>();
		for (String socialName : Constant.PARAMETER_KEY_SOCIALS) {
			Parameter socialParameter = parameterService
					.getParameterByKey(socialName);
			if (socialParameter.getParameterValue() != null
					&& !socialParameter.getParameterValue().trim().equals("")) {
				socialParameters.add(socialParameter);
			}
		}
		model.addAttribute("socials", socialParameters);
		model.addAttribute("email", emailParameter.getParameterValue());
		model.addAttribute("phone", phoneParameter.getParameterValue());
		model.addAttribute("cats", cats);
	}
}
