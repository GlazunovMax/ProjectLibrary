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

public class AddBook implements Command {
	
	private static final String BOOK_TITLE = "bookTitle";
	private static final String AUTHOR_NAME = "authorName";
	private static final String PUBLICATION_YEAR = "publicationYear";
	private static final String PUBLISHED_BY_TITLE = "publishedByTitle";
	private static final String GENRE_TITLE = "genreTitle";
	private static final String PHOTO = "photo";
	
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
				page = "http://localhost:8080/WebLibrary/Controller?command=getAllBook&pageNumber=1&AddBookSuccess=Book successful added in library!";
				response.sendRedirect(page);
			}
		} catch (ServiceException e) {
			page = "WEB-INF/jsp/libraryAdminOrClient.jsp";
			request.setAttribute("AddBookerror", "Book cannot add ");
			e.printStackTrace();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);

		}
	}
	
	
	public byte[] imageInBytes(HttpServletRequest request) throws IOException{

		System.out.println("S photo "+ request.getParameter(PHOTO));
		
		File file = new File(request.getParameter(PHOTO));
		BufferedImage img = ImageIO.read(file);
		int width = img.getWidth();
        int height = img.getHeight();
        System.out.println("Input image size: " + width + " x " + height + ")\n");
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(img, "jpeg", byteArrayOutputStream);
		byteArrayOutputStream.flush();
		byte[] imageInbyte = byteArrayOutputStream.toByteArray();
		byteArrayOutputStream.close();
		System.out.println("imageInByte.length = " + imageInbyte.length + " image size = " + width * height);
		
		return imageInbyte;
		
	}
	
}
