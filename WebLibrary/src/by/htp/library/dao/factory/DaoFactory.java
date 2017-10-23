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

public class DaoFactory {
	private static final DaoFactory instance = new DaoFactory();
	
	private final AuthorDao databaseAuthorImpl = new DatabaseAuthorDao();
	private final BookDao databaseBookImpl = new DatabaseBookDao();
	private final GenreDao databaseGenreImpl = new DatabaseGenreDao();
	private final PublishedByDao databasePublishedByImpl = new DatabasePublishedByDao();
	private final UserDao databaseUserImpl = new DatabaseUserDao();
	
	
	private DaoFactory(){}
	
	public static DaoFactory getInstance() {
		return instance;
	}
	
	public AuthorDao getAuthorDao(){
		return databaseAuthorImpl;
	}
	
	public BookDao getBookDao(){
		return databaseBookImpl;
	}
	
	public GenreDao getGenreDao(){
		return databaseGenreImpl;
	}
	
	public PublishedByDao getPublishedByDao(){
		return databasePublishedByImpl;
	}
	
	public UserDao getUserDao(){
		return databaseUserImpl;
	}
}
