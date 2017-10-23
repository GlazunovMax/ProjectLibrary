package by.htp.library.service.impl;

import java.util.List;
import by.htp.library.bean.Genre;
import by.htp.library.dao.GenreDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.service.GenreService;
import by.htp.library.service.exception.ServiceException;

public class GenreServiceImpl implements GenreService{


	@Override
	public List<Genre> getAllGenres() throws ServiceException {
			List<Genre> genreList = null;
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			GenreDao genreDao = factory.getGenreDao();
			genreList = genreDao.getAll();
		} catch (DaoException e) {
				throw new ServiceException(e);
		}
		
		if (genreList == null || genreList.isEmpty()) {/// ????
			throw new ServiceException("The search has not given any results");
		}

		return genreList;
	}

	@Override
	public void addGenre(Genre genre) throws ServiceException {
		if (genre.getGenreTitle() == null || genre.getGenreTitle().isEmpty()) {/// ????
			throw new ServiceException("Cannot add genre! ");
		}
		
		DaoFactory factory = DaoFactory.getInstance();
		GenreDao genreDao = factory.getGenreDao();
		try {
			genreDao.add(genre);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public void updateGenre(Genre genre) throws ServiceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeGenre(Genre genre) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	
	


}
