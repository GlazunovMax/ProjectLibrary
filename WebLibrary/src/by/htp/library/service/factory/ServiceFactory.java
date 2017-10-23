package by.htp.library.service.factory;

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

public class ServiceFactory {
	private final static ServiceFactory instance = new ServiceFactory();

	private UserService userService = new UserServiceImpl();
	private AuthorService authorService = new AuthorServiceImpl(); 
	private GenreService genreService = new GenreServiceImpl(); 
	private PublishedByService publishedByService = new PublishedByServiceImpl();
	private BookService bookService = new BookServiceImpl();

	public UserService getUserService() {
		return userService;
	}
	
	public AuthorService getAuthorService() {
		return authorService;
	}
	
	public GenreService getGenreService() {
		return genreService;
	}
	
	public PublishedByService getPublishedByService() {
		return publishedByService;
	}
	
	public BookService getBookService() {
		return bookService;
	}
	

	public static ServiceFactory getInstance() {
		return instance;
	}
}
