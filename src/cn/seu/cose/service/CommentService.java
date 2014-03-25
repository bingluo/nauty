package cn.seu.cose.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.CommentDAO;
import cn.seu.cose.dao.DesignerDAO;
import cn.seu.cose.entity.Comment;
import cn.seu.cose.entity.CommentPojo;

@Service
public class CommentService {

	@Autowired
	private CommentDAO commentDAOImpl;
	@Autowired
	private DesignerDAO designerDAOImpl;

	public Comment getCommentById(int id) {
		return commentDAOImpl.getCommentViaId(id);
	}

	public List<CommentPojo> getCommentsByRefAndType(int refId, int type) {
		List<Comment> comments = commentDAOImpl.getCommentViaRefAndType(refId,
				type);
		List<CommentPojo> commentPojos = new ArrayList<CommentPojo>();
		for (Comment comment : comments) {
			CommentPojo commentPojo = new CommentPojo();
			commentPojo.setComment(comment);
			if (comment.getUserId() != -1) {
				commentPojo.setDesigner(designerDAOImpl.getDesignerById(comment
						.getUserId()));
			}
			commentPojos.add(commentPojo);
		}
		return commentPojos;
	}

	public List<CommentPojo> getCommentsByRefAndTypeAndPnAndSize(int refId,
			int type, int pn, int pageSize) {
		List<Comment> comments = commentDAOImpl
				.getCommentViaRefAndTypeAndBaseAndRange(refId, type, pageSize
						* (pn - 1), pageSize);
		List<CommentPojo> commentPojos = new ArrayList<CommentPojo>();
		for (Comment comment : comments) {
			CommentPojo commentPojo = new CommentPojo();
			commentPojo.setComment(comment);
			if (comment.getUserId() != -1) {
				commentPojo.setDesigner(designerDAOImpl.getDesignerById(comment
						.getUserId()));
			}
			commentPojos.add(commentPojo);
		}
		return commentPojos;
	}

	public int getCommentCountByRefAndType(int refId, int type) {
		return commentDAOImpl.getCommentCountViaRefAndType(refId, type);
	}

	public void insertComment(Comment comment) {
		commentDAOImpl.insertComment(comment);
	}

	public void deleteComment(int id) {
		commentDAOImpl.deleteComment(id);
	}

}
