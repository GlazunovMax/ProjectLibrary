package by.htp.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.bean.Genre;
import by.htp.library.command.Command;
import by.htp.library.service.GenreService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class AddGenre implements Command{
	private static final String GENRE_TITLE = "genreTitle";
	private static final String ADD_GENRE = "AddGenre";
	private static final String ADD_BOOKS_PAGE = "addBooks.jsp";
	private static final String ADD_GENRE_ERROR_NULL = "AddGenreErrorNull";
	private static final String MESSAGE_ADD_GENRE_ERROR_NULL = "Cannot add empty genre";
	
	/** Implementation of the interface Command - specific commands - Add Genre
	 * 
	 * @param request - Client request
	 * @param response - Response from server to client
	 * @throws IOException - exception if an input or output error is detected when the servlet handles request 
	 * @throws ServletException - exception if the request could not be handled 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Genre addGenre = new Genre();
		addGenre.setGenreTitle(request.getParameter(GENRE_TITLE));
		
		ServiceFactory factory = ServiceFactory.getInstance();
		GenreService genreService = factory.getGenreService();
		String page = null;
		
		try {
			genreService.addGenre(addGenre);
			if(addGenre != null){
				request.setAttribute(ADD_GENRE, addGenre);
				page = ADD_BOOKS_PAGE;
			}
			if(addGenre.getGenreTitle() == null){
				request.setAttribute(ADD_GENRE_ERROR_NULL, MESSAGE_ADD_GENRE_ERROR_NULL);
				page = ADD_BOOKS_PAGE;
			}

		} catch (ServiceException e) {
			request.setAttribute(ADD_GENRE_ERROR_NULL, MESSAGE_ADD_GENRE_ERROR_NULL);
			page = ADD_BOOKS_PAGE;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}

}
