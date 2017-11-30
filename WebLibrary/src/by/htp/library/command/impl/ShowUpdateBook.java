package by.htp.library.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.library.bean.Book;
import by.htp.library.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

/**
 * 
 *  @author Glazunov Maxim
 * @version 1.0
 *
 */
public class ShowUpdateBook implements Command{
	private static final String ID = "id";
	
	private static final String SHOW_UPDATE_BOOK = "showUpdateBook";
	private static final String PAGE_SHOW_UPDATE_BOOK = "WEB-INF/jsp/editBook.jsp";
	private static final String ERROR_SHOW_UPDATE_BOOK = "errorShowUpdateBook";
	private static final String MESSAGE_ERROR_SHOW_UPDATE_BOOK = "Error date in else...";
	
	
	/** Implementation of the interface Command - specific commands - Show Update Book
	 * 
	 * @param request - Client request
	 * @param response - Response from server to client
	 * @throws IOException - exception if an input or output error is detected when the servlet handles request 
	 * @throws ServletException - exception if the request could not be handled 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Book book = new Book();
		Long idBook = null;
		
		idBook = Long.parseLong(request.getParameter(ID)); 
		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
		String page = null;
		
		try {
			book = bookService.showUpdateBook(idBook);
			if(book!=null){
				request.setAttribute(SHOW_UPDATE_BOOK, book);
				page = PAGE_SHOW_UPDATE_BOOK;
			}else{
				request.setAttribute(ERROR_SHOW_UPDATE_BOOK, MESSAGE_ERROR_SHOW_UPDATE_BOOK);
				page = PAGE_SHOW_UPDATE_BOOK;
			}
			
		} catch (ServiceException e) {
			request.setAttribute(ERROR_SHOW_UPDATE_BOOK, MESSAGE_ERROR_SHOW_UPDATE_BOOK);
			page = PAGE_SHOW_UPDATE_BOOK;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
