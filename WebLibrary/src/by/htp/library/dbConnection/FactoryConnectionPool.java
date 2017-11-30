package by.htp.library.dbConnection;


/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class FactoryConnectionPool {

	/** Creates a reference to the FactoryConnectionPool object class */
	private static final FactoryConnectionPool instance = new FactoryConnectionPool();
	
	/** Creates a reference to the ConnectionPool object class */
	private ConnectionPool pool = new ConnectionPool();
	
	/** Creates a new object */
	private FactoryConnectionPool(){}
	
	/**
	 * Method to get a reference to the FactoryConnectionPool object class {@link FactoryConnectionPool#instance} 
	 * @return a reference to the FactoryConnectionPool object class 
	 */
	public static FactoryConnectionPool getInstance() {
		return instance;
	}
	
	/**
	 * Method to get a reference to the FactoryConnectionPool object class {@link FactoryConnectionPool#pool} 
	 * @return a reference to the FactoryConnectionPool object class 
	 */
	public ConnectionPool getConnectionPool(){
		return pool;
	}
}
