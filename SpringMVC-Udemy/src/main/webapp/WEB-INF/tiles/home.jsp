<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<p>
	<h3>${message}</h3>
</p>

<h2 class="error">${errorMsg}</h2>
<h2 class="valid">${successMsg}</h2>


<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
		<a href=<c:url value="/viewAllCustomers "/>>View All Users (Admin) </a>
	</p>

</sec:authorize>


<sec:authorize access="isAuthenticated()">

<p>
	<a href=<c:url value="/books "/>>View Books</a>
</p>

</sec:authorize>


<sec:authorize access="!isAuthenticated()">
	
	<h2>Welcome !</h2>

</sec:authorize>




