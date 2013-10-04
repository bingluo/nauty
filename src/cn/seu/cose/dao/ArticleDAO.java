package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Article;

interface ArticleDAO {
	Article getArticleById(int id);

	List<Article> getArticlesByCatAndRange(int catId, int base, int range);

	int getArticleCountByCatId(int catId);

	int insertArticle(Article article);

	void updateArticle(Article article);

	void deleteArticle(int id);
}
