package vista;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;

import controlador.CiutatDAO;
import model.Ciutat;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaBuscarCiutat {

    private JFrame frame;
    private JTable tablaCiutats; 
    private DefaultTableModel tableModel; 
    private JTextField txfCiutat;
    private JButton btnTornarbtnNewButton;

    /**
     * Constructor.
     */
    
    public VistaBuscarCiutat() {
        initialize();
      
    }

    /**
     * Inicializa el contingut.
     */
    
    private void initialize() {
        frame = new JFrame("Llistat de Ciutats");
		frame.setBounds(900, 400, 500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Escriu per buscar la ciutat:");
        lblNewLabel.setBounds(10, 11, 163, 14);
        frame.getContentPane().add(lblNewLabel);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Ciutats trobades"); 
        tableModel.addColumn("Codi pais");

        
        tablaCiutats = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaCiutats); 
        scrollPane.setBounds(10, 48, 464, 368);
        frame.getContentPane().add(scrollPane);
        
        txfCiutat = new JTextField();
        txfCiutat.setBounds(173, 8, 86, 20);
        frame.getContentPane().add(txfCiutat);
        txfCiutat.setColumns(10);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String cadena = txfCiutat.getText();
        		mostrarCiutats(cadena);
        	}
        });
        btnBuscar.setBounds(280, 7, 89, 23);
        frame.getContentPane().add(btnBuscar);
        
        btnTornarbtnNewButton = new JButton("Tornar");
        btnTornarbtnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Aplicacio().mostrar();
        		frame.dispose();
        	}
        });
        btnTornarbtnNewButton.setBounds(195, 427, 89, 23);
        frame.getContentPane().add(btnTornarbtnNewButton);
    }

    /**
     * Metode per a mostrar les ciutats.
     */
    
    private void mostrarCiutats(String cadena) {
        tableModel.setRowCount(0);
        try {
            List<Ciutat> ciutats = CiutatDAO.llistarPerNom(cadena);
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