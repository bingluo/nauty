package cn.seu.cose.entity;

public class ArticlePojo extends Article {
	private ArticlePojo previous;
	private ArticlePojo next;

	private String rootCatUri;
	private String uri;

	public String getRootCatUri() {
		return rootCatUri;
	}

	public void setRootCatUri(String rootCatUri) {
		this.rootCatUri = rootCatUri;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ArticlePojo getPrevious() {
		return previous;
	}

	public void setPrevious(ArticlePojo previous) {
		this.previous = previous;
	}

	public ArticlePojo getNext() {
		return next;
	}

	public void setNext(ArticlePojo next) {
		this.next = next;
	}
}
