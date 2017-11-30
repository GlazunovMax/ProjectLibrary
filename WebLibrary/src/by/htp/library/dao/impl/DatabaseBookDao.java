package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import by.htp.library.bean.Book;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dbConnection.ConnectionPool;
import by.htp.library.dbConnection.ConnectionPoolException;
import by.htp.library.dbConnection.FactoryConnectionPool;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class DatabaseBookDao implements BookDao {
	
	private static final Logger log = Logger.getLogger(DatabaseUserDao.class);
	
	
	private static final String SQL_SELECT_TITLE = "SELECT book.id, book.book_title, author.author_name, book.publication_year, published_by.published_by_title, genre.genre_title, book.image, book.content FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where book.book_title like ? AND book.status='Y' limit ?,?";
	private static final String SQL_SELECT_AUTHOR = "SELECT book.id, book.book_title, author.author_name, book.publication_year, published_by.published_by_title, genre.genre_title, book.image, book.content FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where author.author_name like ? AND book.status='Y' limit ?,?";
	private static final String SQL_SELECT_GENRE = "SELECT book.id, book.book_title, author.author_name, book.publication_year, published_by.published_by_title, genre.genre_title, book.image, book.content FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where genre.genre_title like ? AND book.status='Y' limit ?,?";
	private static final String SQL_UPDATE = "UPDATE book SET book_title=?, publication_year=? WHERE id = ?"; 
	private static final String SQL_SELECT_UPDATE = "SELECT book.id, book.book_title, author.author_name, book.publication_year, published_by.published_by_title, genre.genre_title FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where book.id = ?";
	private static final String SQL_DELETE_UPDATE = "UPDATE book SET status='N' WHERE id =?";
	private static final String SQL_SELECT_ALL = "SELECT book.id, book.book_title, author.author_name, book.publication_year, published_by.published_by_title, genre.genre_title, book.image, book.content FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where book.status='Y' limit ?,?";
	
	
	private static final String ID = "id";
	private static final String BOOK_TITLE = "book_title";
	private static final String AUTHOR_NAME= "author_name";
	private static final String PUBLICATION_YEAR = "publication_year";
	private static final String PUBLISHED_BY_TITLE = "published_by_title";
	private static final String GENRE_TITLE = "genre_title";
	private static final String IMAGE = "image";
	private static final String CONTENT = "content";
	
	private static final String TITLE = "title";
	private static final String AUTHOR = "author";
	private static final String GENRE = "genre";
	
	private static final String PERCENT = "%";
	private static final String MESSAGE_ADD_BOOK_EXCEPTION = "Cannot find book by";
	private static final String MESSAGE_ERROR_CONNECTION_POOL = "Error connecting to the connection pool in";
	private static final String LOG_TRACE_RESULT_SET_CLOSE = "resultSet closed";
	private static final String LOG_ERROR_RESULT_SET_CLOSE_EXCEPTION = "Cannot close resultSet";
	private static final String LOG_TRACE_PREPARED_STATEMENT_CLOSE = "preparedStatement closed";
	private static final String LOG_ERROR_PREPARED_STATEMENT_CLOSE_EXCEPTION = "Cannot close preparedStatement";
	
	private static final String MESSAGE_FIND_ALL_BOOK_EXCEPTION = "Cannot find all book ";
	private static final String MESSAGE_ERROR_CONNECTION_POOL_GET_ALL = "Error connecting to the connection pool in getAll";
	
	private static final String MESSAGE_UPDATE_BOOK_EXCEPTION = "Cannot update book by id ";
	private static final String MESSAGE_ERROR_CONNECTION_POOL_UPDATE = "Error connecting to the connection pool in update";
	
	private static final String MESSAGE_SHOW_UPDATE_BOOK_EXCEPTION = "Cannot find book by id ";
	private static final String MESSAGE_ERROR_CONNECTION_POOL_SELECT_UPDATE = "Error connecting to the connection pool in selectUpdate";
	
	private static final String MESSAGE_REMOVE_BOOK_EXCEPTION = "Cannot remove book by id ";
	private static final String MESSAGE_ERROR_CONNECTION_POOL_REMOVE = "Error connecting to the connection pool in remove";
	
	/** Search by the title of the book
	 * 
	 * @param bookTitle
	 * @param start
	 * @param countRows - number of books per page
	 * @return list of books on search
	 * @throws DaoException exception if you cannot find a book by title
	 */
	@Override
	public List<Book> getByTitle(String bookTitle, int start, int countRows) throws DaoException {
		return getListBook(bookTitle, SQL_SELECT_TITLE, TITLE, start, countRows);
	}
	
	
	/** Search by the author of the book
	 * 
	 * @param authorName
	 * @param start
	 * @param countRows - number of books per page
	 * @return list of books on search
	 * @throws DaoException exception if you cannot find a book by author
	 */
	@Override
	public List<Book> getByAuthor(String authorName, int start, int countRows) throws DaoException {
		return getListBook(authorName, SQL_SELECT_AUTHOR, AUTHOR, start, countRows);
	}

	
	/** Search by the genre of the book
	 * 
	 * @param genreName
	 * @param start
	 * @param countRows - number of books per page
	 * @return list of books on search
	 * @throws DaoException exception if you cannot find a book by author
	 */
	@Override
	public List<Book> getByGenre(String genreName, int start, int countRows) throws DaoException {
		return getListBook(genreName, SQL_SELECT_GENRE, GENRE, start, countRows);
	}
	
	
	/**
	 * 
	 * @param str - search criteria
	 * @param SQL - query the database
	 * @param searchBy - search by 
	 * @param start  
	 * @param countRows - number of books per page
	 * @return list of books on search
	 * @throws DaoException  if you cannot find a book by @param searchBy
	 */
	public List<Book> getListBook(String str, String SQL, String searchBy, int start, int countRows) throws DaoException{
		
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		 List<Book> bookList = new ArrayList<Book>();
		 
		try(Connection connection = connectionPool.takeConnection()) {
			
			preparedStatement = connection.prepareStatement(SQL);
			
			
			preparedStatement.setString(1, PERCENT + str + PERCENT);
			preparedStatement.setInt(2, start);
			preparedStatement.setInt(3, countRows);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				Book book = new Book();
				book.setId(resultSet.getLong(ID));
				book.setBookTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthorName(resultSet.getString(AUTHOR_NAME));
				book.setPublicationYear(resultSet.getInt(PUBLICATION_YEAR));
				book.setPublishedById(resultSet.getString(PUBLISHED_BY_TITLE));
				book.setGenreId(resultSet.getString(GENRE_TITLE));
				book.setImage(resultSet.getBytes(IMAGE));
				book.setContent(resultSet.getBytes(CONTENT));
				
				bookList.add(book);
			}
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_ADD_BOOK_EXCEPTION + searchBy, e);
		}catch (ConnectionPoolException e) {
			log.error(MESSAGE_ERROR_CONNECTION_POOL + searchBy, e);
		}finally {
			closeResultSetAndPreparedStatement(resultSet, preparedStatement);
//				try {
//					if (resultSet != null)
//						resultSet.close();
//					log.trace(LOG_TRACE_RESULT_SET_CLOSE);
//				} catch (SQLException e) {
//					log.error(LOG_ERROR_RESULT_SET_CLOSE_EXCEPTION, e);
//				}
//
//				try {
//					if (preparedStatement != null)
//						preparedStatement.close();
//					log.trace(LOG_TRACE_PREPARED_STATEMENT_CLOSE);
//				} catch (SQLException e) {
//					log.error(LOG_ERROR_PREPARED_STATEMENT_CLOSE_EXCEPTION, e);
//				}			
		}	
		return bookList;
	}
	
	
	/** Get all the books from the database
	 * 
	 * @param start
	 * @param countRows - number of books per page
	 * @return List of all books
	 * @throws DaoException if you cannot get all the books
	 */
	@Override
	public List<Book> getByAll(int start, int countRows) throws DaoException {
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		 List<Book> bookList = new ArrayList<Book>();
		 
		 try(Connection connection = connectionPool.takeConnection()) {
				preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
				
				preparedStatement.setInt(1, start);
				preparedStatement.setInt(2, countRows);

				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()){
					Book book = new Book();
					book.setId(resultSet.getLong(ID));
					book.setBookTitle(resultSet.getString(BOOK_TITLE));
					book.setAuthorName(resultSet.getString(AUTHOR_NAME));
					book.setPublicationYear(resultSet.getInt(PUBLICATION_YEAR));
					book.setPublishedById(resultSet.getString(PUBLISHED_BY_TITLE));
					book.setGenreId(resultSet.getString(GENRE_TITLE));
					book.setImage(resultSet.getBytes(IMAGE));
					book.setContent(resultSet.getBytes(CONTENT));
					
					bookList.add(book);	
				}
			} catch (SQLException e) {
				throw new DaoException(MESSAGE_FIND_ALL_BOOK_EXCEPTION, e);
			}catch (ConnectionPoolException e) {
				log.error(MESSAGE_ERROR_CONNECTION_POOL_GET_ALL, e);
			}finally {
				closeResultSetAndPreparedStatement(resultSet, preparedStatement);
//					try {
//						if (resultSet != null)
//							resultSet.close();
//						log.trace(LOG_TRACE_RESULT_SET_CLOSE);
//					} catch (SQLException e) {
//						log.error(LOG_ERROR_RESULT_SET_CLOSE_EXCEPTION, e);
//					}
//
//					try {
//						if (preparedStatement != null)
//							preparedStatement.close();
//						log.trace(LOG_TRACE_PREPARED_STATEMENT_CLOSE);
//					} catch (SQLException e) {
//						log.error(LOG_ERROR_PREPARED_STATEMENT_CLOSE_EXCEPTION, e);
//					}			
			}
			
			return bookList;
	
	}

	
	/** Updating a book 
	 * 
	 * @param book
	 * @throws DaoException if you cannot update a book
	 */
	@Override
	public void update(Book book) throws DaoException {
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		PreparedStatement preparedStatement = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			preparedStatement = connection.prepareStatement(SQL_UPDATE);
			
			preparedStatement.setString(1, book.getBookTitle());
			preparedStatement.setInt(2, book.getPublicationYear());
			preparedStatement.setLong(3, book.getId());
		
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_UPDATE_BOOK_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			log.error(MESSAGE_ERROR_CONNECTION_POOL_UPDATE, e);
		}finally {
			closePreparedStatement(preparedStatement);
//			try {
//				if (preparedStatement != null)
//					preparedStatement.close();
//				log.trace(LOG_TRACE_PREPARED_STATEMENT_CLOSE);
//			} catch (SQLException e) {
//				log.error(LOG_ERROR_PREPARED_STATEMENT_CLOSE_EXCEPTION, e);
//			}	
	}	
}
	
	/** Show changed book
	 * 
	 * @param id
	 * @return a changed book
	 * @throws DaoException if you cannot get a update book by id
	 */
	@Override
	public Book showUpdate(Long id) throws DaoException {
		
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Book book = new Book();
		
		try(Connection connection = connectionPool.takeConnection()) {
			preparedStatement = connection.prepareStatement(SQL_SELECT_UPDATE);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				book.setId(resultSet.getLong(ID));
				book.setBookTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthorName(resultSet.getString(AUTHOR_NAME));
				book.setPublicationYear(resultSet.getInt(PUBLICATION_YEAR));
				book.setPublishedById(resultSet.getString(PUBLISHED_BY_TITLE));
				book.setGenreId(resultSet.getString(GENRE_TITLE));
			}
		}catch (SQLException e) {
			throw new DaoException(MESSAGE_SHOW_UPDATE_BOOK_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			log.error(MESSAGE_ERROR_CONNECTION_POOL_SELECT_UPDATE, e);
		} finally {
			closeResultSetAndPreparedStatement(resultSet, preparedStatement);
//			try {
//				if (resultSet != null)
//					resultSet.close();
//				log.trace("resultSet closed");
//			} catch (SQLException e) {
//				log.error("Cannot close resultSet ", e);
//			}
//
//			try {
//				if (preparedStatement != null)
//					preparedStatement.close();
//				log.trace("preparedStatement closed");
//			} catch (SQLException e) {
//				log.error("Cannot close preparedStatement ", e);
//			}			
	}
		return book;
}

	
	
	/** Deleting a book by id
	 * 
	 * @param id
	 * @return a deleted book
	 * @throws DaoException if you cannot delete a book by id
	 */
	@Override
	public Book remove(long id) throws DaoException {
		
		Book book = null;
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		PreparedStatement preparedStatement = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			preparedStatement = connection.prepareStatement(SQL_DELETE_UPDATE);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
			 
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_REMOVE_BOOK_EXCEPTION, e);
		}catch (ConnectionPoolException e) {
			log.error(MESSAGE_ERROR_CONNECTION_POOL_REMOVE, e);
		}finally {
			closePreparedStatement(preparedStatement);
//			try {
//				if (preparedStatement != null)
//					preparedStatement.close();
//				log.trace("preparedStatement closed");
//			} catch (SQLException e) {
//				log.error("Cannot close preparedStatement ", e);
//			}
		}
		return book;
}
	
	
	
	
	private static final String SQL_TRANSACTION_VARIABLE ="SET @a_id = 0, @p_id = 0, @g_id = 0";
	private static final String SQL_SELECT_TRANSACTION_AUTHOR = "SELECT id INTO @a_id FROM author WHERE author_name = ? ";
	private static final String SQL_SELECT_TRANSACTION_PUBLISHED_BY = "SELECT id INTO @p_id FROM published_by WHERE published_by_title = ?";
	private static final String SQL_SELECT_TRANSACTION_GENRE = "SELECT id INTO @g_id FROM genre WHERE genre_title = ?";
	private static final String SQL_INSERT_TRANSACTION = "INSERT INTO book(book_title, author_id, publication_year, published_by_id, genre_id, status, image) VALUES(?, @a_id, ?, @p_id, @g_id, 'Y', ?)";
	
	private static final String MESSAGE_ROLLBACK_EXCEPTION = "Cannot rollback ";
	private static final String MESSAGE_ERROR_ADD_BOOK = "Cannot add book ";
	
	private static final String LOG_TRACE_PREPARED_STATEMENT_INSERT_CLOSE = "preparedStatementInsert closed ";
	private static final String LOG_ERROR_PREPARED_STATEMENT_INSERT_CLOSE_EXCEPTION = "Cannot close preparedStatementInsert ";
	
	private static final String LOG_TRACE_PREPARED_STATEMENT_GENRE_CLOSE = "preparedStatementGenre closed ";
	private static final String LOG_ERROR_PREPARED_STATEMENT_GENRE_CLOSE_EXCEPTION = "Cannot close preparedStatementGenre ";
	
	private static final String LOG_TRACE_PREPARED_STATEMENT_PUBLISHED_BY_CLOSE = "preparedStatementPublishedBy closed ";
	private static final String LOG_ERROR_PREPARED_STATEMENT_PUBLISHED_BY_CLOSE_EXCEPTION = " Cannot close preparedStatementPublishedBy ";
	 
	private static final String LOG_TRACE_PREPARED_STATEMENT_AUTHOR_CLOSE = "preparedStatementAuthor closed ";
	private static final String LOG_ERROR_PREPARED_STATEMENT_AUTHOR_CLOSE_EXCEPTION = "Cannot close preparedStatementAuthor ";
	
	private static final String LOG_TRACE_PREPARED_STATEMENT_VAR_CLOSE = "preparedStatementVar closed ";
	private static final String LOG_ERROR_PREPARED_STATEMENT_VAR_CLOSE_EXCEPTION = " Cannot close preparedStatementVar ";
	
	private static final String LOG_TRACE_CONNECTION_CLOSE = "connection closed Cannot close connection ";
	private static final String LOG_ERROR_CONNECTION_CLOSE_EXCEPTION = "Cannot close connection ";
	
	
	/** Adding a book
	 * 
	 * @param book
	 * @throws DaoException if you cannot add a book
	 */
	@Override
	public void add(Book book) throws DaoException {
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		Connection connection = null;
		
		
		PreparedStatement preparedStatementVar = null;
		PreparedStatement preparedStatementAuthor = null;
		PreparedStatement preparedStatementPublishedBy = null;
		PreparedStatement preparedStatementGenre = null; 
		PreparedStatement preparedStatementInsert = null;
		
		
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			
			//1-транзакция
			preparedStatementVar = connection.prepareStatement(SQL_TRANSACTION_VARIABLE);
			preparedStatementVar.executeUpdate();
			
			//2-транзакция
			preparedStatementAuthor = connection.prepareStatement(SQL_SELECT_TRANSACTION_AUTHOR);
			preparedStatementAuthor.setString(1, book.getAuthorName());
			preparedStatementAuthor.executeQuery();
			
			//3-транзакция
			preparedStatementPublishedBy = connection.prepareStatement(SQL_SELECT_TRANSACTION_PUBLISHED_BY);
			preparedStatementPublishedBy.setString(1, book.getPublishedById());
			preparedStatementPublishedBy.executeQuery();
			
			//4-транзакция
			preparedStatementGenre = connection.prepareStatement(SQL_SELECT_TRANSACTION_GENRE);
			preparedStatementGenre.setString(1, book.getGenreId());
			preparedStatementGenre.executeQuery();
			
			//5-транзакция
			preparedStatementInsert = connection.prepareStatement(SQL_INSERT_TRANSACTION);
			preparedStatementInsert.setString(1, book.getBookTitle());
			preparedStatementInsert.setInt(2, book.getPublicationYear());
			preparedStatementInsert.setBytes(3, book.getImage());
			
			preparedStatementInsert.executeUpdate();
			
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error(MESSAGE_ROLLBACK_EXCEPTION);
				throw new DaoException(MESSAGE_ERROR_ADD_BOOK, e1);	
			}
			throw new DaoException(MESSAGE_ERROR_ADD_BOOK, e);
			
		} catch (ConnectionPoolException e) {
			log.error(MESSAGE_ERROR_CONNECTION_POOL_REMOVE, e);
		}finally {
			try {
				if (preparedStatementInsert != null)
					preparedStatementInsert.close();
				log.trace(LOG_TRACE_PREPARED_STATEMENT_INSERT_CLOSE);
			} catch (SQLException e) {
				log.error(LOG_ERROR_PREPARED_STATEMENT_INSERT_CLOSE_EXCEPTION, e);
			}
			
			try {
				if (preparedStatementGenre != null)
					preparedStatementGenre.close();
				log.trace(LOG_TRACE_PREPARED_STATEMENT_GENRE_CLOSE);
			} catch (SQLException e) {
				log.error(LOG_ERROR_PREPARED_STATEMENT_GENRE_CLOSE_EXCEPTION, e);
			}
			
			try {
				if (preparedStatementPublishedBy != null)
					preparedStatementPublishedBy.close();
				log.trace(LOG_TRACE_PREPARED_STATEMENT_PUBLISHED_BY_CLOSE); 
			} catch (SQLException e) {
				log.error(LOG_ERROR_PREPARED_STATEMENT_PUBLISHED_BY_CLOSE_EXCEPTION, e);
			}
			 
			try {
				if (preparedStatementAuthor != null)
					preparedStatementAuthor.close();
				log.trace(LOG_TRACE_PREPARED_STATEMENT_AUTHOR_CLOSE); 
			} catch (SQLException e) {
				log.error(LOG_ERROR_PREPARED_STATEMENT_AUTHOR_CLOSE_EXCEPTION, e);
			}
			  
			try {
				if (preparedStatementVar != null)
					preparedStatementVar.close();
				log.trace(LOG_TRACE_PREPARED_STATEMENT_VAR_CLOSE);
			} catch (SQLException e) {
				log.error(LOG_ERROR_PREPARED_STATEMENT_VAR_CLOSE_EXCEPTION, e);
			}
			
			try {
				if (connection != null)
					connection.setAutoCommit(true);
					connection.close();
				log.trace(LOG_TRACE_CONNECTION_CLOSE );
			} catch (SQLException e) {
				log.error(LOG_ERROR_CONNECTION_CLOSE_EXCEPTION, e);
			}
		}
	}
	
	
	
	public void closeResultSetAndPreparedStatement(ResultSet resultSet, PreparedStatement preparedStatement){
		try {
			if (resultSet != null)
				resultSet.close();
			log.trace(LOG_TRACE_RESULT_SET_CLOSE);
		} catch (SQLException e) {
			log.error(LOG_ERROR_RESULT_SET_CLOSE_EXCEPTION, e);
		}

		try {
			if (preparedStatement != null)
				preparedStatement.close();
			log.trace(LOG_TRACE_PREPARED_STATEMENT_CLOSE);
		} catch (SQLException e) {
			log.error(LOG_ERROR_PREPARED_STATEMENT_CLOSE_EXCEPTION, e);
		}	
	}
		
	public void closePreparedStatement(PreparedStatement preparedStatement){
		try {
			if (preparedStatement != null)
				preparedStatement.close();
			log.trace(LOG_TRACE_PREPARED_STATEMENT_CLOSE);
		} catch (SQLException e) {
			log.error(LOG_ERROR_PREPARED_STATEMENT_CLOSE_EXCEPTION, e);
		}	
	}
	
}
