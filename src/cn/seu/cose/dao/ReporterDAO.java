package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Reporter;

public interface ReporterDAO {
	
	public List<Reporter> getAllReportersList();
	
	public List<Reporter> getCertificatedReportersList();
	
	public List<Reporter> getUncertificatedReportersList();
	
	public List<Reporter> getTopKReportersByContribution(int k);
	
	public List<Reporter> getTopKReportersByAccept(int k);
	
	public Reporter getReporterById(int id);
	
	public Reporter getReporter(String username, String password);
	
	public Reporter getReporterByUsername(String username);
	
	public List<Reporter> search(String searchInput);
	
	public void certificate(int id);
	
	public void uncertificate(int id);
	
	public void addReporter(Reporter reporter);
	
	public void updateReporter(Reporter reporter);

	public void deleteReporter(int id);
	
	public void increaseAccept(int id);
	
	public void increaseContribute(int id);
}
