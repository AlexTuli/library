<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Return book to library</title>
</head>
<body>
<div align="center"><h1>RETURN BOOK</h1></div>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="action" value="return-book"/>
    <input type="text" name="id" id="id" placeholder="Enter book ID"/>
    <input type="submit" value="Return book"/>
</form>
</body>
</html>
