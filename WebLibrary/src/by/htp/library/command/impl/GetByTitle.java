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

public class GetByTitle implements Command {
	private static final int COUNT_ROWS_ON_PAGE = 2;
	private static final String PAGE_NUMBER = "pageNumber";
	private static final String ROWS_PER_PAGE = "rowsPerPage";
	private static final String SEARCH_STRING = "search_String";
	
	private static final String PAGE_GET_BY_TITLE = "WEB-INF/jsp/bookAdminOrClient.jsp";
	private static final String BOOK_LIST_AUTHOR_OR_TITLE = "bookListAuthorOrTitle";
	private static final String BOOK_AUTHOR_OR_TITLE = "bookAuthorOrTitle";
	private static final String ERROR_GET_BY = "errorGetBy";
	private static final String MESSAGE_GET_BY_EMPTY = "Books not found...";
	private static final String MESSAGE_GET_BY_EXCEPTION = "Books not found is null...";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookAuthorOrTitle;
		List<Book> bookList = null;

		bookAuthorOrTitle = request.getParameter(SEARCH_STRING);

		int countRow = 0;
		System.out.println(request.getParameter(PAGE_NUMBER));
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
			bookList = bookService.getByTitle(bookAuthorOrTitle, start, countRow);
			if (!bookList.isEmpty()) {
				page = PAGE_GET_BY_TITLE;
				request.setAttribute(BOOK_LIST_AUTHOR_OR_TITLE, bookList);
				request.setAttribute(BOOK_AUTHOR_OR_TITLE, bookAuthorOrTitle);
			} else {
				page = PAGE_GET_BY_TITLE;
				request.setAttribute(ERROR_GET_BY, MESSAGE_GET_BY_EMPTY);
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_GET_BY, MESSAGE_GET_BY_EXCEPTION);
			page = PAGE_GET_BY_TITLE;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
