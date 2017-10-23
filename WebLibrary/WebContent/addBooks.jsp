<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="css/addBookCss.css" rel="stylesheet" type="text/css">
</head>
<body>

<h3 style="text-align: center;">Добавить Книгу</h3>
	<form class="addForm" action="Controller" method="post">
	<input type="hidden" name="command" value="addBook" /> 
	
	<table>
			<tr>
				<td align="left">Название книги:</td>
				<td><input type="text" name="bookTitle"	value="" class="changeCell" /></td>
			</tr>

			<tr>
				<td align="left">Автор:</td>
				<td>
					<select	name="authorName" class="changeCell">
						
							<jsp:useBean id="authorServiceImpl" class="by.htp.library.service.impl.AuthorServiceImpl" scope="application" />
							<c:forEach var="author" items="${authorServiceImpl.getAllAuthors()}">
								<option value="<c:out value="${author.authorName}"/>"> ${author.authorName} </option>
							</c:forEach>
					</select>
				
				</td>
			</tr>

			<tr>
				<td align="left">Год издания:</td>
				<td>
					<input type="text" name="publicationYear" value="" class="changeCell">
				</td>
			</tr>

			<tr>
				<td align="left">Издательство:</td>
				<td>
				<select	name="publishedByTitle" class="changeCell">
						
							<jsp:useBean id="databasePublishedByDao" class="by.htp.library.dao.impl.DatabasePublishedByDao" scope="application" />
							<c:forEach var="publishedBy" items="${databasePublishedByDao.getAllPublishedBy()}">
								<option value="<c:out value="${publishedBy.publishedByTitle}"/>"> ${publishedBy.publishedByTitle} </option>
							</c:forEach>
					</select>
				
				</td>
			</tr>

			<tr>
				<td align="left">Жанр:</td>
				<td>
					<select	name="genreTitle" class="changeCell">
						
							<jsp:useBean id="genreServiceImpl" class="by.htp.library.service.impl.GenreServiceImpl" scope="application" />
							<c:forEach var="genre" items="${genreServiceImpl.getAllGenres()}">
								<option value="<c:out value="${genre.genreTitle}"/>"> ${genre.genreTitle} </option>
							</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		
		<input type="submit" value="send"  class="button"/>
	</form>
	
	<form class="addForm" action="javascript:history.back();" method="POST">
		<input type="submit"  value="Отмена" class="buttonCancel"/>
	</form>

	
<!-- 	ДОбавляем автора если нет -->
	<form action="Controller" method="POST">
	  	<h2 style="margin-bottom: 0px; margin-top: 0px;">Add author :</h2>
	    <input type="hidden" name="command" value="addAuthor" />
		<input type="text" name="authorName" value="" class="add_textbox"/>
		<input type="submit" value="Добавить" class="buttonCancel"/>
	</form>
<!-- Сообщение о добавлении автора -->
	<c:if test="${not empty requestScope.AddAuthor}">
		<h4>Author : <c:out value="${requestScope.AddAuthor.authorName}" /> added successfully!</h4>
	</c:if>
<!-- Сообщение о ошибке добавлении автора -->
	<c:if test="${not empty requestScope.AddAuthorErrorNull}">
		<h4><c:out value="${requestScope.AddAuthorErrorNull}" /> </h4>
	</c:if>
	
	
	<!-- 	ДОбавляем издательство если нет -->
		<form action="Controller" method="POST">
			<h2 style="margin-bottom: 0px; margin-top: 0px;">Add published by :</h2> 
		    <input type="hidden" name="command" value="addPublished" />
			<input type="text" name="publishedByTitle" value="" class="add_textbox" />
			<input type="submit" value="Добавить" class="buttonCancel"/>
		</form>
	<!-- Сообщение о добавлении автора -->
		<c:if test="${not empty requestScope.AddPublishedBy}">
			<h4>Published by : <c:out value="${requestScope.AddPublishedBy.publishedByTitle}" /> added successfully!</h4>
		</c:if>
	<!-- Сообщение о ошибке добавлении автора -->
		<c:if test="${not empty requestScope.AddPublishedErrorNull}">
			<h4><c:out value="${requestScope.AddPublishedErrorNull}" /> </h4>
		</c:if>	
	
	
<!-- 	ДОбавляем жанр если нет -->
	<form action="Controller" method="POST">
		<h2 style="margin-bottom: 0px; margin-top: 0px;">Add genre :</h2> 
	    <input type="hidden" name="command" value="addGenre" />
		<input type="text" name="genreTitle" value="" class="add_textbox"/>
		<input type="submit" value="Добавить" class="buttonCancel"/>
	</form>
<!-- Сообщение о добавлении жанра -->
	<c:if test="${not empty requestScope.AddGenre}">
		<h4>Genre : <c:out value="${requestScope.AddGenre.genreTitle}" /> added successfully!</h4>
	</c:if>
<!-- Сообщение о ошибке добавлении жанра -->
	<c:if test="${not empty requestScope.AddGenreErrorNull}">
		<h4><c:out value="${requestScope.AddGenreErrorNull}" /> </h4>
	</c:if>	
	
				
</body>
</html>