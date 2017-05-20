<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/navigationBar.jsp" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../../style.css" rel="stylesheet" />
	<title>Main page</title>
</head>
<body>
<h1>Students</h1>

<c:url var="addUrl" value="/krams/main/students/add" />

<a href="${addUrl}">Add</a>
<table style="border: 1px solid; width: 500px; text-align:center">
	<thead style="background:#fcf">
		<tr>
			<th>Name</th>
			<th>Status</th>
			<th colspan="2"></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${students}" var="student">
			<c:url var="editUrl" value="/krams/main/students/edit?id=${student.id}" />
			<c:url var="deleteUrl" value="/krams//main/students/delete?id=${student.id}" />
		<tr>
			<td><c:out value="${student.name}" /></td>
			<td><c:out value="${studentService.getPassed(student)}" /></td>
			<td><a href="${editUrl}">Edit</a></td>
			<td><a href="${deleteUrl}">Delete</a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<c:if test="${empty students}">
	There are currently no students in the list. <a href="${addUrl}">Add</a> a student.
</c:if>

</body>
</html>