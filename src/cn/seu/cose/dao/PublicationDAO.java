package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.PublicationPojo;

public interface PublicationDAO {
	List<PublicationPojo> getAllPublications();

	List<PublicationPojo> getAllPublicationsByType(int type);

	PublicationPojo getPublicationById(int id);

	List<PublicationPojo> getRecentPublications();

	List<PublicationPojo> getRecentPublicationsByType(int type);

	List<PublicationPojo> getPublicationsByBaseAndRange(int base, int range);

	void insertPublication(PublicationPojo publication);

	void updatePublication(PublicationPojo publication);

	void deletePublication(int id);

	void addClickCounts(int id);

	int getpubCount();
}
