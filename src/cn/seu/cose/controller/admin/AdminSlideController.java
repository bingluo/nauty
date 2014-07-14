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

import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.entity.SlidePojo;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.CategoryService;
import cn.seu.cose.service.SlideService;
import cn.seu.cose.view.util.ViewUtil;

@Controller
public class AdminSlideController extends AbstractController{
	@Autowired
	private SlideService slideService;
	@Autowired
	private CategoryService catService;
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/admin/slide_list")
	public String slideList(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<SlidePojo> list = slideService.getSlides();
		model.addAttribute("slide_list", list);
		return "admin_slides";
	}
	
	@RequestMapping(value="/admin/add_slide", method=RequestMethod.GET)
	public String getAdd(Model model,  HttpServletResponse response) {
		putAdmin(model, response);
		/* get cats */
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(2);
		model.addAttribute("init_sub_cat_list" ,categories);
		model.addAttribute("topCatId", 2); // init 新闻-特别关注
		model.addAttribute("subCatId", 15);
		List<ArticlePojo> list = articleService.getArticlesByCatId(15);
		model.addAttribute("init_article_list", list);
		return "admin_slides_add";
	}
	
	@RequestMapping(value="/admin/add_slide", method=RequestMethod.POST)
	public void postAdd(@RequestParam("title")String title, @RequestParam("brief")String brief, 
			@RequestParam("articleUri") String articleUri, @RequestParam("picName") String picName, 
			HttpServletResponse response) {
		SlidePojo slide = new SlidePojo();
		slide.setTitle(title);
		slide.setBrief(brief);
		slide.setArticleUri(articleUri);
		slide.setPicName(picName);
		slide.setUpdateTime(new Date());
		slideService.addSlide(slide);
	}
	
	@RequestMapping(value="/admin/del_slide", method=RequestMethod.POST)
	public void postDel(@RequestParam("slideId")String slideIdStr, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(slideIdStr);
			slideService.deleteSlide(id);
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/slide_list");
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
	
	@RequestMapping(value="/admin/alt_slide-{id}", method=RequestMethod.GET)
	public String getAlt(@PathVariable("id")String idStr, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		int id = Integer.parseInt(idStr);
		SlidePojo slide = slideService.getSlideById(id);
		model.addAttribute("slide", slide);
		/*int articleId = slide.getArticleId();
		int topCatId = 2;
		int subCatId = 15;
		if (articleId >=0 ) {
			ArticlePojo article = articleService.getArticleById(articleId);
			topCatId = article.getRootCatId();
			subCatId = article.getCatId();
		}
		
		 get cats 
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(topCatId);
		model.addAttribute("init_sub_cat_list" ,categories);
		model.addAttribute("topCatId", topCatId); // init 新闻-特别关注
		model.addAttribute("subCatId", subCatId);
		List<ArticlePojo> list = articleService.getArticlesByCatId(subCatId);
		model.addAttribute("init_article_list", list);*/
		
		return "admin_slides_alt";
	}
	
	@RequestMapping(value="/admin/alt_slide", method=RequestMethod.POST)
	public void postAlt(@RequestParam("id") String idStr, @RequestParam("title")String title, @RequestParam("brief")String brief, 
			@RequestParam("articleUri") String articleUri, @RequestParam("picName") String picName, 
			HttpServletResponse response) {
		SlidePojo slide = new SlidePojo();
		slide.setId(Integer.parseInt(idStr));
		slide.setTitle(title);
		slide.setBrief(brief);
		slide.setArticleUri(articleUri);
		slide.setPicName(picName);
		slide.setUpdateTime(new Date());
		slideService.updateSlide(slide);
	}
	
	/*@RequestMapping(value="/admin/fetch_articles", method=RequestMethod.POST)
	public void fetchArticles(@RequestParam("subCatId")String subCatIdStr, HttpServletResponse response) {
		int subCatId = Integer.parseInt(subCatIdStr);
		
		List<ArticlePojo> list = articleService.getArticlesByCatId(subCatId);
		JSONArray jsonArray = new JSONArray();
		for (ArticlePojo article : list) {
			JSONObject jo = new JSONObject();
			jo.put("id", article.getId());
			jo.put("title", article.getTitle());
			jsonArray.add(jo);
		}
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}
