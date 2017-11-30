package by.htp.library.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import by.htp.library.bean.Book;
import by.htp.library.command.Command;
import by.htp.library.dbConnection.ConnectionPool;
import by.htp.library.dbConnection.ConnectionPoolException;
import by.htp.library.dbConnection.FactoryConnectionPool;

/** The class handles Get and Post requests
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final CommandProvider provider = new CommandProvider();
	private static final Logger log = Logger.getLogger(Controller.class);

	
	private static final String CONNECTION_POOL_OK = "Connection Pool ok";
	private static final String ERROR_CONNECTION_POOL = "Error connecting to connection pool";
	private static final String TYPE_OPERATION_IMAGE_OR_READ = "txtCallFlag";
	private static final String TYPE_OPERATION_IMAGE = "IMAGE";
	private static final String TYPE_OPERATION_READ = "READ";
	private static final String CONTENT_TYPE_IMAGE = "image/jpeg";
	private static final String CONTENT_TYPE_PDF = "application/pdf";
	private static final String INDEX = "index";
	private static final String LIST_BOOK = "listBook";
	private static final String COMMAND = "command";
	private static final String NAME = "name";
	private static final String LOGIN = "login";
	private static final String SEARCH_OPTION = "search_option";
	private static final String BY_TITLE = "byTitle";
	private static final String BY_AUTHOR = "byAuthor";
	private static final String GET_BY_TITLE = "getByTitle";
	private static final String GET_BY_AUTHOR = "getByAuthor";
	private static final String MESSAGE_POOL_CLOSE = "Pool close";
	
	
	public Controller() {
		super();
	}

	/**
	 * The method initializes the connection pool
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		try {
			connectionPool.initPoolData();
			log.info(CONNECTION_POOL_OK);
		} catch (ConnectionPoolException e) {
			log.info(ERROR_CONNECTION_POOL);
			throw new ConnectionPoolError(ERROR_CONNECTION_POOL, e);
		}
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		super.service(arg0, arg1);
	}
	
	/** The method displaying the picture and content of the book
	 * 
	 * @param request - Client request
	 * @param response - Response from server to client
	 * @throws ServletException - exception if an input or output error is detected when the servlet handles request 
	 * @throws IOException - exception if the request could not be handled 
	 */
	protected void processRequestImageOrRead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		read book
		String typeOperation=null;
		typeOperation = request.getParameter(TYPE_OPERATION_IMAGE_OR_READ);
		
		if(typeOperation.equals(TYPE_OPERATION_IMAGE)){
			proImageOrRead(request, response, CONTENT_TYPE_IMAGE);
		}else if (typeOperation.equals(TYPE_OPERATION_READ)) {
			proImageOrRead(request, response, CONTENT_TYPE_PDF);
		}
	}

	protected void proImageOrRead(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException{
		response.setContentType(type);
		OutputStream outputStream = response.getOutputStream();
		try{
			
			int index = Integer.valueOf(request.getParameter(INDEX)); 
			
			@SuppressWarnings("unchecked")
			ArrayList<Book> list = (ArrayList<Book>)request.getSession(false).getAttribute(LIST_BOOK);
			Book book = list.get(index);
			
			if(type.equals(CONTENT_TYPE_IMAGE)){
				response.setContentLength(book.getImage().length);
				outputStream.write(book.getImage());
			}
			
			if(type.equals(CONTENT_TYPE_PDF)){
				response.setContentLength(book.getContent().length);
				outputStream.write(book.getContent());
			}
		}finally{
			outputStream.close();
		}
	}

	
	
	/**
	 * The method handles Get requests
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		redirect Add Delete Update
		String commandName = null;
		if(request.getParameter(COMMAND) != null){
			commandName = request.getParameter(COMMAND);
			System.out.println(commandName);
			Command command = provider.getCommand(commandName);
			command.execute(request, response);	
		}
		
		// для отображения картинок и чтения
		processRequestImageOrRead(request, response);
	}

	/**
	 * The method handles Post requests
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		
		String name = request.getParameter(NAME);
		String login = request.getParameter(LOGIN);
			if ((name != null) && (login != null)) {
				Cookie cookie = new Cookie(name, login);
				response.addCookie(cookie);
			}
		
		String commandName = null;
		
		if(request.getParameter(SEARCH_OPTION) != null){
			if(request.getParameter(SEARCH_OPTION).equals(BY_TITLE)){commandName = GET_BY_TITLE;}
			if(request.getParameter(SEARCH_OPTION).equals(BY_AUTHOR)){commandName = GET_BY_AUTHOR;}
		}
		

			if(request.getParameter(COMMAND) != null){
				commandName = request.getParameter(COMMAND);
			}
			System.out.println(commandName);
			Command command = provider.getCommand(commandName);
			command.execute(request, response);
	}


	/**
	 * The method destroy connectionPool
	 */
	@Override
	public void destroy() {
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		connectionPool.dispose();
		log.info(MESSAGE_POOL_CLOSE);
	}
	
	

}
