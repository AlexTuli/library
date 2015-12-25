<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:centered title="Registration">

    <jsp:attribute name="infoblock">
        ${param.info}
    </jsp:attribute>
    <jsp:attribute name="header">
        Registration
    </jsp:attribute>
    <jsp:attribute name="footer">
        <div align="center">
            <a href="<c:url value="/controller?action=index"/>">Back</a>
        </div>
    </jsp:attribute>
    <jsp:body>
        <div align="center">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="action" value="registration-user"/>
                <p class="blue">Enter you login</p>
                <p><input type="text" name="login" id="login" placeholder="Enter login"/></p>
                <p class="blue"> Enter you password</p>
                <p><input type="password" name="password" id="password" placeholder="Enter password"/></p>
                <input type="submit" value="Register"/>
            </form>
        </div>
    </jsp:body>
</t:centered>

