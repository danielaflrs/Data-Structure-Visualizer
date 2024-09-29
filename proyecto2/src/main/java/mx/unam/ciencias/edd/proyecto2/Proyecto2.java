package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.Cola;
import mx.unam.ciencias.edd.Pila;
import java.text.Normalizer;
import java.io.IOException;
import java.io.*;

/** 
 * Clase principal para el manejo y visualización de estructuras de datos.
 * Este permite visualizar diferentes tipos de estructuras de datos
 * a partir de datos ingresados a través de un archivo.
 */

public class Proyecto2 {
    public static void main(String[] args) {
	// Lector para los archivos de entrada
	Lector lectorDeArchivos = new Lector();

	// Leer los datos del archivo especificado en el argumento
	Lista<String> listaA = lectorDeArchivos.lector(args[0]);
	Lista<Integer> listaB = new Lista<Integer>();

	// Extrae el primer elemento de la lista que indica el tipo de estructura de datos.
	String primElem = listaA.eliminaPrimero();
	// Separa los elementos restantes en enteros para poder utilizarlos.
	listaB = separador(listaA);

	// Se determina la estructura de datos para graficar.
	switch (primElem.toLowerCase()) {
	case "lista":
	    Lista<Integer> lista1 = listaB;
	    GraficadorLista gl = new GraficadorLista(listaB);
	    System.out.println(gl.dibuja());
	    break;
	case "arbolavl":
	    ArbolAVL<Integer> tipoArbolAVL = new ArbolAVL<Integer>(listaB);
	    System.out.println(tipoArbolAVL);
	    break;
	case "arbolbinarioordenado":
	    ArbolBinarioOrdenado<Integer> tipoArbolBinarioOrdenado = new ArbolBinarioOrdenado<Integer>(listaB);
	    GraficadorArbolBinarioOrdenado abo = new GraficadorArbolBinarioOrdenado(tipoArbolBinarioOrdenado);
	    System.out.println(abo.dibuja());
	    break;
	case "arbolbinariocompleto":
	    ArbolBinarioCompleto<Integer> tipoArbolBinarioCompleto = new ArbolBinarioCompleto<Integer>(listaB);
	    GraficadorArbolBinarioCompleto abc = new GraficadorArbolBinarioCompleto(tipoArbolBinarioCompleto);
	    System.out.println(abc.dibuja());
	    break;
	case "arbolrojinegro":
	    ArbolRojinegro<Integer> tipoArbolRojinegro = new ArbolRojinegro<Integer>(listaB);
	    break;
	case "cola":
	    Cola<Integer> tipoCola = new Cola<Integer>();
	    for (int i : listaB) tipoCola.mete(i);
	    GraficadorCola gc = new GraficadorCola(tipoCola);
	    System.out.println(gc.dibuja());
	    break;
	case "pila":
	    Pila<Integer> tipoPila = new Pila<Integer>();
	    for (int i : listaB) tipoPila.mete(i);
	    GraficadorPila gp = new GraficadorPila(tipoPila);
	    System.out.println(gp.dibuja());
	    break;
	default:
	    System.out.println("Tipo de estructura de datos no reconocido.");
	    break;
        }  
    }

    /**
     * Método auxiliar para convertir una lista de cadenas en una lista de enteros.
     * @param listaElementos Lista de cadenas donde cada cadena contiene uno o más enteros separados por espacios.
     * @return Lista de enteros.
     */
    private static Lista<Integer> separador (Lista<String> listaElementos){
	Lista<Integer> nuevaLista = new Lista<Integer>();
	for(String i: listaElementos) {
	    String[] elementos = i.split(" ");
	    for(int j = 0; j < elementos.length; j++){
		try {
		    int a = Integer.parseInt(elementos[j]);
		    nuevaLista.agrega(a);
		}
		catch (NumberFormatException e){
		    System.out.println("Esto no es un entero");
		    System.exit(-1);
		}
	    }
	}
	return nuevaLista;
    }
}
