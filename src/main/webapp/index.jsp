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

<div align="center">Your books:</div>
<%@ include file="/subscriptions.jsp"%>

<div><a href="${pageContext.request.contextPath}/new-book.jsp">Request for a new book</a></div>
<div><a href="${pageContext.request.contextPath}/return-book.jsp">Return old book</a></div>

</body>
</html>