package cn.seu.cose.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;
import com.ibatis.sqlmap.client.SqlMapClient;
import cn.seu.cose.entity.Comment;

@Component
public class CommentDAOImpl extends SqlMapClientDaoSupport implements CommentDAO{

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Comment getCommentViaId(int id) {
		return (Comment) getSqlMapClientTemplate().queryForObject(
				"COMMENT.selectCommentyById", id);
	}

	@Override
	public void insertComment(Comment comment) {
		getSqlMapClientTemplate().insert("COMMENT.insertComment", comment);
	}

	@Override
	public void updateComment(Comment comment) {
		getSqlMapClientTemplate().update("COMMENT.updateComment", comment);
	}

	@Override
	public void deleteComment(int id) {
		getSqlMapClientTemplate().delete("COMMENT.deleteComment", id);
	}

}
