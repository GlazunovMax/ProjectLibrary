package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import by.htp.library.bean.Author;
import by.htp.library.dao.AuthorDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dbConnection.ConnectionPool;
import by.htp.library.dbConnection.ConnectionPoolException;
import by.htp.library.dbConnection.FactoryConnectionPool;


public class DatabaseAuthorDao  implements AuthorDao{
	private static Logger log = Logger.getLogger(DatabaseAuthorDao.class);
	
	private static final String SQL_SELECT = "SELECT id, author_name FROM author";
	private static final String ID = "id";
	private static final String AUTHOR_NAME = "author_name";
	
	@Override
	public List<Author> getAllBook() throws DaoException {
		List<Author> authorList = new ArrayList<>();
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		Author author;
		
		try(Connection connection = connectionPool.takeConnection()) {	
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_SELECT);
			
			while(resultSet.next()){
				author = new Author();
				author.setId(resultSet.getInt(ID));
				author.setAuthorName(resultSet.getString(AUTHOR_NAME));
				
				authorList.add(author);
			}
		} catch (SQLException e) {
			throw new DaoException("Cannot get authors ", e);
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
		return authorList;
	}

	private static final String SQL_INSERT = "INSERT INTO author (author_name) VALUES(?)";
	@Override
	public void add(Author author) throws DaoException {
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		Connection connection;
		
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT);
			
			preparedStatement.setString(1, author.getAuthorName());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Cannot add author ", e);
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
