<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books in library</title>
</head>
<body>
    <div align="center"><h1>Books in library:</h1></div>
    <div align="left">
        <c:forEach items="${books}" var="book">
            --------------------------------------------------<br/>
            <b>Book tittle: </b><c:out value="${book.title}"/>
            <b>Book author: </b><c:out value="${book.author}"/>
            <b>Book ID: </b><c:out value="${book.id}"/><br/>
        </c:forEach>
    </div>
    <!--TODO Implement it! -->
<a href="/controller/?action=back">Come back</a>
</body>
</html>
