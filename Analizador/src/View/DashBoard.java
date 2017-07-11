package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.Descifrador;
import Model.Archivo;
import Model.Tokens;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Compiladores Clase de la vista que permite interactuar al usuario cargar
 * archivo, analizarlo y guardar un archivo de resultado
 */

public class DashBoard extends JFrame implements ActionListener {
	JFileChooser fileChooser;
	boolean textlisto = false;
	String[] columnNames = { "Lexema", "Token", "Identificador" };
	Object[][] data;
	Object[][] data2;
	File abre;

	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem mntmAbrir;
	private javax.swing.JMenuItem mntmGuardar;
	private javax.swing.JMenuItem mntmSalir;
	private javax.swing.JMenuItem mntmAnalizar;
	private javax.swing.JMenuItem mntmAnalisisSintactico;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPane6;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTable tblSimbolosIniciales;
	private javax.swing.JTable tblSimbolosFinales;
	private javax.swing.JTextArea txtEntrada;
	private javax.swing.JTextArea txtSalida;
	private javax.swing.JTextField txtArchivoEntrada;
	private javax.swing.JTextField txtArchivoSalida;

	public class myTableModel extends DefaultTableModel {
		myTableModel() {

			super(data, columnNames);

		}

		public boolean isCellEditable(int row, int cols) {

			return false;
			// It will make the cells of Column-1 not Editable

		}
	}

	public class myTableModel2 extends DefaultTableModel {

		myTableModel2() {

			super(getData2(), columnNames);

		}

		public boolean isCellEditable(int row, int cols) {
			return false;
			// It will make the cells of Column-1 not Editable

		}
	}

	// Constructor de la clase

	public DashBoard() {
		initComponents();
	}

	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		txtArchivoEntrada = new javax.swing.JTextField();
		txtArchivoEntrada.setEditable(false);
		jScrollPane1 = new javax.swing.JScrollPane();
		txtEntrada = new javax.swing.JTextArea();
		txtEntrada.setEditable(false);
		jScrollPane2 = new javax.swing.JScrollPane();
		tblSimbolosIniciales = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jScrollPane5 = new javax.swing.JScrollPane();
		txtSalida = new javax.swing.JTextArea();
		txtSalida.setEditable(false);
		jLabel6 = new javax.swing.JLabel();
		jScrollPane6 = new javax.swing.JScrollPane();
		tblSimbolosFinales = new javax.swing.JTable();
		txtArchivoSalida = new javax.swing.JTextField();
		txtArchivoSalida.setEditable(false);
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		mntmAbrir = new javax.swing.JMenuItem();
		mntmGuardar = new javax.swing.JMenuItem();
		mntmSalir = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		mntmAnalizar = new javax.swing.JMenuItem();
		mntmAnalisisSintactico = new javax.swing.JMenuItem();
		fileChooser = new JFileChooser();
		Tokens datos = new Tokens();
		data = datos.verificar();
		TableModel model = new myTableModel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		txtEntrada.setColumns(20);
		txtEntrada.setRows(5);
		jScrollPane1.setViewportView(txtEntrada);

		tblSimbolosIniciales.setModel(model);
		jScrollPane2.setViewportView(tblSimbolosIniciales);

		jLabel1.setText("ARCHIVO FUENTE");

		jLabel2.setText("TABLA DE SIMBOLOS");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(129).addComponent(jLabel1)
						.addPreferredGap(ComponentPlacement.RELATED, 363, Short.MAX_VALUE).addComponent(jLabel2)
						.addGap(170))
				.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup().addGap(32).addGroup(jPanel1Layout
						.createParallelGroup(Alignment.LEADING)
						.addComponent(txtArchivoEntrada, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 835,
								Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE).addComponent(
										jScrollPane2, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)))
						.addGap(37)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(32, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addComponent(jLabel2)
								.addComponent(jLabel1))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jScrollPane1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 234,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jScrollPane2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 234,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(txtArchivoEntrada,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		jPanel1.setLayout(jPanel1Layout);

		jTabbedPane1.addTab("Antes", jPanel1);

		jLabel5.setText("ARCHIVO OBJETO");

		txtSalida.setColumns(20);
		txtSalida.setRows(5);
		jScrollPane5.setViewportView(txtSalida);

		jLabel6.setText("TABLA DE SIMBOLOS FINAL");

		tblSimbolosFinales.setModel(model);
		jScrollPane6.setViewportView(tblSimbolosFinales);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(129).addComponent(jLabel5)
						.addPreferredGap(ComponentPlacement.RELATED, 388, Short.MAX_VALUE).addComponent(jLabel6)
						.addGap(166))
				.addGroup(Alignment.LEADING, jPanel2Layout.createSequentialGroup().addGap(32).addGroup(jPanel2Layout
						.createParallelGroup(Alignment.LEADING)
						.addComponent(txtArchivoSalida, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 835,
								Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addComponent(jScrollPane5, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE).addComponent(
										jScrollPane6, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)))
						.addGap(37)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap(32, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING).addComponent(jLabel6)
								.addComponent(jLabel5))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jScrollPane5, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 234,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jScrollPane6, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 234,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(txtArchivoSalida,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		jPanel2.setLayout(jPanel2Layout);

		jTabbedPane1.addTab("Despues", jPanel2);

		jMenu1.setText("Archivo");

		mntmAbrir.setText("Abrir");
		mntmAbrir.addActionListener(this);
		jMenu1.add(mntmAbrir);

		mntmGuardar.setText("Guardar");
		mntmGuardar.addActionListener(this);
		jMenu1.add(mntmGuardar);

		mntmSalir.setText("Salir");
		mntmSalir.addActionListener(this);
		jMenu1.add(mntmSalir);

		jMenuBar1.add(jMenu1);

		jMenu2.setText("Analizador");
		
		mntmAnalizar.setText("Analizador Lexico");
		mntmAnalizar.addActionListener(this);
		jMenu2.add(mntmAnalizar);

		mntmAnalisisSintactico.setText("Analizador Sintactico");
		mntmAnalisisSintactico.addActionListener(this);
		jMenu2.add(mntmAnalisisSintactico);

		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane1));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane1));

		pack();
	}

	// Metodo para realizar las acciones de los botones

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == mntmAbrir) {
			try {

				String entrada = abrirArchivo();
				txtEntrada.setText(entrada);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (evento.getSource() == mntmAnalizar) {
			try {

				String resul = analisisLexico();

				txtSalida.setText(resul);
				textlisto = true;

				TableModel model2 = new myTableModel2();
				tblSimbolosFinales.setModel(model2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (evento.getSource() == mntmAnalisisSintactico) {
			try {

				String resul = analisisSintactico();

				txtSalida.setText(resul);
				textlisto = true;

				TableModel model2 = new myTableModel2();
				tblSimbolosFinales.setModel(model2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (evento.getSource() == mntmGuardar) {
			String edittext = txtEntrada.getText();
			int cantidad = edittext.length();

			if (textlisto) {
				guardarArchivo();

			} else if (cantidad != 0) {
				guardarArchivo();

			} else {

				JOptionPane.showMessageDialog(null, "\nNo hay informacion para guardar", "ADVERTENCIA!!!",
						JOptionPane.WARNING_MESSAGE);
			}
			textlisto = false;
		}

		if (evento.getSource() == mntmSalir)
			System.exit(0);
	}

	// Metodo para abrir el documento de texto
	public String abrirArchivo() throws IOException {

		String texto = "";

		fileChooser.showOpenDialog(this);
		abre = fileChooser.getSelectedFile();

		// se captura la ubicacion del archivo a leer
		if (abre != null) {
			// Se lee el archivo para mostrarse en la ventana grafica de ingreso
			// de datos
			Archivo insumo = new Archivo(abre);
			texto = insumo.getContenido().toString();
			txtArchivoEntrada.setText(insumo.getNombre());
		} else {
			JOptionPane.showMessageDialog(null, "\nNo se ha encontrado el archivo", "ADVERTENCIA!!!",
					JOptionPane.WARNING_MESSAGE);
		}
		return texto;
	}

	public String analisisLexico() throws IOException {
		String salida = "";

		// se captura la ubicacion del archivo a leer
		if (abre != null) {
			// se envia la ubicacion del archivo a la clase Lector
			Descifrador lector = new Descifrador(abre);
			// Se recibe el retorno de la clase lector con el resultado de la
			// compilacion
			salida = lector.getTotalok();
			setData2(lector.getTabla());

		} else {
			JOptionPane.showMessageDialog(null,
					"\nNo se ha encontrado un archivo a analizar, por favor abra un archivo primero por el men˙ Archivo -> Abrir",
					"ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
		}
		return salida;
	}
	
	public String analisisSintactico() throws IOException {
		String salida = "";

		// se captura la ubicacion del archivo a leer
		if (abre != null) {
			//analisis lexico
			salida = analisisLexico();
			txtSalida.setText(salida);
			textlisto = true;
		} else {
			JOptionPane.showMessageDialog(null,
					"\nNo se ha encontrado un archivo a analizar, por favor abra un archivo primero por el men˙ Archivo -> Abrir",
					"ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
		}
		return salida;
	}

	// Metodo para guardar la informacion de las ventanas en un txt
	private void guardarArchivo() {

		try {
			JFileChooser escogerArchivo = new JFileChooser();
			String path2 = System.getProperty("user.dir");
			escogerArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			escogerArchivo.setCurrentDirectory(new File(path2));
			int seleccionado = escogerArchivo.showSaveDialog(this);

			if (seleccionado == JFileChooser.APPROVE_OPTION) {

				Archivo salida = new Archivo(escogerArchivo.getSelectedFile().getAbsolutePath() + "\\salida.txt",
						txtSalida.getText());
				salida.generar();
				txtArchivoSalida.setText(salida.getNombre());
				JOptionPane.showMessageDialog(null, "El archivo se a guardado Exitosamente", "Informaci√≥n",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Su archivo no se ha guardado", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public Object[][] getData2() {
		return data2;
	}

	public void setData2(Object[][] data2) {
		this.data2 = data2;
	}
}