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
	
	public int getBooksCount() throws DaoException{
		int count = 0;
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM Book WHERE status = 'Y'");
			
			while (resultSet.next()) {
				count = resultSet.getInt("COUNT");
			}
		} catch (SQLException e) {
			throw new DaoException("Cannot find count book ", e);
		} catch (ConnectionPoolException e) {
			log.error("Error connecting to the connection pool in update", e);
		}finally {
			try {
				if (resultSet != null)
					resultSet.close();
				log.trace("resultSet closed");
			} catch (SQLException e) {
				log.error("Cannot close resultSet ", e);
			}	
			
			try {
				if (statement != null)
					statement.close();
				log.trace("statement closed");
			} catch (SQLException e) {
				log.error("Cannot close statement ", e);
			}	
		
		}
		return count;
	}
	
	
	
	
	
//	Count genre by title
	public int getGenreCount(String genre) throws DaoException{//&&&&&&&&&&&&&&&&
		int count = 0;
		//Map<String, String> params = g
	//	String genre = null;
	
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where genre.genre_title= '"+ genre +"' AND book.status='Y' "); // " AND book.status='Y' ");

			
			while (resultSet.next()) {
				count = resultSet.getInt("COUNT");
			}
		} catch (SQLException e) {
			throw new DaoException("Cannot find count book by genre ", e);
		} catch (ConnectionPoolException e) {
			log.error("Error connecting to the connection pool in update", e);
		}finally {
			try {
				if (resultSet != null)
					resultSet.close();
				log.trace("resultSet closed");
			} catch (SQLException e) {
				log.error("Cannot close resultSet ", e);
			}	
			
			try {
				if (statement != null)
					statement.close();
				log.trace("statement closed");
			} catch (SQLException e) {
				log.error("Cannot close statement ", e);
			}	
		
		}
		return count;
	}
	
//	Count bool by Author
	public int getAuthorCount(String searchString) throws DaoException{//&&&&&&&&&&&&&&&&
		int count = 0;
	
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
//			resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where genre.genre_title= '"+ searchString +"' AND book.status='Y' "); // " AND book.status='Y' ");
			resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where author.author_name like '%"+ searchString +"%' AND book.status='Y' ");//"%" + str + "%"
			
			while (resultSet.next()) {
				count = resultSet.getInt("COUNT");
			}
		} catch (SQLException e) {
			throw new DaoException("Cannot find count book by genre ", e);
		} catch (ConnectionPoolException e) {
			log.error("Error connecting to the connection pool in update", e);
		}finally {
			try {
				if (resultSet != null)
					resultSet.close();
				log.trace("resultSet closed");
			} catch (SQLException e) {
				log.error("Cannot close resultSet ", e);
			}	
			
			try {
				if (statement != null)
					statement.close();
				log.trace("statement closed");
			} catch (SQLException e) {
				log.error("Cannot close statement ", e);
			}	
		
		}
		return count;
	}
	
//	Count bool by Title
	public int getTitleCount(String searchString) throws DaoException{//&&&&&&&&&&&&&&&&
		int count = 0;
		//Map<String, String> params = g
	//	String genre = null;
	
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
//			resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where author.author_name like '%"+ searchString +"%' AND book.status='Y' ");//"%" + str + "%"
			resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where book.book_title like '%"+ searchString +"%' AND book.status='Y' ");
			while (resultSet.next()) {
				count = resultSet.getInt("COUNT");
			}
		} catch (SQLException e) {
			throw new DaoException("Cannot find count book by genre ", e);
		} catch (ConnectionPoolException e) {
			log.error("Error connecting to the connection pool in update", e);
		}finally {
			try {
				if (resultSet != null)
					resultSet.close();
				log.trace("resultSet closed");
			} catch (SQLException e) {
				log.error("Cannot close resultSet ", e);
			}	
			
			try {
				if (statement != null)
					statement.close();
				log.trace("statement closed");
			} catch (SQLException e) {
				log.error("Cannot close statement ", e);
			}	
		
		}
		return count;
	}
	
	

}
