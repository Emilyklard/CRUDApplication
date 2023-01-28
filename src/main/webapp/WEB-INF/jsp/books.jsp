<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Книги выбранного автора: </h1>
<ul>
    <c:forEach var="book" items="${requestScope.books}">
        <li>${book.name}</li>
    </c:forEach>
</ul>
</body>
</html>
