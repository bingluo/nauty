package cn.seu.cose.dao;

import cn.seu.cose.entity.Admin;

interface AdminDAO {
	AdminDAO getAdmin(String username, String pswd);

	void insertAdmin(Admin admin);

	void updateAdmin(Admin admin);

	void deleteAdmin(int id);
}
