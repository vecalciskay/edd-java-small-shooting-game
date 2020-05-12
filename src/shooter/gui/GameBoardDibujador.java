package shooter.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import shooter.framework.IDibujador;
import shooter.model.Bullet;
import shooter.model.GameBoard;
import shooter.model.GameTarget;

public class GameBoardDibujador implements IDibujador {

	private GameBoard modelo;
	
	private IDibujador dibujadorCanon;
	private IDibujador dibujadorTarget;
	
	public GameBoardDibujador(GameBoard board) {
		modelo = board;
	}
	
	@Override
	public void dibujar(Graphics g, int x, int y) {
		
		BufferedImage bgImage = new BufferedImage(
				GameBoard.ANCHO_BOARD,
				GameBoard.ALTO_BOARD, 
				BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = bgImage.createGraphics();
		
		// Dibujar balas
		for(Bullet bala : modelo.getBullets()) {
			bala.dibujar(g2);
		}
		
		// Dibujar Targets
		for(GameTarget target : modelo.getTargets()) {
			target.dibujar(g2);
		}
		
		modelo.getMainCanon().dibujar(g2);
				
		g.drawImage(bgImage, 0, 0, null);
	}

}
