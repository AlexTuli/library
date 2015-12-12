<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<!-- TODO Change "user" to %username% -->
<div align="center">Hello, User!</div>
<div align="center">Your messages:</div>
<%@ include file="/WEB-INF/norify.jsp" %>

<%@ include file="/WEB-INF/subscriptions.jsp" %>


<div><a href="${pageContext.request.contextPath}/WEB-INF/insert-book-to-subscription.jsp">Request for a new book</a>
</div>
<div><a href="${pageContext.request.contextPath}/WEB-INF/return-book.jsp">Return old book</a></div>

<div align="center">
    <%@ include file="/WEB-INF/look-for-books.jsp" %>
</div>

<!-- PROBABLY I CAN REDIRECT ALL MESEGES HERE (ex:Book added, or smthg else) -->
<div>Message:</div>
<%@ include file="/WEB-INF/user-notification.jsp" %>
<div align="center"> ${message}</div>


</body>
</html>