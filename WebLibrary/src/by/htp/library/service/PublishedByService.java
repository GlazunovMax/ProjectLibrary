package by.htp.library.service;

import java.util.List;

import by.htp.library.bean.PublishedBy;
import by.htp.library.service.exception.ServiceException;

public interface PublishedByService {
	
	void addPublishedBy(PublishedBy publishedBy) throws ServiceException;
	
	List<PublishedBy> getAllPublishedBy() throws ServiceException;
	
//	void updatePublishedBy(PublishedBy publishedBy) throws ServiceException;
//	
//	void removePublishedBy(PublishedBy publishedBy) throws ServiceException;

}
