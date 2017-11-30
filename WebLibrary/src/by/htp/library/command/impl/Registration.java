package by.htp.library.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.library.bean.User;
import by.htp.library.command.Command;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class Registration implements Command {
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String ROLE = "role";
	
	private static final String USER = "user";
	private static final String PAGE_REGISTRATION = "WEB-INF/jsp/libraryAdminOrClient.jsp";
	private static final String PAGE_REGISTRATION_CLIENT = "registration.jsp";
	private static final String PAGE_REGISTRATION_ADMIN = "registrationAdmin.jsp";
	private static final String ERROR_REGISTRATION = "errorRegistration";
	private static final String MESSAGE_ERROR_REGISTRATION = "Incorrect data";
	private static final String CLIENT = "client";

	
	/** Implementation of the interface Command - specific commands - Registration
	 * 
	 * @param request - Client request
	 * @param response - Response from server to client
	 * @throws IOException - exception if an input or output error is detected when the servlet handles request 
	 * @throws ServletException - exception if the request could not be handled 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();

		user.setName(request.getParameter(NAME)); 
		user.setSurname(request.getParameter(SURNAME));
		user.setLogin(request.getParameter(LOGIN));
		user.setPassword(request.getParameter(PASSWORD));
		user.setRole(request.getParameter(ROLE));

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		String page = null;
		HttpSession session = request.getSession(true);
		
		try {
			userService.registration(user);
			if (user != null) {
				session.setAttribute(ROLE, user.getRole());
				request.setAttribute(USER, user);
				page = PAGE_REGISTRATION;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_REGISTRATION, MESSAGE_ERROR_REGISTRATION);
			if(user.getRole().equals(CLIENT)){
				page = PAGE_REGISTRATION_CLIENT;
			}else{
				page = PAGE_REGISTRATION_ADMIN;
			}
			
		}

		String login = request.getParameter(LOGIN);
		session.setAttribute(LOGIN, login);

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

}
