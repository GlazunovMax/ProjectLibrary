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

public class ShowUpdateBook implements Command{
	private static final String ID = "id";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Book book = new Book();
		Long idBook = null;
		
		idBook = Long.parseLong(request.getParameter(ID)); 
		System.out.println( "ID = "+ idBook);
		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
		String page = null;
		
		try {
			book = bookService.showUpdateBook(idBook);
			if(book!=null){
				System.out.println(book.getAuthorName()+ "" + book.getBookTitle() + " " +book.getId() );
				request.setAttribute("showUpdateBook", book);
				page = "WEB-INF/jsp/editBook.jsp";
			}else{
				request.setAttribute("errorShowUpdateBook", "Error date in else...");
				page = "WEB-INF/jsp/editBook.jsp";
			}
			
		} catch (ServiceException e) {
			request.setAttribute("errorShowUpdateBook", "Error date.errorShowUpdateBook..");
			page = "WEB-INF/jsp/editBook.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
