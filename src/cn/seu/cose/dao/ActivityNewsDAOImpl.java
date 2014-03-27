package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.ActivityNews;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
@SuppressWarnings("unchecked")
public class ActivityNewsDAOImpl extends SqlMapClientDaoSupport implements
		ActivityNewsDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<ActivityNews> getActivityLatestNews() {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_NEWS.selectLatestActivityNews");
	}

	@Override
	public List<ActivityNews> getActivityLatestNewsByActivityId(int activityId) {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_NEWS.selectActivityLatestNewsByActivityId",
				activityId);
	}

	@Override
	public List<ActivityNews> getActivityNewsByActivityId(int id) {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_NEWS.selectActivityNewsByActivityId", id);
	}

	@Override
	public ActivityNews getActivityNewsById(int id) {
		return (ActivityNews) getSqlMapClientTemplate().queryForObject(
				"ACTIVITY_NEWS.selectActivityNewsById", id);
	}

	@Override
	public List<ActivityNews> searchActivityNewsByTitle(String title) {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_NEWS.selectActivityNewsByTitle", title);
	}

	@Override
	public void insertActivityNews(ActivityNews activityNews) {
		getSqlMapClientTemplate().insert("ACTIVITY_NEWS.insertActivityNews",
				activityNews);
	}

	@Override
	public void deleteActivityNews(int id) {
		getSqlMapClientTemplate()
				.delete("ACTIVITY_NEWS.deleteActivityNews", id);
	}

	@Override
	public void updateActivityNews(ActivityNews activityNews) {
		getSqlMapClientTemplate().update("ACTIVITY_NEWS.updateActivityNews",
				activityNews);
	}

	@Override
	public List<ActivityNews> getActivityNewsByActivityIdAndBaseAndRange(
			int activityId, int base, int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_NEWS.selectActivityNewsByActivityIdAndBaseAndRange",
				map);
	}

	@Override
	public int getActivityNewsCountByActivityId(int activityId) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"ACTIVITY_NEWS.selectActivityNewsCountByActivityId",
						activityId);
	}
}
