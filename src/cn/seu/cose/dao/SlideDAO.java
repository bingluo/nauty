package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.SlidePojo;

public interface SlideDAO {
	List<SlidePojo> getSlides();

	void insertSlide(SlidePojo slide);

	void updateSlide(SlidePojo slide);

	void deleteSlide(int id);

	SlidePojo getSlideById(int id);
}
