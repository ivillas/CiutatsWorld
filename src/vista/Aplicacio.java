package vista;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.ActionListener;

public class Aplicacio {

	private JFrame frame;
	private JMenu menuMenu, menuAjuda;
	private JMenuItem itemLlistar,itemLlistarNom, itemAddCiutat, itemEditCiutat, itemSortir;
	private JMenuItem itemAjuda, itemInfo;
	private String rutaImatge = "/imatges/world.jpg";

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
		frame.setBounds(900, 400, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
        // Panel personalitzar per la imatge de fons
        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource(rutaImatge));
                
                if (img != null) {
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();
                    g.drawImage(img, 0, 0, panelWidth, panelHeight, this);
                } else {
                    System.out.println("No s'ha pogut carregar l'imatge: " + rutaImatge);
                }
            }
        };
        
        // establim i agregem el panel
        panel.setLayout(new BorderLayout());
        frame.setContentPane(panel);
		JMenuBar menubar = new JMenuBar();
		menuMenu = new JMenu("Menu");		
		itemLlistar = new JMenuItem("Llistes Ciutats");
		itemLlistar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                new VistaLlista().mostrar();							
				frame.dispose();
			}
		});
		itemLlistar.setMnemonic(KeyEvent.VK_L);
		itemLlistar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.ALT_MASK));
		itemLlistar.setActionCommand("llistar");

		
		itemLlistarNom = new JMenuItem("Buscar Ciutats");
		itemLlistarNom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VistaBuscarCiutat().mostrar();							
				frame.dispose();
			}
		});
		itemLlistarNom.setMnemonic(KeyEvent.VK_N);
		itemLlistarNom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.ALT_MASK));
		itemLlistarNom.setActionCommand("buscar");
		
		
		itemAddCiutat = new JMenuItem("Afegir Ciutat");
		itemAddCiutat.setMnemonic(KeyEvent.VK_A);
		itemAddCiutat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.ALT_MASK));
		itemAddCiutat.setActionCommand("addCiutat");
		itemAddCiutat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VistaAddCiutat().mostrar();							
				frame.dispose();
			}
		});
		
		itemEditCiutat = new JMenuItem("Editar Ciutat");
		itemEditCiutat.setMnemonic(KeyEvent.VK_E);
		itemEditCiutat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		itemEditCiutat.setActionCommand("editarCiutat");
		itemEditCiutat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VistaEditCiutat().mostrar();							
				frame.dispose();
			}
		});
		
		itemSortir = new JMenuItem("Sortir");
		itemSortir.setMnemonic(KeyEvent.VK_S);
		itemSortir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
		itemSortir.setActionCommand("sortir");
		itemSortir.addActionListener(null);
		
		
		menuMenu.add(itemLlistar);
		menuMenu.add(itemLlistarNom);
		menuMenu.add(itemAddCiutat);
		menuMenu.add(itemEditCiutat);
		menuMenu.add(itemSortir);
		
		menuAjuda = new JMenu("Ajuda");
		
		
		itemAjuda = new JMenuItem("Javadoc");
		itemAjuda.setMnemonic(KeyEvent.VK_J);
		itemAjuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,ActionEvent.ALT_MASK));
		itemAjuda.setActionCommand("javadoc");
		itemAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 File htmlFile = new File("doc/index.html");
				 try {
					Desktop.getDesktop().browse(htmlFile.toURI());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		itemInfo = new JMenuItem("Informacio");
		itemInfo.setMnemonic(KeyEvent.VK_F);
		itemInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.ALT_MASK));
		itemInfo.setActionCommand("info");
		itemInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VistaInfo().mostrar();							
				frame.dispose();
			}
		});
		
		menuAjuda.add(itemAjuda);
		menuAjuda.add(itemInfo);
		
		menubar.add(menuMenu);
		menubar.add(menuAjuda);
		frame.setJMenuBar(menubar);
		
	}

    /**
     * Mostre la finestra.
     */
    public void mostrar() {
        frame.setVisible(true);
    }
	
}
