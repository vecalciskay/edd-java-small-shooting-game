package shooter.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ShooterFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		
		this.setJMenuBar(menubar);
	}

	protected void menuArchivo_Nuevo() {
		// TODO Auto-generated method stub
		
	}
}
