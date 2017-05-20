<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="style.css" rel="stylesheet" />
</head>
<body>

    <c:url var="studentsUrl" value="/krams/main/students" />
    <c:url var="examsUrl" value="/krams/main/exams" />
    <c:url var="studentExamsUrl" value="/krams/main/studentExams" />
    <nav>
        <ul>
            <li><a href="${studentsUrl}">Students</a></li>
            <li><a href="${examsUrl}">Exams</a></li>
            <li><a href="${studentExamsUrl}">Students and Exams</a></li>
        </ul>
    </nav>
</body>
</html>