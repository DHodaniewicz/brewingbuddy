<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 10.04.2022
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Recipe details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@ include file="/headerMenu.jsp" %>

<div class="container-md">
    <h1>Recipe details</h1>
    <%--@elvariable id="recipeDetails" type="pl.brewingbuddy.entities.Recipe"--%>
    <h4>Basic information</h4>
    <ul class="list-group">
        <li class="list-group-item">Name: ${recipeDetails.name}</li>
        <li class="list-group-item">Style of beer: ${recipeDetails.beerStyle.beerStyle}</li>
        <li class="list-group-item">Expected amount od beer: ${recipeDetails.expectedAmountOfBeer} [L]</li>
        <li class="list-group-item">Created by: ${recipeDetails.user.username}</li>
    </ul>
    <h4>Declared loss during steps of the process</h4>
    <ul class="list-group">
        <li class="list-group-item">Boiling time: ${recipeDetails.timeOfBoiling} [min]</li>
        <li class="list-group-item">Vaporisation speed: ${recipeDetails.vaporisationSpeed} [%/h]</li>
        <li class="list-group-item">Loss during boiling: ${recipeDetails.boilingLoss} [%]</li>
        <li class="list-group-item">Loss during fermentation: ${recipeDetails.fermentationLoss} [%]</li>
    </ul>
    <h4>Mashing process parameters</h4>
    <ul class="list-group">
        <li class="list-group-item">Mashing process duration time: ${recipeDetails.meshProcesTime} [min]</li>
        <li class="list-group-item">Mashing process temperature: ${recipeDetails.meshProcessTemperature} [&#8451]</li>
        <li class="list-group-item">Water to malt ratio: ${recipeDetails.waterMaltRatio} [L/kg]</li>
        <li class="list-group-item">Mashing process performance: ${recipeDetails.waterVolumeForMesh} [%]</li>
        <li class="list-group-item">Water volume needed for mashing: ${recipeDetails.waterVolumeForMesh} [L]</li>
        <li class="list-group-item">Water volume needed for sparging: ${recipeDetails.waterVolumeForSparging} [L]</li>
        <li class="list-group-item">List of Malts:
        <ol class="list-group list-group-numbered">
            <c:forEach items="${recipeDetails.recipeMalt}" var="recipeMalt">
                <li class="list-group-item">${recipeMalt.malt.name} EBC: ${recipeMalt.malt.ebc} Amount: ${recipeMalt.amount} [kg] </li>
            </c:forEach>
        </ol>
        </li>
    </ul>
    <h4>Boiling process parameters</h4>
    <ul class="list-group">
        <li class="list-group-item">Overall volume of boiled wort: ${recipeDetails.amountOfBoiledWort} [L]</li>
        <li class="list-group-item">Percentage of sugar in wort before boiling process: ${recipeDetails.blgBeforeBoiling} [blg]</li>
        <li class="list-group-item">Overall volume of wort after boiling process: ${recipeDetails.amountOfWortAfterBoiling} [blg]</li>
        <li class="list-group-item">List of Hops:
            <ol class="list-group list-group-numbered">
                <c:forEach items="${recipeDetails.recipeHop}" var="recipeHop">
                    <li class="list-group-item">${recipeHop.hop.name} Boiling time: ${recipeHop.timeOfBoiling} [min] Amount: ${recipeHop.amount} [g] </li>
                </c:forEach>
            </ol>
        </li>
    </ul>
    <h4>Yeast used for fermentation proces:</h4>
    <ul class="list-group">
        <li class="list-group-item">Name: ${recipeDetails.yeast.name} </li>
    </ul>
    <h4>Calculated parameters of beer</h4>
    <ul class="list-group">
        <li class="list-group-item">Calculated bitterness of beer in IBU: ${recipeDetails.ibu} [IBU]</li>
        <li class="list-group-item">Amount of sugars before fermentation in Balling scale: ${recipeDetails.blg} [blg]</li>
        <li class="list-group-item">Calculated percentage of alcohol in beer: ${recipeDetails.abv} [%]</li>
        <li class="list-group-item">Calculated color of beer in SRM: ${recipeDetails.srm} [srm]</li>
        <img src="https://3.bp.blogspot.com/-mKoGRphg9RY/WpMJ-rKayNI/AAAAAAAABtc/2oXx14k9A64ayRsgaugHQ3pCoqPNdQWngCLcBGAs/s1600/color%2Bebc.jpg" class="img-fluid" alt="srm scale">
    </ul>
</div>
</body>
</html>
