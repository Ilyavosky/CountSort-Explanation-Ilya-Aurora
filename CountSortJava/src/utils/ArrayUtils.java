package utils;

import java.util.Random;

/**
 * Utilidades para manejo de arreglos
 */
public class ArrayUtils {

    private static final Random random = new Random();

    /**
     * Genera un arreglo de enteros aleatorios
     * @param size Tamaño del arreglo
     * @param min Valor mínimo (inclusivo)
     * @param max Valor máximo (inclusivo)
     * @return Arreglo de enteros aleatorios
     */
    public static Integer[] generateRandomArray(int size, int min, int max) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }

    /**
     * Imprime un arreglo en formato legible
     * @param <T> Tipo de dato del arreglo
     * @param array Arreglo a imprimir
     * @param name Nombre descriptivo del arreglo
     */
    public static <T> void printArray(T[] array, String name) {
        System.out.print(name + ": [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Verifica si un arreglo está ordenado
     * @param <T> Tipo de dato que implementa Comparable
     * @param array Arreglo a verificar
     * @return true si está ordenado, false en caso contrario
     */
    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        if (array == null || array.length <= 1) {
            return true;
        }

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Copia un arreglo
     * @param <T> Tipo de dato del arreglo
     * @param original Arreglo original
     * @return Copia del arreglo
     */
    public static <T> T[] copyArray(T[] original) {
        if (original == null) {
            return null;
        }

        @SuppressWarnings("unchecked")
        T[] copy = (T[]) new Object[original.length];
        System.arraycopy(original, 0, copy, 0, original.length);
        return copy;
    }

    /**
     * Formatea el tiempo de ejecución en milisegundos
     * @param nanos Tiempo en nanosegundos
     * @return String formateado con el tiempo
     */
    public static String formatExecutionTime(long nanos) {
        double millis = nanos / 1_000_000.0;
        if (millis < 1) {
            return String.format("%.3f µs", nanos / 1_000.0);
        } else if (millis < 1000) {
            return String.format("%.3f ms", millis);
        } else {
            return String.format("%.3f s", millis / 1000.0);
        }
    }
}