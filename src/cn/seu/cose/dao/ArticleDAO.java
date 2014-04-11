package cn.seu.cose.dao;

import java.util.Date;
import java.util.List;

import cn.seu.cose.entity.ArticlePojo;

public interface ArticleDAO {
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

	ArticlePojo getExclusiveArticleByCatId(int catId);

	ArticlePojo getPreviousArticle(ArticlePojo article);

	ArticlePojo getNextArticle(ArticlePojo article);

	List<ArticlePojo> searchArticle(String searchInput);
	
	// 通讯员相关的文章操作
	List<ArticlePojo> getContributeArticlesList(int reporterId);
	
	List<ArticlePojo> getWaitArticlesOfReporter(int reporterId);
	
	List<ArticlePojo> getAcceptArticlesList(int reporterId);
	
	List<ArticlePojo> getRejectArticlesList(int reporterId);
	
	void contributeArticle(ArticlePojo article);
	
	void updateContributeArticle(int reporterId, ArticlePojo article);
	
	List<ArticlePojo> searchArticleOfReporter(int reporterId, String searchInput);
	
	
	// 管理员对通讯员contribute的文章的相关操作
	List<ArticlePojo> getContributedArticlesList(Date s, Date e);
	
	List<ArticlePojo> getWaitingArticlesList(Date s, Date e);
	
	List<ArticlePojo> getAcceptArticlesList(Date s, Date e);
	
	List<ArticlePojo> getRejectArticlesList(Date s, Date e);
	
	List<ArticlePojo> getContributedArticlesListByReporter(String username);
	
		// 管理员可以在accept的时候对文章做少量修改
		// 1. article表， 2.reporter表更新贡献统计
	void acceptArticle(ArticlePojo article);
	
	void rejectArticle(int id);
	
	List<ArticlePojo> searchContribute(int type, String searchInput);
	
	
}
