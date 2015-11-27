<%--
  Created by IntelliJ IDEA.
  User: alexandr
  Date: 11/26/15
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books in library</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="action" value="check-books"/>
    <input type="submit" value="Get books list"/>

    <div align="center">Books in library:</div>
    <div align="center"> ${books}</div>
</form>
</body>
</html>
