package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Designer;

public interface DesignerDAO {
	List<Designer> getAllDesigners();

	List<Designer> getAllCertificatedDesigners();

	Designer getDesignerById(int id);

	Designer getDesignerByUsernameAndPswd(String username, String pswd);

	void insertDesigner(Designer designer);

	void certificateDesignerById(int id);

	void updateDesigner(Designer designer);

	Designer getDesignerByName(String name);
	
	void deleteDesigner(int id);
}
