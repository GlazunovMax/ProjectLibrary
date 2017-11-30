package by.htp.library.service.exception;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class ServiceException extends Exception {
	
	private static final long serialVersionUID = -607239192536945296L;

	
	/**
	 * @see ServiceException#ServiceException(Exception)
	 * @see ServiceException#ServiceException(String)
	 * @see ServiceException#ServiceException(String, Exception)
	 */
	public ServiceException() {
		super();
	}

	
	/**
	 * @see ServiceException#ServiceException()
	 * @see ServiceException#ServiceException(Exception)
	 * @see ServiceException#ServiceException(String, Exception)
	 * @param message -  the detail message
	 */
	public ServiceException(String message) {
		super(message);
	}

	
	/**
	 * @see ServiceException#ServiceException()
	 * @see ServiceException#ServiceException(String)
	 * @see ServiceException#ServiceException(String, Exception)
	 * @param e - the cause
	 */
	public ServiceException(Exception e) {
		super(e);
	}

	
	/**
	 * @see ServiceException#ServiceException()
	 * @see ServiceException#ServiceException(String)
	 * @see ServiceException#ServiceException(Exception)
	 * @param message - the detail message
	 * @param e -  the cause
	 */
	public ServiceException(String message, Exception e) {
		super(message, e);
	}
}
