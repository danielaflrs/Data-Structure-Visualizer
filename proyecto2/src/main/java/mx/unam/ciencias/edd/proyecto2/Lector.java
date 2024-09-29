package mx.unam.ciencias.edd.proyecto2;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import mx.unam.ciencias.edd.Lista;

/**
 * Clase para leer archivos y procesar su contenido.
 * Esta da un método para leer un archivo línea por línea y almacenar cada línea en una lista,
 * excluyendo las líneas que comienzan con un caracter de comentario, es decir por '#'.
 */
public class Lector {
    /**
     * Lee el archivo ubicado en la ruta especificada y almacena cada línea en una lista,
     * omitiendo cualquier texto después de '#'.
     * El método tiene excepciones relacionadas con problemas de I/O y finaliza el programa si el archivo no se puede leer.
     *
     * @param path La ruta del archivo que se va a leer.
     * @return Lista de cadenas con las líneas leídas y procesadas del archivo.
     * @throws IOException Si ocurre un error al abrir o leer el archivo.
     */
    public Lista<String> lector (String path){
	Lista<String> listaContenido = new Lista<String>();
	BufferedReader lector = null;
	String linea;

	try {
	    lector = new BufferedReader(new FileReader(path));

	    while((linea = lector.readLine()) != null) {
		String limpia = almohadilla(linea);
		if(!limpia.equals(""))
		    listaContenido.agrega(limpia);
	    }
	} catch (IOException e) {
	    System.out.println("No existe el archivo");
	    System.exit(-1);
	}
	return listaContenido;
    }

    /**
     * Revisa una línea de texto y elimina cualquier contenido después de '#'.
     *
     * @param entrada La línea de texto a procesar.
     * @return La línea de texto sin la parte del comentario.
     */
    private String almohadilla(String entrada) {
	int i = 0;
	while(i < entrada.length()){
	    if(entrada.charAt(i) == '#'){
		return entrada.substring(0,i).trim();
	    }
	    i++;
	}
	return entrada.trim();
    }
}
