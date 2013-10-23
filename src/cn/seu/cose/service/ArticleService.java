package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.ArticleDAO;
import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.util.LinkTool;

@Service
public class ArticleService {
	@Autowired
	ArticleDAO articleDAOImpl;

	@Cacheable(value = "articleCache", key = "'newCenterInIndex'")
	public List<ArticlePojo> newCenterInIndex() {
		List<ArticlePojo> news = articleDAOImpl.getArticlesByCatAndRangeBrief(
				2, 0, 15);
		for (ArticlePojo articlePojo : news) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return news;
	}

	@Cacheable(value = "articleCache", key = "'getArticleByCatIdAndPageIndex:'+ #catId +','+ #index ")
	public List<ArticlePojo> getArticleByCatIdAndPageIndex(int catId, int index) {
		List<ArticlePojo> articles = articleDAOImpl.getArticlesByCatAndRange(
				catId, 10 * (index - 1), 10);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}

	@Cacheable(value = "articleCache", key = "'getArticleByCatIdAndPageIndexAndPageSize:'+ #catId +','+#index+','+#pageSize")
	public List<ArticlePojo> getArticleByCatIdAndPageIndexAndPageSize(
			int catId, int index, int pageSize) {
		List<ArticlePojo> articles = articleDAOImpl.getArticlesByCatAndRange(
				catId, pageSize * (index - 1), pageSize);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}

	public List<ArticlePojo> searchArticle(String searchInput) {
		List<ArticlePojo> articles = articleDAOImpl.searchArticle(searchInput);
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
		ArticlePojo previous = articleDAOImpl.getPreviousArticle(article);
		ArticlePojo next = articleDAOImpl.getNextArticle(article);

		article.setUri(LinkTool.article(article));
		if (previous != null) {
			previous.setUri(LinkTool.article(previous));
		}
		if (next != null) {
			next.setUri(LinkTool.article(next));
		}

		article.setPrevious(previous);
		article.setNext(next);
		return article;
	}

	@Cacheable(value = "articleCache", key = "'getArticleCountByCatId:' + #rootCatId + ',' + #catId")
	public int getArticleCountByCatId(int rootCatId, int catId) {
		if (catId <= 8) {
			return articleDAOImpl.getArticleCountByRootCatId(rootCatId);
		}
		return articleDAOImpl.getArticleCountByCatId(catId);
	}

	@CacheEvict(value = "articleCache", allEntries = true)
	public void addArticle(ArticlePojo article) {
		articleDAOImpl.insertArticle(article);
	}

	@CacheEvict(value = "articleCache", allEntries = true)
	public void updateArticle(ArticlePojo article) {
		articleDAOImpl.updateArticle(article);
	}

	@CacheEvict(value = "articleCache", allEntries = true)
	public void deleteArticle(int id) {
		articleDAOImpl.deleteArticle(id);
	}

	public ArticlePojo getExclusiveArticleByCatId(int catId) {
		return articleDAOImpl.getExclusiveArticleByCatId(catId);
	}

	@Cacheable(value = "articleCache", key = "'getConcerns'")
	public List<ArticlePojo> getConcerns() {
		List<ArticlePojo> articles = articleDAOImpl
				.getArticlesByCatAndRangeBrief(15, 0, 5);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}

	@Cacheable(value = "articleCache", key = "'getEvents'")
	public List<ArticlePojo> getEvents() {
		List<ArticlePojo> articles = articleDAOImpl.getArticlesByCatAndRange(6,
				0, 5);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}

	@Cacheable(value = "articleCache", key = "'getTrains'")
	public List<ArticlePojo> getTrains() {
		List<ArticlePojo> articles = articleDAOImpl.getArticlesByCatAndRange(5,
				0, 5);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}

	@Cacheable(value = "articleCache", key = "'getRelates:'+#catId")
	public List<ArticlePojo> getRelates(int catId) {
		List<ArticlePojo> articles = articleDAOImpl.getArticlesByCatAndRange(
				catId, 0, 10);
		for (ArticlePojo articlePojo : articles) {
			articlePojo.setUri(LinkTool.article(articlePojo));
		}
		return articles;
	}
}
