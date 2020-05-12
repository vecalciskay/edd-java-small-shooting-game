package shooter.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import shooter.model.GameBoard;

public class ShooterFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ShooterPanel panel;
	
	public ShooterFrame() {
		init();
		
		this.pack();
		this.setVisible(true);
	}
	
	public void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menubar = new JMenuBar();
		
		// Menu archivo
		JMenu menu = new JMenu("Archivo");
		JMenuItem menuItem = new JMenuItem("Nuevo");
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuArchivo_Nuevo();
			}
		});
		menu.add(menuItem);
		
		menubar.add(menu);
		
		this.setJMenuBar(menubar);
		
		// Crear el modelo y el panel
		GameBoard board = GameBoard.getOrCreate();
		
		panel = new ShooterPanel();
		panel.setFocusable(true);
		board.observadorVista(panel);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(panel);
	}

	protected void menuArchivo_Nuevo() {
		GameBoard board = GameBoard.getOrCreate();
		board.reset();		
	}
}
