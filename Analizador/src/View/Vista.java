package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.Descifrador;
import Model.Tokens;

/**
 * Compiladores
 * Clase de la vista que permite interactuar al usuario cargar archivo, analizarlo
 * y guardar un archivo de resultado
 */

public class Vista extends JFrame implements ActionListener 
	{
	
		
        Container contenedor;
		JLabel labelIntegrantes;
		JLabel labelTitulo;
		JLabel labelSalida;
		JLabel labelTablaIn;
		JLabel labelTablaOut;
		JTextArea areaDeTexto;
		JTextArea areaDeTextoSalida;
		JButton botonAbrir;
		JButton botonCompilar;
		JButton botonGuardar;
		JButton botonSalir;
		JScrollPane scrollPaneArea;
		JScrollPane scrollPaneAreaSalida;
		JFileChooser fileChooser;
		String texto;
		String total;
		JTable table, table2;
		JPanel topPanel ;
		JScrollPane scrollPaneTabla, scrollPaneTabla2;
		boolean textlisto= false;
		String resultado;
		String[] columnNames = {"Lexema", "Token", "Identificador"};
		Object [][] data;
		Object [][] data2, data3;
	
	
		
	//	Metodo get y set del atributo resultado, recibe el string que se mostrara en la vista	

		public void setresultado(String resultado) {
			this.resultado = resultado;
			
		}
		
		public String getresultado() {
			return resultado;
			
		}
		
		public class myTableModel extends DefaultTableModel
        {
		myTableModel( )
         {
			
          super(data,columnNames);
           
          
         }
		
		
        public boolean isCellEditable(int row,int cols)
        {
        	
           return false;
//It will make the cells of Column-1 not Editable
                                                                                                
        }
   }
		
		
public class myTableModel2 extends DefaultTableModel
        {
	 
		myTableModel2( )
         {
			
          super(getData2(),columnNames);
           
          
                     }
        public boolean isCellEditable(int row,int cols)
         {
           return false;
//It will make the cells of Column-1 not Editable
                                                                                                
          }
   }

	//Constructor de la clase
	
		public Vista()
		{	
	        
			 Tokens datos = new Tokens();
			 data = datos.verificar();
			 TableModel model=new myTableModel();
			 table =new JTable( );
			 table.setRowHeight(25);
			 table.setModel(model);
			 table.setFillsViewportHeight(true);
			 //table.setBackground(Color.BLUE);
			 scrollPaneTabla = new JScrollPane();
			 scrollPaneTabla.setBounds(470, 100, 450, 250);
			 scrollPaneTabla.setViewportView(table);
			 
			 
				
			 TableModel model2=new myTableModel2();
			 table2 =new JTable( );
			 table2.setRowHeight(25);
			 table2.setModel(model2);
			 table2.setFillsViewportHeight(true);
			 //table2.setBackground(Color.RED);
			 scrollPaneTabla2 = new JScrollPane();
			 scrollPaneTabla2.setBounds(470, 400, 450, 250);
			 scrollPaneTabla2.setViewportView(table2);
			
			
			contenedor=getContentPane();
			contenedor.setLayout(null);
			contenedor.setBackground(Color.BLACK);
			
			fileChooser=new JFileChooser();
			
			//Se crean los Labels
			/*labelIntegrantes= new JLabel();
			labelTitulo.setText("Integrantes: Jose Manuel Barragan Guillermo Javier Romero");
			labelTitulo.setBounds(20, 30, 100, 23);*/
			
			labelTitulo= new JLabel();
			labelTitulo.setText("ARCHIVO FUENTE");
			labelTitulo.setBounds(120, 40, 300, 23);
			labelTitulo.setForeground(Color.WHITE);
			
			labelSalida= new JLabel();
			labelSalida.setText("ARCHIVO OBJETO");
			labelSalida.setBounds(120, 410, 300, 23);
			labelSalida.setForeground(Color.WHITE);
			
			labelTablaIn= new JLabel();
			labelTablaIn.setText("TABLA DE SIMBOLOS INICIAL");
			labelTablaIn.setBounds(590, 70, 300, 23);
			labelTablaIn.setForeground(Color.WHITE);
			
			labelTablaOut= new JLabel();
			labelTablaOut.setText("TABLA DE SIMBOLOS FINAL");
			labelTablaOut.setBounds(590, 370, 300, 23);
			labelTablaOut.setForeground(Color.WHITE);
			
			areaDeTexto = new JTextArea();
			areaDeTexto.setLineWrap(true);
			areaDeTexto.setWrapStyleWord(true);
			areaDeTexto.setEditable(false);
		   	scrollPaneArea = new JScrollPane();
			scrollPaneArea.setBounds(20, 70, 350, 250);
	        scrollPaneArea.setViewportView(areaDeTexto);
	        
	        areaDeTextoSalida = new JTextArea();
			areaDeTextoSalida.setLineWrap(true);
			areaDeTextoSalida.setWrapStyleWord(true);
			areaDeTextoSalida.setEditable(false);
		   	scrollPaneAreaSalida = new JScrollPane();
			scrollPaneAreaSalida.setBounds(20, 440, 350, 250);
	        scrollPaneAreaSalida.setViewportView(areaDeTextoSalida);
	        
			//	Se crean los botones
			botonAbrir= new JButton();
			botonAbrir.setText("ABRIR Y ANALIZAR");
			botonAbrir.setBounds(90, 340, 160, 23);
			botonAbrir.addActionListener(this);
						
			botonGuardar= new JButton();
			botonGuardar.setText("GUARDAR");
			botonGuardar.setBounds(580, 680, 90, 23);
			botonGuardar.addActionListener(this);
			
			botonSalir= new JButton();
			botonSalir.setText("SALIR");
			botonSalir.setBounds(750, 680, 90, 23);
			botonSalir.addActionListener(this);
			
			//se agregan los componentes al contenedor
			contenedor.add(labelTitulo);
			contenedor.add(labelSalida);
			contenedor.add(labelTablaIn);
			contenedor.add(labelTablaOut);
			contenedor.add(scrollPaneArea);
			contenedor.add(scrollPaneAreaSalida);
			contenedor.add(botonAbrir);
			contenedor.add(botonGuardar);
			contenedor.add(botonSalir);
			contenedor.add(scrollPaneTabla);
			contenedor.add(scrollPaneTabla2);
			
       		//Se agrega un titulo
			setTitle("COMPILADORES/INTERSEMESTRAL - ANALIZADOR LEXICO");
			//Tamaño de la ventana
			setSize(1000,800);
			//Se centra la pantalla
			setLocationRelativeTo(null);
			
	        
	        
		}
		
		//Metodo para realizar las acciones de los botones

		@Override
		public void actionPerformed(ActionEvent evento) {
			
			
			if (evento.getSource()==botonAbrir)
			{	
				try {
					
					String resul = abrirArchivo();
					
					areaDeTextoSalida.setText(resul);
					textlisto = true;
					areaDeTexto.setText(texto);
					
					 TableModel model2=new myTableModel2();
					 table2.setModel(model2);
					 
								 
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
			}
					
			if (evento.getSource()==botonGuardar)
			{
				String edittext = areaDeTexto.getText();
				int cantidad = edittext.length();
				
				if (textlisto){
					guardarArchivo();
					
				}else if(cantidad != 0){
					guardarArchivo();
					
				}else{
						
						JOptionPane.showMessageDialog(null,
						  		"\nNo hay informacion para guardar",
						  		"ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
					}
				textlisto = false;
			}
			
			if (evento.getSource()==botonSalir)
			{	
				System.exit(0);
			}
		}

		//Metodo para abrir el documento de texto
		public String abrirArchivo() throws IOException {
			
			String aux=""; 		
	 		texto="";
		
	 			
	    	fileChooser.showOpenDialog(this);
	    	File abre=fileChooser.getSelectedFile();

	    	//se captura la ubicacion del archivo a leer
	 			if(abre!=null)
	 			{ 	
	 				//se envia la ubicacion del archivo a la clase Lector
	 				Descifrador lector = new Descifrador(abre);
	 				//Se recibe el retorno de la clase lector con el resultado de la compilacion
	 				total = lector.getTotalok();
	 				setData2(lector.getTabla());
	 				
	 				
	 				
	 				//Se lee el archivo para mostrarse en la ventana grafica de ingreso de datos
	 				FileReader archivos=new FileReader(abre);
	 				BufferedReader lee=new BufferedReader(archivos);
	 				while((aux=lee.readLine())!=null)
	 					{
	 					 texto+= aux+ "\r\n";
	 					}

	 		  		lee.close();
	 			}else{ 	
	 				
	 				JOptionPane.showMessageDialog(null,
	 				  		"\nNo se ha encontrado el archivo",
	 				  		"ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
			}
	 			
	 			
	 		return total;
	 		
		}
		
	
//Metodo para guardar la informacion de las ventanas en un txt
		private void guardarArchivo() {

	 		try
	 		{
	 		 	
				String nombre = "";
				JFileChooser file=new JFileChooser();
				file.showSaveDialog(this);
				File guarda =file.getSelectedFile();
		
				if(guarda !=null)
				{
		 			nombre=file.getSelectedFile().getName();
		 			/*guardamos el archivo y le damos el formato directamente,
		 			 * si queremos que se guarde en formato doc lo definimos como .doc*/
		 			FileWriter  save=new FileWriter(guarda+".txt");
		 			save.write("Origen:\r\n"+areaDeTexto.getText()+"\r\n"+"\r\n"+"Salida:\r\n" +areaDeTextoSalida.getText());
		 			
		 			save.close();
		 			JOptionPane.showMessageDialog(null,
		 					"El archivo se a guardado Exitosamente",
		 					"Información",JOptionPane.INFORMATION_MESSAGE);}
				
		}
	 	   catch(IOException ex)
		   {
			 JOptionPane.showMessageDialog(null,
					 "Su archivo no se ha guardado",
					 "Advertencia",JOptionPane.WARNING_MESSAGE);
		   }
	 	   
	 		}

		public Object[][] getData2() {
			return data2;
		}

		public void setData2(Object[][] data2) {
			this.data2 = data2;
		}
		
		
		}