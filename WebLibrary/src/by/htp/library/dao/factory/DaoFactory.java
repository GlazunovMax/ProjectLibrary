package by.htp.library.dao.factory;

import by.htp.library.dao.AuthorDao;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.GenreDao;
import by.htp.library.dao.PublishedByDao;
import by.htp.library.dao.UserDao;
import by.htp.library.dao.impl.DatabaseAuthorDao;
import by.htp.library.dao.impl.DatabaseBookDao;
import by.htp.library.dao.impl.DatabaseGenreDao;
import by.htp.library.dao.impl.DatabasePublishedByDao;
import by.htp.library.dao.impl.DatabaseUserDao;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0 
 *
 */
public class DaoFactory {
	/** Creates a reference to the DaoFactory object class */
	private static final DaoFactory instance = new DaoFactory();
	
	/** Creates a reference to the DatabaseAuthorDao object class */
	private final AuthorDao databaseAuthorImpl = new DatabaseAuthorDao();
	
	/** Creates a reference to the DatabaseBookDao object class */
	private final BookDao databaseBookImpl = new DatabaseBookDao();

	/** Creates a reference to the DatabaseGenreDao object class */
	private final GenreDao databaseGenreImpl = new DatabaseGenreDao();
	
	/** Creates a reference to the DatabasePublishedByDao object class */
	private final PublishedByDao databasePublishedByImpl = new DatabasePublishedByDao();
	
	/** Creates a reference to the DatabaseUserDao object class */
	private final UserDao databaseUserImpl = new DatabaseUserDao();
	
	/** Creates a new object */
	private DaoFactory(){}
	
	
	/**
	 * Method to get a reference to the DaoFactory object class {@link DaoFactory#instance} 
	 * @return a reference to the DaoFactory object class 
	 */
	public static DaoFactory getInstance() {
		return instance;
	}
	
	
	/**
	 * Method to get a reference to the DatabaseAuthorDao object class {@link DaoFactory#databaseAuthorImpl} 
	 * @return a reference to the DatabaseAuthorDao object class
	 */
	public AuthorDao getAuthorDao(){
		return databaseAuthorImpl;
	}
	
	
	/**
	 * Method to get a reference to the DatabaseBookDao object class {@link DaoFactory#databaseBookImpl} 
	 * @return a reference to the DatabaseBookDao object class
	 */
	public BookDao getBookDao(){
		return databaseBookImpl;
	}
	
	
	/**
	 * Method to get a reference to the DatabaseGenreDao object class {@link DaoFactory#databaseGenreImpl} 
	 * @return a reference to the DatabaseGenreDao object class
	 */
	public GenreDao getGenreDao(){
		return databaseGenreImpl;
	}
	
	
	/**
	 * Method to get a reference to the DatabasePublishedByDao object class {@link DaoFactory#databasePublishedByImpl} 
	 * @return a reference to the DatabasePublishedByDao object class
	 */
	public PublishedByDao getPublishedByDao(){
		return databasePublishedByImpl;
	}
	
	
	/**
	 * Method to get a reference to the DatabaseUserDao object class {@link DaoFactory#databaseUserImpl} 
	 * @return a reference to the DatabaseUserDao object class
	 */
	public UserDao getUserDao(){
		return databaseUserImpl;
	}
}
