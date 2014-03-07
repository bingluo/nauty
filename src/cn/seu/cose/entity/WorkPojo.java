package cn.seu.cose.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class WorkPojo {
	private Work work;
	private Designer designer;
	private Activity activity;
	private List<String> workPics = new ArrayList<String>();

	public List<String> getWorkPics() {
		return workPics;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
		Collections.addAll(workPics, work.getWorkPics().split(";"));
	}

	public Designer getDesigner() {
		return designer;
	}

	public void setDesigner(Designer designer) {
		this.designer = designer;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int getId() {
		return work.getId();
	}

	public String getWorkName() {
		return work.getWorkName();
	}

	public int getUserId() {
		return work.getUserId();
	}

	public int getActivityId() {
		return work.getActivityId();
	}

	public String getIntro() {
		return work.getIntro();
	}

	public int getVoteCount() {
		return work.getVoteCount();
	}

	public Date getUpdateTime() {
		return work.getUpdateTime();
	}

	public String getUserName() {
		return designer.getUserName();
	}

	public String getAvatar() {
		return designer.getAvatar();
	}

	public boolean isCertificated() {
		return designer.isCertificated();
	}

	public String getActivityTitle() {
		return activity.getTitle();
	}
}
