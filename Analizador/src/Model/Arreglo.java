package Model;
import java.util.ArrayList;
import java.util.Iterator;

public class Arreglo<E> implements Iterable<E>{
	
	private ArrayList<E> arrayList;	
	
	public Arreglo(){
		arrayList = new ArrayList<E>();
	}
	
	public Arreglo(Arreglo<E> arreglo) {
		this.arrayList = new ArrayList<E>(arreglo.arrayList);
	}

	public boolean contains (E expresion){
		boolean encontro = false;
		int i = 0;
		if (arrayList.size() > 0){
			while(!encontro && i< arrayList.size()){
				if(arrayList.get(i).toString().equals(expresion.toString()))
					encontro = true;
				i++;
			}
		}
		return encontro;
	}

	public void add(E termino) {
		arrayList.add(termino);
	}
	
	public void addAll(Arreglo<E> arreglo){
		for(E item : arreglo){
			arrayList.add(item);			
		}
	}
	
	public int size(){
		return arrayList.size();
	}
	
	public E get(int posicion){
		return arrayList.get(posicion);
	}
	
	public boolean remove(E item){
		if(arrayList.contains(item)){
			arrayList.remove(item);
			return true;
		} else
			return false;
	}
	
	public String toString(){
		String datos = "";
		for(E item : arrayList){
			datos = datos + item.toString();			
		}
		return datos;
	}

	@Override
	public Iterator<E> iterator() {
		return arrayList.iterator();
	}

}
