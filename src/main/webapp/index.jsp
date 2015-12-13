<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<div> ${param.info} </div>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="action" value="authorize"/>
    <input type="text" name="login" id="login" placeholder="Enter login"/><br/>
    <input type="password" name="password" id="password" placeholder="Enter password"/><br/>
    <input type="submit" value="Login"/>
</form>

<div><a href="${pageContext.request.contextPath}/controller?action=registration">Register</a></div>

<a href="${pageContext.request.contextPath}/controller?action=check-books">Get list books in library</a>
</body>
</html>
