<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Hello world!</title></head>
<body>
<div>Hello world!</div>
<a href="${pageContext.request.contextPath}/new-book.jsp">Request for a new book</a>
<a href="${pageContext.request.contextPath}/return-book.jsp">Return old book</a>

</body>
</html>