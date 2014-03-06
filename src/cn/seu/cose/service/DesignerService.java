package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.DesignerDAO;
import cn.seu.cose.entity.Designer;
import cn.seu.cose.filter.SecurityContextHolder;

@Service
public class DesignerService {

	@Autowired
	DesignerDAO designerDAOImpl;

	public List<Designer> getAllDesigners() {
		return designerDAOImpl.getAllDesigners();
	}

	public List<Designer> getAllCertificatedDesigners() {
		return designerDAOImpl.getAllCertificatedDesigners();
	}

	public Designer getDesignerById(int id) {
		return designerDAOImpl.getDesignerById(id);
	}

	public Designer getDesignerByUsernameAndPswd(String username, String pwd) {
		return designerDAOImpl.getDesignerByUsernameAndPswd(username, pwd);
	}

	public void insertDesigner(Designer designer) {
		designerDAOImpl.insertDesigner(designer);
	}

	public void certificateDesignerById(int id) {
		designerDAOImpl.certificateDesignerById(id);
	}

	public void updateDesigner(Designer designer) {
		designerDAOImpl.updateDesigner(designer);
	}

	/**
	 * return whether the current user sign in or not
	 * 
	 * @return
	 */
	public boolean ifSignIn() {
		Designer designer = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		if (designer == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isTheSignInOne(int designerId) {
		Designer designer = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		if (designer == null || designer.getId() != designerId) {
			return false;
		} else {
			return true;
		}
	}
}
