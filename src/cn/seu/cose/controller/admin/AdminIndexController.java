package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		if (admin != null && !admin.getIsSuper()) {
			model.addAttribute("login_admin", admin.getUsername());
			to = "/admin/article_list";
		} else if (admin !=null && admin.getIsSuper()) {
			to = "/admin/super";
		}else {
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
		if (admin != null && !admin.getIsSuper()) {
			to = "/admin/article_list";
		} else if (admin != null && admin.getIsSuper()) {
			to = "/admin/super";
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
			putAdmin(model, response);
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
	
	/*
	 * super
	 */
	@RequestMapping(value="/admin/super", method=RequestMethod.GET)
	public String loginSuper(Model model) {
		model.addAttribute("super", adminService.getAdmin());
		List<Admin> list = adminService.getAdmins();
		model.addAttribute("admin_list", list);
		return "admin_super";
	}
	
	@RequestMapping(value="/admin/del_admin", method=RequestMethod.POST)
	public void postDel(@RequestParam("id")String adminIdStr, HttpServletResponse response) {
		adminService.deleteAdmin(Integer.parseInt(adminIdStr)); 
	}
	
	@RequestMapping(value="/admin/add_admin", method=RequestMethod.GET)
	public String getAdd(Model model) {
		model.addAttribute("super", adminService.getAdmin());
		return "admin_super_add";
	}
	
	@RequestMapping(value="/admin/add_admin", method=RequestMethod.POST)
	public void postAdd(@RequestParam("username")String username, @RequestParam("password")String pass, HttpServletResponse response) {
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(pass);
		admin.setIsSuper(false);
		adminService.register(admin);
		try {
			response.sendRedirect("/admin/super");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/admin/super_account-{id}", method=RequestMethod.GET)
	public String getSuper(@PathVariable("id")String idStr, Model model) {
		Admin superAdmin = adminService.getAmindById(Integer.parseInt(idStr));
		model.addAttribute("super", superAdmin);
		return "admin_super_alt";
	}
	
	@RequestMapping(value="/admin/super_account", method=RequestMethod.POST)
	public void postSuper(@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("id") String idStr, 
			HttpServletResponse response) {
		Admin superAdmin = new Admin();
		superAdmin.setId(Integer.parseInt(idStr));
		superAdmin.setUsername(username);
		superAdmin.setPassword(password);
		superAdmin.setIsSuper(true);
		adminService.updateSuper(superAdmin);
		try {
			adminService.logout();
			response.sendRedirect("/admin/super");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
