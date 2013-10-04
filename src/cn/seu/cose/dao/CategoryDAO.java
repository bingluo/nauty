package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Category;

interface CategoryDAO {
	List<Category> getCategoriesByParentId(int parentId);
}
