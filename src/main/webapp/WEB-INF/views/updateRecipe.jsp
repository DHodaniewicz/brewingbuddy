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
<%@ include file="/headerMenu.jsp" %>

<%--@elvariable id="newRecipe" type="pl.brewingbuddy.entities.Recipe"--%>
<div class="container-md">
    <h1> Add new brewing recipe </h1>
    <div>
        <form id="mainForm">
            <div class="mb-3">
                <label for="name">Name of recipe:</label>
                <input type="text" name="name" id="name" aria-required="true" value="${newRecipe.name}">
            </div>
            <div class="mb-3">
                <label for="expectedAmountOfBeer"> Expected amount of beer [L]: </label>
                <input value="${newRecipe.expectedAmountOfBeer}" type="number" min="1" step="0.1"
                       name="expectedAmountOfBeer" id="expectedAmountOfBeer" aria-required="true">
            </div>
            <div class="mb-3">
                <label for="beerStyleId">Choose type of beer: </label>
                <select value="${newRecipe.beerStyle.id}" class="form-select" name="beerStyleId" id="beerStyleId"
                        form="mainForm">
                    <c:forEach items="${availableBeerStyles}" var="beerStyle">
                        <c:choose>
                            <c:when test="${newRecipe.beerStyle.id == beerStyle.id}">
                                <option selected="selected" value="${beerStyle.id}">${beerStyle.beerStyle}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${beerStyle.id}">${beerStyle.beerStyle}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="timeOfBoiling"> Time of boiling [min]: </label>
                <input type="number" min="1" max="120" name="expectedAmountOfBeer" id="timeOfBoiling"
                       value="${newRecipe.timeOfBoiling}">
            </div>
            <div class="mb-3">
                <label for="vaporisationSpeed"> Vaporisation speed [%/h]: </label>
                <input type="number" min="0" max="99" name="vaporisationSpeed" id="vaporisationSpeed"
                       value="${newRecipe.vaporisationSpeed}">
            </div>
            <div class="mb-3">
                <label for="boilingLoss"> Loss during boiling process [%]: </label>
                <input type="number" min="0" max="99" name="boilingLoss" id="boilingLoss"
                       value="${newRecipe.boilingLoss}">
            </div>
            <div class="mb-3">
                <label for="fermentationLoss"> Loss during fermentation process [%]: </label>
                <input type="number" min="0" max="99" name="fermentationLoss" id="fermentationLoss"
                       value="${newRecipe.fermentationLoss}">
            </div>
            <h3>Mash process parameters: </h3>
            <div class="mb-3">
                <label for="meshProcesTime"> Mashing process time [min]: </label>
                <input type="number" min="1" max="120" name="meshProcesTime" id="meshProcesTime"
                       value="${newRecipe.meshProcesTime}">
            </div>
            <div class="mb-3">
                <label for="meshProcessTemperature"> Mashing process temperature [C]: </label>
                <input type="number" min="55" max="80" name="meshProcessTemperature" id="meshProcessTemperature"
                       value="${newRecipe.meshProcessTemperature}">
            </div>
            <div class="mb-3">
                <label for="waterMaltRatio"> Water to malt ratio [L/kg]: </label>
                <input type="number" min="1" max="5" step="0.1" name="waterMaltRatio" id="waterMaltRatio"
                       value="${newRecipe.waterMaltRatio}">
            </div>
            <div class="mb-3">
                <label for="meshPerformance"> Mashing process performance [%]: </label>
                <input type="number" min="1" max="100" name="meshPerformance" id="meshPerformance"
                       value="${newRecipe.meshPerformance}">
            </div>
            <div class="mb-3">
                <label for="yeast">Choose type of yeast: </label>
                <select value="${newRecipe.yeast.id}" class="form-select" name="yeast" id="yeast" form="mainForm">
                    <c:forEach items="${availableYeast}" var="yeast">
                        <c:choose>
                            <c:when test="${newRecipe.yeast.id == yeast.id}">
                                <option selected="selected" value="${yeast.id}">${yeast.name} ${yeast.form} </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${yeast.id}">${yeast.name} ${yeast.form} </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="d-grid gap-2">
                <button class="btn btn-primary" type="submit"> Save basic parameters</button>
            </div>
        </form>
    </div>

    <div class="mb2">
        <ul class="list-group">
            <li class="list-group-item">
                Water needed for Mashing process: <span span class="badge bg-primary" id="waterVolumeForMesh"></span>
                [L]
            </li>
            <li class="list-group-item">
                Water needed for Sparging: <span class="badge bg-primary" id="waterVolumeForSparging"></span> [L]
            </li>
            <li class="list-group-item">
                Overall mash volume: <span class="badge bg-primary" id="overallMeshVolume"></span> [L]
            </li>
            <li class="list-group-item">
                Amount of boiled wort: <span class="badge bg-primary" id="amountOfBoiledWort"></span> [L]
            </li>
            <li class="list-group-item">
                Wort volume after boiling process: <span class="badge bg-primary" id="amountOfWortAfterBoiling"></span>
                [L]
            </li>
            <li class="list-group-item">
                BLG value before boiling process: <span class="badge bg-primary" id="blgBeforeBoiling"></span> [blg]
            </li>
            <li class="list-group-item">
                BLG on start of fermentation: <span class="badge bg-primary" id="blg"></span> [blg]
            </li>
            <li class="list-group-item">
                Calculated color of beer: <span class="badge bg-primary" id="srm"></span> [srm]
            </li>
            <li class="list-group-item">
                Expected alcohol: <span class="badge bg-primary" id="abv"></span> [%]
            </li>
            <li class="list-group-item">
                Expected value of ibu: <span class="badge bg-primary" id="ibu"></span> [ibu]
            </li>
        </ul>
        <button id="refreshButton">Refresh</button>
    </div>

    <div>
        <form id="addMaltForm">
            <h3>Choose malt you want to add: </h3>
            <div class="mb-3">
                <label for="selectMalt">Choose type of malt: </label>
                <select class="form-select" name="malt" id="selectMalt" form="addMaltForm">
                    <c:forEach items="${availableMalts}" var="malt">
                        <option value="${malt.id}">${malt.name} </option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="maltAmount"> Amount of malt: </label>
                <input type="number" min="0" step="any" name="amount" id="maltAmount">
                <span> [kg] </span>
            </div>
            <button type="submit"> Add</button>
        </form>
    </div>

    <div>
        <ul class="list-group" id="recipeMaltList">

        </ul>
    </div>

    <div>
        <form id="addHopForm">
            <h3>Choose hop you want to add: </h3>
            <div class="mb-3">
                <label for="selectHop">Choose type of hop: </label>
                <select class="form-select" name="yeast" id="selectHop" form="addHopForm">
                    <c:forEach items="${availableHops}" var="hop">
                        <option value="${hop.id}">${hop.name} </option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="hopAmount"> Amount of hop : </label>
                <input type="number" step="1" min="1" name="hopAmount" id="hopAmount">
                <span> [g] </span>
            </div>
            <div class="mb-3">
                <label for="boilingTime"> Time of boiling: </label>
                <input type="number" step="1" min="1" max="75" name="hopAmount" id="boilingTime">
                <span> [min] </span>
            </div>
            <button type="submit"> Add</button>
        </form>
    </div>

    <div>
        <ul class="list-group" id="recipeHopList">

        </ul>
    </div>

    <div class="d-grid gap-2 col-6">
        <form method="post" action="">
            <button class="btn btn-primary" id="saveRecipe" type="submit"> SAVE RECIPE</button>
        </form>
    </div>
</div>


<script>
    const mainForm = document.getElementById('mainForm');
    const maltForm = document.getElementById('addMaltForm')
    const hopForm = document.getElementById('addHopForm')
    const recipeMaltList = document.getElementById('recipeMaltList')
    const recipeHopList = document.getElementById('recipeHopList')


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

        console.log(JSON.stringify({
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
        }))

        fetch(
            'http://localhost:8080/recipe/basicParams',
            {
                headers: {'Content-Type': 'application/json'},
                credentials: 'include',
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
            updateCalculatedParams(data);
        })
    });

    maltForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const maltId = event.target.querySelector('select').value;
        const amount = event.target.querySelector('input').value;

        console.log(JSON.stringify({maltId: maltId, amount: amount}))

        addMaltToRecipe(maltId, amount).then(function (data) {
            console.log(data)
            renderNewMalt(data);
        }).then(function () {
            getAndUpdateCalculatedParams();
        });
    });

    hopForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const hopId = event.target.querySelector('select').value;
        const amount = event.target.querySelectorAll('input')[0].value;
        const timeOfBoiling = event.target.querySelectorAll('input')[1].value;

        addHopToRecipe(hopId, amount, timeOfBoiling).then(function (data) {
            console.log(data);
            renderNewHop(data);
        }).then(function () {
            getAndUpdateCalculatedParams();
        })
    })

    function updateCalculatedParams(data) {
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
    }

    function deleteMaltFromRecipe(recipeMaltId) {
        return fetch(
            'http://localhost:8080/recipe/malt/delete/' + recipeMaltId,
            {
                method: 'DELETE'
            }
        ).then(
            function (resp) {
                if (!resp.ok) {
                    alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
                }
                return resp.json();
            }
        )
    }

    function updateMaltFromRecipe(recipeMaltId, amount) {
        return fetch(
            'http://localhost:8080/recipe/malt/update/',
            {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    id: recipeMaltId,
                    amount: amount
                }),
            }
        ).then(
            function (resp) {
                if (!resp.ok) {
                    alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
                }
                return resp.json();
            }
        )
    }

    function addMaltToRecipe(maltId, amount) {
        return fetch(
            'http://localhost:8080/recipe/malt/add/',
            {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    maltId: maltId,
                    amount: amount
                }),
            }
        ).then(
            function (resp) {
                if (!resp.ok) {
                    alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
                }
                return resp.json();
            }
        )
    }

    function renderNewMalt(data) {
        console.log(data)

        const newLi = document.createElement('li');
        newLi.className = 'list-group-item';
        newLi.innerText = data.maltName + '   ';

        const maltAmountInput = document.createElement('input');
        maltAmountInput.value = data.amount;
        maltAmountInput.type = 'number';
        maltAmountInput.min = '0';
        maltAmountInput.step = '0.01'
        newLi.appendChild(maltAmountInput);

        const maltUnitSpan = document.createElement('span');
        maltUnitSpan.innerText = ' [kg] '
        newLi.appendChild(maltUnitSpan)

        maltAmountInput.addEventListener('blur', function () {
            updateMaltFromRecipe(recipeMaltId, maltAmountInput.value).then(function () {
                getAndUpdateCalculatedParams();
            });
        })

        const recipeMaltId = data.id;
        console.log(recipeMaltId);

        const deleteButton = document.createElement('button');
        deleteButton.innerText = 'Delete';

        newLi.appendChild(deleteButton);
        recipeMaltList.appendChild(newLi);

        deleteButton.addEventListener('click', function () {
            deleteMaltFromRecipe(recipeMaltId).then(
                function () {
                    deleteButton.parentElement.remove()
                }).then(function () {
                getAndUpdateCalculatedParams();
            });
        });
    }

    function addHopToRecipe(hopId, amount, timeOfBoiling) {
        return fetch(
            'http://localhost:8080/recipe/hop/add/',
            {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    hopId: hopId,
                    amount: amount,
                    timeOfBoiling: timeOfBoiling
                }),
            }
        ).then(
            function (resp) {
                if (!resp.ok) {
                    alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
                }
                return resp.json();
            }
        )
    }

    function deleteHopFromRecipe(recipeHopId) {
        return fetch(
            'http://localhost:8080/recipe/hop/delete/' + recipeHopId,
            {
                method: 'DELETE'
            }
        ).then(
            function (resp) {
                if (!resp.ok) {
                    alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
                }
                return resp.json();
            }
        )
    }

    function updateHopFromRecipe(recipeHopId, amount, timeOfBoiling) {
        return fetch(
            'http://localhost:8080/recipe/hop/update/',
            {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    id: recipeHopId,
                    amount: amount,
                    timeOfBoiling: timeOfBoiling
                }),
            }
        ).then(
            function (resp) {
                if (!resp.ok) {
                    alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
                }
                return resp.json();
            }
        )
    }

    function renderNewHop(data) {

        const recipeHopId = data.id;
        console.log(recipeHopId);

        const newLi = document.createElement('li');
        newLi.className = 'list-group-item';
        newLi.innerText = data.hopName + '   ';

        const hopAmountInput = document.createElement('input');
        hopAmountInput.value = data.amount;
        hopAmountInput.type = 'number';
        hopAmountInput.min = '1';
        hopAmountInput.step = '1';
        newLi.appendChild(hopAmountInput);

        const hopAmountUnitSpan = document.createElement('span')
        hopAmountUnitSpan.innerText = ' [g] '
        newLi.appendChild(hopAmountUnitSpan)

        hopAmountInput.addEventListener('blur', function () {
            updateHopFromRecipe(recipeHopId, hopAmountInput.value, hopBoilingTimeInput.value)
                .then(function () {
                    getAndUpdateCalculatedParams();
                });
        })

        const hopBoilingTimeInput = document.createElement('input');
        hopBoilingTimeInput.value = data.timeOfBoiling;
        hopBoilingTimeInput.type = 'number';
        hopBoilingTimeInput.min = '1';
        hopBoilingTimeInput.max = '75';
        hopBoilingTimeInput.step = '1';
        newLi.appendChild(hopBoilingTimeInput);

        const hopBoilingTimeUnitSpan = document.createElement('span');
        hopBoilingTimeUnitSpan.innerText = ' [min] ';
        newLi.appendChild(hopBoilingTimeUnitSpan);

        hopBoilingTimeInput.addEventListener('blur', function () {
            updateHopFromRecipe(recipeHopId, hopAmountInput.value, hopBoilingTimeInput.value)
                .then(function () {
                    getAndUpdateCalculatedParams();
                });
        })

        const deleteButton = document.createElement('button');
        deleteButton.innerText = 'Delete';

        newLi.appendChild(deleteButton);
        recipeHopList.appendChild(newLi);

        deleteButton.addEventListener('click', function () {
            deleteHopFromRecipe(recipeHopId).then(
                function () {
                    deleteButton.parentElement.remove()
                }).then(function () {
                getAndUpdateCalculatedParams();
            });
        });
    }

    function getAndUpdateCalculatedParams() {
        fetch(
            'http://localhost:8080/recipe/calculated',
            {
                method: 'GET'
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
            updateCalculatedParams(data);
        })
    }


    document.addEventListener('DOMContentLoaded', function () {
        getAllMalts();
        getAllHops();
    })


    function getAllMalts() {
        return fetch(
            'http://localhost:8080/recipe/malt/all',
            {
                method: 'GET',
            }
        ).then(
            function (resp) {
                if (!resp.ok) {
                    alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
                }
                return resp.json();
            }
        ).then(function (data) {
            console.log(data);
            data.forEach(element => {
                renderNewMalt(element);
            })
        })
    }

    function getAllHops() {
        return fetch(
            'http://localhost:8080/recipe/hop/all',
            {
                method: 'GET',
            }
        ).then(
            function (resp) {
                if (!resp.ok) {
                    alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
                }
                return resp.json();
            }
        ).then(function (data) {
            console.log(data);
            data.forEach(element => {
                renderNewHop(element);
            })
        })
    }

</script>
</body>

</html>
