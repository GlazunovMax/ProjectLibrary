package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/dbtest?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root22";

	Connection connection;
	
	public Connection getConnection() {
		return connection;
	}

	public DbConnection(){
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connection Ok");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection yt 1");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection en 2");
		}
		
	}
}
