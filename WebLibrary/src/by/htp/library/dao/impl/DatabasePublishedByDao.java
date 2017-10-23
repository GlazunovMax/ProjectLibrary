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

public class DatabasePublishedByDao implements PublishedByDao {
	private static Logger log = Logger.getLogger(DatabaseGenreDao.class);

	private static final String SQL_SELECT = "SELECT id, published_by_title FROM published_by";
	private static final String ID = "id";
	private static final String PUBLISHED_BY_TITLE = "published_by_title";
	
	@Override
	public List<PublishedBy> getAllPublishedBy() throws DaoException {
		List<PublishedBy> publishedByList = new ArrayList<>();
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		PublishedBy publishedBy;
		
		try(Connection connection = connectionPool.takeConnection()) {
			//
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_SELECT);
			
			while(resultSet.next()){
				publishedBy = new PublishedBy();
				publishedBy.setId(resultSet.getInt(ID));
				publishedBy.setPublishedByTitle(resultSet.getString(PUBLISHED_BY_TITLE));
				
				publishedByList.add(publishedBy);
			}
		} catch (SQLException e) {
			throw new DaoException("Cannot get publishedBy ", e);
		} catch (ConnectionPoolException e) {
			log.error("Error connecting to the connection pool ", e);
		} finally {
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
		return publishedByList;
	}

	private static final String SQL_INSERT = "INSERT INTO published_by (published_by_title) VALUES(?)";
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
			throw new DaoException("Cannot add published by ", e);
		} catch (ConnectionPoolException e) {
			log.error("Cannot close resultSet ", e);
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				log.trace("preparedStatement closed");
			} catch (SQLException e) {
				log.error("Cannot close preparedStatement ", e);
			}
		}
		
	}

}
