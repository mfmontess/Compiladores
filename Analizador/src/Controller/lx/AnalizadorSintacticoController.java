package Controller.lx;

import java.util.*;
import java.util.Map.Entry;

import Model.Gramatica;
import Model.NoTerminal;
import Model.Termino;

import javax.swing.ListModel;

public class AnalizadorSintacticoController {

	public AnalizadorSintacticoController(ListModel<String> model) {
		//Conjuntos primeros		
		
		//Conjuntos Siguientes
		
		//Construir Tabla
	}

	public Object[][] getTabla() {
		return null;
	}
	
	public void ConjuntoPrimero(ArrayList<Gramatica> gramaticas){
		for (Gramatica g : gramaticas)  {
			NoTerminal noTerminal = g.getNoTerminalInicial();
			int k = 1;
			boolean continuar = true;
			
			while (continuar && k<= g.getExpresion().size()){
				ArrayList<Termino> primero = g.getExpresion().get(k).getConjuntoPrimero(); 
				primero.remove(" "); //validar si lo remueve como string o como termino
				
				noTerminal.setConjuntoPrimero(primero); //si ya existe no se debe duplicar
				
				if(!primero.contains(" "))//la cadena vacia se debe comparar como termino no como string
					continuar = false;
				k++ ;
			}
			if(continuar)
				noTerminal.setConjuntoPrimero(new Termino(" ")); //si ya existe no se debe duplicar
		}
	}
	
	public void ConjuntoSiguiente(ArrayList<Gramatica> gramaticas){
		HashMap<Termino, NoTerminal> grafo = new HashMap<>(); 
		gramaticas.get(0).getNoTerminalInicial().setConjuntoSiguiente(new Termino("$"));
		for(Gramatica g : gramaticas)  {
			NoTerminal A = g.getNoTerminalInicial();
			
			for(int i = 0; i< g.getNoTerminales().size(); i++){
				if(i == g.getExpresion().size())
						g.setExpresion(new Termino(" "));
				if(g.getExpresion().get(i+1).getConjuntoPrimero().contains(" ")){ //la cadena vacia se debe comparar como termino no como string
					g.getExpresion().get(i).setConjuntoSiguiente(A.getConjuntoSiguiente());
					grafo.put(g.getExpresion().get(i), A);
				}
			}
		}
		
		//mover conjuntos siguientes
		for (Entry<Termino, NoTerminal> e: grafo.entrySet())
			e.getValue().setConjuntoSiguiente(e.getKey().getConjuntoSiguiente());
	}

}
