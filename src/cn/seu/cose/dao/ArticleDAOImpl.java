package cn.seu.cose.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

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
	// @Cacheable(value = { "articleCache" })
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
	public List<ArticlePojo> searchArticle(String searchInput) {
		return getSqlMapClientTemplate().queryForList("ARTICLE.searchAtrticle", searchInput);
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
	// @CacheFlush(modelId = "articleFlushModel")
	public void insertArticle(ArticlePojo article) {
		getSqlMapClientTemplate().insert("ARTICLE.insertArticle", article);
	}

	@Override
	// @CacheFlush(modelId = "articleFlushModel")
	public void updateArticle(ArticlePojo article) {
		getSqlMapClientTemplate().update("ARTICLE.updateArticle", article);
	}

	@Override
	// @CacheFlush(modelId = "articleFlushModel")
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

	@Override
	public List<ArticlePojo> getContributeArticlesList(int reporterId) {
		return getSqlMapClientTemplate().queryForList(
				"ARTICLE.selectContributeArticlesListOfReporter", reporterId);
	}

	@Override
	public List<ArticlePojo> getAcceptArticlesList(int reporterId) {
		return getSqlMapClientTemplate().queryForList(
				"ARTICLE.selectAcceptArticlesListOfReporter", reporterId);
	}

	@Override
	public List<ArticlePojo> getRejectArticlesList(int reporterId) {
		return getSqlMapClientTemplate().queryForList(
				"ARTICLE.selectRejectArticlesListOfReporter", reporterId);
	}

	@Override
	public void contributeArticle(ArticlePojo article) {
		getSqlMapClientTemplate().insert(
				"ARTICLE.contributeArticle", article);
	}

	@Override
	public List<ArticlePojo> getContributedArticlesList(Date s, Date e) {
		HashMap<String, Date> map = new HashMap<String, Date>();
		map.put("startTime", s);
		map.put("endTime", e);
		return getSqlMapClientTemplate().queryForList(
				"ARTICLE.selectContributeArticlesListToAdmin", map);
	}

	@Override
	public List<ArticlePojo> getContributedArticlesListByReporter(
			String username) {
		return getSqlMapClientTemplate().queryForList(
				"ARTICLE.selectContributedArticlesListByReporter", username);
	}

	@Override
	public void rejectArticle(int id) {
		getSqlMapClientTemplate().update(
				"ARTICLE.rejectArticle", id);
	}

	// 通讯员修改自己提交的文章，但是已经被acc的文章不能被通讯员再次修改，只能由管理员修改
	@Override
	public void updateContributeArticle(int reporterId, ArticlePojo article) {
		getSqlMapClientTemplate().update(
				"ARTICLE.updateContributeArticle", article);
	}

	// 管理员在采纳文章时，可以对文章进行少量的修改。 Acc之后的文章会显示到主页的对应板块中
	@Override
	public void acceptArticle(ArticlePojo article) {
		getSqlMapClientTemplate().update(
				"ARTICLE.acceptArticle", article);
	}
}
