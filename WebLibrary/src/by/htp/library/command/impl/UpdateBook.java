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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Book book = new Book();

		book.setBookTitle(request.getParameter("bookTitle"));
		// book.setAuthorName(request.getParameter("authorName"));
		book.setPublicationYear(Integer.parseInt(request.getParameter("publicationYear")));
		// book.setPublishedById(Integer.parseInt(request.getParameter("publishedByTitle")));
		// book.setGenreId(Integer.parseInt(request.getParameter("genreTitle")));
		book.setId(Long.parseLong(request.getParameter("id")));

		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
		String page = null;

		try {
			bookService.updateBook(book);
			if (book != null) {
				request.setAttribute("BookUpdate", book);
				request.setAttribute("BookSuccessUpdate", "Book changed successfully)");
				page = "WEB-INF/jsp/bookAdmin.jsp";
			}
		} catch (ServiceException e) {
			request.setAttribute("ErrorBookUpdate", "Cannot update, input fields are empty");
			page = "WEB-INF/jsp/editBook.jsp";
		}

		//response.sendRedirect(page);
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

}
