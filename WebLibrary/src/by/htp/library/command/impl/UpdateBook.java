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

public class UpdateBook implements Command {
	private static final String BOOK_TITLE = "bookTitle";
	private static final String PUBLICATION_YEAR = "publicationYear";
	private static final String ID = "id";
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
				page = "http://localhost:8080/WebLibrary/Controller?command=getAllBook&pageNumber=1&BookSuccessUpdate=Book changed successfully!";
				response.sendRedirect(page);
			}
		} catch (ServiceException e) {
			request.setAttribute("ErrorBookUpdate", "Cannot update, input fields are empty");
			page = "WEB-INF/jsp/editBook.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

}
