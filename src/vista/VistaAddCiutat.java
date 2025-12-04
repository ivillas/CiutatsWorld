package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTextField;

import controlador.CiutatDAO;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class VistaAddCiutat {

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtNom;
	private JTextField txtPoblacio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAddCiutat window = new VistaAddCiutat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaAddCiutat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(900, 400, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddCity = new JLabel("Afegeis una ciutat");
		lblAddCity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddCity.setBounds(147, 24, 176, 21);
		frame.getContentPane().add(lblAddCity);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(72, 100, 80, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(72, 140, 80, 14);
		frame.getContentPane().add(lblNom);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(72, 180, 80, 14);
		frame.getContentPane().add(lblPais);
		
		JLabel lblDistricte = new JLabel("Districte");
		lblDistricte.setBounds(72, 220, 80, 14);
		frame.getContentPane().add(lblDistricte);
		
		JLabel lblPoblacio = new JLabel("Poblacio");
		lblPoblacio.setBounds(72, 260, 80, 14);
		frame.getContentPane().add(lblPoblacio);
		
		txtId = new JTextField();
		txtId.setBounds(204, 100, 120, 20);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		txtId.setEditable(false);
		try {
			txtId.setText(CiutatDAO.ultimId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(204, 140, 119, 20);
		frame.getContentPane().add(txtNom);
		
		txtPoblacio = new JTextField();
		txtPoblacio.setColumns(10);
		txtPoblacio.setBounds(204, 260, 119, 20);
		frame.getContentPane().add(txtPoblacio);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(204, 180, 119, 22);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(204, 220, 119, 22);
		frame.getContentPane().add(comboBox_1);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(107, 411, 89, 23);
		frame.getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(280, 411, 89, 23);
		frame.getContentPane().add(btnCancelar);
	}
}
