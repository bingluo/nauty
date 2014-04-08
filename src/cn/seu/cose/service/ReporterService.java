package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.ReporterDAO;
import cn.seu.cose.entity.Reporter;
import cn.seu.cose.filter.SecurityContextHolder;

@Service
public class ReporterService {
	
	@Autowired
	ReporterDAO reporterDAOImpl;
	
	public Reporter logon(String username, String password) {
		Reporter reporter = reporterDAOImpl.getReporter(username, password);
		SecurityContextHolder.getSecurityContext().setReporter(reporter);
		return reporter;
	}

	public void logout() {
		SecurityContextHolder.getSecurityContext().setReporter(null);
	}

	public void register(Reporter reporter) {
		reporterDAOImpl.addReporter(reporter);
	}

	public void modifyInfo(Reporter reporter) {
		reporterDAOImpl.updateReporter(reporter);
	}
	
	public void deleteReporter(int id) {
		reporterDAOImpl.deleteReporter(id);
	}
	
	// get一个通讯员
	public Reporter getReporterById(int id) {
		return reporterDAOImpl.getReporterById(id);
	}
	
	// get所有通讯员
	public List<Reporter> getAllReportersList() {
		return reporterDAOImpl.getAllReportersList();
	}
	
	// get已认证通讯员
	public List<Reporter> getCertificatedReportersList() {
		return reporterDAOImpl.getCertificatedReportersList();
	}
	
	// get待认证通讯员
	public List<Reporter> getUncertificatedReportersList() {
		return reporterDAOImpl.getUncertificatedReportersList();
	}
	
	public List<Reporter> getTopKReportersByContribution(int k) {
		return reporterDAOImpl.getTopKReportersByContribution(k);
	}
	
	public List<Reporter> getTopKReportersByAccept(int k) {
		return reporterDAOImpl.getTopKReportersByAccept(k);
	}
	
	// 认证与注销认证
	public void certificate(int id) {
		reporterDAOImpl.certificate(id);
	}
	public void uncertificate(int id) {
		reporterDAOImpl.uncertificate(id);
	}
	
	// 贡献count和采纳count递增
	public void incrContribute(int id) {
		reporterDAOImpl.increaseContribute(id);
	}
	public void incrAccept(int id) {
		reporterDAOImpl.increaseAccept(id);
	}
}
