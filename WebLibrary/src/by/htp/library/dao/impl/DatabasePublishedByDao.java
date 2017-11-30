package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import by.htp.library.bean.PublishedBy;
import by.htp.library.dao.PublishedByDao;
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
public class DatabasePublishedByDao implements PublishedByDao {
	private static Logger log = Logger.getLogger(DatabaseGenreDao.class);

	private static final String SQL_SELECT = "SELECT id, published_by_title FROM published_by";
	private static final String SQL_INSERT = "INSERT INTO published_by (published_by_title) VALUES(?)";
	private static final String ID = "id";
	private static final String PUBLISHED_BY_TITLE = "published_by_title";
	
	private static final String MESSAGE_GET_PUBLISHED_BY_EXCEPTION = "Cannot get publishedBy ";
	private static final String MESSAGE_ERROR_CONNECTION_POOL = "Error connecting to the connection pool";
	
	private static final String LOG_TRACE_RESULT_SET_CLOSE = "resultSet closed";
	private static final String LOG_ERROR_RESULT_SET_CLOSE_EXCEPTION = "Cannot close resultSet";
	private static final String LOG_TRACE_STATEMENT_CLOSE = "statement closed";
	private static final String LOG_ERROR_STATEMENT_CLOSE_EXCEPTION = "Cannot close statement";
	
	private static final String MESSAGE_ADD_PUBLISHED_BY_EXCEPTION = "Cannot add published by ";
	private static final String LOG_TRACE_PREPARED_STATEMENT_CLOSE = "preparedStatement closed";
	private static final String LOG_ERROR_PREPARED_STATEMENT_CLOSE_EXCEPTION = "Cannot close preparedStatement";
	
	
	/** Get all publishing houses from the database
	 * 
	 * @return List of all publishing houses
	 * @throws DaoException if you cannot get all the publishing houses
	 */
	@Override
	public List<PublishedBy> getAllPublishedBy() throws DaoException {
		List<PublishedBy> publishedByList = new ArrayList<>();
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		PublishedBy publishedBy;
		
		try(Connection connection = connectionPool.takeConnection()) {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_SELECT);
			
			while(resultSet.next()){
				publishedBy = new PublishedBy();
				publishedBy.setId(resultSet.getInt(ID));
				publishedBy.setPublishedByTitle(resultSet.getString(PUBLISHED_BY_TITLE));
				
				publishedByList.add(publishedBy);
			}
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_GET_PUBLISHED_BY_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			log.error(MESSAGE_ERROR_CONNECTION_POOL, e);
		} finally {
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
		return publishedByList;
	}

	
	/** Add the publishing house to the database 
	 * 
	 * @param publishedBy
	 * @throws DaoException if you cannot add publishing house
	 */
	@Override
	public void add(PublishedBy publishedBy) throws DaoException {
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		Connection connection;
		
		PreparedStatement preparedStatement = null;

		try {
			 
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT);
			
			preparedStatement.setString(1, publishedBy.getPublishedByTitle());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_ADD_PUBLISHED_BY_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			log.error(MESSAGE_ERROR_CONNECTION_POOL, e);
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				log.trace(LOG_TRACE_PREPARED_STATEMENT_CLOSE);
			} catch (SQLException e) {
				log.error(LOG_ERROR_PREPARED_STATEMENT_CLOSE_EXCEPTION, e);
			}
		}
	}
}
