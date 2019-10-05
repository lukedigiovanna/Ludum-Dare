package main;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import game.*;
import game.Game.Screen;

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
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isMouseDown = true;
			}
			public void mouseReleased(MouseEvent e) {
				isMouseDown = false;
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				lastMouse = e;
			}
			
		});
		
		this.setFocusable(true);
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (game.getScreen()) {
				case MAIN:
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						game.init();
						game.setScreen(Screen.GAME);
					}
					break;
				case GAME:
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
						game.togglePause();
					break;
				case GAME_OVER:
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						game.setScreen(Screen.MAIN);
					break;
				}
			}
		});
		
	}
	
	private MouseEvent lastMouse = null;
	private boolean isMouseDown = false;
	
	public float getMouseXPercent() {
		if (lastMouse == null)
			return -1;
		else
			return (float)lastMouse.getX()/this.getWidth();
	}
	
	public float getMouseYPercent() {
		if (lastMouse == null)
			return -1;
		else
			return (float)lastMouse.getY()/this.getHeight();
	}
	
	public boolean isMouseDown() {
		return isMouseDown;
	}
	
	private BufferedImage image = new BufferedImage(Game.DISPLAY_WIDTH,Game.DISPLAY_HEIGHT,BufferedImage.TYPE_INT_ARGB);
	
	public void paintComponent(Graphics g) {
		game.draw(image.getGraphics());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
}
