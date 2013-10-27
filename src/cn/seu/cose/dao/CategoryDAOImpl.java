package cn.seu.cose.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.CategoryPojo;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class CategoryDAOImpl extends SqlMapClientDaoSupport implements
		CategoryDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<CategoryPojo> getCategoriesByParentId(int parentId) {
		return getSqlMapClientTemplate().queryForList(
				"CATEGORY.selectCategoryByParentCatId", parentId);
	}

	@Override
	public List<CategoryPojo> getRootCategories() {
		return getSqlMapClientTemplate().queryForList(
				"CATEGORY.selectRootCategories");
	}

	@Override
	public List<CategoryPojo> getAllCats() {
		return getSqlMapClientTemplate().queryForList("CATEGORY.selectAllCats");
	}
	
	@Override
	public CategoryPojo getCatById(int id) {
		return (CategoryPojo) getSqlMapClientTemplate().queryForObject("CATEGORY.selectCategoryById", id);
	}
}

