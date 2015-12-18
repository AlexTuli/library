<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@elvariable id="users" type="java.util.List"--%>
<html>
<head>
    <title>Users list</title>

</head>
<body>
<div align="center"><h1>Users in library:</h1></div>
<div align="left">
    <c:forEach items="${users}" var="user">
        --------------------------------------------------<br/>
        <b>User login: </b><c:out value="${user.login}"/><br/>
        <b>User ID: </b><c:out value="${user.id}"/><br/>
        <b>User Role: </b><c:out value="${user.role}"/><br/>
    </c:forEach>
</div>
</body>
</html>
