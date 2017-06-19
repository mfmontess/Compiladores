package Controller;

import View.DashBoard;
import View.Vista;

/**
 * Compiladores
 * Clase principal, inicia la vista.
 */

public class Principal {

	public static void main(String[] args) {
		/*Vista miVentana = new Vista();
		miVentana.setLocationRelativeTo(null);
		miVentana.setResizable(false);
		miVentana.setVisible(true);*/
		
		DashBoard tablero = new DashBoard();
		tablero.setLocationRelativeTo(null);
		tablero.setResizable(false);
		tablero.setVisible(true);
		
	}
}
