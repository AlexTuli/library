<%--
  Created by IntelliJ IDEA.
  User: alexandr
  Date: 11/26/15
  Time: 7:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Notification</title>
</head>
<body>
<div align="center">
    <h1>NOTIFICATIONS: </h1>
</div>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="action" value="get-notification"/>
    <!-- TODO DO IT DYNAMIC (WITHOUT BUTTON) -->
    <input type="submit" value="Get notification"/>
</form>
</body>
</html>
