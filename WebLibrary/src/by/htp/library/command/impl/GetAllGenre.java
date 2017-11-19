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

public class GetAllGenre implements Command{
	
	private static final String GENRE_TITLE = "genre_title";

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
			request.setAttribute("genre", genreList);	
		} catch (ServiceException e) {
			request.setAttribute("genreEmpty", "There are no genres listed.");
			page = "WEB-INF/error/error.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
