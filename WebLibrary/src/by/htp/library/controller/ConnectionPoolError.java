package by.htp.library.controller;


/**
 * 
 * @author Glazunov Maxim
 * @version 1.0 
 *
 */
public class ConnectionPoolError extends RuntimeException {

	
	private static final long serialVersionUID = -1410237551298190811L;

	/**
	 * @see ConnectionPoolError#ConnectionPoolError(Exception)
	 * @see ConnectionPoolError#ConnectionPoolError(String)
	 * @see ConnectionPoolError#ConnectionPoolError(String, Exception)
	 */
	public ConnectionPoolError() {
		super();
	}

	/**
	 * @see ConnectionPoolError#ConnectionPoolError()
	 * @see ConnectionPoolError#ConnectionPoolError(String)
	 * @see ConnectionPoolError#ConnectionPoolError(Exception)
	 * @param message - the detail message
	 * @param e -  the cause
	 */
	public ConnectionPoolError(String message, Exception e) {
		super(message, e);
	}

	/**
	 * @see ConnectionPoolError#ConnectionPoolError()
	 * @see ConnectionPoolError#ConnectionPoolError(Exception)
	 * @see ConnectionPoolError#ConnectionPoolError(String, Exception)
	 * @param message -  the detail message
	 */
	public ConnectionPoolError(String message) {
		super(message);
	}

	/**
	 * @see ConnectionPoolError#ConnectionPoolError()
	 * @see ConnectionPoolError#ConnectionPoolError(String)
	 * @see ConnectionPoolError#ConnectionPoolError(String, Exception)
	 * @param e - the cause
	 */
	public ConnectionPoolError(Exception e) {
		super(e);
	}

}
