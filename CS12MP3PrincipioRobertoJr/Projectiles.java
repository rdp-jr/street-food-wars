import java.util.*;
import java.awt.*;			
import java.awt.image.*;
import javax.swing.*;


public class Projectiles extends GameObject
{
	boolean isGoingDown;
	String choice;
	ImageIcon isawBasic, isawSpec, isawHeal, isawReload;
	int basicX, basicY, specX, specY, healX, healY, reloadX, reloadY;


	public Projectiles()
	{

		isawBasic = new ImageIcon("isaw_projectile.png");
		isawSpec = new ImageIcon("isaw_spec.png");
		isawHeal = new ImageIcon("isaw_heal.png");
		isawReload = new ImageIcon("isaw_reload.png");

		isGoingDown = false;
		choice = "";
		basicX = 520;
		basicY = 300;
		specX = 120;
		specY = -100;
		
		healX = 620;
		healY = 210;
		reloadX = 610;
		reloadY = 250;

	}

	public void paint(Graphics2D g)
	{
		if (choice.equals("1")) {
			isawBasic.paintIcon(this, g, basicX, basicY);

		} else if (choice.equals("2")) {
			isawSpec.paintIcon(this, g, specX, specY);

		} else if (choice.equals("4")) {
			isawHeal.paintIcon(this, g, healX, healY);

		} else if (choice.equals("5")) {
			isawReload.paintIcon(this, g, reloadX, reloadY);

		} else if (choice.equals("")) {

		}
	}

	public void resetCoor()
	{
		basicX = 520;
		isGoingDown = false;
		reloadY = 250;
		specY = -100;
	}

	public void run()
	{

		while (true) {
			MarioWindow.delay(20);

			if (choice.equals("1")) {
				if (basicX <= 300) {

				} else {
					basicX = basicX - 18;
				}

			} else if (choice.equals("2")) {
				if (specY < 220) {
					specY = specY + 20;
				}

			} else if (choice.equals("3")) {

			} else if (choice.equals("4")) {

				if (healY <= 110) {
					isGoingDown = true;
				}

				if (isGoingDown) {

					if (healY > 250) {
						isGoingDown = false;
					} else {
						healY = healY + 2;
					}

				} else {
					if (healY < 215) {
						isGoingDown = true;
					} else {
						healY = healY - 2;
					}
				}

			} else if (choice.equals("5")) {
				if (reloadY <= 150) {
					reloadY = -200;
				} else {
					reloadY = reloadY - 6;
				}
			}

		}


		
	}
}