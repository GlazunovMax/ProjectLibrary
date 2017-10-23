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

public class AddGenre implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Genre addGenre = new Genre();
		addGenre.setGenreTitle(request.getParameter("genreTitle"));
		
		ServiceFactory factory = ServiceFactory.getInstance();
		GenreService genreService = factory.getGenreService();
		String page = null;
		
		try {
			genreService.addGenre(addGenre);
			if(addGenre != null){
				request.setAttribute("AddGenre", addGenre);
				page = "addBooks.jsp";
			}
			if(addGenre.getGenreTitle() == null){
				request.setAttribute("AddGenreErrorNull", "Cannot add empty genre");
				page = "addBooks.jsp";
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
//		redirect
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}

}
