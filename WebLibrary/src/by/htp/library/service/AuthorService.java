package by.htp.library.service;

import java.util.List;

import by.htp.library.bean.Author;
import by.htp.library.service.exception.ServiceException;

public interface AuthorService {
	
	//create
	void addAuthor(Author author) throws ServiceException;
	
	//read
	List<Author> getAllAuthors() throws ServiceException;
	//Author getByIdAuthor(Long id) throws ServiceException;
	
	//Update 
//	void updateAuthor(Author author) throws ServiceException;
	
	//delete
//	void removeAuthor(Author author) throws ServiceException;
}
