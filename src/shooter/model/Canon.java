package shooter.model;

import java.awt.Graphics;
import java.beans.PropertyChangeSupport;

import shooter.framework.IDibujador;
import shooter.gui.DibujadorCanon;

public class Canon {

	private final static int MAX_GRADO = 180;
	private final static int MIN_GRADO = 0;
	private final static int INITIAL_GRADO = 90;
	private final static int STEP_GRADO = 3;
	
	private PropertyChangeSupport cambiosCanon;
	
	private IDibujador dibujador;
	
	private int gradoActual;
	
	public Canon() {
		gradoActual = INITIAL_GRADO;
		dibujador = new DibujadorCanon(this);
	}
	
	public void dibujar(Graphics gc, int x, int y) {
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

	public int getGradoActual() {
		return gradoActual;
	}

	public void setGradoActual(int gradoActual) {
		this.gradoActual = gradoActual;
	}
}
