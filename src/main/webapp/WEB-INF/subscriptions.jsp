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
        <input type="submit" value="Get book list"/>

        <p>Book: ${book}</p>
    </form>
</div>
</body>
</html>