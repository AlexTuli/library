<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<div align="center">Hello, ${sessionScope.user.login}</div>
<!--<div align="center">Your messages:</div>-->


<%@ include file="/WEB-INF/subscriptions.jsp" %>


<div><a href="${pageContext.request.contextPath}/controller?action=redirect-to-request-for-book">Request for a new book</a>
</div>
<div><a href="${pageContext.request.contextPath}/controller?action=redirect-to-return-book">Return old book</a></div>

<div align="center">
    <a href="${pageContext.request.contextPath}/controller?action=check-books">Get list books in library</a>
</div>

<div>Your notification:</div>
<%--TODO Implement notification--%>
<h1><div align="center"> ${param.notification}</div></h1>


</body>
</html>