package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

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
	public List<ActivityPhoto> getActivityPhotoByActivityId(int id) {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_PHOTO.selectActivityPhotoByActivityId", id);
	}

	@Override
	public List<ActivityPhoto> getRecentActivityPhotoByActivityId(int id) {
		return getSqlMapClientTemplate().queryForList(
				"ACTIVITY_PHOTO.selectRecentActivityPhotoByActivityId", id);
	}

	@Override
	public ActivityPhoto getActivityPhotoById(int id) {
		return (ActivityPhoto) getSqlMapClientTemplate().queryForObject(
				"ACTIVITY_PHOTO.selectActivityPhotoById", id);
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

	@Override
	public List<ActivityPhoto> getActivityPhotoByActivityIdAndBaseAndRange(
			int id, int base, int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", id);
		map.put("pn", base);
		map.put("pageSize", range);
		return getSqlMapClientTemplate()
				.queryForList(
						"ACTIVITY_PHOTO.selectActivityPhotoByActivityIdAndBaseAndRange",
						map);
	}

	@Override
	public int getActivityPhotosCountByActivityId(int activityId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ACTIVITY_PHOTO.selectActivityPhotosCountByActivityId",
				activityId);
	}

}
