<%--
  Created by IntelliJ IDEA.
  User: vielen
  Date: 3/14/20
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login</title>
    <jsp:include page="bootstrap.jsp"/>
</head>
<body>
<div class="wrapper fadeInDown">
    ${sessionScope.activate}
    <div id="formContent">
        <form method="post">
            <h3>Username</h3>
            <c:if test="${usernameError != null}">
                <div class="alert alert-danger" role="alert">
                    ${usernameError}
                </div>
            </c:if>
            <input type="text" name="username" placeholder="Insert your username"/>
            <h3>Password</h3>
            <c:if test="${passwordError != null}">
                <div class="alert alert-danger" role="alert">
                        ${passwordError}
                </div>
            </c:if>
            <input type="password" name="password" placeholder="Insert your password">
            <input type="submit" class="fadeIn fourth" value="Login">
        </form>
        <div id="formFooter">
            <a class="underlineHover" href="registration">Registration</a>
        </div>
    </div>
</div>
</body>
</html>
