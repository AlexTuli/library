<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:centered title="Get new book">

    <jsp:attribute name="infoblock">${param.info}</jsp:attribute>

    <jsp:attribute name="header">
        Add book to your subscription
    </jsp:attribute>

    <jsp:body>
        <div align="center">${param.info}
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="action" value="request-for-book"/>
                <p class="blue">Enter book ID</p>
                <p><input type="text" name="id" id="id" placeholder="Enter book ID"/></p>
                <p><input type="submit" value="Add book"/></p>
            </form>
        </div>
    </jsp:body>

</t:centered>



