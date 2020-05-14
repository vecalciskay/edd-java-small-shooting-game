package shooter.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import shooter.framework.IDibujador;
import shooter.model.Bullet;

public class BulletDibujador implements IDibujador {

	private Bullet modelo;
	
	public BulletDibujador(Bullet b) {
		modelo = b;
	}
	@Override
	public void dibujar(Graphics g, int x, int y) {		
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.red);
		g2.fillOval(x, y, Bullet.ANCHO_BULLET, Bullet.ANCHO_BULLET);
	}

}
