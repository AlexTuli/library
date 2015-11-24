<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subscriptions</title>
</head>
<body>
<div align="center">Your books:</div>
<div>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="action" value="get-subscription"/>
        <!-- TODO AUTO REQUEST -->
        <input type="submit" value="Get list"/>
    </form>
</div>
</body>
</html>