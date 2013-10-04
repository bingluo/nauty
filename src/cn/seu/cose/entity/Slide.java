package cn.seu.cose.entity;

import java.util.Date;

public class Slide {
	private int id;

	private String title;

	private int articleId;

	private int picName;

	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getPicName() {
		return picName;
	}

	public void setPicName(int picName) {
		this.picName = picName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
