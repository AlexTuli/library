<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:centered title="Add book to library">

    <jsp:attribute name="infoblock">
        ${param.info}
    </jsp:attribute>
    <jsp:attribute name="header">
        Add new book to library
    </jsp:attribute>

    <jsp:body>
        <div align="center">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="action" value="add-book"/>
                <p class="blue">Enter book information<br/>Author of Book:</p>
                <p><input type="text" name="author" id="author" placeholder="Enter book Author"/></p>
                <p class="blue">Title of Book:</p>
                <p><input type="text" name="title" id="title" placeholder="Enter book Title"/></p>
                <input type="submit" value="Add book"/>
        </form>
        </div>
    </jsp:body>

</t:centered>
