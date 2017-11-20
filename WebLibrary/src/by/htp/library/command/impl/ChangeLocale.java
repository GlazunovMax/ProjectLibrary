package by.htp.library.command.impl;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;

public class ChangeLocale implements Command{
	private static final String LOCALE = "locale";
	private static final String PAGE = "page";
	private static final String LANGUAGE = "language";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = request.getParameter(PAGE);
		//System.out.println("page " + page);
		String language = request.getParameter(LANGUAGE);
		//System.out.println("language " + language);
		
		Locale locale = new Locale(language);
		request.getSession().setAttribute(LOCALE, locale);
		
		request.getRequestDispatcher(page).forward(request, response);
	}

}
