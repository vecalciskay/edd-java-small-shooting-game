package shooter.gui;

import java.awt.Color;
import java.awt.Graphics;

import shooter.framework.IDibujador;
import shooter.model.GameBoard;
import shooter.model.TargetSquare;

public class TargetSquareDibujador implements IDibujador {

	private TargetSquare modelo;
	
	public TargetSquareDibujador(TargetSquare m) {
		modelo = m;
	}
	
	@Override
	public void dibujar(Graphics g, int x, int y) {
		if (!modelo.isDestruido()) {
			g.setColor(Color.blue);
			g.fillRect(modelo.getX(), modelo.getY(), TargetSquare.ANCHO, TargetSquare.ANCHO);
		}
	}

}
