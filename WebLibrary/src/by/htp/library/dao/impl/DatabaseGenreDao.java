package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import by.htp.library.bean.Genre;
import by.htp.library.dao.GenreDao;
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
public class DatabaseGenreDao implements GenreDao {
	private static Logger log = Logger.getLogger(DatabaseGenreDao.class);
	
	private static final String SQL_SELECT = "SELECT id, genre_title FROM genre";
	private static final String ID = "id";
	private static final String GENRE_TITLE = "genre_title";
	private static final String SQL_INSERT = "INSERT INTO genre (genre_title) VALUES(?)";

	private static final String MESSAGE_GET_GENRE_EXCEPTION = "Cannot get genres ";
	private static final String MESSAGE_ERROR_CONNECTION_POOL = "Error connecting to the connection pool";
	
	private static final String LOG_TRACE_RESULT_SET_CLOSE = "resultSet closed";
	private static final String LOG_ERROR_RESULT_SET_CLOSE_EXCEPTION = "Cannot close resultSet";
	private static final String LOG_TRACE_STATEMENT_CLOSE = "statement closed";
	private static final String LOG_ERROR_STATEMENT_CLOSE_EXCEPTION = "Cannot close statement";
	
	private static final String MESSAGE_ADD_GENRE_EXCEPTION = "Cannot add genre ";
	private static final String LOG_TRACE_PREPARED_STATEMENT_CLOSE = "preparedStatement closed";
	private static final String LOG_ERROR_PREPARED_STATEMENT_CLOSE_EXCEPTION = "Cannot close preparedStatement";
	
	/** Get all genres from the database
	 * 
	 * @return List of all genres
	 * @throws DaoException  if you cannot get all the genres
	 */
	@Override
	public List<Genre> getAll() throws DaoException {
		
		List<Genre> genreList = new ArrayList<>();
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		Statement statement = null;
		ResultSet resultSet = null;
		Genre genre;
		
		try (Connection connection = connectionPool.takeConnection();){
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_SELECT);
			
			while(resultSet.next()){
				genre = new Genre();
				genre.setId(resultSet.getInt(ID));
				genre.setGenreTitle(resultSet.getString(GENRE_TITLE));
				
				genreList.add(genre);
			}
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_GET_GENRE_EXCEPTION, e);
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
		return genreList;
	}

	/** Add the genre to the database 
	 * 
	 * @param genre
	 * @throws DaoException if you cannot add genre
	 */
	@Override
	public void add(Genre genre) throws DaoException {
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		Connection connection;
		
		PreparedStatement preparedStatement = null;

		try {
			 
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT);
			
			preparedStatement.setString(1, genre.getGenreTitle());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_ADD_GENRE_EXCEPTION, e);
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
