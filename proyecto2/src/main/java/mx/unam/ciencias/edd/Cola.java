package mx.unam.ciencias.edd;

/**
 * Clase para colas genéricas.
 */
public class Cola<T> extends MeteSaca<T> {

    /**
     * Regresa una representación en cadena de la cola.
     * @return una representación en cadena de la cola.
     */
    @Override public String toString() {
        // Aquí va su código.
	Nodo cabecita = cabeza;
	String listv = "";
	if(cabecita == null){
	    return listv;
	}
	while(cabecita != null){
	    listv = listv  +  cabecita.elemento.toString() + ",";
	    cabecita = cabecita.siguiente;
	}
	return listv;
	
    }

    /**
     * Agrega un elemento al final de la cola.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void mete(T elemento) {
        // Aquí va su código.
	if(elemento == null){
	    throw new IllegalArgumentException("No ingresaste nada");
	}
	Nodo elem  = new Nodo(elemento);
	if(esVacia()){
	    cabeza = rabo = elem;
	}
	else{
	    rabo.siguiente = elem;
	    rabo = elem;
	    
	}
    }
}
