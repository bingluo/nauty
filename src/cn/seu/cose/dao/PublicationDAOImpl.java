package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.PublicationPojo;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class PublicationDAOImpl extends SqlMapClientDaoSupport implements
		PublicationDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<PublicationPojo> getAllPublications() {
		return getSqlMapClientTemplate().queryForList(
				"PUBLICATION.selectAllPublications");
	}

	@Override
	public PublicationPojo getPublicationById(int id) {
		return (PublicationPojo) getSqlMapClientTemplate().queryForObject(
				"PUBLICATION.selectPublicationById", id);
	}

	@Override
	public List<PublicationPojo> getRecentPublications() {
		return getSqlMapClientTemplate().queryForList(
				"PUBLICATION.selectRecentPublications");
	}

	@Override
	public List<PublicationPojo> getPublicationsByBaseAndRange(int base,
			int range) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"PUBLICATION.selectPublicationsByBaseAndRange", map);
	}

	@Override
	public void insertPublication(PublicationPojo publication) {
		getSqlMapClientTemplate().insert("PUBLICATION.insertPublication",
				publication);
	}

	@Override
	public void updatePublication(PublicationPojo publication) {
		getSqlMapClientTemplate().update("PUBLICATION.updatePublication",
				publication);
	}

	@Override
	public void deletePublication(int id) {
		getSqlMapClientTemplate().delete("PUBLICATION.deletePublication", id);
	}
}
