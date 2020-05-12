package shooter.model;

import shooter.gui.TargetSquareDibujador;

public class TargetSquare extends GameTarget {
	
	public final static int ANCHO = 30;


	public TargetSquare(int x0, int y0, GameBoard gameBoard) {
		this.x = x0;
		this.y = y0;
		dibujador = new TargetSquareDibujador(this);
		padre = gameBoard;
	}


	public boolean estaDentro(int x0, int y0) {
		if (this.x < x0 && 
				(this.x + ANCHO) > x0 &&
				this.y < y0 &&
				(this.y + ANCHO) > y0)
			return true;
		return false;
	}
}
