package by.htp.library.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.library.bean.User;
import by.htp.library.command.Command;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

public class SignIn implements Command {
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String ROLE = "role";
	private static final String USER = "user";
	private static final String PAGE_SING_IN = "WEB-INF/jsp/libraryAdminOrClient.jsp";
	private static final String PAGE_SING_IN_EXCEPTION = "signIn.jsp";
	
	private static final String ERROR_SING_IN = "errorSingIn";
	private static final String MESSAGE_ERROR_SING_IN = "User not registered...";
	
	private static final String ERROR = "error";
	private static final String MESSAGE_ERROR = "wrong password or login";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cookie[] reqCookie = request.getCookies();
		if (reqCookie != null) {
			for (Cookie cookie : reqCookie) {
				response.getWriter().println(cookie.getName() + " - " + cookie.getValue() + "<br/>");
			}
		} else {
			response.getWriter().println("No cookies");
		}

		String login;
		HttpSession session = request.getSession(true);
		login = request.getParameter(LOGIN);
		session.setAttribute(LOGIN, login);

		String password;
		User user = new User();

		login = request.getParameter(LOGIN);
		password = request.getParameter(PASSWORD);

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		String page = null;

		try {
			user = userService.signIn(login, password);
			if (user != null) {
				session.setAttribute(ROLE, user.getRole());
				request.setAttribute(USER, user);
				page = PAGE_SING_IN; 
			} else {
				request.setAttribute(ERROR_SING_IN, MESSAGE_ERROR_SING_IN);
				page = PAGE_SING_IN_EXCEPTION;
			}
		} catch (ServiceException e1) {
			request.setAttribute(ERROR, MESSAGE_ERROR);
			page = PAGE_SING_IN_EXCEPTION;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

}
