package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.Article;
import cn.seu.cose.service.ArticleService;

@Controller
public class AdminArticleController {
	@Autowired
	private ArticleService articleService; 
	
	@RequestMapping("/admin/article_list")
	public String articleList(@RequestParam("catId") String catIdStr, 
			@RequestParam(value="pageIndex", required=false) String pageIndexStr, Model model) {
		int catId = Integer.parseInt(catIdStr);
		int index = 1;
		if (!StringUtils.isBlank(pageIndexStr)) {
			index = Integer.parseInt(pageIndexStr);
		}
		List<Article> list = articleService.getArticleByCatIdAndPageIndex(catId, index);
		model.addAttribute("article_list", list);
		return "admin_articles";
	}
	
	@RequestMapping("/admin/add_article")
	public void postAdd(HttpServletResponse response) {
		/* 
		 * TODO
		 */
		try {
			response.sendRedirect("/admin/article_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/admin/del_article")
	public void postDel(@RequestParam("id") String idStr, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(idStr);
			articleService.deleteArticle(id);
			response.sendRedirect("/admin/article_list");
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("error", 1);
			try {
				response.getWriter().write(jo.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/admin/alt_article")
	public void postAlt(Article article, HttpServletResponse response) {
		/*
		 * TODO
		 */
		try {
			response.sendRedirect("admin/article_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/admin/get_article", method=RequestMethod.POST)
	public void fetchArticle(@RequestParam("id") int id, HttpServletResponse response) {
		Article article = articleService.getArticleById(id);
		JSONObject jo = new JSONObject();
		jo.put("id", article.getId());
		jo.put("title", article.getTitle());
		jo.put("subhead", article.getSubhead());
		jo.put("catId", article.getCatId());
		jo.put("content", article.getContent());
		jo.put("from", article.getFrom());
		jo.put("postTime", article.getPostTime());
		response.setContentType("UTF-8");
		try {
			response.getWriter().write(jo.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
