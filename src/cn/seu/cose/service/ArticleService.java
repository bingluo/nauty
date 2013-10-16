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
				2, 0, 15);
		for (ArticlePojo articlePojo : news) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return news;
	}

	public List<ArticlePojo> getArticleByCatIdAndPageIndex(int catId, int index) {
		List<ArticlePojo> articles = articleDAOImpl.getArticlesByCatAndRange(
				catId, 10 * (index - 1), 10);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}

	public List<ArticlePojo> getArticleByCatIdAndPageIndexAndPageSize(
			int catId, int index, int pageSize) {
		List<ArticlePojo> articles = articleDAOImpl.getArticlesByCatAndRange(
				catId, pageSize * (index - 1), pageSize);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}

	public List<ArticlePojo> getArticlesByCatId(int subCatId) {
		return articleDAOImpl.getArticlesBySubCatId(subCatId);
	}

	public ArticlePojo getArticleByIdBrief(int id) {
		return articleDAOImpl.getArticleByIdBrief(id);
	}

	public ArticlePojo getArticleById(int id) {
		ArticlePojo article = articleDAOImpl.getArticleById(id);
		article.setPrevious(articleDAOImpl.getPreviousArticle(article));
		article.setNext(articleDAOImpl.getNextArticle(article));
		return article;
	}

	public int getArticleCountByCatId(int rootCatId, int catId) {
		if (catId <= 8) {
			return articleDAOImpl.getArticleCountByRootCatId(rootCatId);
		}
		return articleDAOImpl.getArticleCountByCatId(catId);
	}

	public void addArticle(ArticlePojo article) {
		articleDAOImpl.insertArticle(article);
	}

	public void updateArticle(ArticlePojo article) {
		articleDAOImpl.updateArticle(article);
	}

	public void deleteArticle(int id) {
		articleDAOImpl.deleteArticle(id);
	}

	public ArticlePojo getExclusiveArticleByCatId(int catId) {
		return articleDAOImpl.getExclusiveArticleByCatId(catId);
	}

	public List<ArticlePojo> getConcerns() {
		List<ArticlePojo> articles = articleDAOImpl
				.getArticlesByCatAndRangeBrief(15, 0, 5);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}

	public List<ArticlePojo> getEvents() {
		List<ArticlePojo> articles = articleDAOImpl.getArticlesByCatAndRange(6,
				0, 5);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}

	public List<ArticlePojo> getTrains() {
		List<ArticlePojo> articles = articleDAOImpl.getArticlesByCatAndRange(5,
				0, 5);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}

	public List<ArticlePojo> getRelates(int catId) {
		List<ArticlePojo> articles = articleDAOImpl.getArticlesByCatAndRange(
				catId, 0, 10);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}
}
