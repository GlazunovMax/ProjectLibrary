package by.htp.library.command.impl;

import java.io.IOException;
import java.util.List;
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
public class GetByGenre implements Command{
	private static final int COUNT_ROWS_ON_PAGE = 2;
	private static final String PAGE_NUMBER = "pageNumber";
	private static final String ROWS_PER_PAGE = "rowsPerPage";
	private static final String RADIO_BUTTON_GENRE = "radioButton";

	private static final String PAGE_GET_BY_GENRE = "WEB-INF/jsp/bookAdminOrClient.jsp";
	private static final String BOOK_LIST = "bookList";
	private static final String GENRE_NAME = "genreName";
	private static final String ERROR_GET_BY = "errorGetBy";
	private static final String MESSAGE_GET_BY_EMPTY = "Books not found...";
	private static final String MESSAGE_GET_BY_EXCEPTION = "Books null...";
	
	
	/** Implementation of the interface Command - specific commands - Get By Genre
	 * 
	 * @param request - Client request
	 * @param response - Response from server to client
	 * @throws IOException - exception if an input or output error is detected when the servlet handles request 
	 * @throws ServletException - exception if the request could not be handled 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String genreName;
		List<Book> bookList = null;

		genreName = request.getParameter(RADIO_BUTTON_GENRE);
		
		int countRow = 0;
		int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
	
		if(request.getParameter(ROWS_PER_PAGE) != null){
			countRow = Integer.parseInt(request.getParameter(ROWS_PER_PAGE));
		}else{
			countRow = COUNT_ROWS_ON_PAGE;
		}
		
		int start = pageNumber*countRow-countRow;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
		
		String page = null;
		
		try {
			bookList = bookService.getByGenre(genreName, start, countRow);
			
			if(!bookList.isEmpty()){
				page = PAGE_GET_BY_GENRE;
				request.setAttribute(BOOK_LIST, bookList);
				request.setAttribute(GENRE_NAME, genreName);
			}else{
				page = PAGE_GET_BY_GENRE;
				request.setAttribute(ERROR_GET_BY, MESSAGE_GET_BY_EMPTY);
			}
		} catch (ServiceException e) {
			page = PAGE_GET_BY_GENRE;
			request.setAttribute(ERROR_GET_BY, MESSAGE_GET_BY_EXCEPTION);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
