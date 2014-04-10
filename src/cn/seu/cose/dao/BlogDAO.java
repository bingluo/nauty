package cn.seu.cose.dao;

import cn.seu.cose.entity.Blog;

public interface BlogDAO {
	Blog getBlogById(int id);

	void addBlog(Blog blog);

	void archiveBlog(int id);

	void updateBlog(Blog blog);

	void addClickCount(int id);
}
