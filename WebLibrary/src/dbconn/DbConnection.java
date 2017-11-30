package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


/** Class gets connection
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class DbConnection {
	private static final Logger log = Logger.getLogger(DbConnection.class);
	
	private static final String URL = "jdbc:mysql://localhost:3306/dbtest?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root22";
	private static final String DRIVER = "org.gjt.mm.mysql.Driver";
	private static final String MESSAGE_OK = "Connection Ok";
	private static final String MESSAGE_ERROR = "Connection error";

	Connection connection;
	
	public Connection getConnection() {
		return connection;
	}

	public DbConnection(){
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			log.info(MESSAGE_OK);
		} catch (SQLException e) {
			log.error(MESSAGE_ERROR);
		} catch (ClassNotFoundException e) {
			log.error(MESSAGE_ERROR);
		}
		
	}
}
