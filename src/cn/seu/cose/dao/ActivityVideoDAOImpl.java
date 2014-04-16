package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.ibatis.sqlmap.client.SqlMapClient;

import cn.seu.cose.entity.ActivityVideo;

@Component
public class ActivityVideoDAOImpl extends SqlMapClientDaoSupport 
	implements ActivityVideoDAO{

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
		
	@Override
	public List<ActivityVideo> getActivityVideoByActivityId(int id) {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_VIDEO.getActivityVideoByActivityId", id);
	}

	@Override
	public List<ActivityVideo> getActivityVideoByActivityIdAndBaseAndRange(
			int id, int base, int range) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_VIDEO.getActivityVideoByActivityIdAndBaseAndRange", map);
	}

	@Override
	public int getActivityVideoCountByActivityId(int activityId) {
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"ACTIVITY_VIDEO.getActivityVideoCountByActivityId", activityId);
	}

	@Override
	public List<ActivityVideo> getRecentActivityVideoByActivityId(int id) {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_VIDEO.getRecentActivityVideoByActivityId", id);
	}

	@Override
	public ActivityVideo getActivityVideoById(int id) {
		return (ActivityVideo)getSqlMapClientTemplate().queryForObject(
				"ACTIVITY_VIDEO.getActivityVideoById", id);
	}

	@Override
	public void insertActivityVideo(ActivityVideo video) {
		getSqlMapClientTemplate().insert("ACTIVITY_VIDEO.insertActivityVideo", video);
	}

	@Override
	public void deleteActivityVideo(int id) {
		getSqlMapClientTemplate().delete("ACTIVITY_VIDEO.deleteActivityVideo", id);
	}

	@Override
	public void updateActivityVideo(ActivityVideo video) {
		getSqlMapClientTemplate().update("ACTIVITY_VIDEO.updateActivityVideo", video);
	}
	
	
}
