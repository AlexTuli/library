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
            <a class="aligned" href="${pageContext.request.contextPath}/controller?action=show-page&redirect=add-book">Add
                new
                book to library</a>
            <a class="aligned" href="${pageContext.request.contextPath}/controller?action=get-users-list">Get list of
                users</a>
            <a class="aligned" href="${pageContext.request.contextPath}/controller?action=check-books">Get books in
                library</a>
                <%--<a class="aligned" href="${pageContext.request.contextPath}/controller?action=show-page&redirect=notify-user">Notify--%>
                <%--user</a>--%>
            <a class="aligned"
               href="${pageContext.request.contextPath}/controller?action=show-page&redirect=delete-user">Delete
                user</a>
            <a class="aligned"
               href="${pageContext.request.contextPath}/controller?action=show-page&redirect=promote-user">Promote
                user</a>
        </div>
    </jsp:body>
</t:centered>