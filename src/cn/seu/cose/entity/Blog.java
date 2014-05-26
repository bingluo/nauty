package cn.seu.cose.entity;

import java.util.Date;

public class Blog {
	private int id;
	private String title;
	private String pureContent;
	private String content;
	private int designerId;
	private Date updateTime;
	private int clickCount = 0;
	private boolean archived = false;
	private int type;
	private boolean reprinted = false;

	private String designerAvatar;
	private String intro;
	private String designerName;

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

	public String getPureContent() {
		return pureContent;
	}

	public void setPureContent(String pureContent) {
		this.pureContent = pureContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDesignerId() {
		return designerId;
	}

	public void setDesignerId(int designerId) {
		this.designerId = designerId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public String getDesignerAvatar() {
		return designerAvatar;
	}

	public void setDesignerAvatar(String designerAvatar) {
		this.designerAvatar = designerAvatar;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isReprinted() {
		return reprinted;
	}

	public void setReprinted(boolean reprinted) {
		this.reprinted = reprinted;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
}
