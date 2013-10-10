package cn.seu.cose.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.AdminDAOImpl;
import cn.seu.cose.entity.Admin;
import cn.seu.cose.filter.SecurityContextHolder;

@Service
public class AdminService {

	@Autowired
	private AdminDAOImpl adminDAOImpl;

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

	public Admin getAmindById(int id) {
		return adminDAOImpl.getAmindById(id);
	}
}
