package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.Article;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class ArticleDAOImpl extends SqlMapClientDaoSupport implements
		ArticleDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public Article getArticleById(int id) {
		return (Article) getSqlMapClientTemplate().queryForObject(
				"ARTICLE.selectArticleById", id);
	}

	@Override
	public List<Article> getArticlesByCatAndRange(int catId, int base, int range) {
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
	public int insertArticle(Article article) {
		return (Integer) getSqlMapClientTemplate().insert(
				"ARTICLE.insertArticle", article);
	}

	@Override
	public void updateArticle(Article article) {
		getSqlMapClientTemplate().update("ARTICLE.updateArticle", article);
	}

	@Override
	public void deleteArticle(int id) {
		getSqlMapClientTemplate().delete("ARTICLE.deleteArticleById", id);
	}
}
