package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import Controller.Lector;
import Model.AnalizadorSintactico;
import Model.Tokens;

/**
 * Clase que permite cargar una ventana para realizar la comparacion entre el
 * analizador lexico y el sintactico
 * 
 * 
 */
public class Vista extends JFrame implements ActionListener {

	Container contenedor;
	JLabel labelTituloPrincipal;
	JLabel labelTituloPrincipal2;
	JLabel labelTitulo;
	JLabel labelSalida;
	JLabel labelTablaIn;
	JLabel labelTablaOut;
	JSeparator separador1;
	JTextArea areaDeTexto;
	JTextArea areaDeTextoSalida;
	JButton botonAbrir;
	JButton botonCompilar;
	JButton botonGuardar;
	JButton botonCargar;
	JButton botonAnalizar;
	JScrollPane scrollPaneArea;
	JScrollPane scrollPaneAreaSalida;
	JScrollPane scrollPaneTabla3;
	JScrollPane scrollPaneTabla4;
	JFileChooser fileChooser;
	String texto;
	String total;
	JTable table, table2, table3, table4;
	JPanel topPanel;
	JScrollPane scrollPaneTabla, scrollPaneTabla2;
	boolean textlisto = false;
	String resultado;
	String[] columnNames = { "Lexema", "Token", "Identificador" };
	String[] columnNames2 = { "Pila", "ae", "X", "a", "M[X,a]", "X->Y1,Y2..YK",
			"Salida" };
	String[] columnNames3 = { "" };
	Object[][] data;
	Object[][] data2, data3, data4, data5;
	Workbook libro;
	JTextField txt;
	String[][] datos, datos2;
	String[] nomcolumnas, asitotal;

	// Metodo get y set del atributo resultado, recibe el string que se mostrara
	// en la vista

	public void setresultado(String resultado) {
		this.resultado = resultado;

	}

	public String[][] getDatos() {
		return datos;
	}

	public void setDatos(String[][] datos) {
		this.datos = datos;
	}

	public Object[][] getData5() {
		return data5;
	}

	public void setData5(Object[][] data5) {
		this.data5 = data5;
	}

	public Object[][] getData4() {
		return data4;
	}

	public void setData4(Object[][] data4) {
		this.data4 = data4;
	}

	public String getresultado() {
		return resultado;

	}

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

	public class myTableModel3 extends DefaultTableModel {

		myTableModel3() {

			super(getData4(), columnNames2);

		}

		public boolean isCellEditable(int row, int cols) {
			return false;
			// It will make the cells of Column-1 not Editable

		}
	}

	public class myTableModel4 extends DefaultTableModel {

		myTableModel4() {

			super(getData5(), columnNames3);

		}

		public boolean isCellEditable(int row, int cols) {
			return false;
			// It will make the cells of Column-1 not Editable

		}
	}

	public class myTableModel5 extends DefaultTableModel {

		myTableModel5() {

			super(getDatos(), nomcolumnas);

		}

		public boolean isCellEditable(int row, int cols) {
			return false;
			// It will make the cells of Column-1 not Editable

		}
	}

	TableCellRenderer render = new TableCellRenderer() {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			// Si values es nulo dara problemas de renderizado, por lo tanto se
			// pone como vacio
			JLabel lbl = new JLabel(value == null ? "" : value.toString());
			if (row == 0)
				lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setOpaque(true);
			lbl.setBackground(Color.getHSBColor(173, 20, 77));
			if (row == 1)
				lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setOpaque(true);
			lbl.setBackground(Color.getHSBColor(173, 20, 77));
			if (row == 2)
				lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setOpaque(true);
			lbl.setBackground(Color.getHSBColor(173, 20, 77));
			if (row == 3)
				lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setOpaque(true);
			lbl.setBackground(Color.getHSBColor(173, 20, 77));
			if (row == 4)
				lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setOpaque(true);
			lbl.setBackground(Color.getHSBColor(173, 20, 77));

			return lbl;
		}
	};

	// Constructor de la clase

	public Vista() {
		Tokens datos = new Tokens();
		data = datos.verificar();
		TableModel model = new myTableModel();
		table = new JTable();
		table.setRowHeight(25);
		table.setModel(model);
		table.setFillsViewportHeight(true);
		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(400, 155, 350, 200);
		scrollPaneTabla.setViewportView(table);

		TableModel model2 = new myTableModel2();
		table2 = new JTable();
		table2.setRowHeight(25);
		table2.setModel(model2);
		table2.setFillsViewportHeight(true);
		scrollPaneTabla2 = new JScrollPane();
		scrollPaneTabla2.setBounds(400, 405, 350, 200);
		scrollPaneTabla2.setViewportView(table2);

		TableModel model3 = new myTableModel3();
		table3 = new JTable();
		table3.setRowHeight(25);
		table3.setModel(model3);
		table3.setFillsViewportHeight(true);
		table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table3.doLayout();
		scrollPaneTabla3 = new JScrollPane();
		scrollPaneTabla3.setBounds(800, 405, 460, 200);
		scrollPaneTabla3.setViewportView(table3);

		TableModel model4 = new myTableModel4();
		table4 = new JTable();
		table4.setRowHeight(25);
		table4.setModel(model4);
		table4.setFillsViewportHeight(true);
		table4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneTabla4 = new JScrollPane();
		scrollPaneTabla4.setBounds(800, 155, 460, 170);
		scrollPaneTabla4.setViewportView(table4);

		contenedor = getContentPane();
		contenedor.setLayout(null);

		fileChooser = new JFileChooser();

		// Se crean los Labels

		labelTituloPrincipal = new JLabel();
		labelTituloPrincipal.setText("Analizador Lexico");
		labelTituloPrincipal.setBounds(360, 20, 180, 50);

		labelTituloPrincipal2 = new JLabel();
		labelTituloPrincipal2.setText("Analizador Sintactico");
		labelTituloPrincipal2.setBounds(930, 20, 180, 50);

		labelTitulo = new JLabel();
		labelTitulo.setText("Origen de datos");
		labelTitulo.setBounds(20, 130, 180, 23);

		labelSalida = new JLabel();
		labelSalida.setText("Salida de datos");
		labelSalida.setBounds(20, 380, 300, 23);

		labelTablaIn = new JLabel();
		labelTablaIn.setText("Tabla de simbolos inicial");
		labelTablaIn.setBounds(400, 130, 300, 23);

		labelTablaOut = new JLabel();
		labelTablaOut.setText("Tabla de simbolos final");
		labelTablaOut.setBounds(400, 380, 300, 23);

		areaDeTexto = new JTextArea();
		areaDeTexto.setLineWrap(true);
		areaDeTexto.setWrapStyleWord(true);
		areaDeTexto.setEditable(false);
		scrollPaneArea = new JScrollPane();
		scrollPaneArea.setBounds(20, 150, 350, 200);
		scrollPaneArea.setViewportView(areaDeTexto);

		areaDeTextoSalida = new JTextArea();
		areaDeTextoSalida.setLineWrap(true);
		areaDeTextoSalida.setWrapStyleWord(true);
		areaDeTextoSalida.setEditable(false);
		scrollPaneAreaSalida = new JScrollPane();
		scrollPaneAreaSalida.setBounds(20, 400, 350, 200);
		scrollPaneAreaSalida.setViewportView(areaDeTextoSalida);

		// Se crean los botones
		botonAbrir = new JButton();
		botonAbrir.setText("Abrir y Analizar");
		botonAbrir.setBounds(250, 80, 160, 23);
		botonAbrir.addActionListener(this);

		botonGuardar = new JButton();
		botonGuardar.setText("Guardar");
		botonGuardar.setBounds(450, 80, 80, 23);
		botonGuardar.addActionListener(this);

		botonCargar = new JButton();
		botonCargar.setText("Cargar Tabla");
		botonCargar.setBounds(850, 80, 160, 23);
		botonCargar.addActionListener(this);

		botonAnalizar = new JButton();
		botonAnalizar.setText("Analizar");
		botonAnalizar.setBounds(1050, 80, 80, 23);
		botonAnalizar.addActionListener(this);

		// se agregan los componentes al contenedor
		contenedor.add(labelTituloPrincipal);
		contenedor.add(labelTituloPrincipal2);
		contenedor.add(labelTitulo);
		contenedor.add(labelSalida);
		contenedor.add(labelTablaIn);
		contenedor.add(labelTablaOut);
		contenedor.add(scrollPaneArea);
		contenedor.add(scrollPaneAreaSalida);
		contenedor.add(botonAbrir);
		contenedor.add(botonGuardar);
		contenedor.add(botonCargar);
		contenedor.add(botonAnalizar);
		contenedor.add(scrollPaneTabla);
		contenedor.add(scrollPaneTabla2);
		contenedor.add(scrollPaneTabla3);
		contenedor.add(scrollPaneTabla4);

		// Se agrega un titulo
		setTitle("COMPILADORES");
		// Tamaño de la ventana
		setSize(1300, 700);
		// Se centra la pantalla
		setLocationRelativeTo(null);

	}

	// Metodo para realizar las acciones de los botones

	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource() == botonAbrir) {
			try {
				String resul = abrirArchivo();

				areaDeTextoSalida.setText(resul);
				textlisto = true;
				areaDeTexto.setText(texto);

				TableModel model2 = new myTableModel2();
				table2.setModel(model2);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (evento.getSource() == botonGuardar) {
			String edittext = areaDeTexto.getText();
			int cantidad = edittext.length();

			if (textlisto)
				guardarArchivo();
			else if (cantidad != 0)
				guardarArchivo();
			else {
				JOptionPane.showMessageDialog(null,
						"\nNo hay informacion para guardar", "ADVERTENCIA!!!",
						JOptionPane.WARNING_MESSAGE);
			}
			textlisto = false;
		}

		if (evento.getSource() == botonCargar)
			CargarTabla();
		if (evento.getSource() == botonAnalizar) {
			AnalizadorSintactico asintactico = new AnalizadorSintactico(asitotal, getDatos2());
			setData4(asintactico.getSalida1());

			TableModel model3 = new myTableModel3();
			table3.setModel(model3);
		}
	}

	// Metodo para abrir el documento de texto
	public String abrirArchivo() throws IOException {

		String aux = "";
		texto = "";

		fileChooser.showOpenDialog(this);
		File abre = fileChooser.getSelectedFile();

		// se captura la ubicacion del archivo a leer
		if (abre != null) {
			// se envia la ubicacion del archivo a la clase Lector
			Lector lector = new Lector(abre);
			// Se recibe el retorno de la clase lector con el resultado de la
			// compilacion
			total = lector.getTotalok();
			setData2(lector.getTabla());
			asitotal = lector.getPasintac();

			// Se lee el archivo para mostrarse en la ventana grafica de ingreso
			// de datos
			FileReader archivos = new FileReader(abre);
			BufferedReader lee = new BufferedReader(archivos);
			while ((aux = lee.readLine()) != null) {
				texto += aux + "\r\n";
			}

			lee.close();
		} else {
			JOptionPane.showMessageDialog(null,
					"\nNo se ha encontrado el archivo", "ADVERTENCIA!!!",
					JOptionPane.WARNING_MESSAGE);
		}

		return total;
	}

	// Metodo para guardar la informacion de las ventanas en un txt
	private void guardarArchivo() {

		try {
			String nombre = "";
			JFileChooser file = new JFileChooser();
			file.showSaveDialog(this);
			File guarda = file.getSelectedFile();

			if (guarda != null) {
				nombre = file.getSelectedFile().getName();
				/*
				 * guardamos el archivo y le damos el formato directamente, si
				 * queremos que se guarde en formato doc lo definimos como .doc
				 */
				FileWriter save = new FileWriter(guarda + ".txt");
				save.write("Origen:\r\n" + areaDeTexto.getText() + "\r\n"
						+ "\r\n" + "Salida:\r\n" + areaDeTextoSalida.getText());

				save.close();
				JOptionPane.showMessageDialog(null,
						"El archivo se a guardado Exitosamente", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Su archivo no se ha guardado",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void CargarTabla() {

		fileChooser.showOpenDialog(this);
		File abre = fileChooser.getSelectedFile();

		// se captura la ubicacion del archivo a leer
		if (abre != null) {
			try {
				libro = Workbook.getWorkbook(fileChooser.getSelectedFile());
				cargarArchivo();
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"\nNo se ha encontrado el archivo", "ADVERTENCIA!!!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void cargarArchivo() {
		Sheet hoja1 = libro.getSheet(0);

		datos = new String[hoja1.getRows()][hoja1.getColumns()];
		nomcolumnas = new String[hoja1.getColumns()];

		datos2 = new String[hoja1.getRows()][hoja1.getColumns()];

		for (int fila = 0; fila < hoja1.getRows(); fila++) {
			for (int columna = 0; columna < hoja1.getColumns(); columna++) {
				datos2[fila][columna] = hoja1.getCell(columna, fila)
						.getContents();

				if (fila == 0)
					nomcolumnas[columna] = hoja1.getCell(columna, fila)
							.getContents();
				else
					datos[fila - 1][columna] = hoja1.getCell(columna, fila)
							.getContents();
			}
		}
		TableModel modelonuevo = new myTableModel5();
		table4.setModel(modelonuevo);
		table4.getColumnModel().getColumn(0).setCellRenderer(render);
		table4.setFillsViewportHeight(true);
		table4.getColumnModel().getColumn(0).setPreferredWidth(20);
		scrollPaneTabla4.setViewportView(table4);
	}

	public Object[][] getData2() {
		return data2;
	}

	public void setData2(Object[][] data2) {
		this.data2 = data2;
	}

	public String[][] getDatos2() {
		return datos2;
	}

	public void setDatos2(String[][] datos2) {
		this.datos2 = datos2;
	}

}
