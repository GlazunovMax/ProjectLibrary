package by.htp.library.service;

import java.util.List;
import by.htp.library.bean.Book;
import by.htp.library.service.exception.ServiceException;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public interface BookService {
	
	/** Search by the title of the book
	 * 
	 * @param bookTitle
	 * @param start - Starting position of output books on the page
	 * @param countRows - number of books per page
	 * @return list of books on search
	 * @throws ServiceException exception if you cannot find a book by title
	 */
	List<Book> getByTitle(String bookTitle, int start, int countRows) throws ServiceException;
	
	
	/** Search by the author of the book
	 * 
	 * @param authorName
	 * @param start - Starting position of output books on the page
	 * @param countRows - number of books per page
	 * @return list of books on search
	 * @throws ServiceException exception if you cannot find a book by author
	 */
	List<Book> getByAuthor(String authorName, int start, int countRows) throws ServiceException;
	
	/** Search by the genre of the book
	 * 
	 * @param genreName
	 * @param start - Starting position of output books on the page
	 * @param countRows - number of books per page
	 * @return list of books on search
	 * @throws ServiceException exception if you cannot find a book by author
	 */
	List<Book> getByGenre(String genreName, int start, int countRows) throws ServiceException;

	
	/** Updating a book 
	 * 
	 * @param book
	 * @throws ServiceException if you cannot update a book
	 */
	void updateBook(Book book) throws ServiceException;
	
	
	/** Deleting a book by id
	 * 
	 * @param id
	 * @return a deleted book
	 * @throws ServiceException if you cannot delete a book by id
	 */
	Book remove(long id) throws ServiceException;
	
	
	/** Show changed book
	 * 
	 * @param id
	 * @return a changed book
	 * @throws ServiceException if you cannot get a update book by id
	 */
	Book showUpdateBook(Long id) throws ServiceException;
	
	
	/** Adding a book
	 * 
	 * @param book
	 * @throws ServiceException if you cannot add a book
	 */
	void addBook(Book book) throws ServiceException;
	
	
	/** Get all the books from the database
	 * 
	 * @param start - Starting position of output books on the page
	 * @param countRows - number of books per page
	 * @return List of all books
	 * @throws ServiceException if you cannot get all the books
	 */
	List<Book> getAll(int start, int countRows) throws ServiceException;
	
}
