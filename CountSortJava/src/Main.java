import model.CountSortModel;
import utils.ArrayUtils;

/**
 * Clase principal para demostrar el algoritmo Count Sort
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DE COUNT SORT ===\n");

        // Crear instancia del modelo
        CountSortModel countSort = new CountSortModel();

        // Ejemplo 1: Arreglo pequeño
        System.out.println("--- Ejemplo 1: Arreglo pequeño ---");
        Integer[] array1 = {4, 2, 2, 8, 3, 3, 1};
        demonstrateSort(countSort, array1);

        // Ejemplo 2: Arreglo con números negativos
        System.out.println("\n--- Ejemplo 2: Con números negativos ---");
        Integer[] array2 = {-5, 3, -2, 7, 0, -8, 3, -2};
        demonstrateSort(countSort, array2);

        // Ejemplo 3: Arreglo aleatorio grande
        System.out.println("\n--- Ejemplo 3: Arreglo aleatorio grande ---");
        Integer[] array3 = ArrayUtils.generateRandomArray(20, 1, 50);
        demonstrateSort(countSort, array3);

        // Ejemplo 4: Prueba de rendimiento
        System.out.println("\n--- Ejemplo 4: Prueba de rendimiento ---");
        performanceTest(countSort);
    }

    /**
     * Demuestra el ordenamiento de un arreglo
     * @param countSort Instancia del algoritmo Count Sort
     * @param array Arreglo a ordenar
     */
    private static void demonstrateSort(CountSortModel countSort, Integer[] array) {
        ArrayUtils.printArray(array, "Arreglo original");

        long startTime = System.nanoTime();
        Integer[] sorted = countSort.sort(array);
        long endTime = System.nanoTime();

        ArrayUtils.printArray(sorted, "Arreglo ordenado");
        System.out.println("Tiempo de ejecución: " +
                ArrayUtils.formatExecutionTime(endTime - startTime));
        System.out.println("Comparaciones: " + countSort.getComparisons());
        System.out.println("Intercambios: " + countSort.getSwaps());
        System.out.println("¿Está ordenado?: " +
                (ArrayUtils.isSorted(sorted) ? "✓ Sí" : "✗ No"));
    }

    /**
     * Realiza pruebas de rendimiento con diferentes tamaños
     * @param countSort Instancia del algoritmo Count Sort
     */
    private static void performanceTest(CountSortModel countSort) {
        int[] sizes = {100, 1000, 10000, 100000};

        System.out.println("\n" + countSort.getAlgorithmName());
        System.out.println("Complejidad: " + countSort.getTimeComplexity());
        System.out.println("\nTiempos de ejecución:");
        System.out.println("─────────────────────────────────────");

        for (int size : sizes) {
            Integer[] testArray = ArrayUtils.generateRandomArray(size, 0, 1000);

            long startTime = System.nanoTime();
            Integer[] sorted = countSort.sort(testArray);
            long endTime = System.nanoTime();

            System.out.printf("n = %,7d  |  Tiempo: %s%n",
                    size, ArrayUtils.formatExecutionTime(endTime - startTime));

            // Verificar que está ordenado
            if (!ArrayUtils.isSorted(sorted)) {
                System.out.println("  ⚠ ERROR: El arreglo no está ordenado correctamente");
            }
        }
        System.out.println("─────────────────────────────────────");
    }
}