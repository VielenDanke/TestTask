<%--
  Created by IntelliJ IDEA.
  User: vielen
  Date: 3/22/20
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
    ${error}
    <a href="${pageContext.request.contextPath}/">Main page</a>
    <a href="${pageContext.request.contextPath}/login">Login page</a>
    <a href="${pageContext.request.contextPath}/registration">Registration page</a>
</body>
</html>
