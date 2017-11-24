package by.htp.library.service.impl;

import java.util.List;
import by.htp.library.bean.Book;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;

public class BookServiceImpl implements BookService{
	
	private static final String TITLE = "title";
	private static final String AUTHOR = "author";
	private static final String GENRE = "genre";
	private static final String MESSAGE_ERROR_GET_BY = "Can`t get book by ";
	private static final String MESSAGE_ERROR_VALID = "Incorect book by";
	private static final String MESSAGE_ERROR_VALID_TITLE = "Incorect title book";
	private static final String MESSAGE_ERROR_VALID_YEAR = "Incorect year publication";
	private static final String MESSAGE_ERROR_UPDATE_BOOK = "Can`t update book";
	private static final String MESSAGE_ERROR_ID = "Incorect id";
	private static final String MESSAGE_ERROR_REMOVE_BOOK = "Can`t remove book";
	private static final String MESSAGE_ERROR_SHOW_UPDATE_BOOK = "Can`t show update book";
	private static final String MESSAGE_ERROR_ADD_BOOK = "Can`t add book";
	private static final String MESSAGE_ERROR_COUNT_ROWS = "Incorect countRows";
	private static final String MESSAGE_ERROR_GET_ALL_BOOK = "Can`t get all book";
	private static final String MESSAGE_ERROR_EMPTY_BOOK = "List book is empty";
	
	
	@Override
	public List<Book> getByTitle(String bookTitle, int start, int countRows) throws ServiceException {
		
		List<Book> bookList = null;
		valid(bookTitle, TITLE);
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookList = bookDao.getByTitle(bookTitle, start, countRows);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_BY + TITLE, e);
		}
		
		return bookList;
	}

	@Override
	public List<Book> getByAuthor(String authorName, int start, int countRows) throws ServiceException {
		
		List<Book> bookList = null;
		valid(authorName, AUTHOR);
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookList = bookDao.getByAuthor(authorName, start, countRows);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_BY + AUTHOR, e);
		}
		
		return bookList;
	}

	@Override
	public List<Book> getByGenre(String genreName, int start, int countRows) throws ServiceException {
		
		List<Book> bookList = null;
		
		valid(genreName, GENRE);
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookList = bookDao.getByGenre(genreName, start, countRows);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_BY + GENRE, e);
		}
		
		return bookList;
	}

	public void valid(String param, String by) throws ServiceException{
		if (param == null || param.isEmpty()) {///  pегуляными выажениями
			throw new ServiceException(MESSAGE_ERROR_VALID + by);
		}
	}

	@Override
	public void updateBook(Book book) throws ServiceException {
		
		if (book.getBookTitle() == null || book.getBookTitle().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_VALID_TITLE);
		}
		
		if (book.getPublicationYear() == 0 || book.getPublicationYear() < 0) {
			throw new ServiceException(MESSAGE_ERROR_VALID_YEAR);
		}
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookDao.update(book);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_UPDATE_BOOK ,e);
		}
	}

	@Override
	public Book remove(long id) throws ServiceException {
		Book book = null;
		if (id == 0 || id < 0) {
			throw new ServiceException(MESSAGE_ERROR_ID);
		}
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			book = bookDao.remove(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_REMOVE_BOOK ,e);
		}
		return book;
		
	}

	@Override
	public Book showUpdateBook(Long id) throws ServiceException {
		Book book = null;
		
		if (id == 0 || id < 0) {
			throw new ServiceException(MESSAGE_ERROR_ID);
		}
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			book = bookDao.showUpdate(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_SHOW_UPDATE_BOOK ,e);
		}
		
		return book;
	}

	@Override
	public void addBook(Book book ) throws ServiceException {
		
		if (book.getBookTitle() == null || book.getBookTitle().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_VALID_TITLE);
		}
		
		if (book.getPublicationYear() == 0 || book.getPublicationYear() < 0) {
			throw new ServiceException(MESSAGE_ERROR_VALID_YEAR);
		}
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookDao.add(book);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_ADD_BOOK ,e);
		}
		
		
	}

	@Override
	public List<Book> getAll(int start, int countRows) throws ServiceException {
		List<Book> bookList = null;
		
//		if (start == 0 || start < 0 ) {
//			throw new ServiceException("Incorect start");
//		}
		
		if (countRows == 0 || countRows < 0 ) {
			throw new ServiceException(MESSAGE_ERROR_COUNT_ROWS);
		}
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookList = bookDao.getByAll(start, countRows);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_ALL_BOOK ,e);
		}
		
		if (bookList == null || bookList.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_EMPTY_BOOK);
		}
		return bookList;
	}


	
}
