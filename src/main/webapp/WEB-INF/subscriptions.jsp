<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="subscription" type="com.epam.alex.task4.entity.Subscription"--%>
<html>
<head>
    <title>Subscriptions</title>
</head>
<body>
<c:if test="${subscription.bookList.size() > 0}">

<div align="center">
    <h2>Your books:</h2>
</div>
    <div align="left">
        -------------------------------------------------------------------------------------------<br/>
        <c:forEach items="${subscription.bookList}" var="book">
            <b>Book tittle: </b><c:out value="${book.title}"/>
            <b>Book author: </b><c:out value="${book.author}"/>
            <b>Book ID: </b><c:out value="${book.id}"/><br/>
            -------------------------------------------------------------------------------------------<br/>
        </c:forEach>
    </div>
</c:if>
</body>
</html>