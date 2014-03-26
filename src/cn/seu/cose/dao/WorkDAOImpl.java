package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.Work;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class WorkDAOImpl extends SqlMapClientDaoSupport implements WorkDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public Work getWorkById(int id) {
		return (Work) getSqlMapClientTemplate().queryForObject(
				"WORK.selectWorkById", id);
	}

	@Override
	public List<Work> getRecentWorksByActivityId(int activityId) {
		return (List<Work>) getSqlMapClientTemplate().queryForList(
				"WORK.selectRecentWorksByActivityId", activityId);
	}

	@Override
	public List<Work> getWorksByUserId(int userId) {
		return (List<Work>) getSqlMapClientTemplate().queryForList(
				"WORK.selectWorksByUserId", userId);
	}

	@Override
	public List<Work> getWorksByUserIdAndBaseAndRange(int userId, int base,
			int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("base", base);
		map.put("range", range);
		return (List<Work>) getSqlMapClientTemplate().queryForList(
				"WORK.selectWorksByUserIdAndBaseAndRange", map);
	}

	@Override
	public void insertWork(Work work) {
		getSqlMapClientTemplate().insert("WORK.insertWork", work);
	}

	@Override
	public void updateWork(Work work) {
		getSqlMapClientTemplate().update("WORK.updateWork", work);
	}

	@Override
	public void updateVote(int id) {
		getSqlMapClientTemplate().update("WORK.updateVote", id);
	}

	@Override
	public void deleteWork(int id) {
		getSqlMapClientTemplate().delete("WORK.deleteWork", id);
	}

	@Override
	public List<Work> getHotWorks() {
		return getSqlMapClientTemplate().queryForList("WORK.selectHotWorks");
	}

	@Override
	public List<Work> getWorksByActivityIdAndBaseAndRange(int activityId,
			int base, int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"WORK.selectWorksByActivityIdAndBaseAndRange", map);
	}

	@Override
	public int getWorksCountByActivityId(int activityId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"WORK.selectWorksCountByActivityId", activityId);
	}

	@Override
	public int getWorksCountByDesignerId(int designerId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"WORK.selectWorksCountByDesignerId", designerId);
	}

	@Override
	public List<Work> getRecentWorks(int count) {
		return getSqlMapClientTemplate().queryForList("WORK.selectRecentWorks",
				count);
	}
}
