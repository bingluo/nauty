package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.ActivityPhoto;

public interface ActivityPhotoDAO {
	List<ActivityPhoto> getActivityPhotoByActivityId(int id);

	void insertActivityPhoto(ActivityPhoto activityPhoto);

	void deleteActivityPhoto(int id);

	void updateActivityPhoto(ActivityPhoto activityPhoto);
}
