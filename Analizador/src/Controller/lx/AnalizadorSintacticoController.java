package Controller.lx;

import java.io.File;
import java.io.IOException;

import Model.*;

public class AnalizadorSintacticoController {

	private AnalizadorSintactico analizador;

	public AnalizadorSintacticoController(File file) throws Exception {
		String gramaticasTxt = abrirArchivo(file);		
		this.analizador = new AnalizadorSintactico(construirGramaticas(gramaticasTxt));
	}

	private Arreglo<Gramatica> construirGramaticas(String gramaticasTxt) throws Exception {
		Arreglo<Gramatica> gramaticas = new Arreglo<Gramatica>();
		
		for(String expresion : gramaticasTxt.split("\r\n")){
			Gramatica gramatica = new Gramatica(expresion);
			gramaticas.add(gramatica);
		}
		return gramaticas;
	}

	private String abrirArchivo(File file) {
		String texto = "";
		try {
			Archivo insumo = new Archivo(file); // Se lee el archivo para mostrarse en la ventana 
			texto = insumo.getContenido().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return texto;
	}

	
	public AnalizadorSintactico getAnalizador() {
		return analizador;
	}

	
	public void setAnalizador(AnalizadorSintactico analizador) {
		this.analizador = analizador;
	}
}
