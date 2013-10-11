package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.Category;
import cn.seu.cose.service.CategoryService;

@Controller
public class AdminCatController {
	@Autowired
	private CategoryService catService;
	
	@RequestMapping("/admin/sub_cat_list")
	public void fetchChildCatList(@RequestParam("parentId") int parentId, HttpServletResponse response) {
		List<Category> list = catService.getCategoriesByParentId(parentId);
		JSONArray jsonArray = new JSONArray();
		for (Category category : list) {
			JSONObject jo = new JSONObject();
			jo.put("id", category.getId());
			jo.put("catName", category.getCatName());
			jsonArray.add(jo);
		}
		try {
			response.setContentType("text/html;charset=UTF-8"); 
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/admin/top_cat_list")
	public void fetchTopCatList(HttpServletResponse response) {
		List<Category> list = catService.getCategoriesByLevel(0);
		JSONArray jsonArray = new JSONArray();
		for (Category category : list) {
			JSONObject jo = new JSONObject();
			jo.put("id", category.getId());
			jo.put("catName", category.getCatName());
			jsonArray.add(jo);
		}
		try {
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
