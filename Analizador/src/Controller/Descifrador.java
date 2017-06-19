package Controller;

import java.io.*;  

import Model.*;

/**
 * Clase que permite Leer y enviar 
 * los caracteres leidos a la clase Algoritmo
 */
public class Descifrador {  
	
 String linea, leido,total, totalok;
 int longitud=0, estado=0, cont=0, contador=0;
 Character ch[];
 char[] result, totalresult;
 File fileEntrada, nomFile;  
 FileReader entrada, entrada2;
 BufferedReader bufferDeArchivoEntrada;
 String aux="";
 String texto="";
 Object[][] tabla, tablacopia;

 
 //guarda el resultado total luego del analisis lexico
 public String getTotalok() {
	return totalok;
}

public void setTotalok(String totalok) {
	this.totalok = totalok;
}


//constructor de la clase, recibe la ubicacion del archivo como parametro
 public Descifrador(File nFile) throws IOException{  
	 fileEntrada=nFile;   
	 abrirArchivoEntrada(); 
	 leerArchivo();
	 }   
 
 //Metodo quecarga en el buffer la informacion del archivo de texto de entrada
 void abrirArchivoEntrada(){  
	  
	 try {   
		 entrada = new FileReader(fileEntrada);  
		 bufferDeArchivoEntrada = new BufferedReader(entrada); 
		 //bufferDeCadena = new StringBuffer(); 
		 } catch (FileNotFoundException e) { 
			 e.printStackTrace();   
			 }     
	 }    
 
//Metodo que permite leer el archivo caracter a caracter
public void leerArchivo() throws IOException{  
	
	//adiciona la tabla de simbolos y cambia su tamaño para almacenar los id resultantes
	totalresult = new char[0];
	Tokens token = new Tokens();
	tablacopia = token.verificar();
	tabla = new Object[(tablacopia.length)+1][3];
	for(int i=0;i<tablacopia.length;i++){
		
		for(int j=0;j<tablacopia[i].length;j++){
		
			tabla[i][j] = tablacopia[i][j];
		}
	}
	
	
	//se leen las lineas del buffer
		 try {
			while((linea=bufferDeArchivoEntrada.readLine())!=null){ 
				
				longitud=linea.length();
				ch=new Character[longitud];
				result = new char[0];
				estado=0;
				//se lee caracter a caracter de la linea
				for(int i=0;i<=longitud;i++)
				{
					if(i==longitud){
						leido = " ";
					}else{
						ch[i]=linea.charAt(i);
						leido = String.valueOf(ch[i]);
						}
				
				//se crea un nuevo objeto de tipo Algoritmo y se pasan por referencia el caracter y las cadenas donde se almacenara el resultado temporal y final
				 Automatas algoritmo = new Automatas();
				 algoritmo.Automatas(leido, result, totalresult,estado, tabla, contador);
				 result = algoritmo.getTempis();
				 totalresult = algoritmo.getResult();
				 estado = algoritmo.getEstado();
				 tabla = algoritmo.getTabla();
				 contador = algoritmo.getContador();
				 
				}
				//la cadena resultante se combierte a string
					total = String.valueOf(totalresult);
				//se colocan los saltos de linea			
					if(cont==0){
					texto = total+"\r\n";
					cont++;
					totalresult = new char[0];
					}else{
					texto = texto+total+ "\r\n";	
					cont++;
					totalresult = new char[0];
					}
				}			 
		 	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }  
		 //se envia la cadena resultante y la tabla a las variables correspondientes para que sean llamadas por la clase vista
		setTotalok(texto);
		setTabla(tabla);
		 
	 }

public Object[][] getTabla() {
	return tabla;
}

public void setTabla(Object[][] tabla) {
	this.tabla = tabla;
} 

 }
