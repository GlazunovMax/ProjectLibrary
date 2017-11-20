<%@ page language="java" 
			contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"%>
    
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/menuLibraryAdmin.css" rel="stylesheet" type="text/css">
</head>
<body>


<div class="header_menu">
 	<h1 align="center" style="margin-bottom: 0px;" title="<fmt:message key="titleLibrary"/>">
	  <fmt:message key="titleLibrary"/>
	</h1>

<%-- <a href="Controller?language=en_/WEB-INF/jsp/fragment/menuAdminOrClient.jsp "><fmt:message key="english"/></a><span>/</span> --%>
<%-- <a href="Controller?language=ru_/WEB-INF/jsp/fragment/menuAdminOrClient.jsp "><fmt:message key="russian"/></a> --%>
<a href="Controller?language=en&page=/WEB-INF/jsp/fragment/menuAdminOrClient.jsp&command=changeLocale "><fmt:message key="english"/></a><span>/</span>
<a href="Controller?language=ru&page=/WEB-INF/jsp/fragment/menuAdminOrClient.jsp&command=changeLocale "><fmt:message key="russian"/></a>	
	
	<h4><fmt:message key="welcom"/><c:out value=" ${sessionScope.login}"/> !</h4>
	
<%-- 	role = ///<c:out value="${sessionScope.role}"/> --%>
	<c:if test="${sessionScope.role eq 'admin'}">
		<a href="addBooks.jsp"> <fmt:message key="addBook"/> </a><span>/</span>
		<a href="registrationAdmin.jsp"><fmt:message key="registerAsAdmin"/> </a><span>/</span> 
	</c:if>
	<a href="ShowCookies.jsp"> <fmt:message key="showCookies"/> </a><span>/</span>
	<a href="index.jsp"> <fmt:message key="exit"/> </a> 
	
	<br>
	
	
<div class="search_div">
	<form class="search_form" action="Controller" name="search_form" method="post">
		<input type="hidden" name="pageNumber" value="1" />  
		<input type="text" name="search_String" value="" size="50" /> 
		<input class="search_button" type="submit" value="<fmt:message key="search"/>" name="search_button/"> 
			<select	name="search_option">
				<option value="byTitle"> <fmt:message key="title"/></option>
				<option value="byAuthor"> <fmt:message key="author"/></option>
			</select>
	</form>
</div>	
	
	<form class="search_form" action="Controller" method="post">
		<input type="hidden" name="command" value="getAllBook" /> 
		<input type="hidden" name="pageNumber" value="1" /> 
		<h3 align="left" ><input class="search_button" type="submit" value="<fmt:message key="allBooks"/>"/></h3>
	</form>

	<hr>
	
</div>	
	
<div class="left_menu">
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="getByGenre" />   
		<input type="hidden" name="pageNumber" value="1" /> 
				
		<h4><fmt:message key="listGenres"/></h4>
			<jsp:useBean id="genreServiceImpl" class="by.htp.library.service.impl.GenreServiceImpl" scope="application" />
				<c:forEach var="genre" items="${genreServiceImpl.getAllGenres()}">
					<input type="radio" name="radioButton" value="<c:out value="${genre.genreTitle}"/>" /> ${genre.genreTitle} <br>
				</c:forEach>
							
		<input class="search_button" type="submit" value="<fmt:message key="searchGenre"/>"/>
	</form>
</div>			
	
</body>
</html>