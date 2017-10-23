package by.htp.library.service.impl;

import java.util.List;

import by.htp.library.bean.Author;
import by.htp.library.dao.AuthorDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.service.AuthorService;
import by.htp.library.service.exception.ServiceException;

public class AuthorServiceImpl implements AuthorService{

	@Override
	public void addAuthor(Author author) throws ServiceException {
		// TODO Auto-generated method stub
//		validation
		if (author.getAuthorName() == null || author.getAuthorName().isEmpty()) {/// ????
			throw new ServiceException("Cannot add author! ");
		}
		
		DaoFactory factory = DaoFactory.getInstance();
		AuthorDao authorDao = factory.getAuthorDao();
		try {
			authorDao.add(author);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public void updateAuthor(Author author) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAuthor(Author author) throws ServiceException {
		// TODO Auto-generated method stub
		
	}


	
	@Override
	public List<Author> getAllAuthors() throws ServiceException {
		List<Author> authorList = null;
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			AuthorDao authorDao = factory.getAuthorDao();
			authorList = authorDao.getAllBook();
		} catch (DaoException e) {
				throw new ServiceException(e);
		}
		
		if (authorList == null || authorList.isEmpty()) {/// ????
			throw new ServiceException("The search has not given any results");
		}
			
		return authorList;
	}

	
	
	
	

}


