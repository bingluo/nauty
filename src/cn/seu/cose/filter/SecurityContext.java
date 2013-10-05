package cn.seu.cose.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.seu.cose.entity.Admin;

public class SecurityContext {
	private HttpServletRequest httpRequest;
	private HttpSession httpSession;
	private HttpServletResponse httpResponse;
	private Admin admin;

	/**
	 * @return the httpRequest
	 */
	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	/**
	 * @param httpRequest
	 *            the httpRequest to set
	 */
	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	/**
	 * @return the httpServletResponse
	 */
	public HttpServletResponse getHttpResponse() {
		return httpResponse;
	}

	/**
	 * @param httpServletResponse
	 *            the httpServletResponse to set
	 */
	public void setHttpResponse(HttpServletResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	/**
	 * @return the httpSession
	 */
	public HttpSession getHttpSession() {
		return httpSession;
	}

	/**
	 * @param httpSession
	 *            the httpSession to set
	 */
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	/**
	 * @return the admin
	 */
	public Admin getAdmin() {
		return admin;
	}

	/**
	 * @param admin
	 *            the admin to set
	 */
	public void setAdmin(Admin admin) {
		if (httpSession != null && admin != null) {
			httpSession.setAttribute("userId", admin.getId());
		}
		this.admin = admin;
	}
}
