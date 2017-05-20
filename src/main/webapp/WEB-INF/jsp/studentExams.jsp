<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/navigationBar.jsp" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../../style.css" rel="stylesheet" />
    <title>Students and exams page</title>
</head>
<body>
<h1>How students passed exams</h1>

<c:url var="addUrl" value="/krams/main/studentExams/add" />
<a href="${addUrl}">Add</a>
<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#fcf">
    <tr>
        <th>Student</th>
        <th>Exam</th>
        <th>Points</th>
        <th colspan="3"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentExams}" var="tuple">
        <c:url var="editUrl" value="/krams/main/studentExams/edit?id=${tuple.id}" />
        <c:url var="deleteUrl" value="/krams/main/studentExams/delete?id=${tuple.id}" />
        <tr>
            <td><c:out value="${tuple.student.name}" /></td>
            <td><c:out value="${tuple.exam.name}" /></td>
            <td><c:out value="${tuple.points}" /></td>
            <td><a href="${editUrl}">Edit</a></td>
            <td><a href="${deleteUrl}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty studentExams}">
    There are currently no connections in the list. <a href="${addUrl}">Add</a> a connection.
</c:if>

</body>
</html>