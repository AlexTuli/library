<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:centered title="Notify user">

    <jsp:attribute name="infoblock">
        ${param.info}
    </jsp:attribute>

    <jsp:body>
        <div align="center">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="action" value="create-notification"/>
            <p class="blue">Enter user ID</p>
            <p><input type="number" name="user-id" id="user-id" placeholder="Enter user ID"/></p>
            <p class="blue">Enter text of notification(up to 255 chars)</p>
            <p><textarea name="notification" id="notification" placeholder="Enter notification here" rows="4" cols="50"></textarea></p>
            <input type="submit" value="Create notification" size="100"/>
        </form>
        </div>
    </jsp:body>

</t:centered>
