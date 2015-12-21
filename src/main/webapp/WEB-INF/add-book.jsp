<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:centered title="Add book to library">

    <jsp:attribute name="infoblock">
        ${param.info}
    </jsp:attribute>

    <jsp:body>
        <div align="center">
            <p class="blue">Enter book information</p>
            <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="action" value="add-book"/>
            <input type="text" name="author" id="author" placeholder="Enter book Author"/><br/>
            <input type="text" name="title" id="title" placeholder="Enter book Title"/><br/>
            <input type="submit" value="Add book"/>
        </form>
        </div>
    </jsp:body>

</t:centered>
