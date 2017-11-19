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

public class RemoveBook implements Command {
	private static final String ID = "id";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book bookRemove = new Book();
		Long idBook = null;

		idBook = Long.parseLong(request.getParameter(ID));
		System.out.println("id= " + request.getParameter(ID));
		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
		String page = null;
		try {
			if (bookRemove != null) {
				bookRemove = bookService.remove(idBook);
				page = "http://localhost:8080/WebLibrary/Controller?command=getAllBook&pageNumber=1&SuccessBookRemove=The book was successfully deleted.";
				response.sendRedirect(page);
			}
		} catch (ServiceException e) {
			request.setAttribute("ErrorBookRemove", "Cannot delete book");
			page = "WEB-INF/jsp/bookAdminOrClient.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

}
