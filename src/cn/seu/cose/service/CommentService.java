package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.CommentDAO;
import cn.seu.cose.entity.Comment;

@Service
public class CommentService {

	@Autowired
	private CommentDAO commentDAOImpl;

	public Comment getCommentById(int id) {
		return commentDAOImpl.getCommentViaId(id);
	}
	
	public List<Comment> getCommentsByRefAndType(int refId, int type) {
		return commentDAOImpl.getCommentViaRefAndType(refId, type);
	}
	
	public void insertComment(Comment comment) {
		commentDAOImpl.insertComment(comment);
	}
	
	public void deleteComment(int id) {
		commentDAOImpl.deleteComment(id);
	}
	
}
