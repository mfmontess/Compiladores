package Model;

import java.io.*;

public class Archivo {

	private String nombre;
	private StringBuilder contenido;

	public String getNombre() {
		return nombre;
	}

	public StringBuilder getContenido() {
		return contenido;
	}

	public Archivo(File archivo) throws IOException {
		this.nombre = archivo.getAbsolutePath();
		contenido = leerArchivo(archivo);
	}
	
	public Archivo(String nombre, StringBuilder contenido) {
		this.nombre = nombre;
		this.contenido = contenido;
	}
	
	public Archivo(String nombre, String contenido) {
		this.nombre = nombre;
		this.contenido = new StringBuilder(contenido);
	}
	
	public void generar() throws IOException{
		try {
			FileWriter salida = new FileWriter(nombre);
			BufferedWriter bufferSalida = new BufferedWriter(salida);
			bufferSalida.write(contenido.toString());
			bufferSalida.flush();
		} catch (IOException e) {
			throw new IOException("No se pudo generar el archivo debido al siguiente problema: " + e.getMessage());
		}
	}

	private StringBuilder leerArchivo(File archivo) throws IOException{
		StringBuilder contenido = new StringBuilder();
		try {
			FileReader entrada = new FileReader(archivo);
			BufferedReader bufferEntrada = new BufferedReader(entrada);
			String contenedor = bufferEntrada.readLine();
			
			while (contenedor != null) {
				contenido.append(contenedor);
				contenido.append("\r\n");
				contenedor = bufferEntrada.readLine();
			}
		}catch (IOException e){
				throw new IOException("No se pudo realizar la lectura del archivo debido al siguiente problema: " + e.getMessage());
		}
		return contenido;
	}
}