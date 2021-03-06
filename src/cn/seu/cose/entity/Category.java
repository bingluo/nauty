package cn.seu.cose.entity;

public class Category {
	private int id;

	private String catName;

	private int catLevel;

	private int parentCatId;

	private boolean exclusiveArticle;

	private String uriName;

	private int type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getCatLevel() {
		return catLevel;
	}

	public void setCatLevel(int catLevel) {
		this.catLevel = catLevel;
	}

	public int getParentCatId() {
		return parentCatId;
	}

	public void setParentCatId(int parentCatId) {
		this.parentCatId = parentCatId;
	}

	public boolean isExclusiveArticle() {
		return exclusiveArticle;
	}

	public void setExclusiveArticle(boolean exclusiveArticle) {
		this.exclusiveArticle = exclusiveArticle;
	}

	public String getUriName() {
		return uriName;
	}

	public void setUriName(String uriName) {
		this.uriName = uriName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
