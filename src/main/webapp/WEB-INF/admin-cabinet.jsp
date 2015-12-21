<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:centered title="Admin cabinet">

    <jsp:attribute name="infoblock">
            <b>${param.info}</b>
    </jsp:attribute>
    <jsp:attribute name="header">
        Welcome back, ${sessionScope.user.login}
    </jsp:attribute>



    <jsp:body>
        <div align="center">
        <a class="aligned" href="${pageContext.request.contextPath}/controller?action=redirect-add-book">Add new book to library</a><br/>
        <a class="aligned" href="${pageContext.request.contextPath}/controller?action=get-users-list">Get list of users</a><br/>
        <a class="aligned" href="${pageContext.request.contextPath}/controller?action=redirect-notify">Notify user</a><br/>
        <a class="aligned" href="${pageContext.request.contextPath}/controller?action=redirect-delete-user">Delete user</a><br/>
        <a class="aligned" href="${pageContext.request.contextPath}/controller?action=redirect-promote-user">Promote user</a><br/>
        </div>
    </jsp:body>



</t:centered>