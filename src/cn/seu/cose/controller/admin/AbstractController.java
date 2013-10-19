package cn.seu.cose.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import cn.seu.cose.entity.Admin;
import cn.seu.cose.service.AdminService;

public class AbstractController {
	@Autowired
	private AdminService adminService;
	
	protected void putAdmin(Model model,HttpServletResponse response) {
		Admin admin = adminService.getAdmin();
		if (admin != null) {
			model.addAttribute("login_admin", admin.getUsername());
		} else {
//			model.addAttribute("login_admin", "管理员");
			try {
				response.sendRedirect("/admin/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
