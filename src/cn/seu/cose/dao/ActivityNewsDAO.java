package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.ActivityNews;

public interface ActivityNewsDAO {
	List<ActivityNews> getActivityNewsByActivityId(int id);
	
	ActivityNews getActivityNewsById(int id);
	
	List<ActivityNews> searchActivityNewsByTitle(String title);
	
	void insertActivityNews(ActivityNews activityNews);

	void deleteActivityNews(int id);

	void updateActivityNews(ActivityNews activityNews);
}
