package shooter.model;

import java.awt.Graphics;
import java.beans.PropertyChangeSupport;

import shooter.framework.IDibujador;
import shooter.gui.DibujadorCanon;

/**
 * 
 * @author Vladimir
 *
 */
public class Canon {

	private final static int MAX_GRADO = 180;
	private final static int MIN_GRADO = 0;
	private final static int INITIAL_GRADO = 90;
	private final static int STEP_GRADO = 3;
	private final static int STEP_DISPARO = 5;
	private final static int RADIUS_BASE = 60;
	private final static int RADIUS_CANON = 100;
	
	private PropertyChangeSupport cambiosCanon;
	
	private IDibujador dibujador;
	
	private int gradoActual;
	private int x;
	private int y;
	
	public Canon(int base, int alto) {
		gradoActual = INITIAL_GRADO;
		dibujador = new DibujadorCanon(this);
		x = base / 2 - RADIUS_BASE;
		y = alto;
	}
	
	public void dibujar(Graphics gc) {
		dibujador.dibujar(gc, x, y);
	}
	
	public void derecha() {
		if (gradoActual == MIN_GRADO)
			return;
		int gradoAnterior = gradoActual;
		gradoActual -= STEP_GRADO;
		cambiosCanon.firePropertyChange("canon", gradoAnterior, gradoActual);
	}
	
	public void izquierda() {
		if (gradoActual == MAX_GRADO)
			return;
		int gradoAnterior = gradoActual;
		gradoActual += STEP_GRADO;
		cambiosCanon.firePropertyChange("canon", gradoAnterior, gradoActual);
	}
	
	public void disparar() {
		int origenX = x + RADIUS_BASE;
		int origenY = y;
		Bullet bala = new Bullet(origenX, origenY, RADIUS_CANON, gradoActual, STEP_DISPARO); 
		
		cambiosCanon.firePropertyChange("disparo", bala, bala);
	}

	public int getGradoActual() {
		return gradoActual;
	}

	public void setGradoActual(int gradoActual) {
		this.gradoActual = gradoActual;
	}
}
