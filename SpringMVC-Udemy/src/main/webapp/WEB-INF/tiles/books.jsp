<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<h2>View All Books</h2>

<h2 class="error">${errorMsg}</h2>
<h2 class="valid">${successMsg}</h2>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
		<a href=<c:url value="/createBook "/>>Create Book </a>
	</p>

</sec:authorize>

<c:url var="SHOW_BOOKS_URL" value="/books"></c:url>


<form:form id="details" action="${SHOW_BOOKS_URL}" method="post"
	commandName="searchQuery">
	<table>
		
		<tr>
			<td>Title:</td>
			<td><form:input name="title" path="title" type="text"/></td>
			<td><form:errors path="title" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td>Genre:</td>
			<td><form:input name="genre" path="genre" type="text"/></td>
			<td><form:errors path="genre" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td>Author:</td>
			<td><form:input name="author" path="author" type="text"/></td>
			<td><form:errors path="author" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td><input value="Filter Books" type="submit" /></td>
		</tr>
		
	</table>
</form:form>

</br>
</br>

<table class="allCenter" border=1 width=100%>
	<tr>
		<th>ID</th>
		<th>Title</th>
		<th>Genre</th>
		<th>Author</th>
		<th>Quantity</th>
		<th>Price</th>
		<th>Sell</th>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<th>Update</th>
			<th>Delete</th>
		</sec:authorize>
	</tr>

	<c:forEach var="book" items="${books}">
		<tr>
			<td>${book.id}</td>
			<td>${book.title}</td>
			<td>${book.genre}</td>
			<td>${book.author}</td>
			<td>${book.quantity}</td>
			<td>${book.price}</td>
			<td><a href=<c:url value="/sellBook?id=${book.id}"/>>Sell</a></td>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td><a href=<c:url value="/updateBook?id=${book.id}"/>>Update</a></td>
				<td><a href=<c:url value="/deleteBook?id=${book.id}"/>>Delete</a></td>
			</sec:authorize>
		</tr>
	</c:forEach>
</table>




