package shooter.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shooter.framework.IDibujador;
import shooter.model.GameBoard;

public class ShooterPanel extends JPanel implements PropertyChangeListener, KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getRootLogger(); 
	
	public ShooterPanel() {
		this.addKeyListener(this);
		logger.debug("Se crea el panel y se le asigna el keylistener");
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(GameBoard.ANCHO_BOARD, GameBoard.ALTO_BOARD);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		logger.debug("Evento key typed");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		logger.debug("Evento key pressed");
		GameBoard board = GameBoard.getOrCreate();
		int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        
	        case KeyEvent.VK_SPACE:
	            // handle space: shoot
	        	board.getMainCanon().disparar();
	            break;
	        case KeyEvent.VK_LEFT:
	            // handle left
	        	board.getMainCanon().izquierda();
	            break;
	        case KeyEvent.VK_RIGHT :
	            // handle right
	        	board.getMainCanon().derecha();
	            break;
	     }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		logger.debug("Evento key released");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		GameBoard board = GameBoard.getOrCreate();
		IDibujador dibujador = new GameBoardDibujador(board);
		dibujador.dibujar(g, 0, 0);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("Vista")) {
			repaint();
		}
	}
}
