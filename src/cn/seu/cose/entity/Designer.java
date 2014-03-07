package cn.seu.cose.entity;

import java.util.Date;

public class Designer {
	private int id;
	private String userName;
	private String password;
	private String email;
	private String intro = "无";
	private String avatar;
	private Date registerTime;
	private Date certificateTime;
	private boolean certificated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getCertificateTime() {
		return certificateTime;
	}

	public void setCertificateTime(Date certificateTime) {
		this.certificateTime = certificateTime;
	}

	public boolean isCertificated() {
		return certificated;
	}

	public void setCertificated(boolean certificated) {
		this.certificated = certificated;
	}
}
