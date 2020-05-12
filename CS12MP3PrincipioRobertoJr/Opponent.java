import java.util.*;
import java.awt.*;			
import java.awt.image.*;
import javax.swing.*;


public class Opponent extends Heroes
{
	//ImageIcon fullHp;
	ImageIcon charImage, hpBar, iconAlive, iconDead;
	String name;

	public Opponent()
	{
		//Stats
		name = "Isaw";
		hitPoints = 100;
		bulletsLeft = 3;
		bulletsMax = 3;
		healAmount = 12;
		basicAttackDamage = 10;
		specAttackDamage = 22;
		

		//Sprites
		spriteX = 600;
		spriteY = 210;
		charImage = new ImageIcon("isaw.gif");
		hpBar = new ImageIcon("hud2.png");

		iconAlive = new ImageIcon("isaw_icon_alive.png");
		iconDead = new ImageIcon("isaw_icon_dead.png");
	}	

	/*
	public void paint(Graphics2D g)
	{
		//HP Bar (BG)
		g.setColor(Color.GRAY);
		g.fillRect(535, 75, 200, 25);


		//HP Bar (GREEN)
		g.setColor(Color.GREEN);
		int hpBarInt;
		hpBarInt = hitPoints * 2;
		g.fillRect(535,75, hpBarInt, 25);

		//HP Bar HUD
		hpBar.paintIcon(this, g, 0, 0);

		//HP Text
		g.setFont(new Font("Verdana",Font.BOLD,10));
		g.setColor(new Color(27, 52, 74));
		g.drawString("" + hitPoints + "/" + hitPointsMax, 685, 100);

		//Character and Icon
		if (hitPoints > 0) {
			charImage.paintIcon(this, g, 485, 95);
			iconAlive.paintIcon(this, g, 452, 38);
		} else {
			iconDead.paintIcon(this, g, 452, 38);
		}

		//Name Text
		g.setFont(new Font("Verdana", Font.BOLD, 20));
		g.setColor(new Color(27, 52, 74));
		g.drawString("" + name, 560, 73);
				
	
		
	}
	*/


	//Console
	public void visualize()
	{
		int starHp = hitPoints/10;

		System.out.print("[");		

		for (int i = 0; i<10; i++) {
			if (i+1 <= starHp) {
				System.out.print("*");	
			} else {
				System.out.print("-");
			}
			
		}

		System.out.print("] ");
		System.out.print("Opponent HP: " + hitPoints + " | Bullets : " + bulletsLeft + "/3\n");
	}
}