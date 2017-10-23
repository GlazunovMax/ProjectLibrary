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

public class AddBook implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book addbook = new Book();
		
		
		addbook.setBookTitle(request.getParameter("bookTitle"));
		addbook.setAuthorName(request.getParameter("authorName"));
		addbook.setPublicationYear(Integer.parseInt(request.getParameter("publicationYear")));
		addbook.setPublishedById(request.getParameter("publishedByTitle"));
		addbook.setGenreId(request.getParameter("genreTitle"));
		
		String page = null;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
		try {
			bookService.addBook(addbook);
			if(addbook != null){
				request.setAttribute("AddBook", addbook);
				request.setAttribute("AddBookSuccess", "Book add ");
				page = "WEB-INF/jsp/SuccessAddBook.jsp";
			}
		} catch (ServiceException e) {
			page = "WEB-INF/jsp/SuccessAddBook.jsp";
			request.setAttribute("AddBookerror", "Book cannot add ");
			e.printStackTrace();
		}
	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
