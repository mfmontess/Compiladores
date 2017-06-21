package Controller;

import View.DashBoard;

/**
 * Compiladores
 * Clase principal, inicia la vista.
 */

public class Principal {

	public static void main(String[] args) {
		DashBoard tablero = new DashBoard();
		tablero.setLocationRelativeTo(null);
		tablero.setResizable(false);
		tablero.setVisible(true);
	}
}
