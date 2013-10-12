package cn.seu.cose.dao;

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
	public void insertArticle(ArticlePojo article) {
		getSqlMapClientTemplate().insert(
				"ARTICLE.insertArticle", article);
	}

	@Override
	public void updateArticle(ArticlePojo article) {
		getSqlMapClientTemplate().update("ARTICLE.updateArticle", article);
	}

	@Override
	public void deleteArticle(int id) {
		getSqlMapClientTemplate().delete("ARTICLE.deleteArticleById", id);
	}
}
