package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.ArticlePojo;

interface ArticleDAO {
	ArticlePojo getArticleByIdBrief(int id);

	ArticlePojo getArticleById(int id);

	List<ArticlePojo> getArticlesByCatAndRangeBrief(int catId, int base,
			int range);

	List<ArticlePojo> getArticlesByCatAndRange(int catId, int base, int range);

	int getArticleCountByCatId(int catId);

	void insertArticle(ArticlePojo article);

	void updateArticle(ArticlePojo article);

	void deleteArticle(int id);

	int getArticleCountByRootCatId(int rootCatId);

	List<ArticlePojo> getArticlesByRootCatId(int rootCatId);

	List<ArticlePojo> getArticlesBySubCatId(int subCatId);
}
