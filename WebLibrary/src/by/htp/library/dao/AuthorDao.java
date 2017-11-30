package by.htp.library.dao;

import java.util.List;
import by.htp.library.bean.Author;
import by.htp.library.dao.exception.DaoException;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0 
 * 
 */
public interface AuthorDao {
	
	/** Get all authors from the database
	 * 
	 * @return  List of all authors
	 * @throws DaoException exception if you cannot get all the authors
	 */
	List<Author> getAllAuthor() throws DaoException;
	
	/** Add the author to the database 
	 * 
	 * @param author - Author`s name
	 * @throws DaoException exception if you cannot add author
	 */
	void add(Author author) throws DaoException;
	
	
}
