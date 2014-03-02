package cn.seu.cose.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.ActivityNews;
import cn.seu.cose.entity.ActivityPhoto;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
@SuppressWarnings("unchecked")
public class ActivityPhotoDAOImpl extends SqlMapClientDaoSupport implements
		ActivityPhotoDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<ActivityNews> getActivityPhotoByActivityId(int id) {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_PHOTO.selectActivityPhotoByActivityId", id);
	}

	@Override
	public void insertActivityPhoto(ActivityPhoto activityPhoto) {
		getSqlMapClientTemplate().insert("ACTIVITY_PHOTO.insertActivityPhoto",
				activityPhoto);
	}

	@Override
	public void deleteActivityPhoto(int id) {
		getSqlMapClientTemplate().delete("ACTIVITY_PHOTO.deleteActivityPhoto",
				id);
	}

	@Override
	public void updateActivityPhoto(ActivityPhoto activityPhoto) {
		getSqlMapClientTemplate().update("ACTIVITY_PHOTO.updateActivityPhoto",
				activityPhoto);
	}

}
