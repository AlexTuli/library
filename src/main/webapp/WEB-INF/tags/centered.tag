<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Tag used to pages with centered, ex: auth, reg." pageEncoding="UTF-8"%>
<%@attribute name="infoblock" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" required="true" %>
<%@attribute name="header" fragment="true" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/test.css"/>" media="all">


<html>
<head>
    <title>
        ${title}
    </title>
</head>

<body class="border">
<div id="infoblock">
    <p class="info">
        <jsp:invoke fragment="infoblock"/>
    </p>
</div>
<div id="header">
    <p class="header">
        <jsp:invoke fragment="header"/>
    </p>
</div>
<div class="centerVertical" id="content">
    <jsp:doBody/>
</div>
<div class="footer" id="footer">
    <jsp:invoke fragment="footer"/>
    <div align="right">Created by Alexander Bocharnikov</div>
</div>
</body>
</html>