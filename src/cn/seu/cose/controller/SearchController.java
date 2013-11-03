package cn.seu.cose.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.service.ArticleService;

@Controller
public class SearchController extends AbstractController {

	@Autowired
	ArticleService articleService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchArticles(Model model, HttpServletRequest request,
			@RequestParam("keyword") String keyword) {
		basicIssue(model);
		List<ArticlePojo> articles = null;
		if (keyword == null || keyword.trim().equals("")) {
		} else {
			articles = articleService.searchArticle(keyword);
			articleService.highlightKeyword(articles, keyword);
		}
		model.addAttribute("searchResults", articles);
		model.addAttribute("keyword", keyword);
		return "search_results";
	}
}
