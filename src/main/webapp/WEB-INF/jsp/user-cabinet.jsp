<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:centered title="User cabinet">
    <jsp:attribute name="infoblock">
        ${param.notification}
    </jsp:attribute>
    <jsp:attribute name="header">
        Hello, ${sessionScope.user.login}
    </jsp:attribute>
    <jsp:body>
        <div align="center">
            <a class="aligned" href="${pageContext.request.contextPath}/controller?action=redirect-subscriptions">Look
                at my books</a>
            <a class="aligned"
               href="${pageContext.request.contextPath}/controller?action=show-page&redirect=get-new-book">Request
                for a new book</a>
                <%--action=show-page&redirect=registration--%>
            <a class="aligned"
               href="${pageContext.request.contextPath}/controller?action=show-page&redirect=return-book">Return
                old book</a>
            <a class="aligned" href="${pageContext.request.contextPath}/controller?action=check-books">Get list books in
                library</a>

        </div>
    </jsp:body>


</t:centered>







