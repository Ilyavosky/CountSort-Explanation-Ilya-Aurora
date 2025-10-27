package interfaces;

/**
 * Interfaz genérica para algoritmos de ordenamiento
 * @param <T> Tipo de dato que implementa Comparable
 */
public interface ISortable<T extends Comparable<T>> {

    /**
     * Ordena un arreglo de elementos
     * @param array Arreglo a ordenar
     * @return Arreglo ordenado
     */
    T[] sort(T[] array);

    /**
     * Obtiene el nombre del algoritmo de ordenamiento
     * @return Nombre del algoritmo
     */
    String getAlgorithmName();

    /**
     * Obtiene la complejidad temporal del algoritmo
     * @return Descripción de la complejidad
     */
    String getTimeComplexity();
}