package by.htp.library.command.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.library.bean.Book;
import by.htp.library.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class AddBook implements Command {
	
	private static final String BOOK_TITLE = "bookTitle";
	private static final String AUTHOR_NAME = "authorName";
	private static final String PUBLICATION_YEAR = "publicationYear";
	private static final String PUBLISHED_BY_TITLE = "publishedByTitle";
	private static final String GENRE_TITLE = "genreTitle";
	private static final String PHOTO = "photo";
	private static final String PAGE_ADD_BOOK = "http://localhost:8080/WebLibrary/Controller?command=getAllBook&pageNumber=1&AddBookSuccess=Book successful added in library!";
	private static final String PAGE_ADD_BOOK_EXCEPTION = "WEB-INF/jsp/libraryAdminOrClient.jsp";
	private static final String ADD_BOOK_ERROR = "AddBookerror";
	private static final String MESSAGE_ADD_BOOK_ERROR = "Book cannot add";
	private static final String JPEG = "jpeg";
	
	
	/** Implementation of the interface Command - specific commands - Add Book
	 * 
	 * @param request - Client request
	 * @param response - Response from server to client
	 * @throws IOException - exception if an input or output error is detected when the servlet handles request 
	 * @throws ServletException - exception if the request could not be handled 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book addbook = new Book();

		addbook.setBookTitle(request.getParameter(BOOK_TITLE));
		addbook.setAuthorName(request.getParameter(AUTHOR_NAME));
		addbook.setPublicationYear(Integer.parseInt(request.getParameter(PUBLICATION_YEAR)));
		addbook.setPublishedById(request.getParameter(PUBLISHED_BY_TITLE));
		addbook.setGenreId(request.getParameter(GENRE_TITLE));
		addbook.setImage(imageInBytes(request));
		
		String page = null;

		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();
		try {
			bookService.addBook(addbook);
			if (addbook != null) {
				page = PAGE_ADD_BOOK;
				response.sendRedirect(page);
			}
		} catch (ServiceException e) {
			page = PAGE_ADD_BOOK_EXCEPTION;
			request.setAttribute(ADD_BOOK_ERROR, MESSAGE_ADD_BOOK_ERROR);
			e.printStackTrace();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);

		}
	}
	
	/**
	 * 
	 * @param request
	 * @return byte array 
	 * @throws IOException
	 */
	public byte[] imageInBytes(HttpServletRequest request) throws IOException {
			
			File file = new File(request.getParameter(PHOTO));
			BufferedImage img = ImageIO.read(file);
			
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(img, JPEG, byteArrayOutputStream);
			byteArrayOutputStream.flush();
			byte[] imageInbyte = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.close();
			
		return imageInbyte;	
	}
	
}
