package vista;

import java.awt.EventQueue;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controlador.CiutatDAO;

import java.awt.event.ActionListener;

public class Aplicacio {

	private JFrame frame;
	private JMenu menuMenu, menuAjuda;
	private JMenuItem itemLlistar, itemAddCiutat, itemEditCiutat, itemSortir;
	private JMenuItem itemAjuda, itemInfo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacio window = new Aplicacio();
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
	public Aplicacio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menubar = new JMenuBar();
		
		menuMenu = new JMenu("Menu");
		
		itemLlistar = new JMenuItem("Llistes Ciutats");
		itemLlistar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> ciutats = new ArrayList<>();
				try {
					ciutats = CiutatDAO.llistarCiutats();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				for(String ciutat : ciutats) {
					System.out.println(ciutat);
				}
								
				
			}
		});
		itemLlistar.setMnemonic(KeyEvent.VK_L);
		itemLlistar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.ALT_MASK));
		itemLlistar.setActionCommand("llistar");

		
		itemAddCiutat = new JMenuItem("Afegir Ciutat");
		itemAddCiutat.setMnemonic(KeyEvent.VK_A);
		itemAddCiutat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.ALT_MASK));
		itemAddCiutat.setActionCommand("addCiutat");
		itemAddCiutat.addActionListener(null);
		
		itemEditCiutat = new JMenuItem("Editar Ciutat");
		itemEditCiutat.setMnemonic(KeyEvent.VK_E);
		itemEditCiutat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		itemEditCiutat.setActionCommand("editarCiutat");
		itemEditCiutat.addActionListener(null);
		
		itemSortir = new JMenuItem("Sortir");
		itemSortir.setMnemonic(KeyEvent.VK_S);
		itemSortir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
		itemSortir.setActionCommand("sortir");
		itemSortir.addActionListener(null);
		
		
		menuMenu.add(itemLlistar);
		menuMenu.add(itemAddCiutat);
		menuMenu.add(itemEditCiutat);
		menuMenu.add(itemSortir);
		
		menuAjuda = new JMenu("Ajuda");
		
		
		itemAjuda = new JMenuItem("Ajuda");
		itemAjuda.setMnemonic(KeyEvent.VK_J);
		itemAjuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,ActionEvent.ALT_MASK));
		itemAjuda.setActionCommand("ajuda");
		itemAjuda.addActionListener(null);
		
		itemInfo = new JMenuItem("Informacio");
		itemInfo.setMnemonic(KeyEvent.VK_F);
		itemInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.ALT_MASK));
		itemInfo.setActionCommand("info");
		itemInfo.addActionListener(null);
		
		menuAjuda.add(itemAjuda);
		menuAjuda.add(itemInfo);
		
		menubar.add(menuMenu);
		menubar.add(menuAjuda);
		frame.setJMenuBar(menubar);
		
	}

}
