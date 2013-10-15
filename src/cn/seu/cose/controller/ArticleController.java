package cn.seu.cose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.core.CategoryCache;
import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.service.ArticleService;

@Controller
public class ArticleController extends AbstractController {

	@Autowired
	ArticleService articleService;

	@RequestMapping("/{rootCat}/cat-{catId}/{articleId}.html")
	public String newArticle(Model model,
			@PathVariable("rootCat") String rootCat,
			@PathVariable("catId") int catId,
			@PathVariable("articleId") int articleId) {
		addCategories(model);
		ArticlePojo article = articleService.getArticleById(articleId);
		CategoryPojo category = CategoryCache.get(catId);
		model.addAttribute("curCat", category);
		category = CategoryCache.get(category.getParentCatId());
		if (catId == article.getCatId()
				&& category.getUriName().equals(rootCat)) {
			model.addAttribute("article", article);
		}

		// relates
		List<ArticlePojo> relates = articleService.getRelates(catId);
		model.addAttribute("relates", relates);
		return "article";
	}
}
