// ===============================================
// VARIABLES GLOBALES
// ===============================================

let array = [];
let originalArray = [];
let countArrayData = [];
let isRunning = false;
let isPaused = false;
let currentStepNum = 0;
let comparisonsCount = 0;
let operationsCount = 0;
let animationSpeed = 1000;

// ===============================================
// INICIALIZACIÓN
// ===============================================

document.addEventListener('DOMContentLoaded', function() {
    initializeSpeedControl();
    generateArray();
});

function initializeSpeedControl() {
    const speedSlider = document.getElementById('speedSlider');
    speedSlider.addEventListener('input', function(e) {
        animationSpeed = parseInt(e.target.value);
        document.getElementById('speedValue').textContent = animationSpeed + 'ms';
    });
}

// ===============================================
// GENERACIÓN Y RENDERIZADO DE ARRAYS
// ===============================================

function generateArray() {
    const size = 8;
    array = Array.from({length: size}, () => Math.floor(Math.random() * 15) + 1);
    originalArray = [...array];
    resetVisualization();
    renderArray();
}

function renderArray() {
    const container = document.getElementById('arrayContainer');
    container.innerHTML = '';
    
    const maxValue = Math.max(...array);
    
    array.forEach((value, index) => {
        const barContainer = document.createElement('div');
        barContainer.className = 'array-bar';
        
        const bar = document.createElement('div');
        bar.className = 'bar';
        bar.style.height = `${(value / maxValue) * 150}px`;
        bar.id = `bar-${index}`;
        
        const valueLabel = document.createElement('div');
        valueLabel.className = 'value';
        valueLabel.textContent = value;
        
        barContainer.appendChild(bar);
        barContainer.appendChild(valueLabel);
        container.appendChild(barContainer);
    });
}

function renderCountArray(countArray, min) {
    const container = document.getElementById('countArray');
    container.innerHTML = '';
    
    countArray.forEach((count, index) => {
        const item = document.createElement('div');
        item.className = 'count-item';
        item.id = `count-${index}`;
        
        const label = document.createElement('div');
        label.className = 'count-label';
        label.textContent = `[${min + index}]`;
        
        const value = document.createElement('div');
        value.className = 'count-value';
        value.textContent = count;
        
        item.appendChild(label);
        item.appendChild(value);
        container.appendChild(item);
    });
}

// ===============================================
// ACTUALIZACIÓN DE UI
// ===============================================

function updateInfo(step, comps, ops, range) {
    document.getElementById('currentStep').textContent = step;
    document.getElementById('comparisons').textContent = comps;
    document.getElementById('operations').textContent = ops;
    document.getElementById('range').textContent = range;
}

function setStepDescription(text) {
    document.getElementById('stepDescription').textContent = text;
}

// ===============================================
// CONTROLES DE EJECUCIÓN
// ===============================================

async function startSort() {
    if (isRunning) return;
    
    isRunning = true;
    isPaused = false;
    document.getElementById('startBtn').disabled = true;
    document.getElementById('pauseBtn').disabled = false;
    
    await countSort();
    
    isRunning = false;
    document.getElementById('startBtn').disabled = false;
    document.getElementById('pauseBtn').disabled = true;
}

function pauseSort() {
    isPaused = !isPaused;
    document.getElementById('pauseBtn').textContent = isPaused ? '▶️ Continuar' : '⏸️ Pausar';
}

function resetVisualization() {
    isRunning = false;
    isPaused = false;
    currentStepNum = 0;
    comparisonsCount = 0;
    operationsCount = 0;
    array = [...originalArray];
    
    document.getElementById('startBtn').disabled = false;
    document.getElementById('pauseBtn').disabled = true;
    document.getElementById('pauseBtn').textContent = '⏸️ Pausar';
    
    updateInfo(0, 0, 0, 0);
    setStepDescription('Presiona "Iniciar" para comenzar la visualización del Count Sort');
    
    renderArray();
    document.getElementById('countArray').innerHTML = '';
}

// ===============================================
// UTILIDADES
// ===============================================

async function sleep(ms) {
    while (isPaused) {
        await new Promise(resolve => setTimeout(resolve, 100));
    }
    return new Promise(resolve => setTimeout(resolve, ms));
}

// ===============================================
// ALGORITMO COUNT SORT
// ===============================================

async function countSort() {
    currentStepNum = 0;
    comparisonsCount = 0;
    operationsCount = 0;
    
    // PASO 1: Encontrar min y max
    await findMinMax();
    
    const min = Math.min(...array);
    const max = Math.max(...array);
    const range = max - min + 1;
    
    currentStepNum++;
    updateInfo(currentStepNum, comparisonsCount, operationsCount, range);
    await sleep(animationSpeed);
    
    // PASO 2: Crear array de conteo
    const count = await createCountArray(range, min);
    
    // PASO 3: Contar ocurrencias
    await countOccurrences(count, min);
    
    // PASO 4: Acumular conteos
    await accumulateCounts(count, min);
    
    // PASO 5: Construir array ordenado
    await buildSortedArray(count, min);
}

async function findMinMax() {
    setStepDescription('Paso 1: Encontrando el valor mínimo y máximo del array...');
    
    for (let i = 0; i < array.length; i++) {
        document.getElementById(`bar-${i}`).classList.add('comparing');
        await sleep(animationSpeed / 2);
        
        comparisonsCount += 2;
        
        document.getElementById(`bar-${i}`).classList.remove('comparing');
    }
}

async function createCountArray(range, min) {
    setStepDescription(`Paso 2: Creando array de conteo de tamaño ${range} (desde ${min} hasta ${Math.max(...array)})...`);
    const count = new Array(range).fill(0);
    renderCountArray(count, min);
    currentStepNum++;
    updateInfo(currentStepNum, comparisonsCount, operationsCount, range);
    await sleep(animationSpeed);
    return count;
}

async function countOccurrences(count, min) {
    setStepDescription('Paso 3: Contando las ocurrencias de cada elemento...');
    
    for (let i = 0; i < array.length; i++) {
        document.getElementById(`bar-${i}`).classList.add('counting');
        const countIndex = array[i] - min;
        count[countIndex]++;
        operationsCount++;
        
        document.getElementById(`count-${countIndex}`).classList.add('active');
        renderCountArray(count, min);
        await sleep(animationSpeed);
        
        document.getElementById(`bar-${i}`).classList.remove('counting');
        document.getElementById(`count-${countIndex}`).classList.remove('active');
    }
    
    const range = count.length;
    currentStepNum++;
    updateInfo(currentStepNum, comparisonsCount, operationsCount, range);
    await sleep(animationSpeed);
}

async function accumulateCounts(count, min) {
    setStepDescription('Paso 4: Acumulando conteos para obtener posiciones finales...');
    
    for (let i = 1; i < count.length; i++) {
        document.getElementById(`count-${i}`).classList.add('active');
        document.getElementById(`count-${i-1}`).classList.add('active');
        
        count[i] += count[i - 1];
        renderCountArray(count, min);
        operationsCount++;
        await sleep(animationSpeed);
        
        document.getElementById(`count-${i}`).classList.remove('active');
        document.getElementById(`count-${i-1}`).classList.remove('active');
    }
    
    const range = count.length;
    currentStepNum++;
    updateInfo(currentStepNum, comparisonsCount, operationsCount, range);
    await sleep(animationSpeed);
}

async function buildSortedArray(count, min) {
    setStepDescription('Paso 5: Construyendo el array ordenado en sus posiciones finales...');
    const output = new Array(array.length);
    
    for (let i = array.length - 1; i >= 0; i--) {
        const countIndex = array[i] - min;
        document.getElementById(`bar-${i}`).classList.add('counting');
        document.getElementById(`count-${countIndex}`).classList.add('active');
        
        output[count[countIndex] - 1] = array[i];
        count[countIndex]--;
        operationsCount++;
        
        renderCountArray(count, min);
        await sleep(animationSpeed);
        
        document.getElementById(`bar-${i}`).classList.remove('counting');
        document.getElementById(`count-${countIndex}`).classList.remove('active');
    }
    
    const range = count.length;
    currentStepNum++;
    updateInfo(currentStepNum, comparisonsCount, operationsCount, range);
    
    // Mostrar resultado final
    array = output;
    renderArray();
    
    // Marcar como ordenado
    for (let i = 0; i < array.length; i++) {
        document.getElementById(`bar-${i}`).classList.add('sorted');
    }
    
    setStepDescription('✅ ¡Array ordenado exitosamente con Count Sort!');
    await sleep(animationSpeed);
}