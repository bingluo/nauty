package cn.seu.cose.entity;

import java.util.List;

public class CategoryPojo extends Category {
	private List<CategoryPojo> children;

	private List<ArticlePojo> articles;
	private List<Blog> blogs;

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public List<CategoryPojo> getChildren() {
		return children;
	}

	public void setChildren(List<CategoryPojo> children) {
		this.children = children;
	}

	public List<ArticlePojo> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticlePojo> articles) {
		this.articles = articles;
	}
}
