package main;

import javax.swing.JFrame;

import game.Game;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame(Program.GAME_NAME);
		frame.setSize(Game.DISPLAY_WIDTH,Game.DISPLAY_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(100,100);
		frame.setContentPane(new Panel());
		frame.setVisible(true);
	}
}
