<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="subscription" type="com.epam.alex.task4.entity.Subscription"--%>
<t:centered title="Subscription">

    <jsp:attribute name="infoblock">
        ${param.info}
    </jsp:attribute>
    <jsp:attribute name="header">
        Your books
    </jsp:attribute>

    <jsp:body>
        <div align="center">
            <c:forEach items="${subscription.bookList}" var="book">
                <p class="green"><b>Book tittle: </b><c:out value="${book.title}"/>
                    <b>Book author: </b><c:out value="${book.author}"/>
                    <b>Book ID: </b><c:out value="${book.id}"/></p>
            </c:forEach>
        </div>
    </jsp:body>


</t:centered>




