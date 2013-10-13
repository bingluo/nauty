package cn.seu.cose.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.SlidePojo;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class SlideDAOImpl extends SqlMapClientDaoSupport implements SlideDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<SlidePojo> getSlides() {
		return getSqlMapClientTemplate().queryForList("SLIDE.selectSlide");
	}
	
	@Override 
	public SlidePojo getSlideById(int id) {
		return (SlidePojo)getSqlMapClientTemplate().queryForObject("SLIDE.selectSlideById", id);
	}
	
	@Override
	public void insertSlide(SlidePojo slide) {
		getSqlMapClientTemplate().insert("SLIDE.insertSlide", slide);
	}

	@Override
	public void updateSlide(SlidePojo slide) {
		getSqlMapClientTemplate().update("SLIDE.updateSlide", slide);
	}

	@Override
	public void deleteSlide(int id) {
		getSqlMapClientTemplate().delete("SLIDE.deleteSlideById", id);
	}
}
