<%--
  Created by IntelliJ IDEA.
  User: vielen
  Date: 3/14/20
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Cabinet</title>
    <jsp:include page="bootstrap.jsp"/>
</head>
<body>
<div class="card" style="width: 18rem;">
    <img src="https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png" class="card-img-top" alt="...">
    <div class="card-body">
        <h5 class="card-title">User</h5>
        <p class="card-text">Information about your account</p>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item">${name}</li>
        <li class="list-group-item">${number}</li>
        <li class="list-group-item">${birthDate}</li>
        <div>
            <img src="/home/${imageName}" height="200" width="200">
        </div>
        <form action="${pageContext.request.contextPath}/add_message" method="post">
            <input type="text" name="messageText" value="Insert message"/>
            <input type="submit" value="Submit"/>
        </form>
    </ul>
    <div class="card-body">
        <a href="${pageContext.request.contextPath}/" class="card-link">Main page</a>
        <a href="${pageContext.request.contextPath}/logout" class="card-link">Logout</a>
        <a href="${pageContext.request.contextPath}/increase" class="card-link">Increase the number</a>
    </div>
</div>
</body>
</html>
