package main;

import java.awt.*;
import java.awt.image.BufferedImage;

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
						e.printStackTrace();
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
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		frameLoop.start();
	}
	
	private BufferedImage image = new BufferedImage(Game.DISPLAY_WIDTH,Game.DISPLAY_HEIGHT,BufferedImage.TYPE_INT_ARGB);
	
	public void paintComponent(Graphics g) {
		game.draw(image.getGraphics());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
}
