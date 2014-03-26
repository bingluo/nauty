package cn.seu.cose.entity;

import java.util.List;

public class ActivityPojo {
	private Activity activity;
	private int applyCount;
	private int workCount;
	private List<ActivityNews> news;

	public String getTitle() {
		return activity.getTitle();
	}

	public String getIntro() {
		return activity.getIntro();
	}

	public int getId() {
		return activity.getId();
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(int applyCount) {
		this.applyCount = applyCount;
	}

	public int getWorkCount() {
		return workCount;
	}

	public void setWorkCount(int workCount) {
		this.workCount = workCount;
	}

	public List<ActivityNews> getNews() {
		return news;
	}

	public void setNews(List<ActivityNews> news) {
		this.news = news;
	}
}
