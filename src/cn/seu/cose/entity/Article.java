package cn.seu.cose.entity;

import java.util.Date;

public class Article {
	private int id;

	private String title;

	private String subhead;

	private int catId;

	private int rootCatId;

	private String content;

	private String pureText;

	private String from;

	private Date postTime;

	// 通讯员上传的文章
	private int contributedFrom=0;
	private int status=1;
	
	
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

	public String getSubhead() {
		return subhead;
	}

	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getRootCatId() {
		return rootCatId;
	}

	public void setRootCatId(int rootCatId) {
		this.rootCatId = rootCatId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPureText() {
		return pureText;
	}

	public void setPureText(String pureText) {
		this.pureText = pureText;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public int getContributedFrom() {
		return contributedFrom;
	}

	public void setContributedFrom(int contributedFrom) {
		this.contributedFrom = contributedFrom;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
