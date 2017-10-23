package by.htp.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.library.bean.PublishedBy;
import by.htp.library.command.Command;
import by.htp.library.service.PublishedByService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

public class AddPublishedBy implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublishedBy addPublishedBy = new PublishedBy();
		addPublishedBy.setPublishedByTitle(request.getParameter("publishedByTitle"));
		
		ServiceFactory factory = ServiceFactory.getInstance();
		PublishedByService publishedByService = factory.getPublishedByService();
		String page = null;
		
		try {
			publishedByService.addPublishedBy(addPublishedBy);
			if(addPublishedBy != null){
				request.setAttribute("AddPublishedBy", addPublishedBy);
				page = "addBooks.jsp";
			}
			if(addPublishedBy.getPublishedByTitle() == null){
				request.setAttribute("AddPublishedErrorNull", "Cannot add empty published by");
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
