package mx.unam.ciencias.edd.proyecto2;

import  mx.unam.ciencias.edd.Lista;

/**
 * Clase para graficar listas utilizando SVG.
 * Esta toma una lista de enteros y genera una imagen de la EDD en formato SVG.
 */
public class GraficadorLista {

    // Lista de enteros que será graficada.
    Lista<Integer> lista;

    // Inicio del documento SVG
    String init = "<?xml version='1.0' encoding='UTF-8' ?>\n";

    // Dimensiones del SVG, se rellena con el ancho y alto
    String dimensiones = "<svg width='%s' height='%s'>\n";

    // Inicio del grupo de elementos
    String g1 = "  <g>\n";

    // Cierre de grupo de elementos
    String g2 = "  </g>\n";

    // Cierre del documento SVG
    String cierra = "</svg>";

    // Línea que conecta los rectángulos
    String line = "    <line x1='%s' y1='45' x2='%s' y2='45' stroke='black' stroke-width='2' />\n";

    // Rectángulos que representan los elementos de la lista
    String rect = "    <rect x='%s' y='20' width='50' height='50' stroke='black' stroke-width='2' fill='white' />\n";

    // Texto que da el valor de cada elemento dentro del rectángulo
    String text = "    <text fill='black' font-family='sans-serif' font-size='20' x='%s' y='55' text-anchor='middle'>%s</text>\n";

    
    /**
     * Constructor que inicializa la lista a graficar.
     * @param lista Lista de enteros a graficar.
     */
    public GraficadorLista(Lista<Integer> lista) {
	this.lista = lista;
    }

    /**
     * Método para generar la representación SVG de la lista
     * @return String que contiene el código SVG generado
     */
    public String dibuja(){
	// Altura definida del SVG
	int h = 100;
	// Coordenadas iniciales para el rectángulo
	int xr = 50;
	// Coordenadas iniciales para las líneas
	int x1 = 100, x2 = 150;
	// Variables para las  posiciones y valores del texto
	int num = lista.getPrimero(), xn = 75;
	// Ancho del SVG en base a la cantidad de elementos de la lista
	int w = 100*lista.getLongitud();
	// Inicialización del SVG
	String svg = init;
	svg += String.format(dimensiones, w, h);	
	svg += this.g1;
	// Este for es para agregar cada elemento de la lista al SVG
	for (Integer i : this.lista) {
	    num = i;
	    svg += String.format(line, x1, x2);
	    svg += String.format(rect, xr);
	    svg += String.format(text, xn, num);
	    xr += 100;
	    x1 += 100;
	    x2 += 100;
	    xn += 100;
	}
	// Finalización y cierre del SVG
	return svg + g2 + cierra;
    }

    
}
