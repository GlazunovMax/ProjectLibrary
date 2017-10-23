package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import by.htp.library.bean.Book;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dbConnection.ConnectionPool;
import by.htp.library.dbConnection.ConnectionPoolException;
import by.htp.library.dbConnection.FactoryConnectionPool;

public class DatabaseBookDao implements BookDao {
	
	private static final Logger log = Logger.getLogger(DatabaseUserDao.class);
	
	
	private static final String SQL_SELECT_TITLE = "SELECT book.id, book.book_title, author.author_name, book.publication_year, published_by.published_by_title, genre.genre_title, book.image FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where book.book_title like ? AND book.status='Y' limit ?,?";
	private static final String SQL_SELECT_AUTHOR = "SELECT book.id, book.book_title, author.author_name, book.publication_year, published_by.published_by_title, genre.genre_title, book.image FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where author.author_name like ? AND book.status='Y' limit ?,?";
	private static final String SQL_SELECT_GENRE = "SELECT book.id, book.book_title, author.author_name, book.publication_year, published_by.published_by_title, genre.genre_title, book.image FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where genre.genre_title like ? AND book.status='Y' limit ?,?";
	private static final String SQL_UPDATE = "UPDATE book SET book_title=?, publication_year=? WHERE id = ?"; 
	private static final String SQL_SELECT_UPDATE = "SELECT book.id, book.book_title, author.author_name, book.publication_year, published_by.published_by_title, genre.genre_title FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where book.id = ?";
	private static final String SQL_DELETE_UPDATE = "UPDATE book SET status='N' WHERE id =?";
	private static final String SQL_SELECT_ALL = "SELECT book.id, book.book_title, author.author_name, book.publication_year, published_by.published_by_title, genre.genre_title, book.image FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where book.status='Y' limit ?,?";
	
	
	private static final String ID = "id";
	private static final String BOOK_TITLE = "book_title";
	private static final String AUTHOR_NAME= "author_name";
	private static final String PUBLICATION_YEAR = "publication_year";
	private static final String PUBLISHED_BY_TITLE = "published_by_title";
	private static final String GENRE_TITLE = "genre_title";
	private static final String IMAGE = "image";

	
	@Override
	public List<Book> getByTitle(String bookTitle, int start, int countRows) throws DaoException {
		return getListBook(bookTitle, SQL_SELECT_TITLE, "title", start, countRows);
	}
	
	@Override
	public List<Book> getByAuthor(String authorName, int start, int countRows) throws DaoException {
		return getListBook(authorName, SQL_SELECT_AUTHOR, "author", start, countRows);
	}

	//public int totalBooksCount = getGenreCount(str);
	
	@Override
	public List<Book> getByGenre(String genreName, int start, int countRows) throws DaoException {
		return getListBook(genreName, SQL_SELECT_GENRE, "genre", start, countRows);
	}
	
	public List<Book> getListBook(String str, String SQL, String searchBy, int start, int countRows) throws DaoException{
		
		
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		 List<Book> bookList = new ArrayList<Book>();
		 
		try(Connection connection = connectionPool.takeConnection()) {
			
			preparedStatement = connection.prepareStatement(SQL);
			
			
			preparedStatement.setString(1, "%" + str + "%");
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
				
				//preparedStatement.executeUpdate();
				
				bookList.add(book);
			}
		} catch (SQLException e) {
			throw new DaoException("Cannot find book by " + searchBy, e);
		}catch (ConnectionPoolException e) {
			log.error("Error connecting to the connection pool in " + searchBy, e);
		}finally {
				try {
					if (resultSet != null)
						resultSet.close();
					log.trace("resultSet closed");
				} catch (SQLException e) {
					log.error("Cannot close resultSet ", e);
				}

				try {
					if (preparedStatement != null)
						preparedStatement.close();
					log.trace("preparedStatement closed");
				} catch (SQLException e) {
					log.error("Cannot close preparedStatement ", e);
				}			
		}
		
		return bookList;
	
	}
	
	
	@Override
	public List<Book> getByAll(int start, int countRows) throws DaoException {
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		 List<Book> bookList = new ArrayList<Book>();
		 
		 try(Connection connection = connectionPool.takeConnection()) {
				//preparedStatement = connection.prepareStatement("SELECT book.id, book.book_title, author.author_name, book.publication_year, published_by.published_by_title, genre.genre_title, book.image FROM book INNER JOIN author ON author.id = book.author_id INNER JOIN published_by ON published_by.id = book.published_by_id INNER JOIN genre ON genre.id = book.genre_id where book.status='Y' limit "+ start + "," + countRows);
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
					
					//preparedStatement.executeUpdate();
					
					bookList.add(book);
					
				}
			} catch (SQLException e) {
				throw new DaoException("Cannot find all book ", e);
			}catch (ConnectionPoolException e) {
				log.error("Error connecting to the connection pool in getAll ", e);
			}finally {
					try {
						if (resultSet != null)
							resultSet.close();
						log.trace("resultSet closed");
					} catch (SQLException e) {
						log.error("Cannot close resultSet ", e);
					}

					try {
						if (preparedStatement != null)
							preparedStatement.close();
						log.trace("preparedStatement closed");
					} catch (SQLException e) {
						log.error("Cannot close preparedStatement ", e);
					}			
			}
			
			return bookList;
	
	}
	
	
//	public int getBooksCount() throws DaoException{
//		int count = 0;
//		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
//		ConnectionPool connectionPool = factory.getConnectionPool();
//		
//		Statement statement = null;
//		ResultSet resultSet = null;
//		
//		try(Connection connection = connectionPool.takeConnection()) {
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM Book WHERE status = 'Y'");
//			
//			while (resultSet.next()) {
//				count = resultSet.getInt("COUNT");
//			}
//		} catch (SQLException e) {
//			throw new DaoException("Cannot find count book ", e);
//		} catch (ConnectionPoolException e) {
//			log.error("Error connecting to the connection pool in update", e);
//		}finally {
//			try {
//				if (resultSet != null)
//					resultSet.close();
//				log.trace("resultSet closed");
//			} catch (SQLException e) {
//				log.error("Cannot close resultSet ", e);
//			}	
//			
//			try {
//				if (statement != null)
//					statement.close();
//				log.trace("statement closed");
//			} catch (SQLException e) {
//				log.error("Cannot close statement ", e);
//			}	
//		
//		}
//		return count;
//	}
	

	
	//UPDATE book SET book_title=?, author_id=?, publication_year=?, published_by_id=?, genre_id WHERE id = ?"
	@Override
	public void update(Book book) throws DaoException {
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		PreparedStatement preparedStatement = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			preparedStatement = connection.prepareStatement(SQL_UPDATE);
			
			preparedStatement.setString(1, book.getBookTitle());
					//preparedStatement.setLong(2, author.getId());
					//preparedStatement.setString(2, author.getAuthorName());//----
			preparedStatement.setInt(2, book.getPublicationYear());
			//preparedStatement.setInt(4, book.getPublishedById());
			//preparedStatement.setString(4, book.getPublishedById());//----
			//preparedStatement.setInt(5, book.getGenreId());
			//preparedStatement.setString(5, book.getGenreId());//-----
			preparedStatement.setLong(3, book.getId());
		
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException("Cannot update book by id ", e);
		} catch (ConnectionPoolException e) {
			log.error("Error connecting to the connection pool in update", e);
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				log.trace("preparedStatement closed");
			} catch (SQLException e) {
				log.error("Cannot close preparedStatement ", e);
			}	
	}	
}
	
	
	@Override
	public Book showUpdate(Long id) throws DaoException {
		
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		PreparedStatement preparedStatement = null;
		//Statement statement = null;
		ResultSet resultSet = null;
		Book book = new Book();
		
		try(Connection connection = connectionPool.takeConnection()) {
			//statement = connection.createStatement();
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
			throw new DaoException("Cannot find book by id ", e);
		} catch (ConnectionPoolException e) {
			log.error("Error connecting to the connection pool in selectUpdate ", e);
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				log.trace("resultSet closed");
			} catch (SQLException e) {
				log.error("Cannot close resultSet ", e);
			}

			try {
				if (preparedStatement != null)
					preparedStatement.close();
				log.trace("preparedStatement closed");
			} catch (SQLException e) {
				log.error("Cannot close preparedStatement ", e);
			}			
	}
		return book;
}

	
	
	
	@Override
	public Book remove(long id) throws DaoException {
		
		Book book = null;
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		
		PreparedStatement preparedStatement = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			//preparedStatement = connection.prepareStatement(SQL_DELETE);
			preparedStatement = connection.prepareStatement(SQL_DELETE_UPDATE);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException("Cannot remove book by id ", e);
		}catch (ConnectionPoolException e) {
			log.error("Error connecting to the connection pool in remove", e);
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				log.trace("preparedStatement closed");
			} catch (SQLException e) {
				log.error("Cannot close preparedStatement ", e);
			}
		}
		return book;
}
	
	
	
	
	private static final String SQL_TRANSACTION_VARIABLE ="SET @a_id = 0, @p_id = 0, @g_id = 0";
	private static final String SQL_SELECT_TRANSACTION_AUTHOR = "SELECT id INTO @a_id FROM author WHERE author_name = ? ";
	private static final String SQL_SELECT_TRANSACTION_PUBLISHED_BY = "SELECT id INTO @p_id FROM published_by WHERE published_by_title = ?";
	private static final String SQL_SELECT_TRANSACTION_GENRE = "SELECT id INTO @g_id FROM genre WHERE genre_title = ?";
	private static final String SQL_INSERT_TRANSACTION = "INSERT INTO book(book_title, author_id, publication_year, published_by_id, genre_id, status) VALUES(?, @a_id, ?, @p_id, @g_id, 'Y')";
	
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
			preparedStatementInsert.executeUpdate();
			
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error("Cannot rollback ");
				throw new DaoException("Cannot add book ", e1);	
			}
			throw new DaoException("Cannot add book ", e);
			
		} catch (ConnectionPoolException e) {
			log.error("Error connecting to the connection pool in remove", e);
		}finally {
			try {
				if (preparedStatementInsert != null)
					preparedStatementInsert.close();
				log.trace("preparedStatementInsert closed");
			} catch (SQLException e) {
				log.error("Cannot close vpreparedStatementInsert ", e);
			}
			
			try {
				if (preparedStatementGenre != null)
					preparedStatementGenre.close();
				log.trace("preparedStatementGenre closed");
			} catch (SQLException e) {
				log.error("Cannot close preparedStatementGenre ", e);
			}
			
			try {
				if (preparedStatementPublishedBy != null)
					preparedStatementPublishedBy.close();
				log.trace("preparedStatementPublishedBy closed");
			} catch (SQLException e) {
				log.error("Cannot close preparedStatementPublishedBy ", e);
			}
			
			try {
				if (preparedStatementAuthor != null)
					preparedStatementAuthor.close();
				log.trace("preparedStatementAuthor closed");
			} catch (SQLException e) {
				log.error("Cannot close preparedStatementAuthor ", e);
			}
			
			try {
				if (preparedStatementVar != null)
					preparedStatementVar.close();
				log.trace("preparedStatementVar closed");
			} catch (SQLException e) {
				log.error("Cannot close preparedStatementVar ", e);
			}
			
			try {
				if (connection != null)
					connection.setAutoCommit(true);
					connection.close();
				log.trace("connection closed");
			} catch (SQLException e) {
				log.error("Cannot close connection ", e);
			}
		}
	}


	
}
