package Model;

import java.util.ArrayList;

public class Termino {
	private String[] valor;
	private ArrayList<Termino> conjuntoPrimero;
	private ArrayList<Termino> conjuntoSiguiente;

	public Termino(String valor) {
		this.valor[0] = valor;
		conjuntoPrimero = new ArrayList<>();
		conjuntoSiguiente = new ArrayList<>();
	}

	public ArrayList<Termino> getConjuntoPrimero() {
		return conjuntoPrimero;
	}

	public void setConjuntoPrimero(Termino termino) {
		this.conjuntoPrimero.add(termino);
	}
	
	public void setConjuntoPrimero(ArrayList<Termino> terminos) {
		this.conjuntoPrimero.addAll(terminos);
	}

	public String getValor() {
		return valor[0].toString();
	}

	public void setValor(String valor) {
		this.valor[0] = valor;
	}

	public ArrayList<Termino> getConjuntoSiguiente() {
		return conjuntoSiguiente;
	}

	public void setConjuntoSiguiente(Termino termino) {
		this.conjuntoSiguiente.add(termino);
	}
	
	public void setConjuntoSiguiente(ArrayList<Termino> terminos) {
		this.conjuntoSiguiente.addAll(terminos);
	}

}
