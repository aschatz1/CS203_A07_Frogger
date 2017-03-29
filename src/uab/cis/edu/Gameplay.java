package uab.cis.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import java.awt.Rectangle;

import javax.swing.JPanel;

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

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	private boolean play = false;
	private boolean firstPlay = true;
	private boolean collision = false;

	private int score = 0;

	private Timer timer;
	private int delay = 8;

	private int frogX = 480;
	private int frogY = 450;

	private int car1posX = 10;
	private int car1Xdir = 1;

	private int car2posX = 790;
	private int car2Xdir = -1;

	public Gameplay(){
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g){

		//background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 700);

		//Start Message
		if(firstPlay){
			g.setColor(Color.BLUE);
			g.setFont(new Font("Garamond", Font.BOLD, 50));
			g.drawString("Press Enter to Play", 250, 100);
		}

		//Street
		g.setColor(Color.GRAY);
		g.fillRect(0, 275, 1000, 150);

		//Yellow Line
		g.setColor(Color.YELLOW);
		g.fillRect(0, 345, 1000, 10);

		//score
		if(play){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Garamond", Font.BOLD, 50));
			g.drawString("" + score, 950, 40);
		}

		//Frog
		g.setColor(Color.GREEN);
		g.fillRect(frogX, frogY, 30, 40);

		//First car
		g.setColor(Color.PINK);
		g.fillRect(car1posX, 280, 200, 55);

		//Second car
		g.setColor(Color.PINK);
		g.fillRect(car2posX, 360, 200, 55);


		//Lose the game
		if(new Rectangle(frogX, frogY, 30, 40).intersects(new Rectangle(car1posX, 280, 200, 55)) || 
				new Rectangle(frogX, frogY, 30, 40).intersects(new Rectangle(car2posX, 360, 200, 55))){
			play = false;
			collision = true;
			car1Xdir = 0;
			g.setColor(Color.RED);
			g.fillRect(frogX, frogY, 30, 40);
			g.setFont(new Font("Garamond", Font.BOLD, 50));
			g.drawString("Game Over!\n Your Score: " + score, 200, 100);
			g.drawString("Press Enter to Restart", 250, 550);
		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play){
			car1posX += car1Xdir;
			car2posX += car2Xdir;
		}

		if(car1posX < 0){
			car1Xdir = -car1Xdir;
		}

		if(car1posX > 800){
			car1Xdir = -car1Xdir;
		}

		if(car2posX < 0){
			car2Xdir = -car2Xdir;
		}

		if(car2posX > 800){
			car2Xdir = -car2Xdir;
		}

		repaint();

	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && !collision && !firstPlay){
			if(frogX >= 960){
				frogX = 960;
			}else{
				moveRight();
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT && !collision && !firstPlay){
			if(frogX < 20){
				frogX = 0;
			}else{
				moveLeft();
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN && !collision && !firstPlay){
			if(frogY >= 600){
				frogY = 600;
			}else{
				moveDown();
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_UP && !collision && !firstPlay){
			if(frogY <= 240){
				score += 1;

				if(car1Xdir<0){car1Xdir = car1Xdir - score;}
				else{car1Xdir = car1Xdir + score;}

				if(car2Xdir<0){car2Xdir = car2Xdir - score;}
				else{car2Xdir = car2Xdir + score;}

				frogY = 450;
				frogX = 480;
			}else{
				moveUp();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!play){
				firstPlay = false;
				play = true;
				collision = false;
				car1posX = 10;
				car1Xdir = 1;
				car2posX = 790;
				car2Xdir = -1;
				frogX = 480;
				frogY = 450;
				score = 0;				
				repaint();
			}
		}

	}

	public void moveRight() {
		play = true;
		frogX += 20;

	}
	public void moveLeft() {
		play = true;
		frogX -= 20;
	}
	public void moveDown() {
		play = true;
		frogY += 20;
	}

	public void moveUp() {
		play = true;
		frogY -= 20;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
