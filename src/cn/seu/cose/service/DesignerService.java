package cn.seu.cose.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.BlogDAO;
import cn.seu.cose.dao.CommentDAO;
import cn.seu.cose.dao.DesignerDAO;
import cn.seu.cose.entity.Designer;
import cn.seu.cose.filter.SecurityContextHolder;
import cn.seu.cose.util.Constant;
import cn.seu.cose.util.Constant.CommentType;
import cn.seu.cose.util.MailIssue;

@Service
public class DesignerService {

	@Autowired
	DesignerDAO designerDAOImpl;
	@Autowired
	CommentDAO commentDAOImpl;
	@Autowired
	BlogDAO blogDAOImpl;

	public List<Designer> getPopularDesigner() {
		List<Integer> ids = commentDAOImpl.rankCommentCountWithType(
				CommentType.DESIGNER.ordinal(), 6);
		List<Designer> designers = new ArrayList<Designer>();
		for (int id : ids) {
			Designer designer = designerDAOImpl.getDesignerById(id);
			if (designer != null) {
				designers.add(designer);
			}
		}
		return designers;
	}

	public List<Designer> getHardWorkingDesigners(int topN) {
		List<Integer> ids = blogDAOImpl.rankDesignerWithBlogCount(topN);
		List<Designer> designers = new ArrayList<Designer>();
		for (int id : ids) {
			Designer designer = designerDAOImpl.getDesignerById(id);
			if (designer != null) {
				designers.add(designer);
			}
		}
		return designers;
	}

	public List<Designer> getAllDesigners() {
		return designerDAOImpl.getAllDesigners();
	}

	public List<Designer> getAllCertificatedDesigners() {
		return designerDAOImpl.getAllCertificatedDesigners();
	}

	public List<Designer> getAllUncertificatedDesigners() {
		return designerDAOImpl.getAllUncertificatedDesigners();
	}

	public Designer getDesignerById(int id) {
		return designerDAOImpl.getDesignerById(id);
	}

	public Designer getDesignerByName(String username) {
		return designerDAOImpl.getDesignerByName(username);
	}

	public Designer getDesignerByUsernameAndPswd(String username, String pwd) {
		return designerDAOImpl.getDesignerByUsernameAndPswd(username, pwd);
	}

	public List<Designer> searchDesignerByName(String searchInput) {
		return designerDAOImpl.searchDesignerByName(searchInput);
	}

	public void registerDesigner(Designer designer) {
		StringBuilder sb = new StringBuilder();
		sb.append(designer.getUserName()).append(
				"您好：请您点击下面的链接(该链接在12小时内有效)，完成您在嘉兴工业设计协会的验证。");

		String key = java.util.UUID.randomUUID().toString();
		designer.setvCode(key);
		String url = Constant.DOMAIN_ROOT + "/designer/active?key=" + key;
		sb.append("<a href='").append(url).append("'>").append(url)
				.append("</a>");
		MailIssue.send("嘉兴工业设计协会用户注册邮箱验证", sb.toString(), designer.getvEmail());
		designerDAOImpl.insertDesigner(designer);
	}

	public void changeEmail(Designer designer) {
		StringBuilder sb = new StringBuilder();
		sb.append(designer.getUserName()).append(
				"您好：请您点击下面的链接(该链接在12小时内有效)，完成您在嘉兴工业设计协会的邮箱修改的验证。");

		String key = java.util.UUID.randomUUID().toString();
		designer.setvCode(key);
		String url = Constant.DOMAIN_ROOT + "/designer/active?key=" + key;
		sb.append("<a href='").append(url).append("'>").append(url)
				.append("</a>");
		MailIssue.send("嘉兴工业设计协会用户邮箱修改验证", sb.toString(), designer.getvEmail());
		designerDAOImpl.updateDesigner(designer);
	}

	public void retrievePswd(Designer designer) {
		StringBuilder sb = new StringBuilder();
		sb.append(designer.getUserName()).append(
				"您好：您正在进行找回密码的操作，请您点击下面的链接(该链接在12小时内有效)，进行密码重置。");

		String key = java.util.UUID.randomUUID().toString();
		designer.setvCode(key);
		String url = Constant.DOMAIN_ROOT + "/retrieve-pswd?key="
				+ designer.getPassword() + "&username="
				+ designer.getUserName();
		sb.append("<a href='").append(url).append("'>").append(url)
				.append("</a>");
		MailIssue.send("嘉兴工业设计协会用户密码找回", sb.toString(), designer.getEmail());
	}

	/**
	 * 激活用户
	 * 
	 * @param key
	 * @return
	 */
	public Designer activeDesigner(String key) {
		Designer designer = designerDAOImpl.getDesignerByKey(key);
		designer.setEmail(designer.getvEmail());
		designer.setvEmail("");
		designer.setvCode("");
		designerDAOImpl.updateDesigner(designer);
		return designer;
	}

	public void certificateDesignerById(int id) {
		designerDAOImpl.certificateDesignerById(id);
	}

	public void uncertificateDesignerById(int id) {
		designerDAOImpl.uncertificateDesignerById(id);
	}

	public void updateDesigner(Designer designer) {
		designerDAOImpl.updateDesigner(designer);
	}

	public void deleteDesigner(int id) {
		designerDAOImpl.deleteDesigner(id);
	}

	/**
	 * designer sign in
	 */
	public Designer signIn(String name, String pswd) {
		Designer designer = designerDAOImpl.getDesignerByUsernameAndPswd(name,
				pswd);
		SecurityContextHolder.getSecurityContext().setDesigner(designer);
		return designer;
	}

	/**
	 * designer log off
	 */
	public void logOff() {
		SecurityContextHolder.getSecurityContext().setDesigner(null);
	}

	/**
	 * return whether the current user sign in or not
	 * 
	 * @return
	 */
	public boolean isSignIn() {
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

	/**
	 * get the current designer who has signed in
	 */
	public Designer getCurrentUser() {
		Designer designer = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		return designer;
	}
	
	public List<Designer> getAllDesignersByBaseAndRange(int pn, int pageSize) {
		return  designerDAOImpl.getAllDesignersByBaseAndRange((pn-1)*pageSize, pn*pageSize);
	}
	public int getAllDesignerCount() {
		return  designerDAOImpl.getAllDesignerCount();
	}
	public List<Designer> getTypeDesignersByBaseAndRange(int type, int pn, int pageSize) {
		return  designerDAOImpl.getTypeDesignersByBaseAndRange(type, (pn-1)*pageSize, pn*pageSize);
	}
	public int getTypeDesignerCount(int type) {
		return  designerDAOImpl.getTypeDesignerCount(type);
	}
	
}
