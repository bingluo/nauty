package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.CategoryPojo;

interface CategoryDAO {
	List<CategoryPojo> getCategoriesByParentId(int parentId);

	List<CategoryPojo> getRootCategories();

	List<CategoryPojo> getAllCats();
}
