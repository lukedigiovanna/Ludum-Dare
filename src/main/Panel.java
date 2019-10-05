package main;

import java.awt.*;
import javax.swing.*;
import game.*;

public class Panel extends JPanel {
	
	private Game game;
	
	//holds the game object
	public Panel() {
		game = new Game();
		Thread gameLoop = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(game.tickSpeed());
						game.gameLoop();
					} catch (Exception e) {
						
					}
				}
			}
		});
		gameLoop.start();
		Thread frameLoop = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(50);
						repaint();
					} catch (Exception e) {}
					
				}
			}
		});
		frameLoop.start();
	}
	
	public void paintComponent(Graphics g) {
		game.draw(g);
	}
}
