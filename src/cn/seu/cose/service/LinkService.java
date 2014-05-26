package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.LinkDAO;
import cn.seu.cose.entity.Link;


@Service
public class LinkService {
	
	@Autowired
	LinkDAO linkDaoImpl;
	
	public List<Link> getAllLinks() {
		return linkDaoImpl.getAllLinks();
	}
	 
	public void deleteLink(int id) {
		linkDaoImpl.deleteLink(id);
	}
	
	
	public void insertLink(Link link) {
		linkDaoImpl.insertLink(link);
	}
	
}
