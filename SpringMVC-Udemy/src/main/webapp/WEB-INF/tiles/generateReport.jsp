<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<h2>Export report</h2>


<c:url var="GENERATE_REPORT_URL" value="/generateReport"></c:url>

<h2 class="error">${errorMsg}</h2>
<h2 class="valid">${successMsg}</h2>

<form id="details" action="${GENERATE_REPORT_URL}" method="get" >
	<table>
		<tr>
			<td>Path to export directory:</td>
			<td><input name="path" type="text"/></td>
		</tr>
		<tr>
			<td>Export PDF</td>
			 <td><input type="checkbox" name="pdf"/></td>
		</tr>
		<tr>
			<td>Export CSV</td>
			 <td><input type="checkbox" name="csv"/></td>
		</tr>
		
		<tr>
			<td><input value="Export" type="submit" /></td>
		</tr>
		
	</table>
</form>

