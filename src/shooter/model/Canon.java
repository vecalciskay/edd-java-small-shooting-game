package shooter.model;

import java.awt.Graphics;
import java.beans.PropertyChangeSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shooter.framework.IDibujador;
import shooter.gui.CanonDibujador;

/**
 * 
 * @author Vladimir
 *
 */
public class Canon {

	public final static int MAX_GRADO = 180;
	public final static int MIN_GRADO = 0;
	public final static int INITIAL_GRADO = 90;
	public final static int STEP_GRADO = 3;
	public final static int STEP_DISPARO = 5;
	public final static int RADIUS_BASE = 60;
	public final static int RADIUS_CANON = 100;
	
	private IDibujador dibujador;
	
	private int gradoActual;
	private int x;
	private int y;
	
	private GameBoard padre;
	
	private static final Logger logger = LogManager.getRootLogger();
	
	public Canon(int base, int alto, GameBoard gameBoard) {
		gradoActual = INITIAL_GRADO;
		dibujador = new CanonDibujador(this);
		x = base / 2 - RADIUS_BASE;
		y = alto;
		padre = gameBoard;
	}
	
	public void dibujar(Graphics gc) {
		dibujador.dibujar(gc, x, y);
	}
	
	public void derecha() {
		if (gradoActual == MIN_GRADO)
			return;
		int gradoAnterior = gradoActual;
		gradoActual -= STEP_GRADO;
		logger.debug("Canon se mueve a la derecha al angulo " + gradoActual);
		padre.cambioVista(gradoAnterior, gradoActual);
	}
	
	public void izquierda() {
		
		if (gradoActual == MAX_GRADO)
			return;
		int gradoAnterior = gradoActual;
		gradoActual += STEP_GRADO;
		logger.debug("Canon se mueve a la izquierda al angulo " + gradoActual);
		padre.cambioVista(gradoAnterior, gradoActual);
	}
	
	public void disparar() {
		int origenX = x + RADIUS_BASE;
		int origenY = y;
		Bullet bala = new Bullet(origenX, origenY, RADIUS_CANON, gradoActual, STEP_DISPARO); 
		logger.info("Nueva bala creada en angulo " + gradoActual);
		padre.nuevoDisparo(bala);
	}

	public int getGradoActual() {
		return gradoActual;
	}

	public void setGradoActual(int gradoActual) {
		this.gradoActual = gradoActual;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getGradoActualRadian() {
		return (double)gradoActual * Math.PI / 180.0;
	}
	
	
}
