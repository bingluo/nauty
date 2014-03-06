package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Comment;

public interface CommentDAO {

	Comment getCommentViaId(int id);
	List<Comment> getCommentViaRef(int refId);
	
	void insertComment(Comment comment);
	
	@Deprecated
	void updateComment(Comment comment);
	
	void deleteComment(int id);
}
