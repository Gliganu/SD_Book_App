



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>All Users</h2>

<h2 class="error">${errorMsg}</h2>
<h2 class="valid">${successMsg}</h2>


<table class="allCenter" border=1 width=100%>
	<tr>
		<th>Username</th>
		<th>Name</th>
		<th>Personal Code</th>
		<th>Address</th>
		<th>Authority</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>

	<c:forEach var="user" items="${allUsers}">
		<tr>
			<td>${user.username}</td>
			<td>${user.name}</td>
			<td>${user.personalCode}</td>
			<td>${user.address}</td>
			<td>${user.authority}</td>
			<td><a href= <c:url value="/updateUserInfo?username=${user.username}"/>>Edit</a></td>
			<td><a href= <c:url value="/deleteUser?username=${user.username}"/>>Delete</a></td>
		</tr>
	</c:forEach>
</table>




