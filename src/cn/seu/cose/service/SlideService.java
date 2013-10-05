package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.SlideDAOImpl;
import cn.seu.cose.entity.Slide;

@Service
public class SlideService {

	@Autowired
	SlideDAOImpl slideDAOImpl;

	public List<Slide> getSlides() {
		return slideDAOImpl.getSlides();
	}

	public void addSlide(Slide slide) {
		slideDAOImpl.insertSlide(slide);
	}

	public void updateSlide(Slide slide) {
		slideDAOImpl.updateSlide(slide);
	}

	public void deleteSlide(int id) {
		slideDAOImpl.deleteSlide(id);
	}
}
