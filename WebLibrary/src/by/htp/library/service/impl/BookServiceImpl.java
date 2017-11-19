package by.htp.library.service.impl;

import java.io.InputStream;
import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;

public class BookServiceImpl implements BookService{

	@Override
	public List<Book> getByTitle(String bookTitle, int start, int countRows) throws ServiceException {
		
		List<Book> bookList = null;
		valid(bookTitle, "title");
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookList = bookDao.getByTitle(bookTitle, start, countRows);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
		return bookList;
	}

	@Override
	public List<Book> getByAuthor(String authorName, int start, int countRows) throws ServiceException {
		
		List<Book> bookList = null;
		valid(authorName, "author");
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookList = bookDao.getByAuthor(authorName, start, countRows);
		} catch (DaoException e) {
			throw new ServiceException(" рррр", e);
		}
		
		return bookList;
	}

	@Override
	public List<Book> getByGenre(String genreName, int start, int countRows) throws ServiceException {
		
		List<Book> bookList = null;
		
		valid(genreName, "genre");
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookList = bookDao.getByGenre(genreName, start, countRows);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
		return bookList;
	}

	public void valid(String param, String by) throws ServiceException{
		if (param == null || param.isEmpty()) {///  pегуляными выажениями
			throw new ServiceException("Incorect book by " + by);
		}
	}

	@Override
	public void updateBook(Book book) throws ServiceException {
		
		if (book.getBookTitle() == null || book.getBookTitle().isEmpty()) {
			throw new ServiceException("Incorect title book");
		}
		
		if (book.getPublicationYear() == 0 || book.getPublicationYear() < 0) {
			throw new ServiceException("Incorect year publication");
		}
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookDao.update(book);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Book remove(long id) throws ServiceException {
		Book book = null;
		if (id == 0 || id < 0) {
			throw new ServiceException("Incorect id");
		}
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			book = bookDao.remove(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return book;
		
	}

	@Override
	public Book showUpdateBook(Long id) throws ServiceException {
		Book book = null;
		
		if (id == 0 || id < 0) {
			throw new ServiceException("Incorect id");
		}
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			book = bookDao.showUpdate(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
		return book;
	}

	@Override
	public void addBook(Book book ) throws ServiceException {
		
		if (book.getBookTitle() == null || book.getBookTitle().isEmpty()) {
			throw new ServiceException("Incorect title book");
		}
		
		if (book.getPublicationYear() == 0 || book.getPublicationYear() < 0) {
			throw new ServiceException("Incorect year publication");
		}
		
		
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookDao.add(book);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
		
	}

	@Override
	public List<Book> getAll(int start, int countRows) throws ServiceException {
		List<Book> bookList = null;
		
//		if (start == 0 || start < 0 ) {
//			throw new ServiceException("Incorect start");
//		}
		
		if (countRows == 0 || countRows < 0 ) {
			throw new ServiceException("Incorect countRows");
		}
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			BookDao bookDao = factory.getBookDao();
			bookList = bookDao.getByAll(start, countRows);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
		if (bookList == null || bookList.isEmpty()) {
			throw new ServiceException("List book is empty");
		}
		
		
		return bookList;
	}


	
}
