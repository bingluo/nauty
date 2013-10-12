package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.Article;
import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.entity.Category;
import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.CategoryService;

@Controller
public class AdminArticleController {
	@Autowired
	private ArticleService articleService; 
	@Autowired
	private CategoryService catService;
	
	@RequestMapping("/admin/article_list-{topCatId}-{subCatId}-{pageIndex}")
	public String articleList(@PathVariable(value="topCatId") String topCatIdStr, @PathVariable(value="subCatId") String subCatIdStr, 
			@PathVariable(value="pageIndex") String pageIndexStr, Model model) {
		
		int topCatId = Integer.parseInt(topCatIdStr);
		int subCatId = Integer.parseInt(subCatIdStr);
		int pageIndex = Integer.parseInt(pageIndexStr);
		
		/* get top level cats */
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(topCatId);
		model.addAttribute("init_sub_cat_list" ,categories);
		
		int catId = subCatId<=0 ? topCatId : subCatId;
		int index = pageIndex<=0 ? 1 : pageIndex;
		List<ArticlePojo> list = articleService.getArticleByCatIdAndPageIndex(catId, index);
		model.addAttribute("article_list", list);
		
		model.addAttribute("topCatId", topCatId);
		model.addAttribute("subCatId", subCatId);
		model.addAttribute("pageIndex", pageIndex);
		
		return "admin_articles";
	}
	
	@RequestMapping("/admin/article_list")
	public String articleList(Model model) {
		
		List<ArticlePojo> list = articleService.getArticleByCatIdAndPageIndex(1, 1); // init
		model.addAttribute("article_list", list);
		
		/* get top level cats */
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(1);
		model.addAttribute("init_sub_cat_list" ,categories);
		
		model.addAttribute("topCatId", 1);
		model.addAttribute("subCatId", 0);
		model.addAttribute("pageIndex", 1);
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
	
	@RequestMapping(value="/admin/del_article", method=RequestMethod.POST)
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
