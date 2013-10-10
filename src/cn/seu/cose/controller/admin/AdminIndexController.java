package cn.seu.cose.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.Admin;
import cn.seu.cose.service.AdminService;

@Controller
public class AdminIndexController {
	@Autowired
	private AdminService adminService;
	
	
	@RequestMapping("/admin/index")
	public String adminIndex(Model model) {
		Admin admin = adminService.getAdmin();
		if (admin != null) {
			model.addAttribute("login_admin", admin.getUsername());
			return "admin_index";
		} else {
			return "admin_login";
		}
		
	}
	
	@RequestMapping(value="/admin/login", method=RequestMethod.GET)
	public String login() {
		return "admin_login";
	}
	
	@RequestMapping(value="/admin/login", method=RequestMethod.POST)
	public void login(@RequestParam("username") String username, @RequestParam("password") String password
			, HttpServletResponse response) {
		Admin admin = adminService.logon(username, password);
		String to = "/admin/login";
		if (admin != null) {
			to = "/admin/index";
		}
		try {
			response.sendRedirect(to);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
