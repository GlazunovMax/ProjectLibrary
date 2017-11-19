package by.htp.library.dao;

import java.io.InputStream;
import java.util.List;
import by.htp.library.bean.Book;
import by.htp.library.dao.exception.DaoException;

public interface BookDao{
		
		List<Book> getByTitle(String bookTitle, int start, int countRows)throws DaoException;
		
		List<Book> getByAuthor(String authorName, int start, int countRows)throws DaoException;
		
		List<Book> getByGenre(String genreName, int start, int countRows)throws DaoException;
		
		//List<Book> getByGenre(String genreName)throws DaoException;
		
		Book remove(long id) throws DaoException;
		
		Book showUpdate(Long id) throws DaoException;
		
		void update(Book book) throws DaoException;
		
		void add(Book book) throws DaoException;
		
		List<Book> getByAll(int start, int countRows) throws DaoException;
		
		//int getGenreCount(String genre) throws DaoException;//?
}
