package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.Upload;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class UploadDAOImpl extends SqlMapClientDaoSupport implements UploadDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Upload> getUploads() {
		return getSqlMapClientTemplate().queryForList("UPLOAD.selectUpload");
	}

	@Override
	public Upload getUploadById(int id) {
		return (Upload) getSqlMapClientTemplate().queryForObject(
				"UPLOAD.selectUploadById", id);
	}

	@Override
	public void insertUpload(Upload upload) {
		getSqlMapClientTemplate().insert("UPLOAD.insertUpload", upload);
	}

	@Override
	public void updateUpload(Upload upload) {
		getSqlMapClientTemplate().update("UPLOAD.updateUpload", upload);
	}

	@Override
	public void deleteUploadById(int id) {
		getSqlMapClientTemplate().delete("UPLOAD.deletUploadById", id);
	}

	@Override
	public int selectUploadCount() {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"UPLOAD.selectUploadCount");
	}

	@Override
	public List<Upload> getUploadByBaseAndRange(int base, int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"UPLOAD.selectUploadByBaseAndRange", map);
	}
}
