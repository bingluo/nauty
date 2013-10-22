package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.PublicationDAOImpl;
import cn.seu.cose.entity.PublicationPojo;

@Service
public class PublicationService {

	@Autowired
	PublicationDAOImpl publicationDAOImpl;

	public PublicationPojo getPublicationById(int id) {
		PublicationPojo publication = publicationDAOImpl.getPublicationById(id);
		publication.setImgUrls(publication.getImages().split(","));
		return publication;
	}

	public List<PublicationPojo> getRecentPublications() {
		List<PublicationPojo> publications = publicationDAOImpl
				.getRecentPublications();
		for (PublicationPojo publication : publications) {
			publication.setImgUrls(publication.getImages().split(","));
		}
		return publications;
	}

	public List<PublicationPojo> getPublicationByIndexAndPageSize(int index,
			int pageSize) {
		List<PublicationPojo> publications = publicationDAOImpl
				.getPublicationsByBaseAndRange((index - 1) * pageSize, pageSize);
		for (PublicationPojo publication : publications) {
			publication.setImgUrls(publication.getImages().split(","));
		}
		return publications;
	}

	public void addPublication(PublicationPojo publication) {
		publicationDAOImpl.insertPublication(publication);
	}

	public void updatePublication(PublicationPojo publication) {
		publicationDAOImpl.updatePublication(publication);
	}

	public void deletePublication(int id) {
		publicationDAOImpl.deletePublication(id);
	}
}
