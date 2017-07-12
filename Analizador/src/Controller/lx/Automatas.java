package Controller.lx;

import Model.Tokens;

/**
 * 
 * Compiladores
 * Clase que contiene el algoritmo 
 * recibe cada caracter que se debe leer
 */

public class Automatas {
	
	Character au='x';
	int estado,noesid, band=0, contador, posx=0, band2=0;
	char[] ch, tempis, tempis2, result, noid={'?','?','?'}, id={'i','d'}, ent={'N','u','m'}, real={'R','e','a','l'}, resultcopia;
	Object[][] tabla, tablacopia;
    
	/*
     * Metodo String
     * Listado de simbolos 
     */

	private String tokens[]=  
		{
		"=","/","int","while","String","char","Character"
		};
	
		/*
		 * Constructor que recibe el caracter a evaluar 
		 * como parametro y ejecuta el metodo operador
		 */
	
public void Automatas(String caracter, char[] tempis, char[] result, int estado,Object[][] tabla, int contador){  
		 
	//cambia el caracter a validar de string a char
		ch = caracter.toCharArray();
		
		//crea un nuevo objeto para almacenar la tabla resultante y comparar el caracter
		Tokens token = new Tokens();
		
		//verifica si el caracter es un token
		for(int i=0;i<token.verificar().length;i++)
		{
			if(caracter.equalsIgnoreCase((token.verificar()[i][1]).toString())){
				band=1;
				
				if((caracter.equalsIgnoreCase("\""))&&(estado!=-4)){
					band2=1;
				}else if((caracter.equalsIgnoreCase("\""))&&(estado==-4)){
					estado=0;
				}
			}
		}
		
		//si el caracter es un token ingresa
		if(band == 1){
			
			//clona la cadena resultante
			resultcopia = result.clone();
			//cambia la informacion de la cadena temporal de char a string
			String lexema = String.valueOf(tempis);
			//compara la informacion de la cadena temporal para saber si es un token
			for(int i=0;i<token.verificar().length;i++)
			{
				if(lexema.equalsIgnoreCase((token.verificar()[i][1]).toString())){
					band=2;
					posx = i;
					}
			}
			//si es un token la infromacion de la cadena temporal, se almacena en la cadena resultante
			if(band==2){
				result = new char[(result.length+tempis.length)+1];
				System.arraycopy(resultcopia,0,result,0,resultcopia.length);
				System.arraycopy(tempis,0,result,resultcopia.length,tempis.length);
				
			//si no es un token y es una agrupacion de digitos y numeros con el primer caracter como letra, se almacena en la cadena resultante la palabra: id
			}else if (estado>0){
				result = new char[(result.length)+3];
				System.arraycopy(resultcopia,0,result,0,resultcopia.length);
				System.arraycopy(id,0,result,(result.length)-3,id.length);
				
				//si es el primer id identificado se clona la tabla de simbolos y se aumenta en tamaño para almacenar la informacion del primer id (lexema, token, identificador)
				if(contador!=0){
					tablacopia = tabla.clone();
					tabla = new Object[(tabla.length)+1][3];
				
					for(int i=0;i<tablacopia.length;i++){
					
						for(int j=0;j<tablacopia[i].length;j++){
						
							tabla[i][j] = tablacopia[i][j];
						}
					}
				}
					
				tabla[(tabla.length)-1][0] = lexema;	
				tabla[(tabla.length)-1][1] = "id";
				tabla[(tabla.length)-1][2] = "No";
				contador++;
				
			//Si la informacion tiene caracteres desconocidos (no son letras ni digitos), se adiciona a la cadena resultante como ??? almacenado en la variable noid
			}else if(estado==-3){
				result = new char[(result.length)+4];
				System.arraycopy(resultcopia,0,result,0,resultcopia.length);
				System.arraycopy(noid,0,result,(result.length)-4,noid.length);
				
			//si es el primer caracter, se adiciona a la cadena resultante	
			}else if(estado==0){
			result = new char[(result.length)+1];
			System.arraycopy(resultcopia,0,result,0,resultcopia.length);
			
			//si el lexema esta compuesto por solo numeros enteros, se adiciona a la cadena resultante como Num almacenado en la variable ent
			}else if(estado==-2){
				result = new char[(result.length)+4];
				System.arraycopy(resultcopia,0,result,0,resultcopia.length);
				System.arraycopy(ent,0,result,(result.length)-4,ent.length);
				
			//si los caracteres ingresados tienen un " antepuesto lo envia directamente a la cadena resultante	
			}else if(estado==-4){
				
				result = new char[(result.length)+1];
				System.arraycopy(resultcopia,0,result,0,resultcopia.length);
				System.arraycopy(ch,0,result,(result.length)-1,1);
			//si el lexema es un nuemero real lo alaena en la cadena resultante	
			}else if(estado==-1){
				
				result = new char[(result.length)+5];
				System.arraycopy(resultcopia,0,result,0,resultcopia.length);
				System.arraycopy(real,0,result,(result.length)-5,real.length);
				
				
			}
			//Se adiciona el caracter actual a la cadena resultante y se inicializan las variables estado y cadena temporal
			System.arraycopy(ch,0,result,(result.length)-1,1);
			//asigna el estado correspodiente, si esta dentro de " coloca -4, de lo contrario coloca 0
			if(estado!=-4){
			estado=0;
			}
			tempis = new char[0];
			if(band2==1){
				estado=-4;
			}
		//si el caracter entrante es una letra y es el primer caracter, lo adiciona a la cadena temporal y aumenta el valor de estado	
		}else if((au.isLetter(ch[0]))&&(estado == 0)){
			
			tempis = new char[tempis.length+1];
			System.arraycopy(ch,0,tempis,0,1);
			
			estado++;
			
		//si el caracter entrante es un numero y es el primer caracter, lo adiciona a la cadena temporal y cambia el estado a -2	
		}else if ((au.isDigit(ch[0]))&& estado == 0){
			
			tempis = new char[tempis.length+1];
			System.arraycopy(ch,0,tempis,0,1);
			estado = -2;
		
		//si el caracter entrante es un numero y los anteriores tambien son numeros, lo almacena en la cadena temporal y mantiene el estado en -2
		}else if(((au.isDigit(ch[0]))&& (estado == -2))){
			
			tempis = new char[tempis.length+1];
			System.arraycopy(ch,0,tempis,0,1);
			estado = -2;
			
		//si el caracter entrante es una letra y el primer caracter es un numero, se almacena en la cadena temporal y se cambia el estado a -3	
		}else if((au.isLetter(ch[0]))&&(estado == -2)){
			
			tempis = new char[tempis.length+1];
			System.arraycopy(ch,0,tempis,0,1);
			estado = -3;
		
		//si el caracter entrante es un espacio y los caracteres anteriores son numeros, almacena en la cadena resultante: Num
		}else if((ch[0] == ' ')&& estado == -2 ){
			
			resultcopia = result.clone();
			result = new char[result.length+4];
			String lex = tempis.toString();
			
			if (resultcopia.length != 0){
				
				System.arraycopy(resultcopia,0,result,0,resultcopia.length);
				}
			
			System.arraycopy(ent,0,result,resultcopia.length,ent.length);
			System.arraycopy(ch,0,result,(result.length)-1,1);
			
						
			estado=0;
			tempis = new char[0];
			
		//si el caracter entrante es un espacio y es el primer caracter, se impime el mismo espacio en la cadena resultante	
		}else if((ch[0] == ' ')&& estado == 0 ){
		
			resultcopia = result.clone();
			result = new char[result.length+1];
			
			if (resultcopia.length != 0){
				
				System.arraycopy(resultcopia,0,result,0,resultcopia.length);
				}
			
			System.arraycopy(ch,0,result,resultcopia.length,1);
			
				
			estado=0;
			tempis = new char[0];
		
		//si el caracter entrante es numero o letra y los demas caracteres son letras o numeros con el primer caracter como letra, se almacena en la cadena temporal y se inicializan las variables de estado y cadena temporal
		}else if(((au.isDigit(ch[0]))&& (estado > 0))||((au.isLetter(ch[0]))&& (estado > 0))){
			
			tempis2 = tempis.clone();
			tempis = new char[tempis.length+1];
			
			System.arraycopy(tempis2,0,tempis,0,tempis2.length);
			System.arraycopy(ch,0,tempis,tempis2.length,1);
			estado++;
		
		//si el caracter entrante es un espacio y los demas caracteres son letras o numeros con el primer caracter como letra, se valida si es token y se imprime en la cadena resultante
		}else if((ch[0] == ' ')&& estado > 0 ){
			
			//se verifica si el lexema es token
			String lexema = String.valueOf(tempis);
			for(int i=0;i<token.verificar().length;i++)
			{
				if(lexema.equalsIgnoreCase((token.verificar()[i][1]).toString())){
					band=1;
					posx=i;
					
					}
			}
			//si es token copia la cadena temporal a la cadena resultante y copia el espacio
			if(band==1){
				
				resultcopia = result.clone();
				result = new char[(result.length+tempis.length)+1];
				
				System.arraycopy(resultcopia,0,result,0,resultcopia.length);
				System.arraycopy(tempis,0,result,resultcopia.length,tempis.length);
				System.arraycopy(ch,0,result,(result.length)-1,1);
				
								
							
				estado=0;
				tempis = new char[0];
				
			//si no es token copia en la cadena resultante:id	
			}else{
				
				resultcopia = result.clone();
				result = new char[result.length+3];
				
				if (resultcopia.length != 0){
					
					System.arraycopy(resultcopia,0,result,0,resultcopia.length);
					}
				
				System.arraycopy(id,0,result,resultcopia.length,id.length);
				System.arraycopy(ch,0,result,(result.length)-1,1);
				
				//se clona la tabla de simbolos y se aumenta en tamaño para almacenar la informacion del primer id (lexema, token, identificador)
				if(contador!=0){
					tablacopia = tabla.clone();
					tabla = new Object[(tabla.length)+1][3];
				
					for(int i=0;i<tablacopia.length;i++){
					
						for(int j=0;j<tablacopia[i].length;j++){
						
							tabla[i][j] = tablacopia[i][j];
						}
					}
				}
					
				tabla[(tabla.length)-1][0] = lexema;	
				tabla[(tabla.length)-1][1] = "id";
				tabla[(tabla.length)-1][2] = "No";
				contador++;
				
				estado=0;
				tempis = new char[0];
			}
			
		//Si el caracter ingresado es diferente de un espacio, una letra o un numero, lo almacena en la cadena temporal y cambia el estado a -3	
		}else if((ch[0] != ' ')&& estado == -3){
			
			tempis = new char[tempis.length+1];
			System.arraycopy(ch,0,tempis,0,1);
			estado = -3;
			
		//si el caracter ingresado es un espacio y no es ni letra ni numero, se almacena en la cadena resultante como ??? que es la variable noid, se inicializa el estado y la cadena temporal
		}else if((ch[0] == ' ')&& estado == -3){
			
			resultcopia = result.clone();
			result = new char[result.length+4];
			String lex = tempis.toString();
			
			if (resultcopia.length != 0){
				
				System.arraycopy(resultcopia,0,result,0,resultcopia.length);
				}
			
			System.arraycopy(noid,0,result,resultcopia.length,noid.length);
			System.arraycopy(ch,0,result,(result.length)-1,1);
			
						
			estado=0;
			tempis = new char[0];
			
		//si el carecter es un punto . y el lexema anterior es un digito lo ingresa a la cadena temporal y asigna al estado -1	
		}else if((ch[0] == '.')&& estado == -2){
			
			tempis2 = tempis.clone();
			tempis = new char[tempis.length+1];
			
			System.arraycopy(tempis2,0,tempis,0,tempis2.length);
			System.arraycopy(ch,0,tempis,tempis2.length,1);
			estado=-1;
		//si el caracter en un digito y el lexema anterior es un real, lo continua almacenando en la cadena temporal y le asigna a estado el mismo valor -1	
		}else if((au.isDigit(ch[0]))&& (estado == -1)){
			
			tempis2 = tempis.clone();
			tempis = new char[tempis.length+1];
			
			System.arraycopy(tempis2,0,tempis,0,tempis2.length);
			System.arraycopy(ch,0,tempis,tempis2.length,1);
			estado=-1;
			
		//si el caracter ingresado es un espacio y el lexema anterior es un real, ingresa a la cadena resultante el valor real	
		}else if((ch[0] == ' ')&& estado == -1){
			
			resultcopia = result.clone();
			result = new char[result.length+5];
			String lex = tempis.toString();
			
			if (resultcopia.length != 0){
				
				System.arraycopy(resultcopia,0,result,0,resultcopia.length);
				}
			
			System.arraycopy(real,0,result,resultcopia.length,real.length);
			System.arraycopy(ch,0,result,(result.length)-1,1);
			
						
			estado=0;
			tempis = new char[0];
			
		//si el estado indica que el caracter esta entre comillas, lo ingresa directamente a la cadena resultante	
		}else if(estado==-4){
			
			resultcopia = result.clone();
			result = new char[result.length+1];
			System.arraycopy(resultcopia,0,result,0,resultcopia.length);
			System.arraycopy(ch,0,result,(result.length)-1,1);
		//si el caracter ingresado no es letra, ni digito, ni token, le asigna a estado el valor -3
		}else{
			
			tempis = new char[tempis.length+1];
			System.arraycopy(ch,0,tempis,0,1);
			estado = -3;
			
		}
			//se envia el resultado del analisis a las variables correspondientes para que sean llamadas desde la clase lector y pasen como referencia
			
			setTempis(tempis);
			setResult(result);
			setEstado(estado);
			setTabla(tabla);
			setContador(contador);
}


		
public int getEstado() {
	return estado;
}


public void setEstado(int estado) {
	this.estado = estado;
}



public char[] getResult() {
	return result;
}


public void setResult(char[] result) {
	this.result = result;
}


public char[] getTempis() {
	return tempis;
}


public void setTempis(char[] tempis) {
	this.tempis = tempis;
}

public char[] result(){
	return tempis;
}



public Object[][] getTabla() {
	return tabla;
}



public void setTabla(Object[][] tabla) {
	this.tabla = tabla;
}



public int getContador() {
	return contador;
}



public void setContador(int contador) {
	this.contador = contador;
}


}