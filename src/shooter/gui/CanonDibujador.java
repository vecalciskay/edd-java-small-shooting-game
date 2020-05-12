package shooter.gui;

import java.awt.Color;
import java.awt.Graphics;

import shooter.framework.IDibujador;
import shooter.model.Canon;

public class CanonDibujador implements IDibujador {

	private Canon modelo;
	
	public CanonDibujador(Canon canon) {
		this.modelo = canon;
	}

	@Override
	public void dibujar(Graphics g, int x, int y) {
		g.setColor(Color.black);
		int canonX = (int)(Math.cos((double)modelo.getGradoActualRadian()) * (double)Canon.RADIUS_CANON);
		int canonY = (int)(Math.sin((double)modelo.getGradoActualRadian()) * (double)Canon.RADIUS_CANON);
		g.drawLine(modelo.getX() + Canon.RADIUS_BASE, modelo.getY(), 
				modelo.getX() + Canon.RADIUS_BASE + canonX, 
				modelo.getY() - canonY);
		
		g.fillArc(modelo.getX(), modelo.getY() - Canon.RADIUS_BASE, 
				Canon.RADIUS_BASE * 2, Canon.RADIUS_BASE * 2, 0, 180);
	}

}
