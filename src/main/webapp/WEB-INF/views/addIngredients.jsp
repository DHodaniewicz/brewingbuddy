<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 27.03.2022
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add new brewing recipe</title>
</head>
<body>
<%--@elvariable id="recipe" type="pl.brewingbuddy.entities.Recipe"--%>
<form:form method="post" modelAttribute="recipe">
    <p>
        <form:label path="name">Recipe name: </form:label>
        <form:input path="name"/>
        <form:select itemValue="id" itemLabel="beerStyle"
                     path="beerStyle.id" items="${availableBeerStyles}"/>
        <form:label path="public">Public: </form:label>
        <form:checkbox path="public"></form:checkbox>
    </p>
    <p>
        <form:label path="expectedAmountOfBeer">Expected amount of beer: </form:label>
        <form:input path="expectedAmountOfBeer"/>
        <form:label path="expectedAmountOfBeer"> ml</form:label>
    </p>
    <p>
    <p>
        <form:label path="timeOfBoiling">Time of boiling: </form:label>
        <form:input path="timeOfBoiling"/>
        <form:label path="timeOfBoiling"> min</form:label>
    </p>

    <p>
        <form:label path="vaporisationSpeed">Vaporisation speed: </form:label>
        <form:input path="vaporisationSpeed"/>
        <form:label path="vaporisationSpeed"> %/h</form:label>
    </p>
    <p>
        <form:label path="boilingLoss">Loss during boiling: </form:label>
        <form:input path="boilingLoss"/>
        <form:label path="boilingLoss"> % </form:label>
    </p>
    <p>
        <form:label path="fermentationLoss">Loss during fermentation: </form:label>
        <form:input path="fermentationLoss"/>
        <form:label path="fermentationLoss"> % </form:label>
    </p>
    <p>
        <form:label path="meshProcesTime">Mesh process time: </form:label>
        <form:input path="meshProcesTime"/>
        <form:label path="meshProcesTime"> min </form:label>
    </p>
    <p>
        <form:label path="meshProcessTemperature">Mesh process temperature: </form:label>
        <form:input path="meshProcessTemperature"/>
        <form:label path="meshProcessTemperature"> C </form:label>
    </p>
    <p>
        <form:label path="waterMaltRatio">Water / malt ratio: </form:label>
        <form:input path="waterMaltRatio"/>
        <form:label path="waterMaltRatio"> l/kg </form:label>
    </p>
    <p>
        <form:label path="meshPerformance">Mesh performance: </form:label>
        <form:input path="meshPerformance"/>
        <form:label path="meshPerformance"> % </form:label>
    </p>
    <p><input type="submit" value="Save"></p>
</form:form>
</body>
</html>
