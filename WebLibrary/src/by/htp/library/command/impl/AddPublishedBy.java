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

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class AddPublishedBy implements Command{
	
	private static final String PUBLISHED_BY_TITLE = "publishedByTitle";
	private static final String ADD_PUBLISHED_BY = "AddPublishedBy";
	private static final String ADD_BOOKS_PAGE = "addBooks.jsp";
	private static final String ADD_PUBLISHED_ERROR_NULL = "AddPublishedErrorNull";
	private static final String MESSAGE_ADD_PUBLISHED_ERROR_NULL = "Cannot add empty published by";
	
	
	/** Implementation of the interface Command - specific commands - Add Published By
	 * 
	 * @param request - Client request
	 * @param response - Response from server to client
	 * @throws IOException - exception if an input or output error is detected when the servlet handles request 
	 * @throws ServletException - exception if the request could not be handled 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublishedBy addPublishedBy = new PublishedBy();
		addPublishedBy.setPublishedByTitle(request.getParameter(PUBLISHED_BY_TITLE));
		
		ServiceFactory factory = ServiceFactory.getInstance();
		PublishedByService publishedByService = factory.getPublishedByService();
		String page = null;
		
		try {
			publishedByService.addPublishedBy(addPublishedBy);
			if(addPublishedBy != null){
				request.setAttribute(ADD_PUBLISHED_BY, addPublishedBy);
				page = ADD_BOOKS_PAGE;
			}
			if(addPublishedBy.getPublishedByTitle() == null){
				request.setAttribute(ADD_PUBLISHED_ERROR_NULL, MESSAGE_ADD_PUBLISHED_ERROR_NULL);
				page = ADD_BOOKS_PAGE;
			}
		} catch (ServiceException e) {
			request.setAttribute(ADD_PUBLISHED_ERROR_NULL, MESSAGE_ADD_PUBLISHED_ERROR_NULL);
			page = ADD_BOOKS_PAGE;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}

}
