package cn.seu.cose.dao;

import cn.seu.cose.entity.Comment;

public interface CommentDAO {

	Comment getCommentViaId(int id);
	
	void insertComment(Comment comment);
	
	void updateComment(Comment comment);
	
	void deleteComment(int id);
}
