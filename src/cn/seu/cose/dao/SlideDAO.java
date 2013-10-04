package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Slide;

interface SlideDAO {
	List<Slide> getSlides();

	void insertSlide(Slide slide);

	void updateSlide(Slide slide);

	void deleteSlide(int id);
}
