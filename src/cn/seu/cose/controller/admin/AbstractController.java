package cn.seu.cose.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import cn.seu.cose.entity.Admin;
import cn.seu.cose.entity.Reporter;
import cn.seu.cose.filter.SecurityContextHolder;
import cn.seu.cose.view.util.ViewUtil;

public class AbstractController {
	
	protected void putAdmin(Model model,HttpServletResponse response) {
		Admin admin = SecurityContextHolder.getSecurityContext().getAdmin();
		if (admin != null) {
			model.addAttribute("login_admin", admin.getUsername());
		} else {
//			model.addAttribute("login_admin", "管理员");
			try {
				response.sendRedirect(ViewUtil.getContextPath() + "/admin/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void putReporter(Model model,HttpServletResponse response) {
		Reporter reporter = SecurityContextHolder.getSecurityContext().getReporter();
		if(reporter != null) {
			model.addAttribute("login_reporter", reporter.getUsername());
			model.addAttribute("login_reporter_id", reporter.getId());
			model.addAttribute("reporter", reporter);
		} else {
			try {
				response.sendRedirect(ViewUtil.getContextPath() + "/reporter/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public int getPageCount(int pagesize, int count) {
		if (count <= pagesize) {
			return 1;
		} else if (count%pagesize == 0) {
			return count/pagesize;
		} else {
			return count/pagesize +1;
		}
	}	
}
