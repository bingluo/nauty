package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Activity;

public interface ActivityDAO {
	List<Activity> getRecentActivities();

	List<Activity> getCurrentActivities();

	List<Activity> getAllActivities();

	List<Activity> searchActivityByTitle(String title);

	Activity getActivityById(int id);

	void addActivity(Activity activity);

	void updateActivity(Activity activity);

	void deleteActivityById(int id);

	List<Activity> getAdvanceActivities();

	List<Activity> getReviewActivities();
}
