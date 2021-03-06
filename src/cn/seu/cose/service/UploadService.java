package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.UploadDAO;
import cn.seu.cose.entity.Upload;

@Service
public class UploadService {

	@Autowired
	UploadDAO uploadDAOImpl;

	public List<Upload> getUploads() {
		UploadDAO upload;
		return uploadDAOImpl.getUploads();
	}

	public void addUpload(Upload upload) {
		uploadDAOImpl.insertUpload(upload);
	}

	public void deleteUpload(int id) {
		uploadDAOImpl.deleteUploadById(id);
	}

	public Upload getUpload(int id) {
		return uploadDAOImpl.getUploadById(id);
	}

	public void updateUpload(Upload upload) {
		uploadDAOImpl.updateUpload(upload);
	}

	public int getUploadCount() {
		return uploadDAOImpl.selectUploadCount();
	}

	public List<Upload> getUploadsByIndexAndPagesize(int index, int pagesize) {
		return uploadDAOImpl.getUploadByBaseAndRange((index - 1) * pagesize,
				pagesize);
	}
}
