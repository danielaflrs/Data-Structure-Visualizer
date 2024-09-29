package mx.unam.ciencias.edd;

/**
 * Clase para pilas genéricas.
 */
public class Pila<T> extends MeteSaca<T> {

    /**
     * Regresa una representación en cadena de la pila.
     * @return una representación en cadena de la pila.
     */
    @Override public String toString() {
        // Aquí va su código.
	String listv = "";
	Nodo cabecita = cabeza;
	if(cabecita == null){
	    return listv;
	}
      	while(cabecita != null){
	    listv = listv + cabecita.elemento.toString() + "\n";
	    cabecita = cabecita.siguiente;
	}
	return listv;
    }

    /**
     * Agrega un elemento al tope de la pila.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void mete(T elemento) {
        // Aquí va su código.
	if(elemento == null){
	    throw new IllegalArgumentException("No ingresaste nada");
	}
	Nodo nodito = new Nodo(elemento);
	if(cabeza == null){
	    cabeza = rabo = nodito;
	} else {
	    nodito.siguiente = cabeza;
	    cabeza = nodito;
	}
    }
}
