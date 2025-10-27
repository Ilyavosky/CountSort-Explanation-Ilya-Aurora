# Count Sort - Proyecto Completo

## Descripción General

Proyecto educativo que implementa el algoritmo de ordenamiento Count Sort en dos modalidades: una implementación en Java con arquitectura modular y una visualización web interactiva. El objetivo es proporcionar tanto una comprensión teórica como práctica del algoritmo mediante código estructurado y animaciones visuales.

## Estructura del Proyecto

```
CountSort/
│
├── CountSortJava/              # Implementación en Java
│   ├── src/
│   │   ├── interfaces/
│   │   │   └── ISortable.java
│   │   ├── model/
│   │   │   └── CountSortModel.java
│   │   ├── utils/
│   │   │   └── ArrayUtils.java
│   │   └── Main.java
│   └── CountSortJava.iml
│
└── CountSortAnimation/         # Visualización Web
    ├── index.html
    ├── styles.css
    └── script.js
```

## Sobre el Algoritmo Count Sort

Count Sort es un algoritmo de ordenamiento no comparativo que funciona contando el número de ocurrencias de cada elemento único en el array de entrada. Utiliza esta información para colocar cada elemento en su posición correcta en el array de salida.

### Características del Algoritmo

- **Complejidad Temporal**: O(n + k) donde n es el número de elementos y k es el rango de valores
- **Complejidad Espacial**: O(k) para el array de conteo
- **Estabilidad**: Es un algoritmo estable, mantiene el orden relativo de elementos iguales
- **Tipo**: Algoritmo no comparativo

### Funcionamiento

1. Encontrar el valor mínimo y máximo del array
2. Crear un array de conteo de tamaño igual al rango
3. Contar las ocurrencias de cada elemento
4. Acumular los conteos para determinar posiciones
5. Construir el array ordenado usando las posiciones calculadas

## Parte 1: Implementación en Java

### Requisitos

- Java Development Kit (JDK) 21 o superior
- IDE compatible con Java (IntelliJ IDEA, Eclipse, NetBeans, VS Code)

### Estructura de la Implementación

#### Interfaces
**ISortable.java**: Interfaz genérica que define el contrato para algoritmos de ordenamiento. Utiliza genéricos para permitir flexibilidad en los tipos de datos.

#### Model
**CountSortModel.java**: Implementación concreta del algoritmo Count Sort. Incluye:
- Manejo de números positivos y negativos
- Contador de comparaciones y operaciones
- Métodos auxiliares para estadísticas

#### Utils
**ArrayUtils.java**: Utilidades genéricas para manipulación de arrays:
- Generación de arrays aleatorios
- Verificación de ordenamiento
- Impresión formateada
- Medición de tiempo de ejecución

#### Main
**Main.java**: Clase principal que demuestra el uso del algoritmo con:
- Ejemplos con arrays pequeños
- Manejo de números negativos
- Arrays aleatorios grandes
- Pruebas de rendimiento

### Compilación y Ejecución

```bash
# Navegar al directorio del proyecto Java
cd CountSortJava/src

# Compilar todos los archivos
javac interfaces/ISortable.java
javac model/CountSortModel.java
javac utils/ArrayUtils.java
javac Main.java

# Ejecutar el programa
java Main
```

### Salida Esperada

```
=== DEMOSTRACIÓN DE COUNT SORT ===

--- Ejemplo 1: Arreglo pequeño ---
Arreglo original: [4, 2, 2, 8, 3, 3, 1]
Arreglo ordenado: [1, 2, 2, 3, 3, 4, 8]
Tiempo de ejecución: 0.125 ms
Comparaciones: 12
Intercambios: 7
¿Está ordenado?: ✓ Sí
```

## Parte 2: Visualización Web Interactiva

### Requisitos

- Navegador web moderno (Chrome 90+, Firefox 88+, Safari 14+, Edge 90+)
- No requiere servidor web ni instalación de dependencias

### Componentes de la Visualización

#### index.html
Estructura HTML que contiene:
- Controles de interacción (botones de inicio, pausa, reinicio)
- Panel de información con estadísticas en tiempo real
- Contenedores para visualización del array y array de conteo
- Leyenda de colores

#### styles.css
Estilos que proporcionan:
- Diseño responsive y moderno
- Animaciones suaves con transiciones CSS
- Sistema de colores con gradientes
- Layout adaptable para diferentes pantallas

#### script.js
Lógica de la aplicación organizada en secciones:
- Gestión de estado global
- Renderizado dinámico de elementos
- Implementación del algoritmo con animaciones
- Control de velocidad y pausa/reanudación

### Ejecución

1. Navegar a la carpeta CountSortAnimation
2. Abrir el archivo index.html en un navegador web
3. Interactuar con los controles para visualizar el algoritmo

### Características de la Visualización

- **Animación paso a paso**: Visualiza cada fase del algoritmo
- **Control de velocidad**: Ajustable de 100ms a 2000ms
- **Pausa y reanudación**: Control total sobre la ejecución
- **Estadísticas en tiempo real**: Pasos, comparaciones, operaciones y rango
- **Descripciones contextuales**: Explicación de cada paso del algoritmo
- **Código de colores**:
  - Púrpura: Estado normal
  - Rosa/Rojo: Comparando valores
  - Azul: Contando ocurrencias
  - Verde: Array ordenado

## Casos de Uso

### Ventajas del Count Sort

El Count Sort es especialmente eficiente cuando:
- El rango de valores (k) es pequeño comparado con el número de elementos (n)
- Los valores son enteros en un rango conocido y limitado
- Se requiere un algoritmo de ordenamiento estable
- La predictibilidad del tiempo de ejecución es importante

### Limitaciones

No es recomendable usar Count Sort cuando:
- El rango de valores es muy grande (desperdicia memoria)
- Los datos son números de punto flotante
- La memoria disponible es limitada
- Los valores no son discretos

## Conceptos de Programación Aplicados

### En la Implementación Java

- **Programación Orientada a Objetos**: Separación de responsabilidades en clases
- **Genéricos**: Uso de tipos parametrizados para flexibilidad
- **Interfaces**: Contrato común para algoritmos de ordenamiento
- **Encapsulación**: Ocultamiento de detalles de implementación
- **Modularidad**: Código organizado en paquetes lógicos

### En la Visualización Web

- **Separación de Responsabilidades**: HTML para estructura, CSS para presentación, JS para lógica
- **Programación Asíncrona**: Uso de async/await para animaciones
- **Manipulación del DOM**: Creación y actualización dinámica de elementos
- **Gestión de Estado**: Control del flujo de ejecución del algoritmo
- **Programación Orientada a Eventos**: Manejo de interacciones del usuario

## Comparación con Otros Algoritmos

| Algoritmo | Tiempo Promedio | Espacio | Estable | Comparativo |
|-----------|----------------|---------|---------|-------------|
| Count Sort | O(n + k) | O(k) | Sí | No |
| Quick Sort | O(n log n) | O(log n) | No | Sí |
| Merge Sort | O(n log n) | O(n) | Sí | Sí |
| Bubble Sort | O(n²) | O(1) | Sí | Sí |

## Extensiones Posibles

### Para la Implementación Java
- Implementar otros algoritmos de ordenamiento (Radix Sort, Bucket Sort)
- Agregar pruebas unitarias con JUnit
- Crear interfaz gráfica con JavaFX o Swing
- Generar documentación Javadoc

### Para la Visualización Web
- Permitir entrada manual de valores
- Ajustar tamaño del array dinámicamente
- Comparar Count Sort con otros algoritmos lado a lado
- Agregar modo paso a paso manual
- Exportar animación como video o GIF

## Aplicaciones Reales

- **Ordenamiento de datos censales**: Cuando los valores están en rangos conocidos
- **Procesamiento de imágenes**: Ordenamiento de píxeles por intensidad
- **Histogramas**: Conteo de frecuencias de valores
- **Algoritmos base**: Usado internamente por Radix Sort
- **Sistemas de votación**: Conteo de votos con opciones limitadas

## Recursos Adicionales

### Referencias Teóricas
- Cormen, T. H., et al. "Introduction to Algorithms" (Capítulo sobre Sorting)
- Knuth, D. E. "The Art of Computer Programming, Volume 3: Sorting and Searching"

### Complejidad Computacional
- Mejor caso: O(n + k)
- Caso promedio: O(n + k)
- Peor caso: O(n + k)

Donde:
- n = número de elementos en el array
- k = rango de valores (max - min + 1)

## Notas de Implementación

### Manejo de Números Negativos
Ambas implementaciones manejan correctamente números negativos utilizando el valor mínimo como offset para el array de conteo.

### Estabilidad
El algoritmo mantiene el orden relativo de elementos iguales al construir el array de salida desde el final hacia el inicio.

## Licencia

Proyecto educativo de uso libre para fines académicos y de aprendizaje.

## Autor

Proyecto desarrollado con fines educativos para demostrar la implementación y visualización del algoritmo Count Sort.
