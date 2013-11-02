package cn.seu.cose.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.seu.cose.core.SystemContainer;
import cn.seu.cose.service.AdminService;

public class AdminFilter implements Filter {

	private static AdminService adminService = (AdminService) SystemContainer
			.lookup("adminService");

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();

		SecurityContext securityContext = SecurityContextHolder
				.getSecurityContext();

		Integer adminId = (Integer) session.getAttribute("adminId");
		if (adminId != null) {
			securityContext.setAdmin(adminService.getAmindById(adminId));
		}

		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
