<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Список авторов:</h1>
<ul>
    <%--@elvariable id="authors" type="java.servlet.AuthorServlet"--%>
    <c:forEach  items="${requestScope.authors}" var="author">
    <li>

        <a href="/books?authorId=${author.id}">${author.description}</a>
    </li>
    </c:forEach>
</ul>

<h1>Добавить автора:</h1>
<form action="/authors" method="post">
    <label for="first_name">First_name:
        <input type="text" first_name="first_name" id="first_name">
    </label><br>
    <label for="last_name">Last_name:
        <input type="text" last_name="last_name" id="last_name">
    </label><br>
    <button type="submit">Send</button>
</form>

</body>
</html>
