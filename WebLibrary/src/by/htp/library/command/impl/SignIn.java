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
				session.setAttribute("role", user.getRole());
				request.setAttribute("user", user);
				page = "WEB-INF/jsp/libraryAdminOrClient.jsp"; 
			} else {
				request.setAttribute("errorSingIn", "User not registered...");
				page = "signIn.jsp";
			}
		} catch (ServiceException e1) {
			request.setAttribute("error", "wrong password or login");
			page = "signIn.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

}
