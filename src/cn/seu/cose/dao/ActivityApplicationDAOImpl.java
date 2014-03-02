package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.ActivityApplication;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
@SuppressWarnings("unchecked")
public class ActivityApplicationDAOImpl extends SqlMapClientDaoSupport
		implements ActivityApplicationDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<ActivityApplication> getActivityApplicationsByActivityId(int id) {
		return getSqlMapClientTemplate()
				.queryForList(
						"ACTIVITY_APPLICATION.selectActivityEnrollmentByActivityId",
						id);
	}

	@Override
	public void insertActivityApplication(int userId, int activityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("activityId", activityId);
		getSqlMapClientTemplate().insert(
				"ACTIVITY_APPLICATION.insertActivityApplication", map);
	}

	@Override
	public void deleteActivityApplication(int userId, int activityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("activityId", activityId);
		getSqlMapClientTemplate().delete(
				"ACTIVITY_APPLICATION.deleteActivityApplication", map);
	}
}
