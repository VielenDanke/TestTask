<%--
  Created by IntelliJ IDEA.
  User: vielen
  Date: 3/14/20
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <a href="delete_messages">Delete all messages</a>
    </security:authorize>
    <c:if test="${allMessage != null}">
        <c:forEach var="message" items="${allMessage}">
            <li class="list-group-item">${message.user.username}</li>
            <li class="list-group-item">${message.description}</li>
        </c:forEach>
    </c:if>
</body>
</html>
