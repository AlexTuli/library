<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="users" type="java.util.List"--%>
<%--@elvariable id="user" type="com.epam.alex.task4.entity.User"--%>


<t:centered title="User list">

    <jsp:attribute name="infoblock">
        ${param.info}
    </jsp:attribute>
    <jsp:attribute name="header">
        Users in library
    </jsp:attribute>

    <jsp:body>
        <div align="center">
            <c:forEach items="${users}" var="user">
                <p class="green">
                    <b>User ID: </b><c:out value="${user.id}"/>
                    <b>User login: </b><c:out value="${user.login}"/>
                    <b>User First name:</b><c:out value="${user.firstName}"/>
                    <b>User Last name:</b><c:out value="${user.lastName}"/>
                    <b>User Role: </b><c:out value="${user.role}"/>
                    <b>User subscription ID:</b> <c:out value="${user.subscription.id}"/>
                </p>
            </c:forEach>
        </div>

    </jsp:body>

</t:centered>
