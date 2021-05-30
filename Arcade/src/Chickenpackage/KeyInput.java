package Chickenpackage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;

public class KeyInput extends KeyAdapter {
	Game game;
	
	public KeyInput(Game game) {
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		try {
			game.keyPressed(e);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
}
