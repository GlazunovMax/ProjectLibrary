package by.htp.library.service.factory;

import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.service.AuthorService;
import by.htp.library.service.BookService;
import by.htp.library.service.GenreService;
import by.htp.library.service.PublishedByService;
import by.htp.library.service.UserService;
import by.htp.library.service.impl.AuthorServiceImpl;
import by.htp.library.service.impl.BookServiceImpl;
import by.htp.library.service.impl.GenreServiceImpl;
import by.htp.library.service.impl.PublishedByServiceImpl;
import by.htp.library.service.impl.UserServiceImpl;

/**
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class ServiceFactory {
	/** Creates a reference to the ServiceFactory object class */
	private final static ServiceFactory instance = new ServiceFactory();

	/** Creates a reference to the UserServiceImpl object class */
	private UserService userService = new UserServiceImpl();
	
	/** Creates a reference to the AuthorServiceImpl object class */
	private AuthorService authorService = new AuthorServiceImpl(); 
	
	
	/** Creates a reference to the GenreServiceImpl object class */
	private GenreService genreService = new GenreServiceImpl(); 
	
	/** Creates a reference to the PublishedByServiceImpl object class */
	private PublishedByService publishedByService = new PublishedByServiceImpl();
	
	/** Creates a reference to the BookServiceImpl object class */
	private BookService bookService = new BookServiceImpl();

	/** Creates a new object */
	private ServiceFactory(){}
	
	
	/**
	 * Method to get a reference to the UserServiceImpl object class {@link ServiceFactory#userService} 
	 * @return a reference to the UserServiceImpl object class
	 */
	public UserService getUserService() {
		return userService;
	}
	
	
	/**
	 * Method to get a reference to the AuthorServiceImpl object class {@link ServiceFactory#authorService} 
	 * @return a reference to the AuthorServiceImpl object class
	 */
	public AuthorService getAuthorService() {
		return authorService;
	}
	
	
	/**
	 * Method to get a reference to the GenreServiceImpl object class {@link ServiceFactory#genreService} 
	 * @return a reference to the GenreServiceImpl object class
	 */
	public GenreService getGenreService() {
		return genreService;
	}
	
	
	/**
	 * Method to get a reference to the PublishedByServiceImpl object class {@link ServiceFactory#publishedByService} 
	 * @return a reference to the PublishedByServiceImpl object class
	 */
	public PublishedByService getPublishedByService() {
		return publishedByService;
	}
	
	
	/**
	 * Method to get a reference to the BookServiceImpl object class {@link DaoFactory#bookService} 
	 * @return a reference to the BookServiceImpl object class
	 */
	public BookService getBookService() {
		return bookService;
	}
	
	
	/**
	 * Method to get a reference to the ServiceFactory object class {@link ServiceFactory#instance} 
	 * @return a reference to the ServiceFactory object class 
	 */
	public static ServiceFactory getInstance() {
		return instance;
	}
}
