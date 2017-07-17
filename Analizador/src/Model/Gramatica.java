package Model;

public class Gramatica {
	private Arreglo<Termino> expresion;
	private NoTerminal noTerminalInicial;
	private Arreglo<Termino> poscondicion;
	
	public Gramatica(String expresion) throws Exception{
		this.poscondicion = new Arreglo<Termino>();
		this.expresion = construirExpresion(expresion);
	}

	private Arreglo<Termino> construirExpresion(String expresion) throws Exception {
		Arreglo<Termino> expresionTmp = new Arreglo<Termino>();
		String[] condiciones = expresion.split("->");
		
		if(condiciones.length == 2){
			String[] precondicion = condiciones[0].split(":");
			//precondicion
			NoTerminal t = new NoTerminal(precondicion[0]);
			expresionTmp.add(t);
			noTerminalInicial = t;
			
			expresionTmp.add(new Termino("->"));
			
			//poscondicion
			String[] poscondicion = condiciones[1].split(":");
			for(int i = 1; i < poscondicion.length; i++){
				Termino terminoTmp = new Termino(poscondicion[i]);
					this.poscondicion.add(terminoTmp);
					expresionTmp.add(terminoTmp);
			}
			
			return expresionTmp;
		} else
			throw new Exception("La expresion " + expresion + "no contiende una condicion y una poscondicion por lo cual no es posible cargarla.");
	}
	
	public String toString(){
		return expresion.toString();
	}

	public Arreglo<Termino> getExpresion() {
		return expresion;
	}

	public NoTerminal getNoTerminalInicial() {
		return noTerminalInicial;
	}

	public void setNoTerminalInicial(NoTerminal noTerminal) {
		this.noTerminalInicial = noTerminal;
	}

	public Arreglo<Termino> getPoscondicion() {
		return poscondicion;
	}
	
	public void addTermino(Termino termino) {
		expresion.add(termino);
	}

}
