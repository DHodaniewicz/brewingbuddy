<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 09.04.2022
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All available brewing recipes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@ include file="/headerMenu.jsp" %>


<div class="container">
    <h1>All available recipes: </h1>
    <form method="get" action="/recipe/all/filter" id="beerStyleForm">
        <label for="beerStyleId">Filter recipe list</label>
        <select class="form-select" name="beerStyleId" id="beerStyleId" form="beerStyleForm">
            <c:forEach items="${availableBeerStyles}" var="beerStyle">
                <option value="${beerStyle.id}">${beerStyle.beerStyle}</option>
            </c:forEach>
        </select>
        <button class="btn btn-primary" type="submit"> Filter</button>
    </form>
    <form action="/recipe/all" method="get">
        <button class="btn btn-primary"> Remove filter </button>
    </form>
</div>

<div class="container-md">
    <ol class="list-group list-group-numbered">
        <%--@elvariable id="recipe" type="pl.brewingbuddy.entities.Recipe"--%>
        <c:forEach items="${allRecipes}" var="recipe">
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="list-group-horizontal-md">
                        <div class="fw-bold">${recipe.name}</div>
                        <div>${recipe.beerStyle.beerStyle} </div>
                        <div>Blg: ${recipe.blg} Ibu: ${recipe.ibu}</div>
                    </div>
                    <div class="list-group-horizontal-md">
                        <a href="/recipe/details/${recipe.id}"><button class="btn btn-primary"> Details </button></a>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ol>
</div>
</body>
</html>
