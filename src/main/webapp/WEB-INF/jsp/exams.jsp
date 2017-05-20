<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/navigationBar.jsp" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../../style.css" rel="stylesheet" />
    <title>Exam page</title>
</head>
<body>
<h1>Exams</h1>

<c:url var="addUrl" value="/krams/main/exams/add" />
<a href="${addUrl}">Add</a>
<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#fcf">
    <tr>
        <th>Name</th>
        <th colspan="1"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${exams}" var="exam">
        <c:url var="editUrl" value="/krams/main/exams/edit?id=${exam.id}" />
        <c:url var="deleteUrl" value="/krams/main/exams/delete?id=${exam.id}" />
        <tr>
            <td><c:out value="${exam.name}" /></td>
            <td><a href="${editUrl}">Edit</a></td>
            <td><a href="${deleteUrl}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty exams}">
    There are currently no exams in the list. <a href="${addUrl}">Add</a> an exam.
</c:if>

</body>
</html>