package View;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame implements ActionListener {

	private JPanel contentPane;	
	JButton btnAnalizadorSintactico;
	JButton btnAnalizadorLexico;

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 187, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		btnAnalizadorSintactico = new JButton("Analizador Sintactico");
		btnAnalizadorSintactico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AnalizadorSintactico tablero = new AnalizadorSintactico();
				tablero.setLocationRelativeTo(null);
				tablero.setResizable(false);
				tablero.setVisible(true);
			}
		});
		
		btnAnalizadorLexico = new JButton("Analizador Lexico");
		btnAnalizadorLexico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AnalizadorLexico tablero = new AnalizadorLexico();
				tablero.setLocationRelativeTo(null);
				tablero.setResizable(false);
				tablero.setVisible(true);
			}
		});
		contentPane.add(btnAnalizadorLexico);
		contentPane.add(btnAnalizadorSintactico);
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == btnAnalizadorSintactico) {
			
		}		
	}

}
