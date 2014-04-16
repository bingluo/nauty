package cn.seu.cose.entity;

import java.util.Date;

public class ActivityVideo {
	private int id;
	private int activityId;
	private String videoUri;
	private String videoTitle;
	private String videoDesc;
	private Date postTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getVideoUri() {
		return videoUri;
	}
	public void setVideoUri(String videoUri) {
		this.videoUri = videoUri;
	}
	public String getVideoDesc() {
		return videoDesc;
	}
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	
	
}
