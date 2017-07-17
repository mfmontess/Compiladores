package Model;

public class Termino {
	public static final Termino CADENA_VACIA = cadenaVacia(" ");
	
	private String[] valor;
	private Arreglo<Termino> conjuntoPrimero;
	private Arreglo<Termino> conjuntoSiguiente;

	public Termino(String valor) {
		this.valor = new String[1];
		this.valor[0] = valor;
		conjuntoPrimero = new Arreglo<>();
		conjuntoSiguiente = new Arreglo<>();
	}
	
	public String toString(){
		return valor[0];
	}

	public Arreglo<Termino> getConjuntoPrimero() {
		return conjuntoPrimero;
	}

	public void AddItemConjuntoPrimero(Termino termino) {
		if(!this.conjuntoPrimero.contains(termino))
			this.conjuntoPrimero.add(termino);
	}
	
	public void AddItemsConjuntoPrimero(Arreglo<Termino> terminos) {
		for(Termino t : terminos){
			if(!this.conjuntoPrimero.contains(t))
				this.conjuntoPrimero.add(t);
		}
	}

	public String getValor() {
		return valor[0].toString();
	}

	public void setValor(String valor) {
		this.valor[0] = valor;
	}

	public Arreglo<Termino> getConjuntoSiguiente() {
		return conjuntoSiguiente;
	}

	public void AddItemConjuntoSiguiente(Termino termino) {
		this.conjuntoSiguiente.add(termino);
	}
	
	public void AddItemsConjuntoSiguiente(Arreglo<Termino> terminos) {
		this.conjuntoSiguiente.addAll(terminos);
	}

	private static Termino cadenaVacia(String string) {
		if (CADENA_VACIA != null)
			return CADENA_VACIA;
		else
			return new Termino(" ");
	}
}
