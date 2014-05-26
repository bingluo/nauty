package cn.seu.cose.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.ibatis.sqlmap.client.SqlMapClient;

import cn.seu.cose.entity.Link;

@Component
public class LinkDAOImpl extends SqlMapClientDaoSupport implements LinkDAO{

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public List<Link> getAllLinks() {
		return getSqlMapClientTemplate().queryForList("LINK.selectAllLinks");
	}

	@Override
	public void deleteLink(int id) {
		getSqlMapClientTemplate().delete("LINK.deleteLink", id);
	}

}
