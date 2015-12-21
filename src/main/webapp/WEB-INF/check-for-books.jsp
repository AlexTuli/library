<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="books" type="java.util.List"--%>

<t:centered title="Book in library">

    <jsp:body>
        <div align="center"><p class="blue">Books in library:</p></div>
        <div class="center">
            <c:forEach items="${books}" var="book">
                <p class="green">
                <b>Book tittle: </b><c:out value="${book.title}"/>
                <b>Book author: </b><c:out value="${book.author}"/>
                <b>Book ID: </b><c:out value="${book.id}"/>
                </p>
            </c:forEach>
        </div>

    </jsp:body>
</t:centered>