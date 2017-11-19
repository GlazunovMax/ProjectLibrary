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

public class GetByGenre implements Command{
	private static final int COUNT_ROWS_ON_PAGE = 2;
	private static final String PAGE_NUMBER = "pageNumber";
	private static final String ROWS_PER_PAGE = "rowsPerPage";
	private static final String RADIO_BUTTON_GENRE = "radioButton";

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
				page = "WEB-INF/jsp/bookAdminOrClient.jsp";
				request.setAttribute("bookList", bookList);
				request.setAttribute("genreName", genreName);
			}else{
				page = "WEB-INF/jsp/bookAdminOrClient.jsp";
				request.setAttribute("errorGetBy", "Books not found...");
			}
		} catch (ServiceException e) {
			page = "WEB-INF/jsp/bookAdminOrClient.jsp";
			request.setAttribute("errorGetBy", "Books null...");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
