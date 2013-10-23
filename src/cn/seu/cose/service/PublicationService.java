package cn.seu.cose.service;

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
		}
		return publications;
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
}
