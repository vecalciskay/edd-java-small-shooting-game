package shooter.gui;

import java.awt.Color;
import java.awt.Graphics;

import shooter.framework.IDibujador;
import shooter.model.Bullet;

public class BulletDibujador implements IDibujador {

	private Bullet modelo;
	
	public BulletDibujador(Bullet b) {
		modelo = b;
	}
	@Override
	public void dibujar(Graphics g, int x, int y) {		
		g.setColor(Color.red);
		g.fillOval(x, y, Bullet.ANCHO_BULLET, Bullet.ANCHO_BULLET);
	}

}
