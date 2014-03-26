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
import cn.seu.cose.view.util.ViewUtil;

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

	public List<WorkPojo> getWorkByUserAndPnAndSize(int userId, int pn,
			int pageSize) {
		List<WorkPojo> workPojos = new ArrayList<WorkPojo>();
		List<Work> works = workDAOImpl.getWorksByUserIdAndBaseAndRange(userId,
				pageSize * (pn - 1), pageSize);
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

	public String workUrl(int id) {
		StringBuilder url = new StringBuilder();
		Work work = workDAOImpl.getWorkById(id);
		if (work.getUserId() == -1) {
			url.append(ViewUtil.getContextPath()).append("/activity/")
					.append(work.getActivityId()).append("/work/")
					.append(work.getId());
		} else {
			url.append(ViewUtil.getContextPath()).append("/designer/")
					.append(work.getUserId()).append("/work/")
					.append(work.getId());
		}
		return url.toString();
	}

	public List<WorkPojo> getHotWorks() {
		List<WorkPojo> workPojos = new ArrayList<WorkPojo>();
		List<Work> works = workDAOImpl.getHotWorks();
		for (Work work : works) {
			Designer designer = designerDAOImpl.getDesignerById(work
					.getUserId());
			Activity activity = activityDAOImpl.getActivityById(work
					.getActivityId());

			WorkPojo workPojo = new WorkPojo();
			workPojo.setWork(work);
			workPojo.setDesigner(designer);
			workPojo.setActivity(activity);
			workPojos.add(workPojo);
		}
		return workPojos;
	}

	public List<WorkPojo> getRecentWorks(int count) {
		List<WorkPojo> workPojos = new ArrayList<WorkPojo>();
		List<Work> works = workDAOImpl.getRecentWorks(count);
		for (Work work : works) {
			Designer designer = designerDAOImpl.getDesignerById(work
					.getUserId());
			Activity activity = activityDAOImpl.getActivityById(work
					.getActivityId());

			WorkPojo workPojo = new WorkPojo();
			workPojo.setWork(work);
			workPojo.setDesigner(designer);
			workPojo.setActivity(activity);
			workPojos.add(workPojo);
		}
		return workPojos;
	}

	public List<WorkPojo> getRecentWorksByActivityId(int activityId) {
		List<WorkPojo> workPojos = new ArrayList<WorkPojo>();
		List<Work> works = workDAOImpl.getRecentWorksByActivityId(activityId);
		for (Work work : works) {
			Designer designer = designerDAOImpl.getDesignerById(work
					.getUserId());
			WorkPojo workPojo = new WorkPojo();
			workPojo.setWork(work);
			workPojo.setDesigner(designer);
			workPojos.add(workPojo);
		}
		return workPojos;
	}

	public List<WorkPojo> getWorksByActivityIdAndPnAndSize(int activityId,
			int pn, int pageSize) {
		List<WorkPojo> workPojos = new ArrayList<WorkPojo>();
		List<Work> works = workDAOImpl.getWorksByActivityIdAndBaseAndRange(
				activityId, pageSize * (pn - 1), pageSize);
		for (Work work : works) {
			Designer designer = designerDAOImpl.getDesignerById(work
					.getUserId());
			WorkPojo workPojo = new WorkPojo();
			workPojo.setWork(work);
			workPojo.setDesigner(designer);
			workPojos.add(workPojo);
		}
		return workPojos;
	}

	public int getWorksCountByActivityId(int activityId) {
		return workDAOImpl.getWorksCountByActivityId(activityId);
	}

	public int getWorksCountByDesignerId(int designerId) {
		return workDAOImpl.getWorksCountByDesignerId(designerId);
	}
}
