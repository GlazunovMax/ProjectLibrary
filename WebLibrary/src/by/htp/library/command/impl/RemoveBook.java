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
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class RemoveBook implements Command {
	private static final String ID = "id";
	private static final String PAGE_REMOVE = "http://localhost:8080/WebLibrary/Controller?command=getAllBook&pageNumber=1&SuccessBookRemove=The book was successfully deleted.";
	private static final String ERROR_BOOK_REMOVE = "ErrorBookRemove";
	private static final String MESSAGE_ERROR_BOOK_REMOVE = "Cannot delete book";
	private static final String PAGE_REMOVE_EXCEPTION = "WEB-INF/jsp/bookAdminOrClient.jsp";

	
	/** Implementation of the interface Command - specific commands - Remove Book
	 * 
	 * @param request - Client request
	 * @param response - Response from server to client
	 * @throws IOException - exception if an input or output error is detected when the servlet handles request 
	 * @throws ServletException - exception if the request could not be handled 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book bookRemove = new Book();
		Long idBook = null;

		idBook = Long.parseLong(request.getParameter(ID));
		
		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
		String page = null;
		try {
			if (bookRemove != null) {
				bookRemove = bookService.remove(idBook);
				page = PAGE_REMOVE;
				response.sendRedirect(page);
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_BOOK_REMOVE, MESSAGE_ERROR_BOOK_REMOVE);
			page = PAGE_REMOVE_EXCEPTION;
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

}
