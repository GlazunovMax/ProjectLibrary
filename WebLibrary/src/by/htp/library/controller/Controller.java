package by.htp.library.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.library.bean.Book;
import by.htp.library.command.Command;
import by.htp.library.dbConnection.ConnectionPool;
import by.htp.library.dbConnection.ConnectionPoolException;

import by.htp.library.dbConnection.FactoryConnectionPool;



public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final CommandProvider provider = new CommandProvider();
	

	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		try {
			connectionPool.initPoolData();
			System.out.println("Connection Pool ok");
			
		} catch (ConnectionPoolException e) {
			System.out.println("error Pool");
		}
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		super.service(arg0, arg1);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		image
		response.setContentType("image/jpeg");
		OutputStream outputStream = response.getOutputStream();
		try{
//			 URL imageURL = Controller.class.getResource("qq.jpg");
//	            BufferedImage bi = ImageIO.read(imageURL);
//	            ImageIO.setUseCache(false);
//	            ImageIO.write(bi, "jpeg", outputStream);
			
			int index = Integer.valueOf(request.getParameter("index")); 
			
			@SuppressWarnings("unchecked")
			ArrayList<Book> list = (ArrayList<Book>)request.getSession(false).getAttribute("listBook");
			Book book = list.get(index);
			response.setContentLength(book.getImage().length);
			outputStream.write(book.getImage());
		}finally{
			outputStream.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// для отображения картинок 
		processRequest(request, response);
		
		System.out.println(request.getParameter("search_option"));
	//	System.out.println(request.getParameter("search_String"));
		
//		String commandName = null;
		
//		if(Integer.parseInt(request.getParameter("pageNumber")) != 0){
//			commandName = "getAllBook";
//		}
//		if(request.getParameter("search_option") != null){
//			if(request.getParameter("search_option").equals("byTitle")){commandName = "getByTitle";}
//			if(request.getParameter("search_option").equals("byAuthor")){commandName = "getByAuthor";}
//		}
		
		
	
		//commandName = request.getParameter("command");//????
//		System.out.println(commandName);
//		Command command = provider.getCommand(commandName);
//		command.execute(request, response);
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//processRequest(request, response);
		
		String name = request.getParameter("name");
		String login = request.getParameter("login");
			if ((name != null) && (login != null)) {
				Cookie cookie = new Cookie(name, login);
				response.addCookie(cookie);
			}
		
		String commandName = null;
		
		if(request.getParameter("search_option") != null){
			if(request.getParameter("search_option").equals("byTitle")){commandName = "getByTitle";}
			if(request.getParameter("search_option").equals("byAuthor")){commandName = "getByAuthor";}
		}
		
		if(request.getParameter("command") != null){
			commandName = request.getParameter("command");
		}
		
		System.out.println(commandName);
		
		Command command = provider.getCommand(commandName);
		
		command.execute(request, response);
	}

	@Override
	public void destroy() {
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		connectionPool.dispose();
		System.out.println("pool close");
	}

}
