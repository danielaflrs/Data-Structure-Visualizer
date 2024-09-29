package mx.unam.ciencias.edd;

/**
 * Clase para árboles rojinegros. Un árbol rojinegro cumple las siguientes
 * propiedades:
 *
 * <ol>
 *  <li>Todos los vértices son NEGROS o ROJOS.</li>
 *  <li>La raíz es NEGRA.</li>
 *  <li>Todas las hojas (<code>null</code>) son NEGRAS (al igual que la raíz).</li>
 *  <li>Un vértice ROJO siempre tiene dos hijos NEGROS.</li>
 *  <li>Todo camino de un vértice a alguna de sus hojas descendientes tiene el
 *      mismo número de vértices NEGROS.</li>
 * </ol>
 *
 * Los árboles rojinegros se autobalancean.
 */
public class ArbolRojinegro<T extends Comparable<T>>
    extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class VerticeRojinegro extends Vertice {

        /** El color del vértice. */
        public Color color;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public VerticeRojinegro(T elemento) {
            // Aquí va su código.
	    super(elemento);
	    color = Color.NINGUNO;
        }

        /**
         * Regresa una representación en cadena del vértice rojinegro.
         * @return una representación en cadena del vértice rojinegro.
         */
        @Override public String toString() {
            // Aquí va su código.
	    if(color == Color.ROJO)
		return "R" + "{" + elemento.toString() + "}";
	    return "N" + "{" + elemento.toString() + "}";
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param objeto el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeRojinegro}, su elemento es igual al elemento de
         *         éste vértice, los descendientes de ambos son recursivamente
         *         iguales, y los colores son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked")
                VerticeRojinegro vertice = (VerticeRojinegro)objeto;
            // Aquí va su código.
	    return (color == vertice.color && super.equals(objeto));
        }
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinarioOrdenado}.
     */
    public ArbolRojinegro() { super(); }

    /**
     * Construye un árbol rojinegro a partir de una colección. El árbol
     * rojinegro tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        rojinegro.
     */
    public ArbolRojinegro(Coleccion<T> coleccion) {
        // Aquí va su código.
	super(coleccion);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link
     * VerticeRojinegro}.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice rojinegro con el elemento recibido dentro del mismo.
     */
    @Override protected Vertice nuevoVertice(T elemento) {
        // Aquí va su código.
	return new VerticeRojinegro(elemento);
    }

    /**
     * Regresa el color del vértice rojinegro.
     * @param vertice el vértice del que queremos el color.
     * @return el color del vértice rojinegro.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *         VerticeRojinegro}.
     */
    public Color getColor(VerticeArbolBinario<T> vertice) {
        // Aquí va su código.
	return (verticeRojinegro(vertice)).color;
    }
    
    private VerticeRojinegro verticeRojinegro(VerticeArbolBinario<T> vertice){
	return (VerticeRojinegro)vertice;
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol recoloreando
     * vértices y girando el árbol como sea necesario.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        // Aquí va su código.
	super.agrega(elemento);
	VerticeRojinegro v = verticeRojinegro(ultimoAgregado);
	v.color = Color.ROJO;
	rebalanceoAgrega(v);
    }

    private void rebalanceoAgrega(VerticeRojinegro vertice) {
	if(vertice.padre == null){
	    vertice.color = Color.NEGRO;
	    return;
	}
	VerticeRojinegro p = verticeRojinegro(vertice.padre);
	
	if(esNegro(p))
	    return;
	
	VerticeRojinegro a = verticeRojinegro(p.padre);	
	VerticeRojinegro tio = verticeRojinegro(esIzquierdo(p)? a.derecho: a.izquierdo);
	
	if(tio != null && esRojo(tio)){
	    tio.color = Color.NEGRO;
	    p.color = Color.NEGRO;
	    a.color = Color.ROJO;
	    rebalanceoAgrega(a);
	    return;
	}

	if(esIzquierdo(p) && !esIzquierdo(vertice)){
	    super.giraIzquierda(p);
	    VerticeRojinegro vertaux = vertice;
	    vertice = p;
	    p = vertaux;
	}
	else if(!esIzquierdo(p) && esIzquierdo(vertice)){
	    super.giraDerecha(p);
	    VerticeRojinegro vertaux = vertice;
	    vertice = p;
	    p = vertaux;
	}
	
	p.color = Color.NEGRO;
	a.color = Color.ROJO;

	if(esIzquierdo(vertice))
	    super.giraDerecha(a);
	else 
	    super.giraIzquierda(a);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y recolorea y gira el árbol como sea necesario para
     * rebalancearlo.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
        // Aquí va su código.

	VerticeRojinegro vertice = verticeRojinegro(busca(elemento));
	if(vertice == null)
	    return;

	if(vertice.izquierdo != null && vertice.derecho != null){
	    vertice = verticeRojinegro(intercambiaEliminable(vertice));
	}

	VerticeRojinegro fantasma = null;
	
	if(vertice.izquierdo == null && vertice.derecho == null){
	    fantasma = verticeRojinegro(nuevoVertice(null));
	    fantasma.color = Color.NEGRO;
	    fantasma.padre = vertice;
	    vertice.izquierdo = fantasma;
	}
  
	if(vertice.izquierdo != null)
	    fantasma = verticeRojinegro(vertice.izquierdo);
	else
	    fantasma = verticeRojinegro(vertice.derecho);

	eliminaVertice(vertice);

	if(esRojo(fantasma)){
	    fantasma.color = Color.NEGRO;
	}
	else if(esNegro(fantasma) && esNegro(vertice))
	    rebalanceoElimina(fantasma);
 
	if(fantasma.elemento == null)
	    eliminaVertice(fantasma);
	elementos--;
    }

    private void rebalanceoElimina(VerticeRojinegro vertice){
	
	VerticeRojinegro p = verticeRojinegro(vertice.padre);
	
	
	/* Caso 1: P es null*/
	if(p == null)
	    return;

	VerticeRojinegro h = verticeRojinegro(esIzquierdo(vertice)? p.derecho : p.izquierdo);

	/* Caso 2: H es rojo */
	if(esRojo(h)){
	    p.color = Color.ROJO;
	    h.color = Color.NEGRO;

	    if(p.izquierdo == vertice) 
		super.giraIzquierda(p);
	    else
		super.giraDerecha(p);

	    p = verticeRojinegro(vertice.padre);
	    
	    if(esIzquierdo(vertice))
		h = verticeRojinegro(p.derecho);
	    else
		h = verticeRojinegro(p.izquierdo);
	    
	}
	/* hay que actualizar h para que vuelva a ser el hermano de v después de girar */

	
	/* Caso 3: Todos son negros */
	VerticeRojinegro hi = verticeRojinegro(h.izquierdo);
	VerticeRojinegro hd = verticeRojinegro(h.derecho);

	if(esNegro(hi) && esNegro(hd) && esNegro(h) && esNegro(p)){
	    h.color = Color.ROJO;
	    rebalanceoElimina(p);
	    return;
	}

	/* Caso 4: Todos son negros excepto p*/
	if(esNegro(hi) && esNegro(hd) && esNegro(h) && esRojo(p)){
	    h.color = Color.ROJO;
	    p.color = Color.NEGRO;
	    return;
	}

	/* Caso 5: Teniendo sobrinos bicolores, el sobrino NO cruzado es rojo */
	if(esIzquierdo(vertice) && esRojo(hi) && esNegro(hd)){
	    h.color = Color.ROJO;
	    hi.color = Color.NEGRO;
	    super.giraDerecha(h);
	}
	else if(!esIzquierdo(vertice) && esRojo(hd) && esNegro(hi)){
	    h.color = Color.ROJO;
	    hd.color = Color.NEGRO;
	    super.giraIzquierda(h);
	}
	h = verticeRojinegro(esIzquierdo(vertice) ? p.derecho : p.izquierdo);
	hi = verticeRojinegro(h.izquierdo);
	hd = verticeRojinegro(h.derecho);
	/* Caso 6: Teniendo sobrinos bicolores, el sobrino cruzado es rojo*/
	
        h.color = p.color;
	p.color = Color.NEGRO;
   
	if(esIzquierdo(vertice)){
	    hd.color = Color.NEGRO;
	    super.giraIzquierda(p);
	}
	else{
	    hi.color = Color.NEGRO;
	    super.giraDerecha(p);
	}
	return;
	
    }
    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la izquierda por los usuarios de la
     * clase, porque se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                                                "pueden girar a la izquierda " +
                                                "por el usuario.");
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la derecha por los usuarios de la
     * clase, porque se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraDerecha(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                                                "pueden girar a la derecha " +
                                                "por el usuario.");
    }

    private boolean esIzquierdo(Vertice vertice){
	return vertice.padre.izquierdo == vertice;
    }
    private boolean esRojo(VerticeRojinegro vertice){
	return vertice != null && vertice.color == Color.ROJO;
    }
    private boolean esNegro(VerticeRojinegro vertice){
	return vertice == null || vertice.color == Color.NEGRO;
    }
}
