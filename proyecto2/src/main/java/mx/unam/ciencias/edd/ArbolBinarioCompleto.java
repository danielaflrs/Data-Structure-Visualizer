package mx.unam.ciencias.edd;

import java.util.Iterator;

/**
 * <p>Clase para árboles binarios completos.</p>
 *
 * <p>Un árbol binario completo agrega y elimina elementos de tal forma que el
 * árbol siempre es lo más cercano posible a estar lleno.</p>
 */
public class ArbolBinarioCompleto<T> extends ArbolBinario<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Cola para recorrer los vértices en BFS. */
        private Cola<Vertice> cola;

        /* Inicializa al iterador. */
        private Iterador() {
            // Aquí va su código.
	    cola = new Cola<Vertice>();
	    if(raiz != null)
		cola.mete(raiz);
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            // Aquí va su código.
	    return !cola.esVacia(); 
        }

        /* Regresa el siguiente elemento en orden BFS. */
        @Override public T next() {
            // Aquí va su código.
	    Vertice vert = cola.saca();
	    
	    if(vert.izquierdo != null)
		cola.mete(vert.izquierdo);
	    
	    if(vert.derecho != null)
		cola.mete(vert.derecho);
	    
	    return vert.elemento;
        }
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioCompleto() { super(); }

    /**
     * Construye un árbol binario completo a partir de una colección. El árbol
     * binario completo tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario completo.
     */
    public ArbolBinarioCompleto(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un elemento al árbol binario completo. El nuevo elemento se coloca
     * a la derecha del último nivel, o a la izquierda de un nuevo nivel.
     * @param elemento el elemento a agregar al árbol.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
        // Aquí va su código.
	if(elemento == null)
	    throw new IllegalArgumentException("El elemento es null");
	
	Vertice nuevert = nuevoVertice(elemento);
	elementos++;

	if(raiz == null){
	    raiz = nuevert;
	    return;
	}

	Cola<Vertice> colita = new Cola<>();
	colita.mete(raiz);

	while(!colita.esVacia()){
	    Vertice vert = colita.saca();
	    if(vert.izquierdo != null){
		colita.mete(vert.izquierdo);
	    }
	    else{
		vert.izquierdo = nuevert;
		nuevert.padre = vert;
		return;
	    }
	    if(vert.derecho != null){
		colita.mete(vert.derecho);
	    }
	    else{
		vert.derecho = nuevert;
		nuevert.padre = vert;
		return;
	    }
	}
    }

    /**
     * Elimina un elemento del árbol. El elemento a eliminar cambia lugares con
     * el último elemento del árbol al recorrerlo por BFS, y entonces es
     * eliminado.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        // Aquí va su código.
	Vertice vertelem = vertice(busca(elemento));
	if(vertelem == null)
	    return;

	elementos--;
	if(elementos == 0){
	    raiz = null;
	    return;
	}
	
	Cola<Vertice> colita = new Cola<>();
	colita.mete(raiz);

	Vertice vertcolita = buscaVertice(colita);
	intercambiaVertice(vertelem, vertcolita);
	if(vertcolita.padre.izquierdo.equals(vertcolita)){
	    vertcolita.padre.izquierdo = null;
	    return;
	}
	vertcolita.padre.derecho = null;
	return;
	
    }

    private Vertice buscaVertice(Cola<Vertice> colita) {
	if(colita.esVacia())
	    return null;

	Vertice vert = colita.saca();
	if(vert.izquierdo == null && vert.derecho == null){
	    if(colita.esVacia())
		return vert;
	    return buscaVertice(colita);
	}
	if(vert.izquierdo != null)
	    colita.mete(vert.izquierdo);

	if(vert.derecho != null)
	    colita.mete(vert.derecho);
	return buscaVertice(colita);
	
    }

    

    private void intercambiaVertice(Vertice v1, Vertice v2){
	Vertice veraux = nuevoVertice(v1.elemento);
	v1.elemento = v2.elemento;
	v2.elemento = veraux.elemento;
    }

    /**
     * Regresa la altura del árbol. La altura de un árbol binario completo
     * siempre es ⌊log<sub>2</sub><em>n</em>⌋.
     * @return la altura del árbol.
     */
    @Override public int altura() {
        // Aquí va su código.
        if(elementos == 0)
	    return -1;
	return (int)Math.floor(Math.log(elementos) / Math.log(2));
    }

    /**
     * Realiza un recorrido BFS en el árbol, ejecutando la acción recibida en
     * cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void bfs(AccionVerticeArbolBinario<T> accion) {
        // Aquí va su código.
	if(raiz == null)
	    return;

       	Cola<Vertice> cola = new Cola<Vertice>();
	cola.mete(raiz);

	while(!cola.esVacia()){
	    Vertice vert = cola.saca();
	    accion.actua(vert);
	    
	    if(vert.izquierdo != null)
		cola.mete(vert.izquierdo);

	    if(vert.derecho != null)
		cola.mete(vert.derecho);
	}

	
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden BFS.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
}
