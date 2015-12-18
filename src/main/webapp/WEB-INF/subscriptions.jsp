<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="subscription" type="com.epam.alex.task4.entity.Subscription"--%>
<html>
<head>
    <title>Subscriptions</title>
</head>
<body>
<%--TODO THIS IS NOT WORK IF SUBSCRIPTION IS HAVE NO BOOK FIX IT--%>

<div align="center">
    <h2>Your books:</h2>
</div>

    <div align="left">
        -------------------------------------------------------------------------------------------<br/>
        <c:forEach items="${subscription.bookList}" var="user">
            <b>Book tittle: </b><c:out value="${user.title}"/>
            <b>Book author: </b><c:out value="${user.author}"/>
            <b>Book ID: </b><c:out value="${user.id}"/><br/>
            -------------------------------------------------------------------------------------------<br/>
        </c:forEach>
    </div>

</body>
</html>