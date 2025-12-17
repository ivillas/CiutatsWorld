package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;

import controlador.CiutatDAO;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaAddCiutat {

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtNom;
	private JTextField txtPoblacio;
	private String code;
	
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
		
		JComboBox<String> cmbPaisos = new JComboBox<>();
		cmbPaisos.setBounds(204, 180, 119, 22);
		frame.getContentPane().add(cmbPaisos);
		
		List<String> paisos = null;
		try {
			paisos = CiutatDAO.llistaPaisos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String pais : paisos) {
			cmbPaisos.addItem(pais);
		}
		
		try {
		 code = CiutatDAO.codiPais(cmbPaisos.getSelectedItem().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JComboBox cmbDistricte = new JComboBox();
		cmbDistricte.setBounds(204, 220, 119, 22);
		frame.getContentPane().add(cmbDistricte);
	
		
        cmbPaisos.addActionListener(e -> {
            cmbDistricte.removeAllItems(); // Netejar els districtes abans de carregar

            try {
            	code = CiutatDAO.codiPais(cmbPaisos.getSelectedItem().toString());
            	            	
                List<String> districtes = CiutatDAO.llistaDistrictes(code); 
                for (String districte : districtes) {
                    cmbDistricte.addItem(districte); 
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Mostrar error si hi ha una excepció
            }
        });
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean guardarOk =true;
				String ciutat = txtNom.getText();
				try {
					if(CiutatDAO.existeixCiutat(ciutat, code)) {
						JOptionPane.showMessageDialog(frame,"La ciutat ja existeix en aquest pais");
						txtNom.requestFocus();
						txtNom.selectAll();
						guardarOk = false;
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				String contingut = txtPoblacio.getText();
				if(!isNumeric(contingut)) {
					 JOptionPane.showMessageDialog(frame,"La població nomes pot contindre un nombre enter positiu");
					 txtPoblacio.requestFocus();
					 txtPoblacio.selectAll();
					 guardarOk = false;
				}
				long poblacio = Long.parseLong(txtPoblacio.getText());
			if(poblacio < 10000 || poblacio > 5000000000L) {
				 JOptionPane.showMessageDialog(frame,"La poblacio ha de ser numeric i ha d'estar entre 10.000 i 5.000.000.000 habitans");
				 txtPoblacio.requestFocus();
				 txtPoblacio.selectAll();
				 guardarOk = false;
			}
			
			String district = cmbDistricte.getSelectedItem().toString();
				
			try {
				if(!CiutatDAO.existeixCiutat(ciutat, code)) {
				CiutatDAO.addCiutat(ciutat, code,district , poblacio);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(guardarOk) {
    		new Aplicacio().mostrar();
    		frame.dispose();
			}
			}
			
			

			private static boolean isNumeric(String str) {
			    return str.matches("\\d+"); 
			}
			
	
			
		});
		btnGuardar.setBounds(107, 411, 89, 23);
		frame.getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		new Aplicacio().mostrar();
        		frame.dispose();
			}
		});
		btnCancelar.setBounds(280, 411, 89, 23);
		frame.getContentPane().add(btnCancelar);
	}
	
    /**
     * Mostre la finestra.
     */
    public void mostrar() {
        frame.setVisible(true);
    }
}
