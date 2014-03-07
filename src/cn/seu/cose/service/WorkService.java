package cn.seu.cose.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.ActivityDAO;
import cn.seu.cose.dao.DesignerDAO;
import cn.seu.cose.dao.WorkDAO;
import cn.seu.cose.entity.Activity;
import cn.seu.cose.entity.Designer;
import cn.seu.cose.entity.Work;
import cn.seu.cose.entity.WorkPojo;

@Service
public class WorkService {

	@Autowired
	WorkDAO workDAOImpl;
	@Autowired
	DesignerDAO designerDAOImpl;
	@Autowired
	ActivityDAO activityDAOImpl;

	public WorkPojo getWorkViaId(int id) {
		WorkPojo workPojo = new WorkPojo();
		Work work = workDAOImpl.getWorkById(id);
		workPojo.setWork(work);

		if (work.getUserId() != -1) {
			Designer designer = designerDAOImpl.getDesignerById(work
					.getUserId());
			workPojo.setDesigner(designer);
		}
		if (work.getActivityId() != -1) {
			Activity activity = activityDAOImpl.getActivityById(work
					.getActivityId());
			workPojo.setActivity(activity);
		}
		return workPojo;
	}

	public List<WorkPojo> getWorkByUser(int userId) {
		List<WorkPojo> workPojos = new ArrayList<WorkPojo>();
		List<Work> works = workDAOImpl.getWorksByUserId(userId);
		Designer designer = designerDAOImpl.getDesignerById(userId);
		for (Work work : works) {
			WorkPojo workPojo = new WorkPojo();
			workPojo.setWork(work);
			workPojo.setDesigner(designer);
			workPojos.add(workPojo);
		}
		return workPojos;
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
