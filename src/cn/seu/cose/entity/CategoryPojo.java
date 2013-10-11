package cn.seu.cose.entity;

import java.util.List;

public class CategoryPojo extends Category {
	private List<CategoryPojo> children;

	public List<CategoryPojo> getChildren() {
		return children;
	}

	public void setChildren(List<CategoryPojo> children) {
		this.children = children;
	}
}
