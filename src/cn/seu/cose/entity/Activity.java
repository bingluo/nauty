package cn.seu.cose.entity;

import java.sql.Date;

public class Activity {
	private int id;
	private String title;
	private String intro;
	// one title one pic as a logo
	private String titlePic;
	// beginning time of applying activity
	private Date appBeginTime;
	// ending time of applying activity
	private Date appEndTime;
	// beginning time of activity
	private Date actBeginTime;
	// ending time of activity
	private Date actEndTime;

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

	public String getTitlePic() {
		return titlePic;
	}

	public void setTitlePic(String titlePic) {
		this.titlePic = titlePic;
	}

	public Date getAppBeginTime() {
		return appBeginTime;
	}

	public void setAppBeginTime(Date appBeginTime) {
		this.appBeginTime = appBeginTime;
	}

	public Date getAppEndTime() {
		return appEndTime;
	}

	public void setAppEndTime(Date appEndTime) {
		this.appEndTime = appEndTime;
	}

	public Date getActBeginTime() {
		return actBeginTime;
	}

	public void setActBeginTime(Date actBeginTime) {
		this.actBeginTime = actBeginTime;
	}

	public Date getActEndTime() {
		return actEndTime;
	}

	public void setActEndTime(Date actEndTime) {
		this.actEndTime = actEndTime;
	}
}
