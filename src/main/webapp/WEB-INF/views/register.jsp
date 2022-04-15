<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 14.04.2022
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%--@elvariable id="user" type="pl.brewingbuddy.entities.User"--%>
<form:form method="post" modelAttribute="user">
<div class="container-md align-items-center">
    <h3>Register new user: </h3>
    <div class="mb-3">
        <form:label path="username">Username: </form:label>
        <form:input path="username"/>
        <div>
            <form:errors cssClass="alert-danger" path="username"/>
        </div>

    </div>
    <div class="mb-3">
        <form:label path="email">Email: </form:label>
        <form:input path="email"/>
        <div>
            <form:errors cssClass="alert-danger" path="email"/>
        </div>
    </div>
    <div class="mb-3">
        <form:label path="password">Password: </form:label>
        <form:password path="password"/>
        <div>
            <form:errors cssClass="alert-danger" path="password"/>
        </div>
    </div>
    <input type="submit" value="Register">
    </form:form>
</div>
</body>
</html>
