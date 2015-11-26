<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<!-- TODO Change "user" to %username% -->
<div align="center">Hello, User!</div>
<div align="center">Your messages: </div>
<%@ include file="/norify.jsp" %>

<%@ include file="/subscriptions.jsp"%>




<div><a href="${pageContext.request.contextPath}/insert-book-to-subscription.jsp">Request for a new book</a></div>
<div><a href="${pageContext.request.contextPath}/return-book.jsp">Return old book</a></div>

<div align="center"><%@ include file="/look-for-books.jsp"%></div>

<!-- PROBABLY I CAN REDIRECT ALL MESEGES HERE (ex:Book added, or smthg else) -->
<div>Message: </div>
<%@ include file="/user-notification.jsp"%>
<div align="center"> ${message}</div>


</body>
</html>