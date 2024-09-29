package mx.unam.ciencias.edd;

import java.util.Iterator;

/**
 * <p>Clase para árboles binarios ordenados. Los árboles son genéricos, pero
 * acotados a la interfaz {@link Comparable}.</p>
 *
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 *       descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos sus
 *       descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>>
    extends ArbolBinario<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los vértices en DFS in-order. */
        private Pila<Vertice> pila;

        /* Inicializa al iterador. */
        private Iterador() {
            // Aquí va su código.
	    pila = new Pila<Vertice>();
	    Vertice apuntador = raiz;
	    while(apuntador != null){
		pila.mete(apuntador);		
		apuntador = apuntador.izquierdo;
	    }
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            // Aquí va su código.
	    return !pila.esVacia();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override public T next() {
            // Aquí va su código.
	    Vertice vert = pila.saca();
	    Vertice verd;
	    if(vert.derecho != null) {
		verd = vert.derecho;
		while(verd != null){
		    pila.mete(verd);
		    verd = verd.izquierdo;
		}
	    }
	    return vert.elemento;
	    
        }
    }

    /**
     * El vértice del último elemento agegado. Este vértice sólo se puede
     * garantizar que existe <em>inmediatamente</em> después de haber agregado
     * un elemento al árbol. Si cualquier operación distinta a agregar sobre el
     * árbol se ejecuta después de haber agregado un elemento, el estado de esta
     * variable es indefinido.
     */
    protected Vertice ultimoAgregado;

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioOrdenado() { super(); }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario ordenado.
     */
    public ArbolBinarioOrdenado(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        // Aquí va su código.
	if(elemento == null)
	    throw new IllegalArgumentException("El elemento es null");
	
	Vertice vert = nuevoVertice(elemento);
	elementos++;
	
	if(raiz == null)
	    raiz = vert;
	else
	    agrega(raiz, vert);

	ultimoAgregado = vert;
    }

    private void agrega(Vertice actual, Vertice vertice){
	if(vertice.elemento.compareTo(actual.elemento) <= 0){
	    if(actual.izquierdo == null){
		actual.izquierdo = vertice;
		vertice.padre = actual;
	    }
	    else
		agrega(actual.izquierdo, vertice);
	}
	else{
	    if(actual.derecho == null){
		actual.derecho = vertice;
		vertice.padre = actual;
	    }
	    	else
	    agrega(actual.derecho, vertice);	
	}	
    }
    

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        // Aquí va su código.
	Vertice vertice = vertice(busca(elemento));
	if(vertice == null)
	    return;
	elementos--;
	
	if(vertice.izquierdo == null || vertice.derecho == null)
	    eliminaVertice(vertice);
	else
	    eliminaVertice(intercambiaEliminable(vertice));
    }

    /**
     * Intercambia el elemento de un vértice con dos hijos distintos de
     * <code>null</code> con el elemento de un descendiente que tenga a lo más
     * un hijo.
     * @param vertice un vértice con dos hijos distintos de <code>null</code>.
     * @return el vértice descendiente con el que vértice recibido se
     *         intercambió. El vértice regresado tiene a lo más un hijo distinto
     *         de <code>null</code>.
     */
    protected Vertice intercambiaEliminable(Vertice vertice) {
        // Aquí va su código.
	Vertice v = maxEnSubarbol(vertice.izquierdo);
	vertice.elemento = v.elemento;
	return v;
    }

    private Vertice maxEnSubarbol(Vertice vertice){
	if(vertice(vertice).derecho == null)
	    return vertice;
	return maxEnSubarbol(vertice(vertice).derecho);
    }

    /**
     * Elimina un vértice que a lo más tiene un hijo distinto de
     * <code>null</code> subiendo ese hijo (si existe).
     * @param vertice el vértice a eliminar; debe tener a lo más un hijo
     *                distinto de <code>null</code>.
     */
    protected void eliminaVertice(Vertice vertice) {
        // Aquí va su código.
	Vertice p = vertice.padre;
	Vertice u = null;
	
	if(vertice.izquierdo != null)
	    u = vertice.izquierdo;
	if(vertice.derecho != null)
	    u = vertice.derecho;
	
	if(p == null)
	    raiz = u;
	else{
	    if(p.izquierdo == vertice)
		p.izquierdo = u;
	    else
		p.derecho = u;
	}
	if(u != null)
	    u.padre = p;
    }

    /**
     * Busca un elemento en el árbol recorriéndolo in-order. Si lo encuentra,
     * regresa el vértice que lo contiene; si no, regresa <code>null</code>.
     * @param elemento el elemento a buscar.
     * @return un vértice que contiene al elemento buscado si lo
     *         encuentra; <code>null</code> en otro caso.
     */
    @Override public VerticeArbolBinario<T> busca(T elemento) {
        // Aquí va su código.
	if(elemento == null)
	    return null;
	return busca(elemento, raiz);
    }

    private VerticeArbolBinario<T> busca(T elemento, VerticeArbolBinario<T> vertice) {
	if (vertice == null)
	    return null;
	if(elemento.compareTo(vertice(vertice).elemento) == 0)
	    return vertice;
	if(elemento.compareTo(vertice(vertice).elemento) < 0)
	    return busca(elemento, vertice(vertice).izquierdo);
	else
	    return busca(elemento, vertice(vertice).derecho);
	
    }

    /**
     * Regresa el vértice que contiene el último elemento agregado al
     * árbol. Este método sólo se puede garantizar que funcione
     * <em>inmediatamente</em> después de haber invocado al método {@link
     * agrega}. Si cualquier operación distinta a agregar sobre el árbol se
     * ejecuta después de haber agregado un elemento, el comportamiento de este
     * método es indefinido.
     * @return el vértice que contiene el último elemento agregado al árbol, si
     *         el método es invocado inmediatamente después de agregar un
     *         elemento al árbol.
     */
    public VerticeArbolBinario<T> getUltimoVerticeAgregado() {
        return ultimoAgregado;
    }

    /**
     * Gira el árbol a la derecha sobre el vértice recibido. Si el vértice no
     * tiene hijo izquierdo, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraDerecha(VerticeArbolBinario<T> vertice) {
        // Aquí va su código.
	Vertice q = vertice(vertice);
	Vertice p = q.izquierdo;
	
	if(q == null || p == null)
	    return;

	if(q.padre == null){
	    raiz = p;
	    p.padre = null;
	} else{
	    p.padre = q.padre;
	    if(q.padre.izquierdo == q)
		q.padre.izquierdo = p;
	    else
		q.padre.derecho = p;
	}

	q.izquierdo = p.derecho;
	if(q.izquierdo != null)
	    q.izquierdo.padre = q;
	
	q.padre = p;
	p.derecho = q;
    }

    /**
     * Gira el árbol a la izquierda sobre el vértice recibido. Si el vértice no
     * tiene hijo derecho, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        // Aquí va su código.
	Vertice p = vertice(vertice);
	Vertice q = p.derecho;
	
	if(q == null || p == null)
	    return;

	if(p.padre == null){
	    raiz = q;
	    q.padre = null;
	} else{
	    q.padre = p.padre;
	    if(p.padre.derecho == p)
		p.padre.derecho = q;
	    else
		p.padre.izquierdo = q;
	}

	p.derecho = q.izquierdo;
	if(p.derecho != null)
	    p.derecho.padre = p;
	
	p.padre = q;
	q.izquierdo = p;
    }

    /**
     * Realiza un recorrido DFS <em>pre-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPreOrder(AccionVerticeArbolBinario<T> accion) {        // Aquí va su código.
	dfsPreOrder(raiz, accion);
	
	
    }

    private void dfsPreOrder(VerticeArbolBinario<T> vertice, AccionVerticeArbolBinario<T> accion){
	
	if(vertice(vertice) == null)
	    return;
	accion.actua(vertice);
	dfsPreOrder(vertice(vertice).izquierdo, accion);
	dfsPreOrder(vertice(vertice).derecho, accion);	
    }

    /**
     * Realiza un recorrido DFS <em>in-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsInOrder(AccionVerticeArbolBinario<T> accion) {
        // Aquí va su código.
	dfsInOrder(raiz, accion);
    }

    private void dfsInOrder(VerticeArbolBinario<T> vertice, AccionVerticeArbolBinario<T> accion){
	if(vertice(vertice) == null)
	    return;
	dfsInOrder(vertice(vertice).izquierdo, accion);
	accion.actua(vertice);
	dfsInOrder(vertice(vertice).derecho, accion);
    }
    

    /**
     * Realiza un recorrido DFS <em>post-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPostOrder(AccionVerticeArbolBinario<T> accion) {
        // Aquí va su código.
	dfsPostOrder(raiz,accion);
    }

    private void dfsPostOrder(VerticeArbolBinario<T> vertice, AccionVerticeArbolBinario<T> accion){
	if(vertice == null)
	    return;
	dfsPostOrder(vertice(vertice).izquierdo,accion);
	dfsPostOrder(vertice(vertice).derecho,accion);
	accion.actua(vertice);
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
}
