package shooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shooter.framework.IDibujador;
import shooter.gui.BulletDibujador;

public class Bullet {
	
	public static final int ANCHO_BULLET = 10;
	private static int ID_GENERATOR = 1;

	private int origenX;
	private int origenY;
	private int modulo;
	private int grado;
	private int velocidad;
	private boolean eliminada;
	private int id;
	
	private IDibujador dibujador;
	
	private PropertyChangeSupport cambio;

	public static final Logger logger = LogManager.getRootLogger();
	
	public Bullet(int origenX, int origenY, int radiusCanon, int gradoActual, int stepDisparo) {
		this.origenX = origenX;
		this.origenY = origenY;
		this.modulo = radiusCanon;
		this.grado = gradoActual;
		this.velocidad = stepDisparo;
		this.id = ID_GENERATOR;
		ID_GENERATOR++;
		
		cambio = new PropertyChangeSupport(this);
		
		dibujador = new BulletDibujador(this);
	}

	public void mover() {
		
		int oldValue = this.modulo;
		this.modulo += this.velocidad;
		Point ubicacion = getUbicacion();
		
		if (ubicacion.getX() < 0 || 
				ubicacion.getY() < 0 || 
				ubicacion.getX() > GameBoard.ANCHO_BOARD) {
			eliminada = true;
			logger.info("Bala eliminada porque (x,y): (" + ubicacion.getX() + "," + ubicacion.getY() + ")");
			cambio.firePropertyChange("Perdida", 1, 0);
		}
		else {
			cambio.firePropertyChange("Modulo", oldValue, this.modulo);			
		}
	}
	
	public void dibujar(Graphics g) {
		Point ubicacionBala = getUbicacion();
		dibujador.dibujar(g, ubicacionBala.x, ubicacionBala.y);
	}

	public Point getUbicacion() {
		double radianes = (double)grado * Math.PI / 180.0;
		int x = origenX + (int)((double)this.modulo * Math.cos(radianes));
		int y = origenY - (int)((double)this.modulo * Math.sin(radianes));
		
		return new Point(x,y);
	}

	public boolean estaEnPanel() {
		return !eliminada;
	}

	public void observadorMovimiento(GameBoard gameBoard) {
		cambio.addPropertyChangeListener(gameBoard);
	}

	public int getOrigenX() {
		return origenX;
	}

	public int getOrigenY() {
		return origenY;
	}

	public int getGrado() {
		return grado;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public boolean isEliminada() {
		return eliminada;
	}
	
	public void setEliminada(boolean b) {
		eliminada = b;
	}

	public int getId() {
		return id;
	}
}
