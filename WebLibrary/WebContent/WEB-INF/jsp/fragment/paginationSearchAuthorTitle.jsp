<%@ page language="java" contentType="text/html; charset=UTF-8"
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
</head>
<body>
<div class="right_menu">
	
	<jsp:useBean id="dbCountRows" class="by.htp.library.dao.impl.DatabaseCountRows" scope="request" />

	<c:if test="${param.search_option eq 'byAuthor'}">
		<h4> <fmt:message key="findByAuthor"/> <c:out value="${dbCountRows.getAuthorCount(requestScope.bookAuthorOrTitle)}" /> </h4>
		<c:set var="countAllBook" value="${dbCountRows.getAuthorCount(requestScope.bookAuthorOrTitle)}"/>
		<c:set var="getBy" value="getByAuthor" scope="session"/>
	</c:if>
	
	<c:if test="${param.search_option eq 'byTitle'}">
			<h4> <fmt:message key="findByTitle"/> <c:out value="${dbCountRows.getTitleCount(requestScope.bookAuthorOrTitle)}" /> </h4>
    		<c:set var="countAllBook" value="${dbCountRows.getTitleCount(requestScope.bookAuthorOrTitle)}"/>
			<c:set var="getBy" value="getByTitle" scope="session"/>
	</c:if>


	
	<c:set var="listBook" value="${requestScope.bookListAuthorOrTitle}" scope="session"/>
	
	<jsp:useBean id="book" class="by.htp.library.bean.Book" scope="page" /> 
	
	
		<c:forEach var="book" items="${requestScope.bookListAuthorOrTitle}">
		
		<div class="books">
		
			 <a href="${pageContext.request.contextPath}/Controller?index=${listBook.indexOf(book)}&txtCallFlag=READ">
		      	<img src="${pageContext.request.contextPath}/Controller?index=${listBook.indexOf(book)}&txtCallFlag=IMAGE" height="250" width="190" alt="Обложка"/>	
			 </a>
		               
		                <strong> </strong><c:out value=" " /> <br><%-- чтоб title with image  не на одной строке// --%>
						<strong> <fmt:message key="titleBook"/> </strong><c:out value="${book.bookTitle}" /> <br>
						<strong> <fmt:message key="author"/>: </strong><c:out value="${book.authorName}" /> <br>
						<strong> <fmt:message key="publicationYear"/> </strong><c:out value="${book.publicationYear}" /> <br>
						<strong> <fmt:message key="published"/> </strong><c:out value="${book.publishedById}" /> <br>
						<strong> <fmt:message key="genre"/> </strong><c:out value="${book.genreId}" /> <br>
						
					<c:if test="${sessionScope.role eq 'admin'}">
						<form class="dellUpdate" action="Controller" method="POST">
							<input type="hidden" name="id" value="${book.id}">
							<input class="buttonEditDelPage" type="submit" name="DEL" value="<fmt:message key="delete"/>" onclick="if(!(confirm('Are you sure you want to delete this book?'))) return false"/>
							<input type="hidden" name="command" value="RemoveBook">
						</form>
						<form class="dellUpdate" action="Controller"  method="post" >
							<input type="hidden" name="command" value="ShowUpdateBook">
							<input type="hidden" name="id" value="${book.id}">
							<input class="buttonEditDelPage" type="submit" value="<fmt:message key="edit"/>"/>
						</form>
						<br>
					</c:if>	
		</div>
				
		</c:forEach>
					
	
		<br> <br> 
	
<!-- 	Pagination -->
	<c:set var="rowsPerPage" value="2" />
	<c:set var="pageNumber" value="${param.pageNumber}"/>
	<c:set var="pageLinkA">
		<fmt:formatNumber value="${countAllBook/rowsPerPage}" maxFractionDigits="0" />
	</c:set>
	
	<c:set var="pageLinkB" value="${countAllBook/rowsPerPage}"/>
	
	<c:choose>
	    <c:when test="${pageLinkA == 0}">
	        <c:set var="numberOfPages" value="1" scope="session"/>   
	    </c:when>
 
	    <c:when test="${pageLinkB > pageLinkA}">
	        <c:set var="x" value="${pageLinkB%pageLinkA}"/>
	        <c:if test="${x>0}">
	            <c:set var="numberOfPages" value="${pageLinkB - x + 1}" scope="session"/>   
	        </c:if>
	    </c:when>
 
	    <c:when test="${pageLinkA>=pageLinkB}">
	        <c:set var="numberOfPages" value="${pageLinkA}" scope="session"/>    
	    </c:when>
	</c:choose>
	

	<fmt:message key="pages"/> 
	<c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${i!=pageNumber}">
             	
				<form class="links" action="Controller" method="POST">
		
					<input type="hidden" name="command" value="${getBy }" >

					<input type="hidden" name="search_String" value="${requestScope.bookAuthorOrTitle}">
					<input type="hidden" name="pageNumber" value="${i}">
					<input type="hidden" name="rowsPerPage" value="${rowsPerPage}">
					<input type="hidden" name="search_option" value="${param.search_option}">

					<input class="buttonEditDelPage" type="submit" value="${i}"/>
				</form>
				
            </c:when>
            <c:otherwise>
                <c:out value="${i}"/>
            </c:otherwise>        
        </c:choose>       
    </c:forEach>  

	
</div>	

</body>
</html>