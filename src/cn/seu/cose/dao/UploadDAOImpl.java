package cn.seu.cose.dao;

import java.util.List;

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
		return (Upload)getSqlMapClientTemplate().queryForObject("UPLOAD.selectUploadById", id);
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
	
}
