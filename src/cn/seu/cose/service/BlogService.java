package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.BlogDAO;
import cn.seu.cose.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDAO blogDAOImpl;

	public Blog getBlogById(int id) {
		return blogDAOImpl.getBlogById(id);
	}

	public List<Blog> getBlogsByDesignerIdAndPnAndPageSize(int designerId,
			int pn, int pageSize) {
		return blogDAOImpl.getBlogByDesignerIdAndBaseAndRange(designerId,
				(pn - 1) * pageSize, pageSize);
	}

	public void newBlog(String title, String pureContent, String content,
			int designerId) {
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setPureContent(pureContent);
		blog.setContent(content);
		blog.setDesignerId(designerId);
		blogDAOImpl.addBlog(blog);
	}

	public void archiveBlog(int id) {
		blogDAOImpl.archiveBlog(id);
	}

	public void updateBlog(String title, String pureContent, String content,
			int id) {
		Blog blog = blogDAOImpl.getBlogById(id);
		blog.setTitle(title);
		blog.setPureContent(pureContent);
		blog.setContent(content);
		blogDAOImpl.updateBlog(blog);
	}

	public void addClickCount(int id) {
		blogDAOImpl.addClickCount(id);
	}
}
