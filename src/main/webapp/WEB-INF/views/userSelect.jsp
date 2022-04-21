<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 21.04.2022
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Choose active user</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@ include file="/headerMenu.jsp" %>
<%--@elvariable id="user" type="pl.brewingbuddy.entities.User"--%>
<div class="container-md">
    <form id="userForm" method="post">
        <label>Choose available user: </label>
        <select class="form-select" name="userId" id="userId" form="userForm">
            <c:forEach items="${availableUsers}" var="user">
                <option value="${user.id}">${user.username}</option>
            </c:forEach>
        </select>
        <input type="submit">
    </form>
</div>

</body>
</html>
