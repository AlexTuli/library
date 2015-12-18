<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<div align="center">
    <h4>Welcome back, ${sessionScope.user.login}</h4><br/>
    <b>${param.info}</b>
</div>

<a href="${pageContext.request.contextPath}/controller?action=redirect-add-book">Add new book to library</a><br/>
<a href="${pageContext.request.contextPath}/controller?action=get-users-list">Get list of users</a><br/>
<a href="${pageContext.request.contextPath}/controller?action=redirect-notify">Notify user</a><br/>
</body>
</html>
