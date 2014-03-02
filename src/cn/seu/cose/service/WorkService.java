package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.WorkDAOImpl;
import cn.seu.cose.entity.Work;


@Service
public class WorkService {
	
	@Autowired
	WorkDAOImpl workDAOImpl;
	
	public Work getWorkViaId(int id) {
		return workDAOImpl.getWorkById(id);
	}
	
	public List<Work> getWorkByUser(int userId) {
		return workDAOImpl.getWorksByUserId(userId);
	}
	
	public void insertWork(Work work) {
		workDAOImpl.insertWork(work);
	}
	
	public void updateWork(Work work) {
		workDAOImpl.updateWork(work);
	}
	
	public void deleteWork(int id) {
		workDAOImpl.deleteWork(id);
	}
	
	public void updateWork(int id) {
		workDAOImpl.updateVote(id);
	}
}
