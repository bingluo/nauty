package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.ibatis.sqlmap.client.SqlMapClient;

import cn.seu.cose.entity.Reporter;

@Component
public class ReporterDAOImpl extends SqlMapClientDaoSupport implements ReporterDAO{

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public List<Reporter> getAllReportersList() {
		return getSqlMapClientTemplate().queryForList(
				"REPORTER.selectAllReportersList");
	}

	@Override
	public List<Reporter> getCertificatedReportersList() {
		return getSqlMapClientTemplate().queryForList(
				"REPORTER.selectCertificatedReportersList");
	}

	@Override
	public List<Reporter> getUncertificatedReportersList() {
		return getSqlMapClientTemplate().queryForList(
				"REPORTER.selectUncertificatedReportersList");
	}

	@Override
	public List<Reporter> getTopKReportersByContribution(int k) {
		return getSqlMapClientTemplate().queryForList(
				"REPORTER.selectTopKReportersByContribution", k);
	}

	@Override
	public List<Reporter> getTopKReportersByAccept(int k) {
		return getSqlMapClientTemplate().queryForList(
				"REPORTER.selectTopKReportersByAccept", k);
	}

	@Override
	public Reporter getReporterById(int id) {
		return (Reporter)getSqlMapClientTemplate().queryForObject(
				"REPORTER.selectReporterById", id);
	}
	
	@Override
	public Reporter getReporterByUsername(String username) {
		return (Reporter) getSqlMapClientTemplate().queryForObject(
				"REPORTER.selectReporterByUsername", username);
	}

	@Override
	public Reporter getReporter(String username, String password) {
		HashMap<String, String>	map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		return (Reporter)getSqlMapClientTemplate().queryForObject(
				"REPORTER.selectReporter", map);
	}

	@Override
	public List<Reporter> search(String searchInput) {
		return getSqlMapClientTemplate().queryForList(
				"REPORTER.searchReporter", searchInput);
	}
	
	@Override
	public void certificate(int id) {
		getSqlMapClientTemplate().update(
				"REPORTER.certificate", id);
	}

	@Override
	public void uncertificate(int id) {
		getSqlMapClientTemplate().update(
				"REPORTER.uncertificate", id);
	}

	@Override
	public void addReporter(Reporter reporter) {
		getSqlMapClientTemplate().insert(
				"REPORTER.insertReporter", reporter);
	}

	@Override
	public void updateReporter(Reporter reporter) {
		getSqlMapClientTemplate().update(
				"REPORTER.updateReporter", reporter);
	}

	@Override
	public void deleteReporter(int id) {
		getSqlMapClientTemplate().delete(
				"REPORTER.deleteReporter", id);	
	}

	@Override
	public void increaseAccept(int id) {
		getSqlMapClientTemplate().update(
				"REPORTER.increaseAccept", id);
	}

	@Override
	public void increaseContribute(int id) {
		getSqlMapClientTemplate().update(
				"REPORTER.increaseContribute", id);
	}

}
