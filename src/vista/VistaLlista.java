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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
        frame.getContentPane().setLayout(null);

        JLabel lblCiutats = new JLabel("Ciutats:");
        lblCiutats.setBounds(0, 0, 452, 14);
        frame.getContentPane().add(lblCiutats);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Llista de Ciutats"); 

        tablaCiutats = new JTable(tableModel); 
        JScrollPane scrollPane = new JScrollPane(tablaCiutats); 
        scrollPane.setBounds(0, 14, 434, 225);
        frame.getContentPane().add(scrollPane);
        
        JButton btntornar = new JButton("Tornar");
        btntornar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Aplicacio().mostrar();
        		frame.dispose();
        	}
        });
        btntornar.setBounds(171, 238, 89, 23);
        frame.getContentPane().add(btntornar);
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