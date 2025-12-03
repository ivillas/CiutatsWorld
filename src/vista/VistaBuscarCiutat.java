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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaBuscarCiutat {

    private JFrame frame;
    private JTable tablaCiutats; 
    private DefaultTableModel tableModel; 
    private JTextField txfCiutat;

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
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Escriu per buscar la ciutat:");
        lblNewLabel.setBounds(0, 11, 154, 14);
        frame.getContentPane().add(lblNewLabel);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Llista de Ciutats"); 

        tablaCiutats = new JTable(tableModel); 
        JScrollPane scrollPane = new JScrollPane(tablaCiutats); 
        scrollPane.setBounds(0, 28, 424, 222);
        frame.getContentPane().add(scrollPane);
        
        txfCiutat = new JTextField();
        txfCiutat.setBounds(148, 8, 86, 20);
        frame.getContentPane().add(txfCiutat);
        txfCiutat.setColumns(10);
        
        JButton btnBuscar = new JButton("New button");
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String cadena = txfCiutat.getText();
        		mostrarCiutats(cadena);
        	}
        });
        btnBuscar.setBounds(280, 7, 89, 23);
        frame.getContentPane().add(btnBuscar);
    }

    /**
     * Metode per a mostrar les ciutats.
     */
    
    private void mostrarCiutats(String cadena) {
        try {
            List<String> ciutats = CiutatDAO.llistarPerNom(cadena);
            for (String ciutat : ciutats) {
                tableModel.addRow(new Object[]{ciutat}); // Añadimos cada ciudad como una fila en la tabla
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