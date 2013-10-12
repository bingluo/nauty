package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.util.Date;
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
		
		/* get cats */
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
		
		/* get cats */
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(1);
		model.addAttribute("init_sub_cat_list" ,categories);
		
		model.addAttribute("topCatId", 1);
		model.addAttribute("subCatId", 0);
		model.addAttribute("pageIndex", 1);
		return "admin_articles";
	}
	
	@RequestMapping(value="/admin/add_article", method=RequestMethod.GET)
	public String getAdd(Model model) {
		/* get cats */
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(1);
		model.addAttribute("init_sub_cat_list" ,categories);
		
		return "admin_articles_add";
	}
	
	@RequestMapping(value="/admin/add_article", method=RequestMethod.POST)
	public void postAdd(@RequestParam("title") String title, @RequestParam("subhead") String subhead,
			@RequestParam("catId") String catIdStr, @RequestParam("rootCatId") String rootCatIdStr,
			@RequestParam("content") String content, @RequestParam("from") String from, 
			HttpServletResponse response) {
		ArticlePojo article = new ArticlePojo();
		article.setTitle(title);
		article.setSubhead(subhead);
		article.setCatId(Integer.parseInt(catIdStr));
		article.setRootCatId(Integer.parseInt(rootCatIdStr));
		article.setContent(content);
		article.setFrom(from);
		article.setPostTime(new Date());
		articleService.addArticle(article);
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
	
	@RequestMapping(value="/admin/alt_article-{id}", method=RequestMethod.GET)
	public String getAlt(@PathVariable("id") String idStr, Model model) {
		Article article = articleService.getArticleById(Integer.parseInt(idStr));
		model.addAttribute("article", article);
		/* get cats */
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(article.getRootCatId());
		model.addAttribute("init_sub_cat_list" ,categories);
		
		return "admin_articles_alt";
	}
	
	@RequestMapping(value="/admin/alt_article", method=RequestMethod.POST)
	public void postAlt(@RequestParam("id") String idStr, @RequestParam("title") String title, @RequestParam("subhead") String subhead,
			@RequestParam("catId") String catIdStr, @RequestParam("rootCatId") String rootCatIdStr,
			@RequestParam("content") String content, @RequestParam("from") String from, 
			HttpServletResponse response) {
		ArticlePojo article = new ArticlePojo();
		article.setId(Integer.parseInt(idStr));
		article.setTitle(title);
		article.setSubhead(subhead);
		article.setCatId(Integer.parseInt(catIdStr));
		article.setRootCatId(Integer.parseInt(rootCatIdStr));
		article.setContent(content);
		article.setFrom(from);
		article.setPostTime(new Date());
		articleService.updateArticle(article);
	}
	
}
