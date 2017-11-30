package by.htp.library.command.impl;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class ChangeLocale implements Command{
	private static final String LOCALE = "locale";
	private static final String PAGE = "page";
	private static final String LANGUAGE = "language";
	
	/** Implementation of the interface Command - specific commands - Change Locale
	 * 
	 * @param request - Client request
	 * @param response - Response from server to client
	 * @throws IOException - exception if an input or output error is detected when the servlet handles request 
	 * @throws ServletException - exception if the request could not be handled 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = request.getParameter(PAGE);
		
		String language = request.getParameter(LANGUAGE);
		
		
		Locale locale = new Locale(language);
		request.getSession().setAttribute(LOCALE, locale);
		
		request.getRequestDispatcher(page).forward(request, response);
	}

}
