package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.ArticleDAOImpl;
import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.util.LinkTool;

@Service
public class ArticleService {
	@Autowired
	ArticleDAOImpl articleDAOImpl;

	public List<ArticlePojo> newCenterInIndex() {
		List<ArticlePojo> news = articleDAOImpl.getArticlesByCatAndRangeBrief(
				2, 0, 10);
		for (ArticlePojo articlePojo : news) {
			articlePojo.setRootCatUri(LinkTool.article(articlePojo));
		}
		return news;
	}

	public List<ArticlePojo> getArticleByCatIdAndPageIndex(int catId, int index) {
		return articleDAOImpl.getArticlesByCatAndRange(catId, 10 * (index - 1),
				10 * index);
	}

	public ArticlePojo getArticleByIdBrief(int id) {
		return articleDAOImpl.getArticleByIdBrief(id);
	}

	public ArticlePojo getArticleById(int id) {
		return articleDAOImpl.getArticleById(id);
	}

	public int getArticleCountByCatId(int catId) {
		return articleDAOImpl.getArticleCountByCatId(catId);
	}

	public int addArticle(ArticlePojo article) {
		return articleDAOImpl.insertArticle(article);
	}

	public void updateArticle(ArticlePojo article) {
		articleDAOImpl.updateArticle(article);
	}

	public void deleteArticle(int id) {
		articleDAOImpl.deleteArticle(id);
	}
}
