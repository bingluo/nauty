package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.ActivityVideo;

public interface ActivityVideoDAO {
	
	List<ActivityVideo> getActivityVideoByActivityId(int id);

	List<ActivityVideo> getActivityVideoByActivityIdAndBaseAndRange(int id,
			int base, int range);

	int getActivityVideoCountByActivityId(int activityId);

	List<ActivityVideo> getRecentActivityVideoByActivityId(int id);

	ActivityVideo getActivityVideoById(int id);

	void insertActivityVideo(ActivityVideo video);

	void deleteActivityVideo(int id);

	void updateActivityVideo(ActivityVideo video);
	
}
