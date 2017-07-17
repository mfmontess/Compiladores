package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.lx.AnalizadorSintacticoController;
import Model.Archivo;

public class AnalizadorSintactico extends JFrame implements ActionListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4474157176216462060L;
	JFileChooser fileChooser;
	boolean textlisto = false;
	String[] columnNames = { "No Terminal", "Simbolo de Entrada" };
	Object[][] data;
	Object[][] data2;
	File abre;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem mntmAbrir;
	private javax.swing.JMenuItem mntmGuardar;
	private javax.swing.JMenuItem mntmSalir;
	private javax.swing.JMenuItem mntmReducir;
	private javax.swing.JMenuItem mntmTabla;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPane6;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTable tblSimbolos;
	private JList<String> lstGramaticas;
	private javax.swing.JTextField txtArchivoEntrada;
	private AnalizadorSintacticoController controlador;

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
	/**
	 * Create the frame.
	 */
	public AnalizadorSintactico() {
		initComponents();
	}
	
	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel2 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jScrollPane5 = new javax.swing.JScrollPane();
		lstGramaticas = new JList<String>();		
		jLabel6 = new javax.swing.JLabel();
		jScrollPane6 = new javax.swing.JScrollPane();
		tblSimbolos = new javax.swing.JTable();
		txtArchivoEntrada = new javax.swing.JTextField();
		txtArchivoEntrada.setEditable(false);
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		mntmAbrir = new javax.swing.JMenuItem();
		mntmGuardar = new javax.swing.JMenuItem();
		mntmSalir = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		mntmReducir = new javax.swing.JMenuItem();
		mntmTabla = new javax.swing.JMenuItem();
		fileChooser = new JFileChooser();
		TableModel model = new myTableModel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel5.setText("GRAMATICAS");

		jScrollPane5.setViewportView(lstGramaticas);
		
		jLabel6.setText("TABLA");

		tblSimbolos.setModel(model);
		jScrollPane6.setViewportView(tblSimbolos);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2Layout.setHorizontalGroup(
			jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addGap(129)
					.addComponent(jLabel5)
					.addPreferredGap(ComponentPlacement.RELATED, 396, Short.MAX_VALUE)
					.addComponent(jLabel6)
					.addGap(282))
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addGap(32)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtArchivoEntrada, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addComponent(jScrollPane5, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
							.addComponent(jScrollPane6, GroupLayout.PREFERRED_SIZE, 493, GroupLayout.PREFERRED_SIZE)))
					.addGap(37))
		);
		jPanel2Layout.setVerticalGroup(
			jPanel2Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addContainerGap(32, Short.MAX_VALUE)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jLabel5)
						.addComponent(jLabel6))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jScrollPane5, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
						.addComponent(jScrollPane6, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtArchivoEntrada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		jPanel2.setLayout(jPanel2Layout);

		jTabbedPane1.addTab("", jPanel2);

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

		jMenu2.setText("Analizador Sintactico");
		
		mntmReducir.setText("Reducir");
		mntmReducir.addActionListener(this);
		jMenu2.add(mntmReducir);

		mntmTabla.setText("Generar Tabla");
		mntmTabla.addActionListener(this);
		jMenu2.add(mntmTabla);

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

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == mntmAbrir){
			try{
				File gramaticas = abrirArchivo();
				controlador = new AnalizadorSintacticoController(gramaticas);
				importToList(controlador);
			} catch(Exception e){
				System.out.println(e.getMessage());
			}			
		}
		else if (evento.getSource() == mntmTabla) {
				if(lstGramaticas.getModel() != null){
					ConstruccionTablaSintactico();
					//Se construyo exitosamente
					textlisto = true;
				} else
					JOptionPane.showMessageDialog(null, "\nNo hay informacion para analizar", "ERROR!!!",
							JOptionPane.ERROR_MESSAGE);
		}
		else if (evento.getSource() == mntmGuardar) {
			String edittext = "";
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
		else if (evento.getSource() == mntmSalir)
			System.exit(0);		
	}
	
	// Metodo para abrir el documento de texto
		public static File abrirArchivo() throws Exception{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(null);
			File abre = fileChooser.getSelectedFile();// se captura la ubicacion del archivo a leer
			
			if (abre != null) {
				return abre;
			} else
				throw new Exception("No se ha encontrado el archivo");
		}
		
		public void importToList(AnalizadorSintacticoController controlador){
			DefaultListModel<String> modelo = new DefaultListModel<String>();
						
			for(int i = 0; i<controlador.getAnalizador().getGramaticas().size(); i++){
		        modelo.addElement(controlador.getAnalizador().getGramaticas().get(i).toString());
			}
			lstGramaticas.setModel(modelo);
		}
		
		public void ConstruccionTablaSintactico(){
			if (abre != null) {				
				//Cargar Tabla
				TableModel model2 = new myTableModel2();
				setData2(controlador.getAnalizador().getTabla());
				tblSimbolos.setModel(model2);
			} else {
				JOptionPane.showMessageDialog(null,
						"\nNo se ha encontrado un archivo a analizar, por favor abra un archivo primero por el men˙ Archivo -> Abrir",
						"ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
			}
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
							"");
					salida.generar();
					txtArchivoEntrada.setText(salida.getNombre());
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
