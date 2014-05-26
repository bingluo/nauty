package cn.seu.cose.dao;

import cn.seu.cose.entity.Link;

import java.util.List;

public interface LinkDAO {

	public List<Link> getAllLinks();
	
	public void deleteLink(int id);
	
	public void insertLink(Link link);
	
}
