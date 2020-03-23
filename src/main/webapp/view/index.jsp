<%--
  Created by IntelliJ IDEA.
  User: vielen
  Date: 3/14/20
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Title</title>
    <jsp:include page="bootstrap.jsp"/>
</head>
<body>
    <security:authorize access="isAuthenticated()">
        <a href="${pageContext.request.contextPath}/cabinet">Cabinet</a>
    </security:authorize>
    <security:authorize access="!isAuthenticated()">
        <a href="${pageContext.request.contextPath}/registration">Registration</a>
        <a href="${pageContext.request.contextPath}/login">Login</a>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </security:authorize>
</body>
</html>
