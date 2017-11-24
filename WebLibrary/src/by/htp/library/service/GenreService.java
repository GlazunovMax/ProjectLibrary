package by.htp.library.service;

import java.util.List;

import by.htp.library.bean.Genre;
import by.htp.library.service.exception.ServiceException;

public interface GenreService {
	
	void addGenre(Genre genre) throws ServiceException;
	
	List<Genre> getAllGenres() throws ServiceException;
	
//	void updateGenre(Genre genre) throws ServiceException;
//	
//	void removeGenre(Genre genre) throws ServiceException;

}
