package Controller.lx;

import View.AnalizadorLexico;
import View.Home;

/**
 * Compiladores
 * Clase principal, inicia la vista.
 */

public class Principal {

	public static void main(String[] args) {
		Home principal = new Home();
		principal.setLocationRelativeTo(null);
		principal.setResizable(false);
		principal.setVisible(true);
	}
}
