<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<h2>Create new book</h2>


<c:url var="UPDATE_BOOK_INFO_URL" value="/createBook"></c:url>


<form:form id="details" action="${UPDATE_BOOK_INFO_URL}" method="post"
	commandName="book">
	<table>

		<tr>
			<td>ID:</td>
			<td><form:input name="id" path="id" type="text"/></td>
			<td><form:errors path="id" cssClass="error" /></td>
		</tr>
		
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
			<td>Quantity:</td>
			<td><form:input name="quantity" path="quantity" type="text"/></td>
			<td><form:errors path="quantity" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td>Price:</td>
			<td><form:input name="price" path="price" type="text"/></td>
			<td><form:errors path="price" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td><input value="Update Book Info" type="submit" /></td>
		</tr>
		
	</table>
</form:form>


