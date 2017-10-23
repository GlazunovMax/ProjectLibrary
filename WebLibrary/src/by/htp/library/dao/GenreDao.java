package by.htp.library.dao;

import java.util.List;
import by.htp.library.bean.Genre;
import by.htp.library.dao.exception.DaoException;

public interface GenreDao {
	List<Genre> getAll() throws DaoException;
	
	void add(Genre genre) throws DaoException;
}
