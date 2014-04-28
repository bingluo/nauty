package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Designer;

public interface DesignerDAO {

	List<Designer> getAllDesigners();

	List<Designer> getAllCertificatedDesigners();

	List<Designer> getAllUncertificatedDesigners();

	Designer getDesignerById(int id);

	Designer getDesignerByUsernameAndPswd(String username, String pswd);

	List<Designer> searchDesignerByName(String searchInput);

	void insertDesigner(Designer designer);

	void certificateDesignerById(int id);

	void uncertificateDesignerById(int id);

	void updateDesigner(Designer designer);

	Designer getDesignerByName(String name);

	void deleteDesigner(int id);

	Designer getDesignerByKey(String key);
	
	List<Designer> getAllDesignersByBaseAndRange(int base, int range);
	int getAllDesignerCount();
	List<Designer> getTypeDesignersByBaseAndRange(int type, int base, int range);
	int getTypeDesignerCount(int type);
	
}
