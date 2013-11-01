package cn.seu.cose.entity;

import java.util.Date;

public class Upload {
	private int id;

	private String initialName;

	private String uploadName;

	private Date uploadTime;

	private int uploadSize;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInitialName() {
		return initialName;
	}

	public void setInitialName(String initialName) {
		this.initialName = initialName;
	}

	public String getUploadName() {
		return uploadName;
	}

	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public int getUploadSize() {
		return uploadSize;
	}

	public void setUploadSize(int uploadSize) {
		this.uploadSize = uploadSize;
	}
}
