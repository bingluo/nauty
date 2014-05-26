package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Blog;

public interface BlogDAO {
	List<Integer> rankDesignerWithBlogCount(int topN);

	List<Blog> getRecentBlogsByBaseAndRange(int base, int range);

	List<Blog> getHotBlogsByBaseAndRange(int base, int range);

	int getBlogsCountByType(int type);

	int getBlogsCountByDesignerId(int designerId);

	Blog getBlogById(int id);

	List<Blog> getBlogByTypeAndBaseAndRange(int type, int base, int range);

	List<Blog> getBlogByDesignerIdAndBaseAndRange(int designerId, int base,
			int range);

	void addBlog(Blog blog);

	void archiveBlog(int id);

	void updateBlog(Blog blog);

	void addClickCount(int id);
}
