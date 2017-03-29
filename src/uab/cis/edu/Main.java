package uab.cis.edu;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * 
 * @author Andrew Schatz
 * 
 * This is a Frogger game.
 * 
 * Press the Enter key to begin.
 * Use the arrow keys to move the frog.
 * 
 * If you can get a score higher than 10 I will buy you a case of beer.
 *
 */

public class Main {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		Gameplay gamePlay = new Gameplay();
		obj.setBounds(0, 0, 1000, 700);
		obj.setTitle("Frogger");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);

	}

}
