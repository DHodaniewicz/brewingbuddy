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

const refreshButton = document.getElementById('refreshButton');

refreshButton.addEventListener('click', function (event) {
    getAndUpdateCalculatedParams();
})

document.addEventListener('DOMContentLoaded', function () {

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
        console.log(data)
    })
}

function renderAllMalts(data) {
    data.forEach(renderNewMalt())
}
