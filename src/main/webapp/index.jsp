<%--
  Created by IntelliJ IDEA.
  User: alexandr
  Date: 11/26/15
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="action" value="authorize"/><br/>
    <input type="text" name="login" id="login" placeholder="Enter login"/><br/>
    <input type="password" name="password" id="password" placeholder="Enter password"/><br/>
    <input type="submit" value="Login"/>
</form>


<a href="user-index.jsp">User-index</a>
<a href="admin-index.jsp">Admin-index</a>
</body>
</html>
