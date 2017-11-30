package by.htp.library.command.impl;

import java.io.IOException;
import java.util.List;
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
public class GetAllGenre implements Command{
	
	private static final String GENRE_TITLE = "genre_title";
	private static final String GENRE = "genre";
	private static final String GENRE_EMPTY = "genreEmpty";
	private static final String MESSAGE_GENRE_EMPTY = "There are no genres listed.";
	private static final String PAGE_ERROR = "WEB-INF/error/error.jsp";
	
	
	/** Implementation of the interface Command - specific commands - Get All Genre
	 * 
	 * @param request - Client request
	 * @param response - Response from server to client
	 * @throws IOException - exception if an input or output error is detected when the servlet handles request 
	 * @throws ServletException - exception if the request could not be handled 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Genre> genreList = null;
		Genre genre = new Genre();
		
		genre.setGenreTitle(request.getParameter(GENRE_TITLE));
	
		ServiceFactory factory = ServiceFactory.getInstance();
		GenreService genreService = factory.getGenreService();
		String page = null;
		
		try {  
			genreList = genreService.getAllGenres();
			request.setAttribute(GENRE, genreList);	
		} catch (ServiceException e) {
			request.setAttribute(GENRE_EMPTY, MESSAGE_GENRE_EMPTY);
			page = PAGE_ERROR;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
