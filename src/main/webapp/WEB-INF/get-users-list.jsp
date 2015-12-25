<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="users" type="java.util.List"--%>

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
                    <b>User login: </b><c:out value="${user.login}"/>
                    <b>User ID: </b><c:out value="${user.id}"/>
                    <b>User Role: </b><c:out value="${user.role}"/>
                </p>
            </c:forEach>
        </div>

    </jsp:body>

</t:centered>
