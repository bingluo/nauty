package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Blog;

public interface BlogDAO {
	Blog getBlogById(int id);

	List<Blog> getBlogByDesignerIdAndBaseAndRange(int designerId, int base,
			int range);

	void addBlog(Blog blog);

	void archiveBlog(int id);

	void updateBlog(Blog blog);

	void addClickCount(int id);
}
