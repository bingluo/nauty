package cn.seu.cose.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.Category;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class CategoryDAOImpl extends SqlMapClientDaoSupport implements
		CategoryDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Category> getCategoriesByParentId(int parentId) {
		return getSqlMapClientTemplate().queryForList(
				"CATEGORY.selectCategoryByParentCatId", parentId);
	}
	
	@Override
	public List<Category> getCategoriesByLevel(int level) {
		return getSqlMapClientTemplate().queryForList(
				"CATEGORY.selectCategoryByParentCatId", level);
	}
}
