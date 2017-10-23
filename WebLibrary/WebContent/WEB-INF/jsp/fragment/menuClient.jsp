<%@ page language="java" 
		 contentType="text/html; charset=UTF-8" 
		 import="by.htp.library.bean.User"
		 import="by.htp.library.bean.Genre"
    	 pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="header_menu">
	<h1 align="center" style="margin-bottom: 0px;" title="Онлайн библиотека ">
		Онлайн библиотека
	</h1>
	
	<h4><c:out value="Добро пожаловать, ${sessionScope.login}"/> !</h4>
	
	<a href="ShowCookies.jsp"> Показать куки </a><span>/</span>
	<a href="index.jsp"> Выход </a>
	
	
	<br>

	<form  action="Controller" class="search_form" name="search_form" method="post">
		<input type="hidden" name="search" value="searchClient"/>
		<input type="hidden" name="pageNumber" value="1" /> 
		<input type="text" name="search_String" value="" size="50" /> 
		<input type="submit" value="Поиск" name="search_button/"> 
			<select	name="search_option">
				<option value="byTitle">Название</option>
				<option value="byAuthor">Автор</option>
			</select>
	</form>
	
	<form class="search_form" action="Controller" method="post">
		<input type="hidden" name="command" value="getAllBook" /> 
		<input type="hidden" name="pageNumber" value="1" />
		<input type="hidden" name="search" value="searchClient"/>
		<h3 align="left" ><input type="submit" value="Все книги"/></h3>
	</form>

	<hr>
	
</div>

	<div class="left_menu">
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="getByGenre" />  
			<input type="hidden" name="search" value="searchClient"/>
			<input type="hidden" name="pageNumber" value="1" />
						
			<h4>Список жанров:</h4>
				<jsp:useBean id="genreServiceImpl" class="by.htp.library.service.impl.GenreServiceImpl" scope="application" />
					<c:forEach var="genre" items="${genreServiceImpl.getAllGenres()}">
						<input type="radio" name="simple" value="<c:out value="${genre.genreTitle}" />" /> ${genre.genreTitle} <br>
					</c:forEach>			
				<input type="submit" value="Поиск по жанрам"/>
		</form>
	</div>
	
	
</body>
</html>