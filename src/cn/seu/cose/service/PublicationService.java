package cn.seu.cose.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.PublicationDAO;
import cn.seu.cose.entity.PublicationPojo;

@Service
public class PublicationService {

	@Autowired
	PublicationDAO publicationDAOImpl;

	public List<PublicationPojo> getAllPublications() {
		List<PublicationPojo> publications = publicationDAOImpl
				.getAllPublications();
		for (PublicationPojo publication : publications) {
			resolveImgs(publication);
			setBrief(publication);
		}
		return publications;
	}
	
	public int getPubCount() {
		return publicationDAOImpl.getpubCount();
	}

	@Cacheable(value = "publicationCache", key = "'getPublicationById:'+#id")
	public PublicationPojo getPublicationById(int id) {
		PublicationPojo publication = publicationDAOImpl.getPublicationById(id);
		resolveImgs(publication);
		return publication;
	}

	@Cacheable(value = "publicationCache", key = "'getRecentPublications'")
	public List<PublicationPojo> getRecentPublications() {
		List<PublicationPojo> publications = publicationDAOImpl
				.getRecentPublications();
		for (PublicationPojo publication : publications) {
			resolveImgs(publication);
			setBrief(publication);
		}
		return publications;
	}

	@Cacheable(value = "publicationCache", key = "'getPublicationByIndexAndPageSize:' + #index + ',' + #pageSize")
	public List<PublicationPojo> getPublicationByIndexAndPageSize(int index,
			int pageSize) {
		List<PublicationPojo> publications = publicationDAOImpl
				.getPublicationsByBaseAndRange((index - 1) * pageSize, pageSize);
		for (PublicationPojo publication : publications) {
			resolveImgs(publication);
			setBrief(publication);
		}

		return publications;
	}

	@CacheEvict(value = "publicationCache", allEntries = true)
	public void addPublication(PublicationPojo publication) {
		publicationDAOImpl.insertPublication(publication);
	}

	@CacheEvict(value = "publicationCache", allEntries = true)
	public void updatePublication(PublicationPojo publication) {
		publicationDAOImpl.updatePublication(publication);
	}

	@CacheEvict(value = "publicationCache", allEntries = true)
	public void deletePublication(int id) {
		publicationDAOImpl.deletePublication(id);
	}

	@CacheEvict(value = "publicationCache", allEntries = true)
	public void clickUp(int id) {
		publicationDAOImpl.addClickCounts(id);
	}

	/**
	 * 解析图片，并设置封面
	 * 
	 * @param publication
	 */
	private void resolveImgs(PublicationPojo publication) {
		if (publication == null) {
			return;
		}
		publication.setImgUrls(publication.getImages().split(","));
		if (publication.getImgUrls().length > 0) {
			publication.setCoverUrl(publication.getImgUrls()[0]);
		}
	}

	/**
	 * 设置publication的简介brief
	 * 
	 * @param publication
	 */
	private void setBrief(PublicationPojo publication) {
		int endIndex = 40;
		if (publication.getIntro().length() < endIndex) {
			endIndex = publication.getIntro().length();
		}
		String brief = endIndex < publication.getIntro().length() ? publication
				.getIntro().substring(0, endIndex) + "..." : publication
				.getIntro();
		publication.setBrief(brief);
	}

	/**
	 * 为publication分类（时间、热门）
	 * 
	 * @param publications
	 * @return
	 */
	public List<String> classify(List<PublicationPojo> publications) {
		ArrayList<PublicationPojo> sortList = new ArrayList<PublicationPojo>();
		for (PublicationPojo publication : publications) {
			sortList.add(publication);
		}

		ArrayList<String> years = new ArrayList<String>();
		for (PublicationPojo publication : publications) {
			int year = publication.getPostTime().getYear() + 1900;
			publication.setYear(year + "");
			if (!years.contains(year + "")) {
				years.add(year + "");
			}
		}
		Collections.sort(sortList, new PublicationComparator());
		for (int i = 0; i < 4; i++) {
			PublicationPojo e = sortList.get(i);
			if (e != null) {
				e.setHot(true);
			}
		}
		return years;
	}

	private class PublicationComparator implements Comparator<PublicationPojo> {
		@Override
		public int compare(PublicationPojo o1, PublicationPojo o2) {
			if (o1.getClickCounts() < o2.getClickCounts()) {
				return -1;
			} else if (o1.getClickCounts() == o2.getClickCounts()) {
				return 0;
			} else {
				return 1;
			}
		}
	}
}
