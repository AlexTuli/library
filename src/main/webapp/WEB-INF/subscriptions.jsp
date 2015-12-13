<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subscriptions</title>
</head>
<body>
<div align="center"><h2>Your books:</h2></div>
    <div align="left">
        -------------------------------------------------------------------------------------------<br/>
        <c:forEach items="${subscription.bookList}" var="book">
            <b>Book tittle: </b><c:out value="${book.title}"/>
            <b>Book author: </b><c:out value="${book.author}"/>
            <b>Book ID: </b><c:out value="${book.id}"/><br/>
            -------------------------------------------------------------------------------------------<br/>
        </c:forEach>
    </div>
</body>
</html>