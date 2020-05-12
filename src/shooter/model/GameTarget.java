package shooter.model;

import java.awt.Graphics;

import shooter.framework.IDibujador;

public abstract class GameTarget {
	
	protected IDibujador dibujador;

	protected int x;
	protected int y;
	protected boolean destruido;
	protected GameBoard padre;

	public abstract boolean estaDentro(int x0, int y0);
	
	public void dibujar(Graphics g) {
		dibujador.dibujar(g, x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public GameBoard getPadre() {
		return padre;
	}

	public void setPadre(GameBoard padre) {
		this.padre = padre;
	}

	protected void setDestruido(boolean b) {
		destruido = b;
	}
	
	public boolean isDestruido() {
		return destruido;
	}
}
