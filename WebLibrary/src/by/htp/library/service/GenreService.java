package by.htp.library.service;

import java.util.List;

import by.htp.library.bean.Genre;
import by.htp.library.service.exception.ServiceException;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public interface GenreService {
	
	/** Add the genre to the database 
	 * 
	 * @param genre
	 * @throws ServiceException if you cannot add genre
	 */
	void addGenre(Genre genre) throws ServiceException;
	
	/** Get all genres from the database
	 * 
	 * @return List of all genres
	 * @throws ServiceException  if you cannot get all the genres
	 */
	List<Genre> getAllGenres() throws ServiceException;

}
