package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Work;

public interface WorkDAO {

	Work getWorkById(int id);

	List<Work> getRecentWorksByActivityId(int activityId);

	List<Work> getWorksByActivityIdAndBaseAndRange(int activityId, int base,
			int range);

	int getWorksCountByActivityId(int activityId);

	int getWorksCountByDesignerId(int designerId);

	List<Work> getHotWorks();

	List<Work> getWorksByUserId(int userId);

	List<Work> getWorksByUserIdAndBaseAndRange(int userId, int base, int range);

	void insertWork(Work work);

	void updateWork(Work work);

	void updateVote(int id);

	void deleteWork(int id);
}
