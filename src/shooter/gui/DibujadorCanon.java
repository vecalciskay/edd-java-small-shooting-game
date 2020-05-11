package shooter.gui;

import java.awt.Graphics;

import shooter.framework.IDibujador;
import shooter.model.Canon;

public class DibujadorCanon implements IDibujador {

	private Canon modelo;
	
	public DibujadorCanon(Canon canon) {
		this.modelo = canon;
	}

	@Override
	public void dibujar(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
