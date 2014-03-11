package cn.seu.cose.entity;

import java.sql.Timestamp;

public class Activity {
	private int id;
	private String title;
	private String intro;
	// one title one pic as a logo
	private String titlePic;
	// beginning time of applying activity
	private Timestamp appBeginTime;
	// ending time of applying activity
	private Timestamp appEndTime;
	// beginning time of activity
	private Timestamp actBeginTime;
	// ending time of activity
	private Timestamp actEndTime;

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

	public Timestamp getAppBeginTime() {
		return appBeginTime;
	}

	public void setAppBeginTime(Timestamp appBeginTime) {
		this.appBeginTime = appBeginTime;
	}

	public Timestamp getAppEndTime() {
		return appEndTime;
	}

	public void setAppEndTime(Timestamp appEndTime) {
		this.appEndTime = appEndTime;
	}

	public Timestamp getActBeginTime() {
		return actBeginTime;
	}

	public void setActBeginTime(Timestamp actBeginTime) {
		this.actBeginTime = actBeginTime;
	}

	public Timestamp getActEndTime() {
		return actEndTime;
	}

	public void setActEndTime(Timestamp actEndTime) {
		this.actEndTime = actEndTime;
	}
}
