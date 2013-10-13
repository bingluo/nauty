package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.SlideDAOImpl;
import cn.seu.cose.entity.SlidePojo;
import cn.seu.cose.util.LinkTool;

@Service
public class SlideService {

	@Autowired
	SlideDAOImpl slideDAOImpl;

	public List<SlidePojo> getSlides() {
		List<SlidePojo> slides = slideDAOImpl.getSlides();
		for (SlidePojo slidePojo : slides) {
			slidePojo.setArticleUri(LinkTool.article(slidePojo.getArticleId()));
			slidePojo.setImgUri(LinkTool.image(slidePojo.getPicName()));
		}
		return slides;
	}
	public SlidePojo getSlideById(int id) {
		return slideDAOImpl.getSlideById(id);
	}
	
	
	public void addSlide(SlidePojo slide) {
		slideDAOImpl.insertSlide(slide);
	}

	public void updateSlide(SlidePojo slide) {
		slideDAOImpl.updateSlide(slide);
	}

	public void deleteSlide(int id) {
		slideDAOImpl.deleteSlide(id);
	}
}
