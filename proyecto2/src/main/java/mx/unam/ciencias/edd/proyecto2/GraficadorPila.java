package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Pila;

/**
 * Clase para graficar una pila de enteros en formato SVG.
 */
public class GraficadorPila {
    // La pila de enteros a graficar. 
    Pila<Integer> pila;

    // Inicio del documento SVG. 
    String init = "<?xml version='1.0' encoding='UTF-8' ?>\n";

    // Dimensiones del SVG, se rellena con el ancho y alto 
    String dimensiones = "<svg width='%s' height='%s'>\n";

    // Inicio del grupo de elementos
    String g1 = "  <g>\n";

    // Cierre de grupo de elementos
    String g2 = "  </g>\n";
    
    // Cierre del documento SVG.
    String cierra = "</svg>";

    // Formato para dibujar un rectángulo en SVG.
    String rect = "    <rect x='25' y='%s' width='90' height='50' stroke='black' stroke-width='2' fill='white' />\n";

    // Formato para dibujar texto en SVG. 
    String text = "    <text fill='black' font-family='sans-serif' font-size='32' x='70' y='%s' text-anchor='middle'>%s</text>\n";

    
    /**
     * Constructor. Inicializa la pila a graficar.
     * @param pila la pila de enteros a graficar.
     */
    public GraficadorPila(Pila<Integer> pila) {
	this.pila = pila;
    }

    /**
     * Genera el código SVG que representa la pila.
     * @return una cadena con el código SVG.
     */
    public String dibuja(){
	int yr = 30;
	int num, yn = 18;
	String svg = init;
	int total = 1;
	String svgaux = "";

	// Mientras la pila no esté vacía, dibuja los rectángulos y textos(elementos de la pila).
	while(!pila.esVacia()) {
	    yn = yr + 25;
	    num = pila.saca();
	    svgaux += String.format(rect, yr);
	    svgaux += String.format(text, yn, num);
	    yr += 50;
	    total++;
	}
	int h = 50*total, w = 140;
	svg += String.format(dimensiones, w, h);
	svg += this.g1;
	svg += svgaux;
	return svg + g2 + cierra;
    }
}
