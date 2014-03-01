package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Activity;

public interface ActivityDAO {
	List<Activity> getRecentActivities();

	List<Activity> getCurrentActivities();

	List<Activity> getAllActivities();

	Activity getActivityById(int id);

	void updateActivity(Activity activity);

	void deleteActivityById(int id);
}
