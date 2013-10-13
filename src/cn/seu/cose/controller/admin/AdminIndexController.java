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
public class AdminIndexController extends AbstractController{
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/admin/index")
	public void adminIndex(Model model, HttpServletResponse response) {
		String to = "";
		Admin admin = adminService.getAdmin();
		if (admin != null) {
			model.addAttribute("login_admin", admin.getUsername());
			to = "/admin/article_list";
		} else {
			to = "/admin/login";
		}
		try {
			response.sendRedirect(to);
		} catch (IOException e) {
			e.printStackTrace();
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
			to = "/admin/article_list";
		}
		try {
			response.sendRedirect(to);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/admin/logout")
	public String logout() {
		adminService.logout();
		return "admin_login";
	}
	
	@RequestMapping("/admin/account") 
	public String getAccount(Model model, HttpServletResponse response) {
		Admin admin = adminService.getAdmin();
		if (admin != null) {
			putAdmin(model);
			model.addAttribute("admin", admin);
			return "admin_account";
		} else {
			try {
				response.sendRedirect("/admin/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping(value="/admin/account", method=RequestMethod.POST) 
	public void getAccount(@RequestParam("password") String password, HttpServletResponse response) {
		if (adminService.getAdmin() != null) {
			adminService.modifyPswd(password);
		}
		try {
			adminService.logout();
			response.sendRedirect("/admin/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
