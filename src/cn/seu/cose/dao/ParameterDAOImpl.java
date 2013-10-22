package cn.seu.cose.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.Parameter;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class ParameterDAOImpl extends SqlMapClientDaoSupport implements
		ParameterDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Parameter> getAllParameters() {
		return getSqlMapClientTemplate().queryForList(
				"PARAMETER.selectAllParameter");
	}

	@Override
	public Parameter getParameterByKey(String key) {
		return (Parameter) getSqlMapClientTemplate().queryForObject(
				"PARAMETER.selectParameterByKey", key);
	}

	@Override
	public void updateParameter(Parameter paramete) {
		getSqlMapClientTemplate().update("PARAMETER.updateParameter", paramete);
	}
}
