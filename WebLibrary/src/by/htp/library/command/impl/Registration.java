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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();

		user.setName(request.getParameter("name")); // именовать переменные
		user.setSurname(request.getParameter("surname"));
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		user.setRole(request.getParameter("role"));

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		String page = null;

		try {
			userService.registration(user);
			if (user != null) {
				if (user.getRole().equals("client")) {	// сомманд
					request.setAttribute("user", user);
					page = "WEB-INF/jsp/successRegistr.jsp";
					// page="WEB-INF/jsp/libraryClient.jsp";
				} else if (user.getRole().equals("admin")) {
					request.setAttribute("user", user);
					page = "WEB-INF/jsp/libraryAdmin.jsp";
				}
				
			}
		} catch (ServiceException e) {
			request.setAttribute("errorRegistration", "Incorrect data");
			if(user.getRole().equals("client")){
				page = "registration.jsp";
			}else{
				page = "registrationAdmin.jsp";
			}
			
		}

		HttpSession session = request.getSession(true);
		String login = request.getParameter("login");
		session.setAttribute("login", login);

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

}
