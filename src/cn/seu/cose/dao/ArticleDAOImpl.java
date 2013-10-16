package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;
import org.springmodules.cache.annotations.CacheFlush;
import org.springmodules.cache.annotations.Cacheable;

import cn.seu.cose.entity.ArticlePojo;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class ArticleDAOImpl extends SqlMapClientDaoSupport implements
		ArticleDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public ArticlePojo getArticleByIdBrief(int id) {
		return (ArticlePojo) getSqlMapClientTemplate().queryForObject(
				"ARTICLE.selectArticleByIdBrief", id);
	}

	@Override
	public ArticlePojo getArticleById(int id) {
		return (ArticlePojo) getSqlMapClientTemplate().queryForObject(
				"ARTICLE.selectArticleById", id);
	}

	@Override
	public List<ArticlePojo> getArticlesByCatAndRangeBrief(int catId, int base,
			int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catId", catId);
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"ARTICLE.selectArticleByCatIdAndBaseAndRangeBrief", map);
	}

	@Override
	@Cacheable(modelId = "articleCacheModel")
	public List<ArticlePojo> getArticlesByCatAndRange(int catId, int base,
			int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catId", catId);
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"ARTICLE.selectArticleByCatIdAndBaseAndRange", map);
	}

	@Override
	public int getArticleCountByCatId(int catId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ARTICLE.selectCountByCatId", catId);
	}

	@Override
	public int getArticleCountByRootCatId(int rootCatId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ARTICLE.selectCountByRootCatId", rootCatId);
	}

	@Override
	public List<ArticlePojo> getArticlesByRootCatId(int rootCatId) {
		return getSqlMapClientTemplate().queryForList(
				"ARTICLE.selectArticleByRootCatId", rootCatId);
	}

	@Override
	public List<ArticlePojo> getArticlesBySubCatId(int subCatId) {
		return getSqlMapClientTemplate().queryForList(
				"ARTICLE.selectArticleBySubCatId", subCatId);
	}

	@Override
	@CacheFlush(modelId = "articleFlushModel")
	public void insertArticle(ArticlePojo article) {
		getSqlMapClientTemplate().insert("ARTICLE.insertArticle", article);
	}

	@Override
	@CacheFlush(modelId = "articleFlushModel")
	public void updateArticle(ArticlePojo article) {
		getSqlMapClientTemplate().update("ARTICLE.updateArticle", article);
	}

	@Override
	@CacheFlush(modelId = "articleFlushModel")
	public void deleteArticle(int id) {
		getSqlMapClientTemplate().delete("ARTICLE.deleteArticleById", id);
	}

	@Override
	public ArticlePojo getExclusiveArticleByCatId(int catId) {
		return (ArticlePojo) getSqlMapClientTemplate().queryForObject(
				"ARTICLE.selectExclusiveArticleByCatId", catId);
	}

	@Override
	public ArticlePojo getPreviousArticle(ArticlePojo article) {
		return (ArticlePojo) getSqlMapClientTemplate().queryForObject(
				"ARTICLE.selectPreviousArticle", article);
	}

	@Override
	public ArticlePojo getNextArticle(ArticlePojo article) {
		return (ArticlePojo) getSqlMapClientTemplate().queryForObject(
				"ARTICLE.selectNextArticle", article);
	}
}
