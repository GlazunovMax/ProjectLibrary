package by.htp.library.dao.exception;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0 
 *
 */
public class DaoException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * @see DaoException#DaoException(Exception)
	 * @see DaoException#DaoException(String)
	 * @see DaoException#DaoException(String, Exception)
	 */
	public DaoException() {
		super();
	}

	/**
	 * @see DaoException#DaoException()
	 * @see DaoException#DaoException(Exception)
	 * @see DaoException#DaoException(String, Exception)
	 * @param message -  the detail message
	 */
	public DaoException(String message) {
		super(message);
	}
    
	/**
	 * @see DaoException#DaoException()
	 * @see DaoException#DaoException(String)
	 * @see DaoException#DaoException(String, Exception)
	 * @param e - the cause
	 */
	public DaoException(Exception e) {
		super(e);
	}

	/**
	 * @see DaoException#DaoException()
	 * @see DaoException#DaoException(String)
	 * @see DaoException#DaoException(Exception)
	 * @param message - the detail message
	 * @param e -  the cause
	 */
	public DaoException(String message, Exception e) {
		super(message, e);
	}
}
