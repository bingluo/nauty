package cn.seu.cose.entity;

public class PublicationPojo extends Publication {
	private String[] imgUrls;
	private String coverUrl;
	private String brief;
	private String year;
	private boolean isHot;

	public String[] getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String[] imgUrls) {
		this.imgUrls = imgUrls;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}
}
