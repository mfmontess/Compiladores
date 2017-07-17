package Model;

import java.util.HashMap;
import java.util.Map.Entry;

public class AnalizadorSintactico {

	private Object[][] tablaAnalisis;
	private Arreglo<Gramatica> gramaticas;
	private Arreglo<Termino> noTerminales;
	private Arreglo<Termino> simbolosEntrada;
	
	public AnalizadorSintactico(Arreglo<Gramatica> gramaticas) {
		this.gramaticas = gramaticas;
		this.noTerminales = ObtenerNoTerminales(gramaticas);
		this.simbolosEntrada = ObtenerSimbolosEntrada(gramaticas);
		
		//Conjuntos primeros
		ConstruirConjuntosPrimero(gramaticas);
		
		//Conjuntos Siguientes
		ConjuntoSiguiente(gramaticas);
				
		//Construir Tabla
		this.tablaAnalisis = ConstruirTablaAnalisis(noTerminales);
	}
	
	public Object[][] getTablaAnalisis() {
		return tablaAnalisis;
	}
	
	public void ConstruirConjuntosPrimero(Arreglo<Gramatica> gramaticas){
		String nivel = "";
		for (Gramatica g : gramaticas)  {
			NoTerminal noTerminal = g.getNoTerminalInicial();
			System.out.println(nivel+ noTerminal.toString());
			int k = 0;
			int n = g.getPoscondicion().size();
			boolean continuar = true;
			
			while (continuar && k < n){
				Arreglo<Termino> primero = ConjuntoPrimero(g.getPoscondicion().get(k), nivel + "-");
				Arreglo<Termino> primeroTmp = new Arreglo<Termino>(primero);
				primeroTmp.remove(Termino.CADENA_VACIA);
				
				noTerminal.AddItemsConjuntoPrimero(primeroTmp); //si ya existe no se debe duplicar
				
				if(!primero.contains(Termino.CADENA_VACIA))
					continuar = false;
				k++ ;
			}
			if(continuar)
				noTerminal.AddItemConjuntoPrimero(Termino.CADENA_VACIA); //si ya existe no se debe duplicar
		}
	}
	
	public Arreglo<Termino> ConjuntoPrimero(Termino termino, String nivel){
		System.out.println(nivel+ termino.toString());
		Arreglo<Termino> primero = termino.getConjuntoPrimero();
		if(noTerminales.contains(termino)){
			for (Gramatica g : gramaticas)  {
				if(g.getNoTerminalInicial().toString().equals(termino.toString())){
					int k = 0, n = g.getPoscondicion().size();
					boolean continuar = true;
					while (continuar && k < n){
						primero.addAll(ConjuntoPrimero(g.getPoscondicion().get(k), nivel + "-"));
						Arreglo<Termino> primeroTmp = new Arreglo<Termino>(primero);
						primeroTmp.remove(Termino.CADENA_VACIA);
						
						termino.AddItemsConjuntoPrimero(primeroTmp); //si ya existe no se debe duplicar
						
						if(!primero.contains(Termino.CADENA_VACIA))
							continuar = false;
						k++ ;
					}
					if(continuar)
						termino.AddItemConjuntoPrimero(Termino.CADENA_VACIA); //si ya existe no se debe duplicar
				}
			}
		} else{
			//primero.add(termino);
			termino.AddItemConjuntoPrimero(termino);
		}
			
		return primero;
	}
	
	public void ConjuntoSiguiente(Arreglo<Gramatica> gramaticas){
		HashMap<Termino, NoTerminal> grafo = new HashMap<>(); 
		gramaticas.get(0).getNoTerminalInicial().AddItemConjuntoSiguiente(new Termino("$"));
		for(Gramatica g : gramaticas)  {
			NoTerminal A = g.getNoTerminalInicial();
			
			for(int i = 0; i< g.getPoscondicion().size(); i++){
				if (noTerminales.contains(g.getPoscondicion().get(i))){
					if(i == g.getExpresion().size())
						g.addTermino(Termino.CADENA_VACIA);
					if(g.getExpresion().get(i+1).getConjuntoPrimero().contains(Termino.CADENA_VACIA)){ //la cadena vacia se debe comparar como termino no como string
						g.getExpresion().get(i).AddItemsConjuntoSiguiente(A.getConjuntoSiguiente());
						grafo.put(g.getExpresion().get(i), A);
					}
				}
			}
		}
		
		//mover conjuntos siguientes
		for (Entry<Termino, NoTerminal> e: grafo.entrySet())
			e.getValue().AddItemsConjuntoSiguiente(e.getKey().getConjuntoSiguiente());
	}
	
	public Object [][] ConstruirTabla(Arreglo<Gramatica> gramaticas){
		Object [][] tabla = new Object[noTerminales.size()][simbolosEntrada.size()];
		
		for(Gramatica g : gramaticas)  {
			Arreglo<Termino> alfa = g.getPoscondicion();
			NoTerminal A = g.getNoTerminalInicial();
			
			for (Termino a : alfa.get(0).getConjuntoPrimero()) {//ajustar
				//tabla[A][a] = g;
				if(alfa.get(0).getConjuntoPrimero().contains(Termino.CADENA_VACIA)){
					for (Termino b : A.getConjuntoSiguiente()) {
						//tabla[A][b] = g;
					}
					if(A.getConjuntoSiguiente().contains(new Termino("$"))){
						//tabla[A][new Termino("$")] = g;
					}
				}
			}
		}
		return tabla;
	}
	
	public Object [][] ConstruirTablaAnalisis(Arreglo<Termino> noTerminales){
		Object [][] tabla = new Object[noTerminales.size()][3];
		
		for(int i = 0; i < noTerminales.size(); i++){
			tabla[i][0] = noTerminales.get(i).toString();
			tabla[i][1] = noTerminales.get(i).getConjuntoPrimero().toString();
			tabla[i][2] = noTerminales.get(i).getConjuntoSiguiente().toString();
		}
		return tabla;
	}
	
	public Arreglo<Termino> ObtenerSimbolosEntrada(Arreglo<Gramatica> gramaticas){
		Arreglo<Termino> terminales = new Arreglo<>();
		for(Gramatica g : gramaticas)  {
			for(Termino n : g.getPoscondicion()){
				if(!terminales.contains(n) && !noTerminales.contains(n)){
					terminales.add(n);
				}
			}
		}
		
		return terminales;
	}
	
	public Arreglo<Termino> ObtenerNoTerminales(Arreglo<Gramatica> gramaticas){
		Arreglo<Termino> noTerminales = new Arreglo<>();
		for(Gramatica g : gramaticas)  {
			if(!noTerminales.contains(g.getNoTerminalInicial())){
					noTerminales.add(g.getNoTerminalInicial());
			}
		}
		
		return noTerminales;
	}

	
	public Arreglo<Gramatica> getGramaticas() {
		return gramaticas;
	}

	public void setGramaticas(Arreglo<Gramatica> gramaticas) {
		this.gramaticas = gramaticas;
	}

}
