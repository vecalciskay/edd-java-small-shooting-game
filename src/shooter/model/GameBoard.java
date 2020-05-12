package shooter.model;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shooter.gui.ShooterPanel;
import shooter.util.Lista;

/**
 * El tablero es de 800x500 En la parte superior se colocan los targets de forma
 * horizontal uno tras de otro. Cada uno de 30 pixeles de ancho y alto y
 * separados por 30 pixeles
 * 
 * Listener es para las balas que se pierden o que se mueven
 */
public class GameBoard implements PropertyChangeListener {

	public static final int ANCHO_BOARD = 800;
	public static final int ALTO_BOARD = 500;
	private static GameBoard instancia;

	private Canon mainCanon;
	private Lista<Bullet> bullets;
	private Lista<GameTarget> targets;

	private PropertyChangeSupport cambios;

	private static final Logger logger = LogManager.getRootLogger();

	public static GameBoard getOrCreate() {
		if (instancia == null)
			instancia = new GameBoard();
		return instancia;
	}

	private GameBoard() {
		cambios = new PropertyChangeSupport(this);
		reset();
	}

	public void reset() {
		mainCanon = new Canon(ANCHO_BOARD, ALTO_BOARD, this);
		bullets = new Lista<Bullet>();

		targets = new Lista<GameTarget>();
		initTargets();
	}

	public void initTargets() {
		int x0 = 100;
		int y0 = 20;
		for (int i = 0; i < 10; i++) {
			GameTarget obj = new TargetSquare(x0, y0, this);
			targets.insertar(obj);
			x0 += 2 * TargetSquare.ANCHO;
		}
	}

	public Canon getMainCanon() {
		return mainCanon;
	}

	public void setMainCanon(Canon mainCanon) {
		this.mainCanon = mainCanon;
	}

	public Lista<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(Lista<Bullet> bullets) {
		this.bullets = bullets;
	}

	public Lista<GameTarget> getTargets() {
		return targets;
	}

	public void setTargets(Lista<GameTarget> targets) {
		this.targets = targets;
	}

	public void observadorVista(PropertyChangeListener panel) {
		this.cambios.addPropertyChangeListener(panel);
	}

	public void cambioVista(int gradoAnterior, int gradoActual) {
		cambios.firePropertyChange("Vista", gradoAnterior, gradoActual);
	}

	public void nuevoDisparo(Bullet bala) {

		bala.observadorMovimiento(this);

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				bullets.insertar(bala);
				while (!bala.isEliminada() && bala.estaEnPanel()) {
					bala.mover();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						;
					}
				}
			}
		});
		thread.start();
		// cambios.firePropertyChange("Disparo", bala, bala);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("Perdida")) {
			Bullet eliminarBullet = (Bullet) evt.getSource();
			try {
				bullets.eliminar(eliminarBullet);
				logger.info("Se elimino la bala " + eliminarBullet.getId() + " de la lista de balas");
				cambios.firePropertyChange("Vista", 1, 0);
			} catch (Exception q) {
				q.printStackTrace();
			}
		}
		if (evt.getPropertyName().equals("Modulo")) {
			try {
				checkBalaGolpeaTargets((Bullet) evt.getSource());
			} catch (Exception e) {
				logger.error("No pudo eliminar la bala que golpeo target");
			}
			cambios.firePropertyChange("Vista", 1, 0);
		}
	}

	private void checkBalaGolpeaTargets(Bullet source) throws Exception {

		boolean targetEliminado = false;

		for (GameTarget target : targets) {
			Point ubicacion = source.getUbicacion();
			if (target.estaDentro(ubicacion.x, ubicacion.y)) {
				target.setDestruido(true);
				source.setEliminada(true);
				bullets.eliminar(source);
				targetEliminado = true;
				logger.info("Se elimino la bala " + source.getId() + " porque golpeo un target");
			}
		}

		if (targetEliminado) {
			boolean termino = true;
			for (GameTarget target : targets) {
				if (!target.isDestruido()) {
					termino = false;
					break;
				}
			}
			if (termino) {
				logger.info("El juego termino");
			}
		}
	}
}
