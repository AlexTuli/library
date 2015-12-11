<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<div> ${error} </div>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="action" value="authorize"/><br/>
    <input type="text" name="login" id="login" placeholder="Enter login"/><br/>
    <input type="password" name="password" id="password" placeholder="Enter password"/><br/>
    <input type="submit" value="Login"/>
</form>


<a href="WEB-INF/user-cabinet.jsp">User-index</a>
<a href="WEB-INF/admin-cabinet.jsp">Admin-index</a>
</body>
</html>
