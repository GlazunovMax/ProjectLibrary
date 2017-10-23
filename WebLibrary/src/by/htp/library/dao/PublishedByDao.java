package by.htp.library.dao;

import java.util.List;
import by.htp.library.bean.PublishedBy;
import by.htp.library.dao.exception.DaoException;

public interface PublishedByDao {
		
	List<PublishedBy> getAllPublishedBy() throws DaoException;
	
	void add(PublishedBy publishedBy) throws DaoException;
}
