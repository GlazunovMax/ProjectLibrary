package by.htp.library.dao;

import java.util.List;
import by.htp.library.bean.Genre;
import by.htp.library.dao.exception.DaoException;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public interface GenreDao {
	
	/** Get all genres from the database
	 * 
	 * @return List of all genres
	 * @throws DaoException  if you cannot get all the genres
	 */
	List<Genre> getAll() throws DaoException;
	
	/** Add the genre to the database 
	 * 
	 * @param genre
	 * @throws DaoException if you cannot add genre
	 */
	void add(Genre genre) throws DaoException;
}
