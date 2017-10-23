package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Author;
import by.htp.library.dao.exception.DaoException;

public interface AuthorDao {
	
	List<Author> getAllBook() throws DaoException;
	//all
	void add(Author author) throws DaoException;
	
	
}
