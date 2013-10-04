package cn.seu.cose.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.Slide;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class SlideDAOImpl extends SqlMapClientDaoSupport implements SlideDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Slide> getSlides() {
		return getSqlMapClientTemplate().queryForList("SLIDE.selectSlide");
	}

	@Override
	public void insertSlide(Slide slide) {
		getSqlMapClientTemplate().insert("SLIDE.insertSlide", slide);
	}

	@Override
	public void updateSlide(Slide slide) {
		getSqlMapClientTemplate().update("SLIDE.updateSlide", slide);
	}

	@Override
	public void deleteSlide(int id) {
		getSqlMapClientTemplate().delete("SLIDE.deleteSlideById", id);
	}
}
