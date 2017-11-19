package by.htp.library.service;

import java.io.InputStream;
import java.util.List;
import by.htp.library.bean.Book;
import by.htp.library.service.exception.ServiceException;

public interface BookService {
	
	List<Book> getByTitle(String bookTitle, int start, int countRows) throws ServiceException;
	
	List<Book> getByAuthor(String authorName, int start, int countRows) throws ServiceException;
	
	List<Book> getByGenre(String genreName, int start, int countRows) throws ServiceException;

	void updateBook(Book book) throws ServiceException;
	
	Book remove(long id) throws ServiceException;
	
	Book showUpdateBook(Long id) throws ServiceException;
	
	void addBook(Book book) throws ServiceException;
	
	List<Book> getAll(int start, int countRows) throws ServiceException;
	
	//int getGenreCount(String genre) throws ServiceException;
}
