package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Comment;

public interface CommentDAO {
	List<Integer> rankCommentCountWithType(int type, int topN);

	Comment getCommentViaId(int id);

	List<Comment> getCommentViaRefAndType(int refId, int type);

	List<Comment> getCommentViaRefAndTypeAndBaseAndRange(int refId, int type,
			int base, int range);

	int getCommentCountViaRefAndType(int refId, int type);

	void insertComment(Comment comment);

	@Deprecated
	void updateComment(Comment comment);

	void deleteComment(int id);
}
