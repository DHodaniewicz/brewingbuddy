<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 14.04.2022
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
  <div class="container-md">
  <form method="post">
    <div class="mb-3"><label> User Name : <input type="text" name="username"/> </label></div>
    <div class="mb-3"><label> Password: <input type="password" name="password"/> </label></div>
    <div class="mb-3"><input type="submit" value="Sign In"/></div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>
  </div>
  
  </body>
</html>
