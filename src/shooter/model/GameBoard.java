package shooter.model;

import shooter.util.Lista;

/**
 * El tablero es de 800x500 En la parte superior se colocan los targets de forma
 * horizontal uno tras de otro. Cada uno de 30 pixeles de ancho y alto y
 * separados por 30 pixeles
 */
public class GameBoard {

	public static final int ANCHO_BOARD = 800;
	public static final int ALTO_BOARD = 500;
	
	private Canon mainCanon;
	private Lista<Bullet> bullets;
	private Lista<GameTarget> targets;

	public GameBoard() {
		mainCanon = new Canon(ANCHO_BOARD, ALTO_BOARD);
		bullets = new Lista<Bullet>();

		targets = new Lista<GameTarget>();
		initTargets();
	}

	public void initTargets() {
		int x0 = 100;
		int y0 = 20;
		for (int i = 0; i < 20; i++) {
			GameTarget obj = new TargetSquare(x0,y0);
			targets.insertar(obj);
		}
	}

}
