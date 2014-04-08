package cn.seu.cose.entity;

public class Reporter {
	
	private int id;
	// basic infos
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	// contributed article counts
	private int contributeCount;
	// accepted article counts
	private int acceptCount;
	// whether is authorized
	private boolean certificated;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getContributeCount() {
		return contributeCount;
	}
	public void setContributeCount(int contributeCount) {
		this.contributeCount = contributeCount;
	}
	public int getAcceptCount() {
		return acceptCount;
	}
	public void setAcceptCount(int acceptCount) {
		this.acceptCount = acceptCount;
	}
	public boolean isCertificated() {
		return certificated;
	}
	public void setCertificated(boolean certificated) {
		this.certificated = certificated;
	} 
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
