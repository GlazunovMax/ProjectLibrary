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
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookAuthorOrTitle;
		String searchOption;
		List<Book> bookList = null;

		bookAuthorOrTitle = request.getParameter("search_String");

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
		String role = request.getParameter("search");

		try {
			bookList = bookService.getByTitle(bookAuthorOrTitle, start, countRow);
			if (!bookList.isEmpty()) {
				if (role.equals("searchClient"))
					page = "WEB-INF/jsp/bookClient.jsp";
				if (role.equals("searchAdmin"))
					page = "WEB-INF/jsp/bookAdmin.jsp";
				request.setAttribute("bookListAuthorOrTitle", bookList);
				request.setAttribute("bookAuthorOrTitle", bookAuthorOrTitle);
			} else {
				if (role.equals("searchClient"))
					page = "WEB-INF/jsp/bookClient.jsp";
				if (role.equals("searchAdmin"))
					page = "WEB-INF/jsp/bookAdmin.jsp";
				request.setAttribute("errorGetBy", "Books not found...");
			}
		} catch (ServiceException e) {
			request.setAttribute("errorGetBy", "Books not found is empty");

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
