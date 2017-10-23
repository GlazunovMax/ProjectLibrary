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

public class DatabaseGenreDao implements GenreDao {
	private static Logger log = Logger.getLogger(DatabaseGenreDao.class);
	
	private static final String SQL_SELECT = "SELECT id, genre_title FROM genre";
	private static final String ID = "id";
	private static final String GENRE_TITLE = "genre_title";
	private static final String SQL_INSERT = "INSERT INTO genre (genre_title) VALUES(?)";

	
	
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
			throw new DaoException("Cannot get genres ", e);
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
		return genreList;
	}


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
