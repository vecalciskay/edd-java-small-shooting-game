package shooter.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import shooter.framework.IDibujador;
import shooter.model.Canon;

public class CanonDibujador implements IDibujador {

	private Canon modelo;
	
	public CanonDibujador(Canon canon) {
		this.modelo = canon;
	}

	@Override
	public void dibujar(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D)g; 
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.black);
		int canonX = (int)(Math.cos((double)modelo.getGradoActualRadian()) * (double)Canon.RADIUS_CANON);
		int canonY = (int)(Math.sin((double)modelo.getGradoActualRadian()) * (double)Canon.RADIUS_CANON);
		
		g2.setStroke(new BasicStroke(5));
		g2.drawLine(modelo.getX() + Canon.RADIUS_BASE, modelo.getY(), 
				modelo.getX() + Canon.RADIUS_BASE + canonX, 
				modelo.getY() - canonY);
		
		g2.fillArc(modelo.getX(), modelo.getY() - Canon.RADIUS_BASE, 
				Canon.RADIUS_BASE * 2, Canon.RADIUS_BASE * 2, 0, 180);
	}

}
