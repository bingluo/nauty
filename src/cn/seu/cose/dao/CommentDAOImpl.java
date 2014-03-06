package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentViaRef(int refId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("referenceId", refId);
		return (List<Comment>) getSqlMapClientTemplate().queryForList(
				"COMMENT.selectCommentByRef", map);
	}

	@Override
	public void insertComment(Comment comment) {
		getSqlMapClientTemplate().insert("COMMENT.insertComment", comment);
	}

	@Override
	@Deprecated
	public void updateComment(Comment comment) {
		getSqlMapClientTemplate().update("COMMENT.updateComment", comment);
	}

	@Override
	public void deleteComment(int id) {
		getSqlMapClientTemplate().delete("COMMENT.deleteComment", id);
	}

}
