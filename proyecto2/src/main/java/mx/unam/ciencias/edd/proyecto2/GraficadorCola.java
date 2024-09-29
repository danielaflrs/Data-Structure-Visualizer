package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Cola;

/**
 * Clase para graficar una cola de enteros en formato SVG.
 */
public class GraficadorCola {
    // La cola de enteros a graficar. 
    Cola<Integer> cola;

    // Inicio del documento SVG. 
    String init = "<?xml version='1.0' encoding='UTF-8' ?>\n";

    // Dimensiones del SVG, se rellena con el ancho y alto 
    String dimensiones = "<svg width='%s' height='%s'>\n";

    // Inicio del grupo de elementos
    String g1 = "  <g>\n";

    // Cierre del grupo de elementos
    String g2 = "  </g>\n";

    // Cierre del documento SVG
    String cierra = "</svg>";

    // Formato para dibujar un rectángulo en SVG.
    String rect = "    <rect x='%s' y='20' width='60' height='50' stroke='black' stroke-width='2' fill='white' />\n";

    // Formato para dibujar texto en SVG. 
    String text = "    <text fill='black' font-family='sans-serif' font-size='25' x='%s' y='55' text-anchor='middle'>%s</text>\n";

    /**
     * Constructor. Inicializa la cola a graficar.
     * @param cola la cola de enteros a graficar.
     */
    public GraficadorCola(Cola<Integer> cola) {
	this.cola = cola;
    }

    /**
     * Genera el código SVG que representa la cola.
     * @return una cadena con el código SVG.
     */
    public String dibuja(){
	int xr = 50;
	int xn = 70;
	int total = 1;
	String svg = init;
	int num;
	String svgaux = "";

	// Mientras la cola no sea vacía, dibuja los rectángulos y textos(elementos de la cola).
	while(!cola.esVacia()) {
	    xn = xr + 28;
	    num = cola.saca();
	    svgaux += String.format(rect, xr);
	    svgaux += String.format(text, xn, num);
	    xr += 60;
	    total++;
	}
	int w = 64*total, h = 100;
	svg += String.format(dimensiones, w, h);
	svg += this.g1;
	svg += svgaux;
	return svg + g2 + cierra;
    }

    
}
