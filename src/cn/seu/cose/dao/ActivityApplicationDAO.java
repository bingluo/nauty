package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.ActivityApplication;

public interface ActivityApplicationDAO {
	List<ActivityApplication> getActivityApplicationsByActivityId(int id);

	int getActivityApplicationCountByActivityId(int id);

	ActivityApplication getActivityApplicationsByUserIdAndActivityId(
			int userId, int activityId);

	void insertActivityApplication(int userId, int activityId);

	void deleteActivityApplication(int userId, int activityId);
}
