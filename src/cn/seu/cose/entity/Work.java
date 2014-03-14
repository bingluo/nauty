package cn.seu.cose.entity;

import java.util.Date;

/**
 * designing work
 * 
 */
public class Work {
	private int id;
	private String workName;
	// designer's id
	private int userId;
	// distinguish multiple pictures with ';'
	private String workPics;
	// maybe null, not belong to any activity
	private int activityId = -1;
	private String intro;
	private int voteCount;
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getWorkPics() {
		return workPics;
	}

	public void setWorkPics(String workPics) {
		this.workPics = workPics;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
