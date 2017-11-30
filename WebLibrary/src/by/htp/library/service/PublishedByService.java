package by.htp.library.service;

import java.util.List;

import by.htp.library.bean.PublishedBy;
import by.htp.library.service.exception.ServiceException;

/**
 * 
 *@author Glazunov Maxim
 *@version 1.0
 *
 */
public interface PublishedByService {
	
	/** Add the publishing house to the database 
	 * 
	 * @param publishedBy
	 * @throws ServiceException if you cannot add publishing house
	 */
	void addPublishedBy(PublishedBy publishedBy) throws ServiceException;
	
	
	/** Get all publishing houses from the database
	 * 
	 * @return List of all publishing houses
	 * @throws ServiceException if you cannot get all the publishing houses
	 */
	List<PublishedBy> getAllPublishedBy() throws ServiceException;

}
