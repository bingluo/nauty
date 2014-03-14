package cn.seu.cose.dao;

import java.util.List;

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
}
