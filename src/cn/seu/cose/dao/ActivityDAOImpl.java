package cn.seu.cose.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.Activity;

import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("unchecked")
@Component
public class ActivityDAOImpl extends SqlMapClientDaoSupport implements
		ActivityDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Activity> getRecentActivities() {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY.selectRecentActivities");
	}

	@Override
	public List<Activity> getCurrentActivities() {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY.selectCurrentActivities");
	}

	@Override
	public List<Activity> getAllActivities() {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY.selectAllActivities");
	}

	@Override
	public Activity getActivityById(int id) {
		return (Activity) getSqlMapClientTemplate().queryForObject(
				"ACTIVITY.selectActivityById", id);
	}

	@Override
	public List<Activity> searchActivityByTitle(String title) {
		return (List<Activity>) getSqlMapClientTemplate().queryForList(
				"ACTIVITY.searchActivityByTitle", title);
	}

	@Override
	public void addActivity(Activity activity) {
		getSqlMapClientTemplate().insert("ACTIVITY.insertActivity", activity);
	}

	@Override
	public void updateActivity(Activity activity) {
		getSqlMapClientTemplate().update("ACTIVITY.updateActivity", activity);
	}

	@Override
	public void deleteActivityById(int id) {
		getSqlMapClientTemplate().delete("ACTIVITY.deleteActivityById", id);
	}

	@Override
	public List<Activity> getAdvanceActivities() {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY.selectAdvanceActivities");
	}

	@Override
	public List<Activity> getReviewActivities() {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY.selectReviewActivities");
	}

	@Override
	public List<Activity> getIndexActivities() {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY.selectIndexActivities");
	}
}
