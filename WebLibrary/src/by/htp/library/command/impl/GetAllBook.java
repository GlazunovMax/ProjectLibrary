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

public class GetAllBook implements Command {
	private static final int COUNT_ROWS_ON_PAGE = 2;
	private static final String PAGE_NUMBER = "pageNumber";
	private static final String ROWS_PER_PAGE = "rowsPerPage";
	private static final String ADD_BOOK_SUCCESS = "AddBookSuccess";
	private static final String SUCCESS_BOOK_REMOVE = "SuccessBookRemove";
	private static final String BOOK_SUCCESS_UPDATE = "BookSuccessUpdate";
	private static final String ALL_BOOK_LIST = "allBookList";
	private static final String PAGE_GET_ALL_SUCCESS_OR_EXCEPTION = "WEB-INF/jsp/libraryAdminOrClient.jsp";
	private static final String ERROR_GET_ALL = "errorGetAll";
	private static final String MESSAGE_GET_ALL_BOOK_EMPTY = "Books not found...";
	private static final String MESSAGE_GET_ALL_BOOK_EXCEPTION = "List books is empty...";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<Book> bookList = null;
		int countRow = 0;
		
		System.out.println(request.getParameter(PAGE_NUMBER));
		int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
		
		System.out.println(request.getParameter(ROWS_PER_PAGE));
		if(request.getParameter(ROWS_PER_PAGE) != null){
			countRow = Integer.parseInt(request.getParameter(ROWS_PER_PAGE));
		}else{
			countRow = COUNT_ROWS_ON_PAGE;
		}
		
		int start = pageNumber*countRow-countRow;

		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
		
		String page = null;
		
		String addBookSuccess = request.getParameter(ADD_BOOK_SUCCESS);
		String successBookRemove = request.getParameter(SUCCESS_BOOK_REMOVE);
		String bookSuccessUpdate = request.getParameter(BOOK_SUCCESS_UPDATE);
		
		try {
			bookList = bookService.getAll(start, countRow);
			
			if(!bookList.isEmpty()){		
				page = PAGE_GET_ALL_SUCCESS_OR_EXCEPTION;
				request.setAttribute(ALL_BOOK_LIST, bookList);
				request.setAttribute(ADD_BOOK_SUCCESS, addBookSuccess);
				request.setAttribute(SUCCESS_BOOK_REMOVE, successBookRemove);
				request.setAttribute(BOOK_SUCCESS_UPDATE, bookSuccessUpdate);
			}else{
				page = PAGE_GET_ALL_SUCCESS_OR_EXCEPTION;
				request.setAttribute(ERROR_GET_ALL, MESSAGE_GET_ALL_BOOK_EMPTY);
			}
			
		} catch (ServiceException e) {
			request.setAttribute(ERROR_GET_ALL, MESSAGE_GET_ALL_BOOK_EXCEPTION);
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
		
	

}
