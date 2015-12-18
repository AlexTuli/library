<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Notify</title>
</head>
<body>
<div align="center">${param.info}</div>
<div>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="action" value="create-notification"/>
        <input type="number" name="user-id" id="user-id" placeholder="Enter user ID"/><br/>
        <textarea name="notification" id="notification" placeholder="Enter notification here" rows="4" cols="50"></textarea>
        <br/>
        <button type="submit">Create notification!</button>
    </form>
</div>
</body>
</html>
