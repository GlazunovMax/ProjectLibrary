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

public class Registration implements Command {
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String ROLE = "role";

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
				session.setAttribute("role", user.getRole());
				request.setAttribute("user", user);
				page = "WEB-INF/jsp/libraryAdminOrClient.jsp";
			}
		} catch (ServiceException e) {
			request.setAttribute("errorRegistration", "Incorrect data");
			if(user.getRole().equals("client")){
				page = "registration.jsp";
			}else{
				page = "registrationAdmin.jsp";
			}
			
		}

		String login = request.getParameter("login");
		session.setAttribute("login", login);

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

}
