package by.htp.library.service.exception;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = -607239192536945296L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}
}
