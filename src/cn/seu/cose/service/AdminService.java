package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.AdminDAO;
import cn.seu.cose.entity.Admin;
import cn.seu.cose.filter.SecurityContextHolder;

@Service
public class AdminService {

	@Autowired
	private AdminDAO adminDAOImpl;

	public Admin logon(String username, String password) {
		Admin admin = adminDAOImpl.getAdmin(username, password);
		SecurityContextHolder.getSecurityContext().setAdmin(admin);
		return admin;
	}

	public void logout() {
		SecurityContextHolder.getSecurityContext().setAdmin(null);
	}

	public void register(Admin admin) {
		adminDAOImpl.insertAdmin(admin);
	}

	public void modifyPswd(String password) {
		Admin admin = SecurityContextHolder.getSecurityContext().getAdmin();
		admin.setPassword(password);
		adminDAOImpl.updateAdmin(admin);
	}

	public boolean deleteAdmin(int id) {
		Admin admin = SecurityContextHolder.getSecurityContext().getAdmin();
		if (admin != null && admin.getIsSuper()) {
			adminDAOImpl.deleteAdmin(id);
			return true;
		} else {
			return false;
		}
	}

	public Admin getAdmin() {
		return SecurityContextHolder.getSecurityContext().getAdmin();
	}

	public Admin getAmindById(int id) {
		return adminDAOImpl.getAmindById(id);
	}

	/*
	 * 获取普通的admin
	 */
	public List<Admin> getAdmins() {
		return adminDAOImpl.getAdmins();
	}

	/*
	 * 修改超级管理员账号或密码
	 */
	public boolean updateSuper(Admin admin) {
		try {
			adminDAOImpl.updateSuperAdmin(admin);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
