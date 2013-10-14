package cn.seu.cose.util;

import cn.seu.cose.core.SystemContainer;
import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.service.ArticleService;

public class LinkTool {

	private static ArticleService articleService = (ArticleService) SystemContainer
			.lookup("articleService");

	public static String image(String picName) {
		StringBuilder sb = new StringBuilder();
//		sb.append|("/static/images/upload/").append(picName);
		sb.append("/static/upload/").append(picName);
		return sb.toString();
	}

	public static String article(ArticlePojo article) {
		if(article == null) {
			return "/";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("/").append(article.getRootCatUri()).append("/");
		sb.append(article.getCatId()).append("/");
		sb.append(article.getId()).append(".html");
		return sb.toString();
	}

	public static String article(int id) {
		StringBuilder sb = new StringBuilder();
		ArticlePojo article = (ArticlePojo) articleService
				.getArticleByIdBrief(id);
		return article(article);
	}
}
