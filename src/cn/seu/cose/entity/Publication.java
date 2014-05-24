package cn.seu.cose.entity;

import java.util.Date;

public class Publication {
	private int id;
	private String title;
	private String intro;
	private String images;
	private String linkUrl;
	private int type; // 1:协会刊物，2：企业内刊，3：文化交流
	
	private int clickCounts;
	private Date postTime;

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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public int getClickCounts() {
		return clickCounts;
	}

	public void setClickCounts(int clickCounts) {
		this.clickCounts = clickCounts;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
