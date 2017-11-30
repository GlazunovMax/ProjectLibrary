package by.htp.library.dbConnection;


/**
 * 
 * @author Glazunov Maxim
 * @version 1.0 
 *
 */
public class ConnectionPoolException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param message - the detail message
	 * @param e -  the cause
	 */
	public ConnectionPoolException(String message, Exception e){
		super(message, e);
	}
}
