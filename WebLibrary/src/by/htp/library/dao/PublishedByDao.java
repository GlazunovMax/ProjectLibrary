package by.htp.library.dao;

import java.util.List;
import by.htp.library.bean.PublishedBy;
import by.htp.library.dao.exception.DaoException;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public interface PublishedByDao {
		
	/** Get all publishing houses from the database
	 * 
	 * @return List of all publishing houses
	 * @throws DaoException if you cannot get all the publishing houses
	 */
	List<PublishedBy> getAllPublishedBy() throws DaoException;
	
	
	/** Add the publishing house to the database 
	 * 
	 * @param publishedBy
	 * @throws DaoException if you cannot add publishing house
	 */
	void add(PublishedBy publishedBy) throws DaoException;
}
