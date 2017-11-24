package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dbConnection.ConnectionPool;
import by.htp.library.dbConnection.ConnectionPoolException;
import by.htp.library.dbConnection.FactoryConnectionPool;

public class DatabaseCountRows {
	private static final Logger log = Logger.getLogger(DatabaseCountRows.class);
	
	private static final String SQL_GET_BOOK_COUNT = "SELECT COUNT(*) AS COUNT FROM Book WHERE status = 'Y'";
	private static final String SQL_GET_GENRE_COUNT_1 = "SELECT COUNT(*) AS COUNT FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where genre.genre_title= '";
	private static final String SQL_GET_GENRE_COUNT_2 = "' AND book.status='Y' ";
	private static final String SQL_GET_AUTHOR_COUNT_1 = "SELECT COUNT(*) AS COUNT FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where author.author_name like '%";
	private static final String SQL_GET_AUTHOR_COUNT_2 = "%' AND book.status='Y' ";
	private static final String SQL_GET_TITLE_COUNT_1 = "SELECT COUNT(*) AS COUNT FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where book.book_title like '%";
	private static final String SQL_GET_TITLE_COUNT_2 = "%' AND book.status='Y' ";

	private static final String COUNT = "COUNT";
	private static final String MESSAGE_FIND_COUNT_BOOK_EXCEPTION = "Cannot find count book ";
	private static final String MESSAGE_FIND_COUNT_BOOK_BY_GENRE_EXCEPTION = "Cannot find count book by genre";
	private static final String MESSAGE_FIND_COUNT_BOOK_BY_AUTHOR_EXCEPTION = "Cannot find count book by author";
	private static final String MESSAGE_FIND_COUNT_BOOK_BY_TITLE_EXCEPTION = "Cannot find count book by title";

	private static final String MESSAGE_ERROR_CONNECTION_POOL = "Error connecting to the connection pool"; // DataSource
	
	private static final String LOG_TRACE_RESULT_SET_CLOSE = "resultSet closed";
	private static final String LOG_ERROR_RESULT_SET_CLOSE_EXCEPTION = "Cannot close resultSet";
	private static final String LOG_TRACE_STATEMENT_CLOSE = "statement closed";
	private static final String LOG_ERROR_STATEMENT_CLOSE_EXCEPTION = "Cannot close statement";
	
	public int getBooksCount() throws DaoException{
		int count = 0;
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_GET_BOOK_COUNT);
			
			while (resultSet.next()) {
				count = resultSet.getInt(COUNT);
			}
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_FIND_COUNT_BOOK_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			log.error(MESSAGE_ERROR_CONNECTION_POOL, e);
		}finally {
			close(resultSet, statement);
		}
		return count;
	}
	
	
	
	
	
//	Count genre by title
	public int getGenreCount(String genre) throws DaoException{
		int count = 0;
		
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			//resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where genre.genre_title= '"+ genre +"' AND book.status='Y' "); 
			resultSet = statement.executeQuery(SQL_GET_GENRE_COUNT_1 + genre + SQL_GET_GENRE_COUNT_2);
			
			while (resultSet.next()) {
				count = resultSet.getInt(COUNT);
			}
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_FIND_COUNT_BOOK_BY_GENRE_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			log.error(MESSAGE_ERROR_CONNECTION_POOL, e);
		}finally {
			close(resultSet, statement);
		}
		return count;
	}
	
//	Count by Author
	public int getAuthorCount(String author) throws DaoException{
		int count = 0;
	
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			//resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where author.author_name like '%"+ author +"%' AND book.status='Y' ");
			resultSet = statement.executeQuery(SQL_GET_AUTHOR_COUNT_1 + author + SQL_GET_AUTHOR_COUNT_2);
			
			while (resultSet.next()) {
				count = resultSet.getInt(COUNT);
			}
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_FIND_COUNT_BOOK_BY_AUTHOR_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			log.error(MESSAGE_ERROR_CONNECTION_POOL, e);
		}finally {
			close(resultSet, statement);
		}
		return count;
	}
	
//	Count by Title
	public int getTitleCount(String title) throws DaoException{
		int count = 0;
	
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			//resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where book.book_title like '%"+ searchString +"%' AND book.status='Y' ");
			resultSet = statement.executeQuery(SQL_GET_TITLE_COUNT_1 + title + SQL_GET_TITLE_COUNT_2);
			
			while (resultSet.next()) {
				count = resultSet.getInt(COUNT);
			}
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_FIND_COUNT_BOOK_BY_TITLE_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			log.error(MESSAGE_ERROR_CONNECTION_POOL, e);
		}finally {
			close(resultSet, statement);
		}
		return count;
	}
	
	
	public void close(ResultSet resultSet, Statement statement){
		try {
			if (resultSet != null)
				resultSet.close();
			log.trace(LOG_TRACE_RESULT_SET_CLOSE);
		} catch (SQLException e) {
			log.error(LOG_ERROR_RESULT_SET_CLOSE_EXCEPTION, e);
		}	
		
		try {
			if (statement != null)
				statement.close();
			log.trace(LOG_TRACE_STATEMENT_CLOSE);
		} catch (SQLException e) {
			log.error(LOG_ERROR_STATEMENT_CLOSE_EXCEPTION, e);
		}	
	}
}
