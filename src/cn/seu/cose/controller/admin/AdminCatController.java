package cn.seu.cose.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.entity.Category;
import cn.seu.cose.service.CategoryService;

@Controller
public class AdminCatController {
	@Autowired
	private CategoryService catService;
	
	@RequestMapping("/admin/cat_list")
	public String catList() {
		catService.getCategoriesByParentId(0);
		
		return "admin_catgories";
	}
	
	
}
