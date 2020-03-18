<%--
  Created by IntelliJ IDEA.
  User: vielen
  Date: 3/14/20
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Registration</title>
    <jsp:include page="bootstrap.jsp"/>
</head>
<body>
<div class="wrapper fadeInDown">
    ${error}
    <div id="formContent">
        <form:form action="save" method="post" modelAttribute="user">
            <h3>Username</h3>
            <form:input path="username" cssClass="fadeIn second"/>
            <h3>Password</h3>
            <form:password path="password" cssClass="fadeIn third"/>
            <h3>Date of birth</h3>
            <form:input type="date" path="dateOfBirth" cssClass="fadeIn third"/>
            <input type="submit" class="fadeIn fourth" value="Submit">
        </form:form>
        <div id="formFooter">
            <a class="underlineHover" href="${pageContext.request.contextPath}/login">Login</a>
        </div>
    </div>
</div>
</body>
</html>
