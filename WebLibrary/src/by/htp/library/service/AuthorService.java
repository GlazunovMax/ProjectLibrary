package by.htp.library.service;

import java.util.List;
import by.htp.library.bean.Author;
import by.htp.library.service.exception.ServiceException;

public interface AuthorService {
	
	/**Add the author to the database 
	 * 
	 * @param author - Author`s name
	 * @throws ServiceException exception if you cannot add author
	 */
	void addAuthor(Author author) throws ServiceException;
	
	
	/** Get all authors from the database
	 * 
	 * @return  List of all authors 
	 * @throws ServiceException exception if you cannot get all the authors
	 */
	List<Author> getAllAuthors() throws ServiceException;
	
}
