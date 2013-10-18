package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Admin;

interface AdminDAO {
	Admin getAdmin(String username, String pswd);

	void insertAdmin(Admin admin);

	void updateAdmin(Admin admin);

	void deleteAdmin(int id);

	Admin getAmindById(int id);

	List<Admin> getAdmins();

	void updateSuperAdmin(Admin admin);
}
