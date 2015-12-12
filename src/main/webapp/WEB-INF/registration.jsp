<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="action" value="registration-user"/>
    <h2>Enter you login</h2>
    <input type="text" name="login" id="login" placeholder="Enter login"/><br/>
    <h2>Enter you password</h2>
    <input type="password" name="password" id="password" placeholder="Enter password"/><br/>
    <input type="submit" value="Register"/>
</form>

</body>
</html>
