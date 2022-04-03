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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1> Add new brewing recipe </h1>
<div>
    <form id="mainForm">
        <div class="mb-3">
            <label for="name">Name of recipe:</label>
            <input type="text" name="name" id="name">
        </div>
        <div class="mb-3">
            <label for="expectedAmountOfBeer"> Expected amount of beer [L]: </label>
            <input type="number" min="1" step="0.1" name="expectedAmountOfBeer" id="expectedAmountOfBeer">
        </div>
        <div class="mb-3">
            <label for="beerStyleId">Choose type of beer: </label>
            <select class="form-select" name="beerStyleId" id="beerStyleId" form="mainForm">
                <c:forEach items="${availableBeerStyles}" var="beerStyle">
                    <option value="${beerStyle.id}">${beerStyle.beerStyle}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="timeOfBoiling"> Time of boiling [min]: </label>
            <input type="number" min="1" max="120" name="expectedAmountOfBeer" id="timeOfBoiling">
        </div>
        <div class="mb-3">
            <label for="vaporisationSpeed"> Vaporisation speed [%/h]: </label>
            <input type="number" min="0" max="100" name="vaporisationSpeed" id="vaporisationSpeed">
        </div>
        <div class="mb-3">
            <label for="boilingLoss"> Loss during boiling process [%]: </label>
            <input type="number" min="0" max="100" name="boilingLoss" id="boilingLoss">
        </div>
        <div class="mb-3">
            <label for="fermentationLoss"> Loss during fermentation process [%]: </label>
            <input type="number" min="0" max="100" name="fermentationLoss" id="fermentationLoss">
        </div>
        <h3>Mash process parameters: </h3>
        <div class="mb-3">
            <label for="meshProcesTime"> Mashing process time [min]: </label>
            <input type="number" min="1" max="120" name="meshProcesTime" id="meshProcesTime">
        </div>
        <div class="mb-3">
            <label for="meshProcessTemperature"> Mashing process temperature [C]: </label>
            <input type="number" min="55" max="80" name="meshProcessTemperature" id="meshProcessTemperature">
        </div>
        <div class="mb-3">
            <label for="waterMaltRatio"> Water to malt ratio [L/kg]: </label>
            <input type="number" min="1" max="5" step="0.1" name="waterMaltRatio" id="waterMaltRatio">
        </div>
        <div class="mb-3">
            <label for="meshPerformance"> Mashing process performance [%]: </label>
            <input type="number" min="1" max="120" name="meshPerformance" id="meshPerformance">
        </div>
        <div class="mb-3">
            <label for="yeast">Choose type of yeast: </label>
            <select class="form-select" name="yeast" id="yeast" form="mainForm">
                <c:forEach items="${availableYeast}" var="yeast">
                    <option value="${yeast.id}">${yeast.name} ${yeast.form} </option>
                </c:forEach>
            </select>
        </div>
        <button type="submit"> Save</button>
    </form>
</div>

<div class="mb2">
    <ul class="ul">
        <li>
            Water needed for Mashing process: <span id="waterVolumeForMesh"></span> [L]
        </li>
        <li>
            Water needed for Sparging: <span id="waterVolumeForSparging"></span> [L]
        </li>
        <li>
            Overall mash volume:  <span id="overallMeshVolume"></span> [L]
        </li>
        <li>
            Amount of boiled wort: <span id="amountOfBoiledWort"></span> [L]
        </li>
        <li>
            Wort volume after boiling process: <span id="amountOfWortAfterBoiling"></span> [L]
        </li>
        <li>
            BLG value before boiling process: <span id="blgBeforeBoiling"></span> [blg]
        </li>
        <li>
            BLG on start of fermentation: <span id="blg"></span> [blg]
        </li>
        <li>
            Calculated color of beer: <span id="srm"></span> []
        </li>
        <li>
            Expected alcohol: <span id="abv"></span> [%]
        </li>
        <li>
            Expected value of ibu: <span id="ibu"></span> [%]
        </li>
    </ul>
</div>

<div>
    <form id="addMaltForm">
        <h3>Choose malt you want to add: </h3>
        <div class="mb-3">
            <label for="selectMalt">Choose type of malt: </label>
            <select class="form-select" name="yeast" id="selectMalt" form="addMaltForm">
                <c:forEach items="${availableMalts}" var="malt">
                    <option value="${malt.id}">${malt.name} </option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="maltAmount"> Amount of malt [kg]: </label>
            <input type="number" step="0.01" min="0.01" name="amount" id="maltAmount">
        </div>
        <button type="submit"> Save</button>
    </form>
</div>


<script>
    const mainForm = document.getElementById('mainForm');
    const maltForm = document.getElementById('addMaltForm')


    const amountOfBoiledWort = document.querySelector('#amountOfBoiledWort')
    const amountOfWortAfterBoiling = document.querySelector('#amountOfWortAfterBoiling')
    const blgBeforeBoiling = document.querySelector('#blgBeforeBoiling')
    const overallMeshVolume = document.querySelector('#overallMeshVolume')
    const waterVolumeForMesh = document.querySelector('#waterVolumeForMesh')
    const waterVolumeForSparging = document.querySelector('#waterVolumeForSparging')
    const abv = document.querySelector('#abv')
    const srm = document.querySelector('#srm')
    const blg = document.querySelector('#blg')
    const ibu = document.querySelector('#ibu')

    mainForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const formInputs = event.target.querySelectorAll('input')

        const name = formInputs[0].value;
        const expectedAmountOfBeer = formInputs[1].value;
        const beerStyleId = event.target.querySelector('#beerStyleId').value;
        const timeOfBoiling = formInputs[2].value;
        const vaporisationSpeed = formInputs[3].value;
        const boilingLoss = formInputs[4].value;
        const fermentationLoss = formInputs[5].value;
        const meshProcesTime = formInputs[6].value;
        const meshProcessTemperature = formInputs[7].value;
        const waterMaltRatio = formInputs[8].value;
        const meshPerformance = formInputs[9].value;
        const yeastId = event.target.querySelector('#yeast').value;

        console.log(JSON.stringify({name: name, expectedAmountOfBeer: expectedAmountOfBeer, beerStyleId: beerStyleId}))

        fetch(
            'http://localhost:8080/recipe/basicParams',
            {
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    name: name,
                    expectedAmountOfBeer: expectedAmountOfBeer,
                    beerStyleId: beerStyleId,
                    timeOfBoiling: timeOfBoiling,
                    vaporisationSpeed: vaporisationSpeed,
                    boilingLoss: boilingLoss,
                    fermentationLoss: fermentationLoss,
                    meshProcesTime: meshProcesTime,
                    meshProcessTemperature: meshProcessTemperature,
                    waterMaltRatio: waterMaltRatio,
                    meshPerformance: meshPerformance,
                    yeastId: yeastId
                }),
                method: 'POST'
            }
        ).then(
            function (resp) {
                if (!resp.ok) {
                    alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
                }
                return resp.json();
            }
        ).then(function (data) {
            console.log(data)
            amountOfBoiledWort.textContent = data.amountOfBoiledWort;
            blgBeforeBoiling.textContent = data.blgBeforeBoiling;
            amountOfWortAfterBoiling.textContent = data.amountOfWortAfterBoiling;
            overallMeshVolume.textContent = data.overallMeshVolume;
            waterVolumeForMesh.textContent = data.waterVolumeForMesh;
            waterVolumeForSparging.textContent = data.waterVolumeForSparging;
            abv.textContent = data.abv;
            srm.textContent = data.srm;
            blg.textContent = data.blg;
            ibu.textContent = data.ibu;
        })
    });

    maltForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const maltId = event.target.querySelector('select').value;
        const amount = event.target.querySelector('input').value;

        console.log(JSON.stringify({maltId: maltId, amount: amount}))

        fetch(
            'http://localhost:8080/recipe/malt/add',
            {
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    maltId: maltId,
                    amount: amount
                }),
                method: 'POST'
            }
        ).then(
            function (resp) {
                if (!resp.ok) {
                    alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
                }
                return resp.json();
            }
        ).then(function (data) {
            console.log(data)
        })

    });

</script>
</body>

</html>
