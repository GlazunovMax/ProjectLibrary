package by.htp.library.service.impl;

import java.util.List;

import by.htp.library.bean.Author;
import by.htp.library.dao.AuthorDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.service.AuthorService;
import by.htp.library.service.exception.ServiceException;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class AuthorServiceImpl implements AuthorService{
	
	private static final String MESSAGE_ERROR_ADD_AUTHOR = "Can`t add author";
	private static final String MESSAGE_ERROR_GET_ALL_AUTHOR = "Can`t get all author";
	private static final String MESSAGE_ERROR_EMPTY_AUTHOR = "The search has not given any results";

	/**Add the author to the database 
	 * 
	 * @param author - Author`s name
	 * @throws ServiceException exception if you cannot add author
	 */
	@Override
	public void addAuthor(Author author) throws ServiceException {
		
		if (author.getAuthorName() == null || author.getAuthorName().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_ADD_AUTHOR);
		}
		
		DaoFactory factory = DaoFactory.getInstance();
		AuthorDao authorDao = factory.getAuthorDao();
		try {
			authorDao.add(author);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_ADD_AUTHOR ,e);
		}
	}

	
	/** Get all authors from the database
	 * 
	 * @return  List of all authors 
	 * @throws ServiceException exception if you cannot get all the authors
	 */
	@Override
	public List<Author> getAllAuthors() throws ServiceException {
		List<Author> authorList = null;
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			AuthorDao authorDao = factory.getAuthorDao();
			authorList = authorDao.getAllAuthor();
		} catch (DaoException e) {
				throw new ServiceException(MESSAGE_ERROR_GET_ALL_AUTHOR ,e);
		}
		
		if (authorList == null || authorList.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_EMPTY_AUTHOR);
		}
			
		return authorList;
	}

	
	
	
	

}


