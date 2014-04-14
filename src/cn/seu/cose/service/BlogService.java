package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.BlogDAO;
import cn.seu.cose.dao.DesignerDAO;
import cn.seu.cose.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDAO blogDAOImpl;
	@Autowired
	private DesignerDAO designerDAOImpl;

	public int getBlogCountByDesignerId(int designerId) {
		return blogDAOImpl.getBlogsCountByDesignerId(designerId);
	}

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

	public List<Blog> getRecentBlogsByPnAndPageSize(int pn, int pageSize) {
		List<Blog> recentBlogs = blogDAOImpl.getRecentBlogsByBaseAndRange(
				(pn - 1) * pageSize, pageSize);
		for (int i = 0; i < 2 && i < recentBlogs.size(); i++) {
			Blog blog = recentBlogs.get(i);
			blog.setDesignerAvatar(designerDAOImpl.getDesignerById(
					blog.getDesignerId()).getAvatar());
			int length = 50 > blog.getPureContent().length() ? blog
					.getPureContent().length() : 60;
			blog.setIntro(blog.getPureContent().substring(0, length) + "...");
		}
		return recentBlogs;
	}

	public List<Blog> getHotBlogsByPnAndPageSize(int pn, int pageSize) {
		List<Blog> hotBlogs = blogDAOImpl.getHotBlogsByBaseAndRange((pn - 1)
				* pageSize, pageSize);
		for (int i = 0; i < 2 && i < hotBlogs.size(); i++) {
			Blog blog = hotBlogs.get(i);
			blog.setDesignerAvatar(designerDAOImpl.getDesignerById(
					blog.getDesignerId()).getAvatar());
			int length = 50 > blog.getPureContent().length() ? blog
					.getPureContent().length() : 60;
			blog.setIntro(blog.getPureContent().substring(0, length) + "...");
		}
		return hotBlogs;
	}
}
