package Model;

import java.util.ArrayList;

public class Gramatica {
	private ArrayList<Termino> expresion;
	private NoTerminal noTerminalInicial;
	private ArrayList<Termino> noTerminales;

	public ArrayList<Termino> getExpresion() {
		return expresion;
	}

	public void setExpresion(ArrayList<Termino> expresion) {
		this.expresion.addAll(expresion);
	}
	
	public void setExpresion(Termino termino) {
		this.expresion.add(termino);
	}

	public NoTerminal getNoTerminalInicial() {
		return noTerminalInicial;
	}

	public void setNoTerminalInicial(NoTerminal noTerminal) {
		this.noTerminalInicial = noTerminal;
	}

	public ArrayList<Termino> getNoTerminales() {
		return noTerminales;
	}

	public void setNoTerminales(NoTerminal termino) {
		this.noTerminales.add(termino);
	}

}
