package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.Admin;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class AdminDAOImpl extends SqlMapClientDaoSupport implements AdminDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public AdminDAO getAdmin(String username, String pswd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", pswd);
		return (AdminDAO) getSqlMapClientTemplate().queryForObject(
				"ADMIN.selectAdminByNameAndPswd", map);
	}

	@Override
	public void insertAdmin(Admin admin) {
		getSqlMapClientTemplate().insert("ADMIN.insertAdmin", admin);
	}

	@Override
	public void updateAdmin(Admin admin) {
		getSqlMapClientTemplate().update("ADMIN.updateAdmin", admin);
	}

	@Override
	public void deleteAdmin(int id) {
		getSqlMapClientTemplate().delete("ADMIN.deleteAdminById", id);
	}

}
