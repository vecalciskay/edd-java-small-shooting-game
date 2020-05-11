package shooter.model;

public class TargetSquare extends GameTarget {
	
	private final static int ANCHO = 30;

	private int x;
	private int y;

	public TargetSquare(int x0, int y0) {
		this.x = x0;
		this.y = y0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
