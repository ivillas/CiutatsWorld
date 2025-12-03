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

public class VistaLlista {

    private JFrame frame;
    private JTable tablaCiutats; 
    private DefaultTableModel tableModel; 

    /**
     * Constructor.
     */
    
    public VistaLlista() {
        initialize();
        mostrarCiutats(); // Metode per mostrar les ciutats
    }

    /**
     * Inicializa el contingut.
     */
    
    private void initialize() {
        frame = new JFrame("Llistat de Ciutats");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JLabel lblCiutats = new JLabel("Ciutats:");
        frame.getContentPane().add(lblCiutats, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Llista de Ciutats"); 

        tablaCiutats = new JTable(tableModel); 
        JScrollPane scrollPane = new JScrollPane(tablaCiutats); 
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Metode per a mostrar les ciutats.
     */
    
    private void mostrarCiutats() {
        try {
            List<String> ciutats = CiutatDAO.llistarCiutats();
            for (String ciutat : ciutats) {
                tableModel.addRow(new Object[]{ciutat}); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al carregar les ciutats.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mostre la finestra.
     */
    public void mostrar() {
        frame.setVisible(true);
    }
}