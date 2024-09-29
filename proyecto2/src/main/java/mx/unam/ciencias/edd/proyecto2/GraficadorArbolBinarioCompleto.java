package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.VerticeArbolBinario;

/**
 * Clase para graficar un árbol binario completo en formato SVG.
 */
public class GraficadorArbolBinarioCompleto {
    /** El árbol binario completo a graficar. */
    ArbolBinarioCompleto<Integer> arbolBinarioCompleto;

    /** Inicio del documento SVG. */
    String init = "<?xml version='1.0' encoding='UTF-8' ?>\n";

    /** Dimensiones del documento SVG. */
    String dimensiones = "<svg width='%s' height='%s'>\n";

    // Inicio del grupo de elementos
    String g1 = "  <g>\n";

    // Cierre de grupo de elementos    
    String g2 = "  </g>\n";

    // Cierre del documento SVG
    String cierra = "</svg>";

    /** Formato para dibujar una línea en SVG. */
    String line = "    <line  x1='%s' y1='%s' x2='%s' y2='%s' stroke='black' stroke-width='2'  />\n";

    /** Formato para dibujar un círculo(el vértice) en SVG. */
    String circ = "    <circle cx='%s' cy='%s' r='20' stroke='black' stroke-width='1.5' fill='white'  />\n";

    /** Formato para dibujar el texto a contener en el vértice en SVG. */
    String text = "    <text fill='black' font-family='sans-serif' font-size='20' x='%s' y='%s' text-anchor='middle'>%s</text>\n";

    /**
     * Constructor. Inicializa el árbol binario completo a graficar.
     * @param arbolBinarioCompleto el árbol binario completo a graficar.
     */
    public GraficadorArbolBinarioCompleto(ArbolBinarioCompleto<Integer> arbolBinarioCompleto) {
        this.arbolBinarioCompleto = arbolBinarioCompleto;
    }

    /**
     * Genera el código SVG que representa el árbol binario completo.
     * @return una cadena con el código SVG.
     */
    public String dibuja(){
        int w = arbolBinarioCompleto.getElementos() * 100;
        int h = (arbolBinarioCompleto.altura() * 40) + ((arbolBinarioCompleto.altura() - 1)* 120);
        int x1, x2;
        int y1, y2;
        double cx = w/2, cy = 40;
        int num;
        int xn, yn;

        String svg = init;
        svg += String.format(dimensiones, w, h);
        svg += this.g1;

        String s = dibuja(arbolBinarioCompleto.raiz(), 0, 0, w/2);

        return svg + s + g2 + cierra;
    }

    /**
     * Genera el código SVG que representa un subárbol a partir de un vértice.
     * @param vertice el vértice que raíz del subárbol.
     * @param a la coordenada x del vértice padre.
     * @param y la coordenada y del vértice padre.
     * @param w la coordenada x del vértice actual.
     * @return una cadena con el código SVG del subárbol.
     */
    private String dibuja(VerticeArbolBinario vertice, int a, int y, int w) {
    String s = "";
    if (vertice.hayIzquierdo()) {
        int w_izquierdo = w - (w - a) / 2;
        s += dibuja(vertice.izquierdo(), w, y + 40, w_izquierdo);
        int x_izquierdo = w_izquierdo, y_izquierdo = y + 40;
        s += String.format(line, w, y + 20, x_izquierdo, y_izquierdo);
    }
    if (vertice.hayDerecho()) {
        int w_derecho = w + (w - a) / 2;
        s += dibuja(vertice.derecho(), w, y + 40, w_derecho);
        int x_derecho = w_derecho, y_derecho = y + 40;
        s += String.format(line, w, y + 20, x_derecho, y_derecho);
    }
    int xn = w, yn = y + 20;
    s += String.format(circ, w, y + 20);
    s += String.format(text, xn, yn + 8, vertice.get());
    return s;
}

}
