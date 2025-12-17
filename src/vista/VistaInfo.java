package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaInfo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaInfo window = new VistaInfo();
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
	public VistaInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbltitolInfo = new JLabel("Informació de l'aplicació");
		lbltitolInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltitolInfo.setBounds(125, 37, 215, 47);
		frame.getContentPane().add(lbltitolInfo);
		
		JLabel lblContextInfo = new JLabel("<html>Aplicació per poder llistar, crear, editar o <br> eliminar  ciutats de la base de dades World<html>");
		lblContextInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContextInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblContextInfo.setBounds(73, 91, 304, 79);
		frame.getContentPane().add(lblContextInfo);
		
		JLabel lblVersionInfo = new JLabel("Versió: 1.2");
		lblVersionInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVersionInfo.setBounds(186, 193, 75, 14);
		frame.getContentPane().add(lblVersionInfo);
		
		JButton btnTornar = new JButton("Tornar");
		btnTornar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Aplicacio().mostrar();
        		frame.dispose();
        	}
        });
		btnTornar.setBounds(172, 227, 89, 23);
		frame.getContentPane().add(btnTornar);
	}
	
    /**
     * Mostre la finestra.
     */
    public void mostrar() {
        frame.setVisible(true);
    }
}
