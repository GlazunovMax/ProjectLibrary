package by.htp.library.service.impl;

import java.util.List;
import by.htp.library.bean.Genre;
import by.htp.library.dao.GenreDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.service.GenreService;
import by.htp.library.service.exception.ServiceException;

public class GenreServiceImpl implements GenreService{
	private static final String MESSAGE_ERROR_GET_ALL_GENRE = "Can`t get all genre";
	private static final String MESSAGE_ERROR_EMPTY_GENRE = "The search has not given any results";
	private static final String MESSAGE_ERROR_ADD_GENRE = "Can`t add genre";

	@Override
	public List<Genre> getAllGenres() throws ServiceException {
			List<Genre> genreList = null;
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			GenreDao genreDao = factory.getGenreDao();
			genreList = genreDao.getAll();
		} catch (DaoException e) {
				throw new ServiceException(MESSAGE_ERROR_GET_ALL_GENRE, e);
		}
		
		if (genreList == null || genreList.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_EMPTY_GENRE);
		}

		return genreList;
	}

	@Override
	public void addGenre(Genre genre) throws ServiceException {
		if (genre.getGenreTitle() == null || genre.getGenreTitle().isEmpty()) {/// ????
			throw new ServiceException(MESSAGE_ERROR_ADD_GENRE);
		}
		
		DaoFactory factory = DaoFactory.getInstance();
		GenreDao genreDao = factory.getGenreDao();
		try {
			genreDao.add(genre);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_ADD_GENRE, e);
		}	
	}
}
