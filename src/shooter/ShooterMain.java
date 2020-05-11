package shooter;

import java.awt.EventQueue;

import shooter.gui.ShooterFrame;

public class ShooterMain {
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				ShooterFrame frame = new ShooterFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}
