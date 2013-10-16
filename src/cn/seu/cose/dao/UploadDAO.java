package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Upload;

interface UploadDAO {
	List<Upload> getUploads();

	void insertUpload(Upload upload);

	void deleteUploadById(int id);

	Upload getUploadById(int id);

	void updateUpload(Upload upload);

	int selectUploadCount();

	List<Upload> getUploadByBaseAndRange(int base, int range);
}
