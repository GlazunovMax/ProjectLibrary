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
public class UpdateBook implements Command {
	private static final String BOOK_TITLE = "bookTitle";
	private static final String PUBLICATION_YEAR = "publicationYear";
	private static final String ID = "id";
	
	private static final String PAGE_UPDATE = "http://localhost:8080/WebLibrary/Controller?command=getAllBook&pageNumber=1&BookSuccessUpdate=Book changed successfully!";
	private static final String PAGE_UPDATE_EXCEPTION = "WEB-INF/jsp/editBook.jsp";
	private static final String ERROR_BOOK_UPDATE = "ErrorBookUpdate";
	private static final String MESSAGE_ERROR_BOOK_UPDATE = "Cannot update, input fields are empty";
	
	
	/** Implementation of the interface Command - specific commands - Update Book
	 * 
	 * @param request - Client request
	 * @param response - Response from server to client
	 * @throws IOException - exception if an input or output error is detected when the servlet handles request 
	 * @throws ServletException - exception if the request could not be handled 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Book book = new Book();

		book.setBookTitle(request.getParameter(BOOK_TITLE));
		book.setPublicationYear(Integer.parseInt(request.getParameter(PUBLICATION_YEAR)));
		book.setId(Long.parseLong(request.getParameter(ID)));

		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
		String page = null;

		try {
			bookService.updateBook(book);
			if (book != null) {
				page = PAGE_UPDATE;
				response.sendRedirect(page);
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_BOOK_UPDATE, MESSAGE_ERROR_BOOK_UPDATE);
			page = PAGE_UPDATE_EXCEPTION;
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

}
