<%--
  Created by IntelliJ IDEA.
  User: alexandr
  Date: 11/26/15
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book insertion</title>
</head>
<body>
<div>Add book to you're subscription:</div>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="action" value="request-for-book"/>
    <input type="text" name="id" id="id" placeholder="Enter book ID"/>
    <button type="submit">Add book!</button>
</form>
</body>
</html>
