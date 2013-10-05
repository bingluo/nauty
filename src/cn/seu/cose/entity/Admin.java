package cn.seu.cose.entity;

public class Admin {
	private int id;

	private String username;

	private String password;

	private boolean isSuper;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}
}
