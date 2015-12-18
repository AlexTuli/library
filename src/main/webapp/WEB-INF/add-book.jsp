<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new book to library</title>
</head>
<body>
<div align="center"><b>Enter book information</b></div>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="action" value="add-book"/>
    <input type="text" name="author" id="author" placeholder="Enter book Author"/><br/>
    <input type="text" name="title" id="title" placeholder="Enter book Title"/><br/>
    <input type="submit" value="Add book"/>
</form>

</body>
</html>
