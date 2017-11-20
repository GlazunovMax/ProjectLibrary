package by.htp.library.controller;

public class ConnectionPoolError extends RuntimeException {

	
	private static final long serialVersionUID = -1410237551298190811L;

	public ConnectionPoolError() {
		super();
	}

	public ConnectionPoolError(String message, Exception e) {
		super(message, e);
	}

	public ConnectionPoolError(String message) {
		super(message);
	}

	public ConnectionPoolError(Exception e) {
		super(e);
	}

}
