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
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<Book> bookList = null;
		int countRow = 0;
		
		System.out.println(request.getParameter(PAGE_NUMBER));
		int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
		
		System.out.println("AAA ");
		
		System.out.println(request.getParameter(ROWS_PER_PAGE));
		if(request.getParameter(ROWS_PER_PAGE) != null){
			countRow = Integer.parseInt(request.getParameter(ROWS_PER_PAGE));
		}else{
			countRow = COUNT_ROWS_ON_PAGE;
		}
		
		
		int start = pageNumber*countRow-countRow;
		System.out.println("rows = " + start);

		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
		String page = null;
		String role = request.getParameter("search");
		System.out.println("role = " + request.getParameter("search"));
		
		try {
			bookList = bookService.getAll(start, countRow);
			
			if(!bookList.isEmpty()){
				if(role.equals("searchClient")) page = "libraryClient.jsp";
				if(role.equals("searchAdmin"))	page = "WEB-INF/jsp/libraryAdmin.jsp";
				request.setAttribute("allBookList", bookList);
			}else{
				if(role.equals("searchClient")) page = "libraryClient.jsp";
				if(role.equals("searchAdmin"))	page = "WEB-INF/jsp/libraryAdmin.jsp";
				request.setAttribute("errorGetAll", "Books not found...");
			}
			
		} catch (ServiceException e) {
			request.setAttribute("errorGetAll", "List books is empty...");
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
		
	

}