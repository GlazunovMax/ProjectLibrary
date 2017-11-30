package by.htp.library.dao;

import java.util.List;
import by.htp.library.bean.Book;
import by.htp.library.dao.exception.DaoException;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0 
 *
 */
public interface BookDao{
		
		/** Search by the title of the book
		 * 
		 * @param bookTitle
		 * @param start - Starting position of output books on the page
		 * @param countRows - number of books per page
		 * @return list of books on search
		 * @throws DaoException exception if you cannot find a book by title
		 */
		List<Book> getByTitle(String bookTitle, int start, int countRows)throws DaoException;
		
		
		/** Search by the author of the book
		 * 
		 * @param authorName
		 * @param start - Starting position of output books on the page
		 * @param countRows - number of books per page
		 * @return list of books on search
		 * @throws DaoException exception if you cannot find a book by author
		 */
		List<Book> getByAuthor(String authorName, int start, int countRows)throws DaoException;
		
		
		/** Search by the genre of the book
		 * 
		 * @param genreName
		 * @param start - Starting position of output books on the page
		 * @param countRows - number of books per page
		 * @return list of books on search
		 * @throws DaoException exception if you cannot find a book by author
		 */
		List<Book> getByGenre(String genreName, int start, int countRows)throws DaoException;
		
		
		/** Deleting a book by id
		 * 
		 * @param id
		 * @return a deleted book
		 * @throws DaoException if you cannot delete a book by id
		 */
		Book remove(long id) throws DaoException;
		
		
		/** Show changed book
		 * 
		 * @param id
		 * @return a changed book
		 * @throws DaoException if you cannot get a update book by id
		 */
		Book showUpdate(Long id) throws DaoException;
		
		
		/** Updating a book 
		 * 
		 * @param book
		 * @throws DaoException if you cannot update a book
		 */
		void update(Book book) throws DaoException;
		
		
		/** Adding a book
		 * 
		 * @param book
		 * @throws DaoException if you cannot add a book
		 */
		void add(Book book) throws DaoException;
		
		
		/** Get all the books from the database
		 * 
		 * @param start - Starting position of output books on the page
		 * @param countRows - number of books per page
		 * @return List of all books
		 * @throws DaoException if you cannot get all the books
		 */
		List<Book> getByAll(int start, int countRows) throws DaoException;
		
}
