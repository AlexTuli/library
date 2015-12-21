<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:centered title="Promote user">

       <jsp:attribute name="infoblock">
           ${param.info}
       </jsp:attribute>

    <jsp:body>
        <div align="center">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="action" value="promote-user"/>
            <p class="blue">Enter user ID, to promote:</p>
            <p><input type="number" name="id" id="id" placeholder="Enter user ID"/></p>
            <p><input type="submit" value="Promote"/></p>
        </form>
        </div>
    </jsp:body>

</t:centered>

