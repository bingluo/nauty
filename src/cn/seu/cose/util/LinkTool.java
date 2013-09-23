package cn.seu.cose.util;
//888
public class LinkTool {
	private static String DOMAIN = "http://localhost/";

	public static String blog(long blogid) {
		StringBuilder sb = new StringBuilder();
		sb.append(DOMAIN);
		sb.append("blog/").append(blogid).append(".html");
		return sb.toString();
	}

	public static String user(long userid) {
		StringBuilder sb = new StringBuilder();
		sb.append(DOMAIN);
		sb.append("user/").append(userid).append(".html");
		return sb.toString();
	}

	public static String folder(long folderId) {
		StringBuilder sb = new StringBuilder();
		sb.append(DOMAIN);
		sb.append("blog-folder/").append(folderId).append(".html");
		return sb.toString();
	}

	public static String action(String actionName) {
		StringBuilder sb = new StringBuilder();
		sb.append(DOMAIN);
		sb.append("action/").append(actionName).append(".html");
		return sb.toString();
	}

	public static String domain() {
		return DOMAIN;
	}

	public static String blogEdit(String blogId) {
		StringBuilder sb = new StringBuilder();
		sb.append(DOMAIN);
		sb.append("edit-blog/").append(blogId).append(".html");
		return sb.toString();
	}

	public static String image(String uri) {
		StringBuilder sb = new StringBuilder();
		sb.append(DOMAIN);
		sb.append("/static/images/upload/").append(uri);
		return sb.toString();
	}
}
