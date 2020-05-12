import java.util.*;
import java.awt.*;			
import java.awt.image.*;
import javax.swing.*;
import java.util.Random;


public class Gfx extends GameObject
{
	boolean hasTurn, isGameOver, isChoosingAi;
	ImageIcon bg, hudTurn, statusBar, hudBarWait, playAgain, cloud1, cloud2, cloud3, bg_front, bg_back;
	int c1X, c1Y, c2X, c2Y, c3X, c3Y, c1, c2, c3;

	Random r = new Random();


	public Gfx()
	{
		hasTurn = true;
		bg = new ImageIcon("bg.png");
		hudTurn = new ImageIcon("hudturn.png");
		hudBarWait = new ImageIcon("hudwait.png");
		statusBar = new ImageIcon("statusbar.png");
		playAgain = new ImageIcon("play_again_btn.png");
		bg_front = new ImageIcon("bg_front.png");
		bg_back = new ImageIcon("bg_back.png");
		cloud1 = new ImageIcon("cloud1.png");
		cloud2 = new ImageIcon("cloud2.png");
		cloud3 = new ImageIcon("cloud3.png");

		c1X = r.nextInt(750) + 1;
		c1Y = r.nextInt(100) + 1;

		c2X = r.nextInt(750) + 1;
		c2Y = r.nextInt(100) + 1;

		c3X = r.nextInt(750) + 1;
		c3Y = r.nextInt(100) + 1;

		c1 = r.nextInt(3) + 1;
		c2 = r.nextInt(3) + 1;
		c3 = r.nextInt(3) + 1;


		
	}

	public void randomizeC1()
	{
		c1Y = r.nextInt(100) + 1;
		c1 = r.nextInt(3) + 1;
	}

	public void randomizeC2()
	{
		c2Y = r.nextInt(100) + 1;
		c2 = r.nextInt(3) + 1;
	}

	public void randomizeC3()
	{
		c3Y = r.nextInt(100) + 1;
		c3 = r.nextInt(3) + 1;
	}



	public void paint(Graphics2D g)
	{
		
		//bg.paintIcon(this, g, 0, 0);
		bg_back.paintIcon(this, g, 0, 0);

		//Clouds
		cloud1.paintIcon(this, g, c1X, c1Y);
		cloud2.paintIcon(this, g, c2X, c2Y);
		cloud3.paintIcon(this, g, c3X, c3Y);



		bg_front.paintIcon(this, g, 0, 0);
		
		statusBar.paintIcon(this, g, 0, 0);

		if (hasTurn) {
			hudTurn.paintIcon(this, g, 0, 0);
			
		} else {
			hudBarWait.paintIcon(this, g, 0, 0);
		}

		if (isGameOver) {
			playAgain.paintIcon(this, g, 338, 250);
		}


	}

	public void run()
	{
		while (true) {
			MarioWindow.delay(20);

			if (c1X <= -500) {
				c1X = 800;
				randomizeC1();
			} else {
				c1X = c1X - c1;
			}

			if (c2X <= -500) {
				c2X = 825;
				randomizeC2();
			} else {
				c2X = c2X - c2;
			}

			if (c3X <= -500) {
				c3X = 850;
				randomizeC3();
			} else {
				c3X = c3X - c3;
			}





		}
	}
}