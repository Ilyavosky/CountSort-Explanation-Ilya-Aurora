package model;

import interfaces.ISortable;

/**
 * Implementación del algoritmo Count Sort para enteros
 * El Count Sort es eficiente para ordenar números en un rango conocido
 */
public class CountSortModel implements ISortable<Integer> {

    private int comparisons;
    private int swaps;

    public CountSortModel() {
        this.comparisons = 0;
        this.swaps = 0;
    }

    @Override
    public Integer[] sort(Integer[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        resetCounters();
        return countSort(array);
    }

    /**
     * Implementación del Count Sort
     * @param array Arreglo a ordenar
     * @return Arreglo ordenado
     */
    private Integer[] countSort(Integer[] array) {
        // Encontrar el valor mínimo y máximo
        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            comparisons++;
            if (array[i] < min) {
                min = array[i];
            }
            comparisons++;
            if (array[i] > max) {
                max = array[i];
            }
        }

        // Calcular el rango
        int range = max - min + 1;

        // Crear arreglo de conteo
        int[] count = new int[range];
        Integer[] output = new Integer[array.length];

        // Contar ocurrencias de cada elemento
        for (int i = 0; i < array.length; i++) {
            count[array[i] - min]++;
        }

        // Modificar el arreglo de conteo para contener posiciones reales
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Construir el arreglo de salida
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
            swaps++;
        }

        return output;
    }

    @Override
    public String getAlgorithmName() {
        return "Count Sort";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n + k) donde n es el número de elementos y k es el rango";
    }

    /**
     * Obtiene el número de comparaciones realizadas
     * @return Número de comparaciones
     */
    public int getComparisons() {
        return comparisons;
    }

    /**
     * Obtiene el número de intercambios realizados
     * @return Número de intercambios
     */
    public int getSwaps() {
        return swaps;
    }

    /**
     * Reinicia los contadores de estadísticas
     */
    private void resetCounters() {
        this.comparisons = 0;
        this.swaps = 0;
    }
}