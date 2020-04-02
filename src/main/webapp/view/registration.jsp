<%--
  Created by IntelliJ IDEA.
  User: vielen
  Date: 3/14/20
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form action="save" method="post" enctype="multipart/form-data">
            <h3>Username</h3>
            <input type="text" name="username" class="fadeIn second"/>
            <h3>Password</h3>
            <input type="password" name="password" class="fadeIn third"/>
            <h3>Date of birth</h3>
            <input type="date" name="dateOfBirth" class="fadeIn third"/>
            <h3>Email</h3>
            <input type="email" name="email"/>
            <h3>Image</h3>
            <input type="file" name="multipartFile"/>
            <input type="submit" class="fadeIn fourth" value="Submit">
        </form>
        <div id="formFooter">
            <a class="underlineHover" href="login">Login</a>
        </div>
    </div>
</div>
</body>
</html>
