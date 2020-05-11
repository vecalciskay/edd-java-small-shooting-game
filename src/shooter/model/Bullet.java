package shooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;

public class Bullet {
	
	private static final int ANCHO_BULLET = 10;

	private int origenX;
	private int origenY;
	private int modulo;
	private int grado;
	private int velocidad;
	
	private PropertyChangeSupport cambio;

	public Bullet(int origenX, int origenY, int radiusCanon, int gradoActual, int stepDisparo) {
		this.origenX = origenX;
		this.origenY = origenY;
		this.modulo = radiusCanon;
		this.grado = gradoActual;
		this.velocidad = stepDisparo;
		
		cambio = new PropertyChangeSupport(this);
	}

	public void mover() {
		int oldValue = this.modulo;
		this.modulo += this.velocidad;
		int x = origenX + (int)((double)this.modulo * Math.sin((double)grado));
		int y = origenY - (int)((double)this.modulo * Math.cos((double)grado));
		
		if (x < 0 || y < 0 || x > GameBoard.ANCHO_BOARD)
			cambio.firePropertyChange("perdida", 1, 0);
		else
			cambio.firePropertyChange("modulo", oldValue, this.modulo);
	}
	
	public void dibujar(Graphics g) {
		int x = origenX + (int)((double)this.modulo * Math.sin((double)grado));
		int y = origenY - (int)((double)this.modulo * Math.cos((double)grado));
		
		BufferedImage rsm = new BufferedImage(ANCHO_BULLET, ANCHO_BULLET, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rsm.createGraphics();
		g2d.setColor(Color.red);
		g2d.fillOval(x, y, ANCHO_BULLET, ANCHO_BULLET);
		g.drawImage(rsm, x, y, null);
	}
}
