<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:centered title="Return book to library">
    <jsp:attribute name="infoblock">
        ${param.info}
    </jsp:attribute>
    <jsp:attribute name="header">
        Return book
    </jsp:attribute>
    <jsp:body>
        <div align="center">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="action" value="return-book"/>
                <p class="blue">Enter book ID</p>
                <p><input type="text" name="id" id="id" placeholder="Enter book ID"/></p>
                <p><input type="submit" value="Return book"/></p>
            </form>
        </div>
    </jsp:body>
</t:centered>

