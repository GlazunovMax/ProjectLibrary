package by.htp.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.library.bean.Author;
import by.htp.library.command.Command;
import by.htp.library.service.AuthorService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

public class AddAuthor implements Command {
	private static final String AUTHOR_NAME = "authorName";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Author addAuthor = new Author();
		
		addAuthor.setAuthorName(request.getParameter(AUTHOR_NAME));
		
		ServiceFactory factory = ServiceFactory.getInstance();
		AuthorService authorService = factory.getAuthorService();
		String page = null;
		
		try {
			authorService.addAuthor(addAuthor);
			if(addAuthor != null){
				request.setAttribute("AddAuthor", addAuthor);
				page = "addBooks.jsp";
			}
			if(addAuthor.getAuthorName() == null){
				request.setAttribute("AddAuthorErrorNull", "Cannot add empty author");
				page = "addBooks.jsp";
			}
			
		} catch (ServiceException e) {
			request.setAttribute("AddAuthorErrorNull", "Cannot add empty author");
			page = "addBooks.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}

}
