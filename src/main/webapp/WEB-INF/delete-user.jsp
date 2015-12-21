<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:centered title="Delete user">

    <jsp:attribute name="infoblock">
        ${param.info}
    </jsp:attribute>

    <jsp:body>
        <div align="center">
            <p class="blue">Delete user from library DB<br/> Enter user ID</p>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="action" value="delete-user"/>
                <p><input type="number" name="id" id="id" placeholder="Enter user ID"/></p>
                <input type="submit" value="Delete user" size="100"/>
            </form>
        </div>
    </jsp:body>

</t:centered>

