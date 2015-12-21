<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:centered title="Welcome to library">

    <jsp:attribute name="infoblock">
        ${param.info}
    </jsp:attribute>

    <jsp:attribute name="footer">
        <div align="center">
        <a href="${pageContext.request.contextPath}/controller?action=registration">Register</a>
        <a href="${pageContext.request.contextPath}/controller?action=check-books">Get list books in library</a>
        </div>
    </jsp:attribute>


    <jsp:body>
        <div align="center">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="action" value="authorize"/>
            <p class="blue">Enter Login</p>
            <p> <input type="text" name="login" id="login" placeholder="Enter login"/></p>
            <p class="blue">Enter password </p>
            <p><input type="password" name="password" id="password" placeholder="Enter password"/></p>
            <input type="submit" value="Login" size="100"/>
        </form>
        </div>
    </jsp:body>

</t:centered>