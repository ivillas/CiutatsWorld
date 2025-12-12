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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlador.CiutatDAO;
import model.Ciutat;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaEditCiutat {

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtNom;
	private JTextField txtPoblacio;
	private String code;
	private JTextField txfCiutat;
	  private JTable tablaCiutats; 
	    private DefaultTableModel tableModel; 
	    List<Ciutat> ciutats = new ArrayList<>();
	    Ciutat cityActual; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaEditCiutat window = new VistaEditCiutat();
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
	public VistaEditCiutat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Editar Ciutats");
		frame.setBounds(900, 400, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar Ciutat");
		lblBuscar.setBounds(83, 36, 89, 14);
		frame.getContentPane().add(lblBuscar);
		  tableModel = new DefaultTableModel();
	        tableModel.addColumn("Ciutats trobades"); 
	        tableModel.addColumn("Codi pais");
	        tablaCiutats = new JTable(tableModel); 
	        JScrollPane scrollPane = new JScrollPane(tablaCiutats); 
	      
	        scrollPane.setBounds(10, 64, 464, 145);
	        frame.getContentPane().add(scrollPane);

		
		
		
		
		txfCiutat = new JTextField();
		txfCiutat.setColumns(10);
		txfCiutat.setBounds(182, 33, 86, 20);
		frame.getContentPane().add(txfCiutat);
		

		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		
        		String cadena = txfCiutat.getText();
        		mostrarCiutats(cadena);
        	}
			
		});
		btnBuscar.setBounds(286, 32, 89, 23);
		frame.getContentPane().add(btnBuscar);
		

		
		JLabel lblAddCity = new JLabel("Edita o elimina una ciutat");
		lblAddCity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddCity.setBounds(135, 0, 240, 21);
		frame.getContentPane().add(lblAddCity);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(117, 220, 80, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(117, 260, 80, 14);
		frame.getContentPane().add(lblNom);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(117, 300, 80, 14);
		frame.getContentPane().add(lblPais);
		
		JLabel lblDistricte = new JLabel("Districte");
		lblDistricte.setBounds(117, 340, 80, 14);
		frame.getContentPane().add(lblDistricte);
		
		JLabel lblPoblacio = new JLabel("Poblacio");
		lblPoblacio.setBounds(117, 380, 80, 14);
		frame.getContentPane().add(lblPoblacio);
		
		txtId = new JTextField();
		txtId.setBounds(249, 220, 120, 20);
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
		txtNom.setBounds(249, 260, 119, 20);
		frame.getContentPane().add(txtNom);
		
		txtPoblacio = new JTextField();
		txtPoblacio.setColumns(10);
		txtPoblacio.setBounds(249, 380, 119, 20);
		frame.getContentPane().add(txtPoblacio);
		
		JComboBox<String> cmbPaisos = new JComboBox<>();
		cmbPaisos.setBounds(249, 300, 119, 22);
		frame.getContentPane().add(cmbPaisos);
		
		List<String> paisos = null;
		try {
			paisos = CiutatDAO.llistaPaisos();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(String pais : paisos) {
			cmbPaisos.addItem(pais);
		}
		
		try {
		 code = CiutatDAO.codiPais(cmbPaisos.getSelectedItem().toString());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		JComboBox cmbDistricte = new JComboBox();
		cmbDistricte.setBounds(249, 340, 119, 22);
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
                ex.printStackTrace(); 
            }
        });
		
        
        tablaCiutats.addMouseListener(new MouseAdapter()  {
            @Override
            public void mouseClicked(MouseEvent e) {
            	String nomPais = null;
                if (e.getSource() == tablaCiutats) {
                    int filaSeleccionada = tablaCiutats.getSelectedRow(); 
                                      if (filaSeleccionada != -1) { 
                        cityActual = ciutats.get(filaSeleccionada);
                        
                        txtId.setText(cityActual.getID()) ; 
                        txtNom.setText(cityActual.getName()); 
                         try {
								nomPais = CiutatDAO.nomPais(cityActual.getCountryCode());
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
                        cmbPaisos.setSelectedItem(nomPais); 
                        cmbDistricte.setSelectedItem(cityActual.getDistrict());
                        txtPoblacio.setText(String.valueOf(cityActual.getPopulation()));
                    }
                }
            }
        });
        
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean guardarOk =true;
				String ciutat = txtNom.getText();
				try {
					if(CiutatDAO.existeixCiutat(ciutat, code) && ciutat != toString().valueOf(cityActual.getName()) ) {
						JOptionPane.showMessageDialog(frame,"Ja existeix una ciutat amb aquest nom en aquest pais");
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
				CiutatDAO.editarCiutat(ciutat, code, district, poblacio);
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
		btnGuardar.setBounds(236, 427, 89, 23);
		frame.getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		new Aplicacio().mostrar();
        		frame.dispose();
			}
		});
		btnCancelar.setBounds(355, 427, 89, 23);
		frame.getContentPane().add(btnCancelar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(108, 427, 89, 23);
		frame.getContentPane().add(btnEliminar);
	}
	
    /**
     * Metode per a mostrar les ciutats.
     */
    
    private void mostrarCiutats(String cadena) {
        tableModel.setRowCount(0);
        try {
            ciutats = CiutatDAO.llistarPerNom(cadena);
            for (Ciutat ciutat : ciutats) {
                tableModel.addRow(new Object[]{ciutat.getName(), ciutat.getCountryCode()}); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al cargar las ciudades.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

	
    /**
     * Mostre la finestra.
     */
    public void mostrar() {
        frame.setVisible(true);
    }
}
