package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.VerticeArbolBinario;

/**
 * Clase para graficar un árbol binario ordenado en formato SVG.
 */
public class GraficadorArbolBinarioOrdenado {

    /** El árbol binario ordenado a graficar. */
    private ArbolBinarioOrdenado<Integer> arbolBinarioOrdenado;

    /** Inicio del documento SVG. */
    private final String init = "<?xml version='1.0' encoding='UTF-8' ?>\n";

    /** Dimensiones del documento SVG. */
    private final String dimensiones = "<svg width='%s' height='%s'>\n";

    /** Se abre un grupo en SVG. */
    private final String g1 = "  <g>\n";

    /** Cierre de un grupo en SVG. */
    private final String g2 = "  </g>\n";

    /** Cierre del documento SVG. */
    private final String cierra = "</svg>";

    /** Para dibujar una línea en SVG. */
    private final String line = "    <line  x1='%s' y1='%s' x2='%s' y2='%s' stroke='black' stroke-width='2'  />\n";

    /** Para dibujar un círculo (vértice) en SVG. */
    private final String circ = "    <circle cx='%s' cy='%s' r='20' stroke='black' stroke-width='1.5' fill='white'  />\n";

    /** Para dibujar texto en SVG. */
    private final String text = "    <text fill='black' font-family='sans-serif' font-size='20' x='%s' y='%s' text-anchor='middle'>%s</text>\n";

    /**
     * Constructor. Inicializa el árbol binario ordenado a graficar.
     * @param arbolBinarioOrdenado el árbol binario ordenado a graficar.
     */
    public GraficadorArbolBinarioOrdenado(ArbolBinarioOrdenado<Integer> arbolBinarioOrdenado) {
        this.arbolBinarioOrdenado = arbolBinarioOrdenado;
    }

    /**
     * Genera el código SVG que representa el árbol binario ordenado.
     * @return una cadena con el código SVG.
     */
    public String dibuja() {
	int w = arbolBinarioOrdenado.getElementos() * 80;
	int h = arbolBinarioOrdenado.altura() * 100;

	String svg = init;
	svg += String.format(dimensiones, w, h);
	svg += this.g1;

	String s = dibuja(arbolBinarioOrdenado.raiz(), 0, 0, w / 2);

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
            s += dibuja(vertice.izquierdo(), a, y + 40, w - (w - a) / 2);
            s += String.format(line, w, y + 20, w - (w - a) / 2, y + 40);
        }
        if (vertice.hayDerecho()) {
            s += dibuja(vertice.derecho(), w, y + 40, w + (w - a) / 2);
            s += String.format(line, w, y + 20, w + (w - a) / 2, y + 40);
        }
        int xn = w, yn = y + 20;
        s += String.format(circ, w, y + 20);
        s += String.format(text, xn, yn + 8, vertice.get());
        return s;
    }
}
