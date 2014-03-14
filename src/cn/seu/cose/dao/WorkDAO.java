package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Work;

public interface WorkDAO {

	Work getWorkById(int id);

	List<Work> getRecentWorksByActivityId(int activityId);

	List<Work> getHotWorks();

	List<Work> getWorksByUserId(int userId);

	void insertWork(Work work);

	void updateWork(Work work);

	void updateVote(int id);

	void deleteWork(int id);
}
