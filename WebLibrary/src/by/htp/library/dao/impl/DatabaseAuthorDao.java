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
	private static final String SQL_INSERT = "INSERT INTO author (author_name) VALUES(?)";
	private static final String ID = "id";
	private static final String AUTHOR_NAME = "author_name";
	
	private static final String MESSAGE_GET_AUTHOR_EXCEPTION = "Cannot get authors ";
	private static final String MESSAGE_ERROR_CONNECTION_POOL = "Error connecting to the connection pool";
	
	private static final String LOG_TRACE_RESULT_SET_CLOSE = "resultSet closed";
	private static final String LOG_ERROR_RESULT_SET_CLOSE_EXCEPTION = "Cannot close resultSet";
	private static final String LOG_TRACE_STATEMENT_CLOSE = "statement closed";
	private static final String LOG_ERROR_STATEMENT_CLOSE_EXCEPTION = "Cannot close statement";
	
	private static final String MESSAGE_ADD_AUTHOR_EXCEPTION = "Cannot add author ";
	private static final String LOG_TRACE_PREPARED_STATEMENT_CLOSE = "preparedStatement closed";
	private static final String LOG_ERROR_PREPARED_STATEMENT_CLOSE_EXCEPTION = "Cannot close preparedStatement";
	
	
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
			throw new DaoException(MESSAGE_GET_AUTHOR_EXCEPTION, e);
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
		return authorList;
	}

	
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
			throw new DaoException(MESSAGE_ADD_AUTHOR_EXCEPTION, e);
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
