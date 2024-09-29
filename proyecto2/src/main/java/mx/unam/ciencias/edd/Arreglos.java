package mx.unam.ciencias.edd;

import java.util.Comparator;

/**
 * Clase para ordenar y buscar arreglos genéricos.
 */
public class Arreglos {

    /* Constructor privado para evitar instanciación. */
    private Arreglos() {}

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
	quickSort(arreglo,comparador, 0, arreglo.length-1);
    }

    private static <T> void swap(T[] arreglo, int i, int j) {
	T aux = arreglo[i];
	arreglo[i] = arreglo[j];
	arreglo[j] = aux;
    }
    
    
    private static <T> void
	quickSort(T[] arreglo, Comparator<T> comparador, int a, int b) {
	if (a >= b)
	    return;
	int i = a + 1;
	int j = b;
	while(i < j) {
	    if((comparador.compare(arreglo[i],arreglo[a]) > 0) && (comparador.compare(arreglo[j], arreglo[a]) <= 0)){
		swap(arreglo, i, j);
		i++;
		j--;
	    } else if (comparador.compare(arreglo[i],arreglo[a]) <= 0) {
		i++;
	    } else {
		j--;
	    }
	}
	if (comparador.compare(arreglo[i],arreglo[a]) > 0){
	    i--;
	}
	swap(arreglo,a,i);
	quickSort(arreglo, comparador, a, i-1);
	quickSort(arreglo, comparador, i+1, b);
    }
    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
    selectionSort(T[] arreglo, Comparator<T> comparador) {
	for(int i=0; i < arreglo.length; i++){
	    for(int j = i +1; j < arreglo.length; j++){
		if(comparador.compare(arreglo[j],arreglo[i]) < 0){
		    swap(arreglo,i,j);
		}
	    }
	}
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
        // Aquí va su código.
	int a = 0;
	int b = arreglo.length-1;
	int e;
	
	while(a <= b){
	    e = (a + b)/2;
	    if(comparador.compare(arreglo[e],elemento) == 0){
		return e;
	    }
	    else if(comparador.compare(arreglo[e],elemento) < 0){
		a = e+1;		    
	    }
	    else{
		b = e-1;
	    }
	}
	return -1;
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }
}
