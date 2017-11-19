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
<link href="css/menuLibraryAdmin.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="fragment/menuAdminOrClient.jsp"/> 

<div class="right_menu">
	<c:if test="${not empty requestScope.bookList}">
	
	<jsp:useBean id="dbCountRows" class="by.htp.library.dao.impl.DatabaseCountRows" scope="page" />
	<h4><fmt:message key="foundBook"/> <c:out value="${dbCountRows.getGenreCount(requestScope.genreName)}" /> </h4>
	
	<c:set var="listBook" value="${requestScope.bookList}" scope="session"/>
	<jsp:useBean id="book" class="by.htp.library.bean.Book" scope="page" /> 
	
	
		<c:forEach var="book" items="${requestScope.bookList}">
		
		<div class="books">
		      
			<a href="${pageContext.request.contextPath}/Controller?index=${listBook.indexOf(book)}&txtCallFlag=READ">
		      	<img src="${pageContext.request.contextPath}/Controller?index=${listBook.indexOf(book)}&txtCallFlag=IMAGE" height="250" width="190" alt="<fmt:message key="cover"/>"/>	
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
					</c:if>	

						<br>
		</div>
				
		</c:forEach>
					
	
		<br> <br> 
	
<!-- 	Pagination for genre -->
	<c:set var="countAllBook" value="${dbCountRows.getGenreCount(requestScope.genreName)}"/>
	<c:out value="${countAllBook}"/>
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
			
					<input type="hidden" name="command" value="${param.command}" >
					<input type="hidden" name="simple" value="${requestScope.genreName}">
					<input type="hidden" name="pageNumber" value="${i}">
					<input type="hidden" name="rowsPerPage" value="${rowsPerPage}">
					<input class="buttonEditDelPage" type="submit" value="${i}"/>
				</form>
				
            </c:when>
            <c:otherwise>
                <c:out value="${i}"/>
            </c:otherwise>        
        </c:choose>       
    </c:forEach>  
	
	
	
	</c:if>
</div>	

		
	<c:if test="${empty requestScope.bookList}">
		<h4><c:out value="${requestScope.errorGetBy}" /> </h4>
	</c:if>
	
	<!-- UPDATE -->
	<c:if test="${not empty requestScope.BookSuccessUpdate}">
		<h4><c:out value="${requestScope.BookSuccessUpdate}" /> </h4>
	</c:if>
	
	<!-- REMOVE -->
	<c:if test="${empty requestScope.ErrorBookRemove}">
		<h4><c:out value="${requestScope.ErrorBookRemove}" /> </h4>
	</c:if>
	
	
	<c:if test="${not empty requestScope.bookListAuthorOrTitle}">
		<jsp:include page="fragment/paginationSearchAuthorTitle.jsp"/> 
	</c:if>
	

	
</body>
</html>