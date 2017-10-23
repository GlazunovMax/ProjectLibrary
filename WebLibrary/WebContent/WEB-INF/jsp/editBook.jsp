<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="by.htp.library.bean.Book" 
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

<c:if test="${not empty requestScope.showUpdateBook}">

	<h3 style="text-align: center;">Edit book</h3>
	<form class="addForm" action="Controller" method="POST">
		<input type="hidden" name="command" value="UpdateBook">
		<input type="hidden" name="id"	value="${requestScope.showUpdateBook.id}" />
		<table>
			<tr>
				<td align="left">Название книги:</td>
				<td><input type="text" name="bookTitle"
					value="${requestScope.showUpdateBook.bookTitle}" class="changeCell"/></td>
			</tr>

			<tr>
				<td align="left">Автор:</td>
				<td>
					<select class="changeCell"	name="authorName">
						<option selected value="requestScopeAuthorName">${requestScope.showUpdateBook.authorName}</option>
							<jsp:useBean id="databaseAuthorDao" class="by.htp.library.dao.impl.DatabaseAuthorDao" scope="application" />
							<c:forEach var="author" items="${databaseAuthorDao.getAllBook()}">
								<option value="<c:out value="${author.authorName}"/>"> ${author.authorName} </option>
							</c:forEach>
					</select>
				
				</td>
			</tr>

			<tr>
				<td align="left">Год издания:</td>
				<td><input type="text" name="publicationYear" 
					value="${requestScope.showUpdateBook.publicationYear}" class="changeCell">
				</td>
			</tr>

			<tr>
				<td align="left">Издательство:</td>
				<td>
				<select class="changeCell"	name="publishedByTitle">
						<option selected value="requestScopePublishedById">${requestScope.showUpdateBook.publishedById}</option>
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
					<select	class="changeCell" name="genreTitle">
						<option selected value="requestScopeGenre">${requestScope.showUpdateBook.genreId}</option>
							<jsp:useBean id="genreServiceImpl" class="by.htp.library.service.impl.GenreServiceImpl" scope="application" />
							<c:forEach var="genre" items="${genreServiceImpl.getAllGenres()}">
								<option value="<c:out value="${genre.genreTitle}"/>"> ${genre.genreTitle} </option>
							</c:forEach>
					</select>
				</td>
			</tr>
		</table>

		<input type="submit" name="save" value="Сохранить" class="button"/>
	</form>
</c:if>
	
	
	<c:if test="${empty requestScope.showUpdateBook}">
		<h4><c:out value="${requestScope.errorShowUpdateBook}" /> </h4>
	</c:if>
	

	<form class="addForm" action="javascript:history.back();" method="POST">
		<input type="submit" name="DEL" value="Отмена" class="buttonCancel" />
	</form>
	
</body>
</html>