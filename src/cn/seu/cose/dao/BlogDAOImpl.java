package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.Blog;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class BlogDAOImpl extends SqlMapClientDaoSupport implements BlogDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public Blog getBlogById(int id) {
		return (Blog) getSqlMapClientTemplate().queryForObject(
				"BLOG.selectBlogByBlogId", id);
	}

	@Override
	public List<Blog> getBlogByDesignerIdAndBaseAndRange(int designerId,
			int base, int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("designerId", designerId);
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"BLOG.selectBlogByDesignerIdAndBaseAndRange", map);
	}

	@Override
	public void addBlog(Blog blog) {
		getSqlMapClientTemplate().insert("BLOG.insertBlog", blog);
	}

	@Override
	public void archiveBlog(int id) {
		getSqlMapClientTemplate().update("BLOG.archiveBlog", id);
	}

	@Override
	public void updateBlog(Blog blog) {
		getSqlMapClientTemplate().update("BLOG.updateBlog", blog);
	}

	@Override
	public void addClickCount(int id) {
		getSqlMapClientTemplate().update("BLOG.addClickCount", id);
	}

	@Override
	public int getBlogsCountByDesignerId(int designerId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BLOG.selectBlogCountByDesignerId", designerId);
	}

	@Override
	public List<Blog> getRecentBlogsByBaseAndRange(int base, int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"BLOG.selectRecentBlogsByBaseAndRange", map);
	}

	@Override
	public List<Blog> getHotBlogsByBaseAndRange(int base, int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"BLOG.selectHotBlogsByBaseAndRange", map);
	}

	@Override
	public List<Integer> rankDesignerWithBlogCount(int topN) {
		return getSqlMapClientTemplate().queryForList(
				"BLOG.rankDesignerWithBlogCount", topN);
	}

	@Override
	public List<Blog> getBlogByTypeAndBaseAndRange(int type, int base, int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"BLOG.selectBlogByTypeAndBaseAndRange", map);
	}

	@Override
	public int getBlogsCountByType(int type) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BLOG.selectBlogCountByType", type);
	}

}
