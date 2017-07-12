package Controller.lx;

import java.io.File;

import javax.swing.JOptionPane;

import Model.Archivo;

public class AnalizadorController {	

	private Archivo insumo;
	private Archivo salida;
	
	public Archivo getInsumo() {
		return insumo;
	}

	public void setInsumo(Archivo insumo) {
		this.insumo = insumo;
	}

	public Archivo getSalida() {
		return salida;
	}

	public void setSalida(Archivo salida) {
		this.salida = salida;
	}	

	public AnalizadorController(){
	}

	public Archivo cargarInsumo(File archivo){
		insumo = null;
		try {
			System.out.println("Abrir archivo: " + archivo.getAbsolutePath());
			insumo = new Archivo(archivo);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Se ha presentado el siguiente error. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return insumo;
	}
	
	public Archivo convertirArchivo(Archivo insumo, String ruta) {
		salida = null;
		
		if(insumo != null){
			System.out.println("Convertir letras y numeros.");
			//StringBuilder textoPermutado = permutarLetrasyNumeros(insumo.getContenido());
			//salida = new Archivo(ruta + "\\resultado.txt", textoPermutado);
			
			try {
				System.out.println("Generar archivo de resultados: " + salida.getNombre());
				salida.generar();
				JOptionPane.showMessageDialog(null, "Proceso terminado, ver archivo resultado.txt");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Se ha presentado el siguiente error. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Se debe cargar primero un insumo para poder realizar la conversion de este. Por favor vaya al menú Archivo -> Abrir y seleccione el archivo a convertir", "Error", JOptionPane.ERROR_MESSAGE);
		
		return salida;
	}

}