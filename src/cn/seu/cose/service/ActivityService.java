package cn.seu.cose.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.ActivityApplicationDAO;
import cn.seu.cose.dao.ActivityDAO;
import cn.seu.cose.dao.ActivityNewsDAO;
import cn.seu.cose.dao.ActivityPhotoDAO;
import cn.seu.cose.dao.WorkDAO;
import cn.seu.cose.entity.Activity;
import cn.seu.cose.entity.ActivityApplication;
import cn.seu.cose.entity.ActivityNews;
import cn.seu.cose.entity.ActivityPhoto;
import cn.seu.cose.entity.ActivityPojo;

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
	@Autowired
	private WorkDAO workDAOImpl;

	public List<Activity> getRecentActivities() {
		return activityDAOImpl.getRecentActivities();
	}

	public List<Activity> getCurrentActivities() {
		return activityDAOImpl.getCurrentActivities();
	}

	public List<Activity> getAdvanceActivities() {
		return activityDAOImpl.getAdvanceActivities();
	}

	public List<Activity> getReviewActivities() {
		return activityDAOImpl.getReviewActivities();
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

	public void addActivity(Activity activity) {
		activityDAOImpl.addActivity(activity);
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

	public ActivityApplication getActivityApplicationsByUserIdAndActivityId(
			int userId, int activityId) {
		return activityApplicationDAOImpl
				.getActivityApplicationsByUserIdAndActivityId(userId,
						activityId);
	}

	public void applyActivity(int userId, int activityId) {
		activityApplicationDAOImpl
				.insertActivityApplication(userId, activityId);
	}

	public void cancelActivityApplication(int userId, int activityId) {
		activityApplicationDAOImpl
				.deleteActivityApplication(userId, activityId);
	}

	public List<ActivityNews> getActivityLatestNews() {
		return activityNewsDAOImpl.getActivityLatestNews();
	}

	public List<ActivityNews> getActivityNewsByIdAndPnAndSize(int id, int pn,
			int pageSize) {
		return activityNewsDAOImpl.getActivityNewsByActivityIdAndBaseAndRange(
				id, pageSize * (pn - 1), pageSize);
	}

	public int getActivityNewsCountByActivityId(int activityId) {
		return activityNewsDAOImpl.getActivityNewsCountByActivityId(activityId);
	}

	public List<ActivityNews> getActivityLatestNewsByActivityId(int activityId) {
		return activityNewsDAOImpl
				.getActivityLatestNewsByActivityId(activityId);
	}

	public List<ActivityNews> getActivityNewsByActivityId(int id) {
		return activityNewsDAOImpl.getActivityNewsByActivityId(id);
	}

	public ActivityNews getActivityNewsById(int id) {
		return activityNewsDAOImpl.getActivityNewsById(id);
	}

	public List<ActivityNews> searchActivityNewsByTitle(String title) {
		return activityNewsDAOImpl.searchActivityNewsByTitle(title);
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

	public int getActivityPhotosCountByActivityId(int activityId) {
		return activityPhotoDAOImpl
				.getActivityPhotosCountByActivityId(activityId);
	}

	public List<ActivityPhoto> getActivityPhotoByActivityIdAndPnAndSize(int id,
			int pn, int pageSize) {
		return activityPhotoDAOImpl
				.getActivityPhotoByActivityIdAndBaseAndRange(id, (pn - 1)
						* pageSize, pageSize);
	}

	public List<ActivityPhoto> getRecentActivityPhotoByActivityId(int id) {
		return activityPhotoDAOImpl.getRecentActivityPhotoByActivityId(id);
	}

	public ActivityPhoto getActivityPhotoById(int id) {
		return activityPhotoDAOImpl.getActivityPhotoById(id);
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

	public List<ActivityPojo> getIndexActivities() {
		List<ActivityPojo> activities = new ArrayList<ActivityPojo>();
		List<Activity> indexActivities = activityDAOImpl.getIndexActivities();
		for (Activity activity : indexActivities) {
			ActivityPojo activityPojo = new ActivityPojo();
			activityPojo.setActivity(activity);
			int applyCount = activityApplicationDAOImpl
					.getActivityApplicationCountByActivityId(activity.getId());
			activityPojo.setApplyCount(applyCount);
			int worksCount = workDAOImpl.getWorksCountByActivityId(activity
					.getId());
			activityPojo.setWorkCount(worksCount);
			List<ActivityNews> news = getActivityLatestNewsByActivityId(activity
					.getId());
			activityPojo.setNews(news);
			activities.add(activityPojo);
		}
		return activities;
	}
}
