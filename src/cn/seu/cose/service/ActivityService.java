package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.ActivityApplicationDAO;
import cn.seu.cose.dao.ActivityDAO;
import cn.seu.cose.dao.ActivityNewsDAO;
import cn.seu.cose.dao.ActivityPhotoDAO;
import cn.seu.cose.entity.Activity;
import cn.seu.cose.entity.ActivityApplication;
import cn.seu.cose.entity.ActivityNews;
import cn.seu.cose.entity.ActivityPhoto;

@Service
public class ActivityService {
	@Autowired
	private ActivityDAO activityDAOImpl;
	@Autowired
	private ActivityApplicationDAO activityApplicationDAOImpl;
	@Autowired
	private ActivityNewsDAO activityNewsDAOImpl;
	@Autowired
	private ActivityPhotoDAO activityPhotoDAOImpl;

	public List<Activity> getRecentActivities() {
		return activityDAOImpl.getRecentActivities();
	}

	public List<Activity> getCurrentActivities() {
		return activityDAOImpl.getCurrentActivities();
	}

	public Activity getActivityById(int id) {
		return activityDAOImpl.getActivityById(id);
	}
	
	public List<Activity> getAllActivities() {
		return activityDAOImpl.getAllActivities();
		
	}
	
	public List<Activity> searchActivityByTitle(String title) {
		return activityDAOImpl.searchActivityByTitle(title);
	}
	
	public void updateActivity(Activity activity) {
		activityDAOImpl.updateActivity(activity);
	}

	public void deleteActivityById(int id) {
		activityDAOImpl.deleteActivityById(id);
	}

	public List<ActivityApplication> getActivityApplicationsByActivityId(int id) {
		return activityApplicationDAOImpl
				.getActivityApplicationsByActivityId(id);
	}

	public void applyActivity(int userId, int activityId) {
		activityApplicationDAOImpl
				.insertActivityApplication(userId, activityId);
	}

	public void cancelActivityApplication(int userId, int activityId) {
		activityApplicationDAOImpl
				.deleteActivityApplication(userId, activityId);
	}

	public List<ActivityNews> getActivityNewsByActivityId(int id) {
		return activityNewsDAOImpl.getActivityNewsByActivityId(id);
	}

	public ActivityNews getActivityNewsById(int id) {
		return activityNewsDAOImpl.getActivityNewsById(id);
	}
	public void addActivityNews(ActivityNews activityNews) {
		activityNewsDAOImpl.insertActivityNews(activityNews);
	}

	public void deleteActivityNews(int id) {
		activityNewsDAOImpl.deleteActivityNews(id);
	}

	public void updateActivityNews(ActivityNews activityNews) {
		activityNewsDAOImpl.updateActivityNews(activityNews);
	}

	public List<ActivityPhoto> getActivityPhotoByActivityId(int id) {
		return activityPhotoDAOImpl.getActivityPhotoByActivityId(id);
	}

	/**
	 * add multi-pictures
	 * 
	 * @param activityPhoto
	 */
	public void addActivityPhoto(ActivityPhoto activityPhoto) {
		activityPhotoDAOImpl.insertActivityPhoto(activityPhoto);
	}

	public void deleteActivityPhoto(int id) {
		activityPhotoDAOImpl.deleteActivityPhoto(id);
	}

	public void updateActivityPhoto(ActivityPhoto activityPhoto) {
		activityPhotoDAOImpl.updateActivityPhoto(activityPhoto);
	}
}
