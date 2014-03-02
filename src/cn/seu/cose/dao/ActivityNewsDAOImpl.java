package cn.seu.cose.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.ibatis.sqlmap.client.SqlMapClient;

import cn.seu.cose.entity.ActivityNews;

@Component
@SuppressWarnings("unchecked")
public class ActivityNewsDAOImpl extends SqlMapClientDaoSupport implements
		ActivityNewsDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public List<ActivityNews> getActivityNewsByActivityId(int id) {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_NEWS.selectActivityNewsByActivityId", id);
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
}
