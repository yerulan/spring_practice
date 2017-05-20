<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit connection page</title>
</head>
<body>

<h1>Change information about passing exam</h1>

<c:url var="saveUrl" value="/krams/main/studentExams/edit?id=${studentExamAttribute.id}" />
<form:form modelAttribute="studentExamAttribute" method="POST" action="${saveUrl}">
    <table>

        <tr>
            <td><form:label path="id">Id:</form:label></td>
            <td><form:input path="id" disabled="true"/></td>
        </tr>

        <tr>
            <td><form:label path="student.name">Student:</form:label></td>
            <td><form:input path="student.name"/></td>
        </tr>

        <tr>
            <td><form:label path="exam.name">Exam:</form:label></td>
            <td><form:input path="exam.name"/></td>
        </tr>

        <tr>
            <td><form:label path="points">Points:</form:label></td>
            <td><form:input path="points"/></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>

</body>
</html>