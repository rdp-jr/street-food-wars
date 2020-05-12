import java.util.*;
import java.awt.*;			
import java.awt.image.*;
import javax.swing.*;


public class MainHero extends Heroes
{
	boolean hasTurn;

	String name, attackChoice;
	ImageIcon charImage, hpBar, iconAlive, iconDead, charBasicAttack, kwekSpec, kwekReload, kwekHeal, kwekUltimate;
	int basicAttackX, basicAttackY, bulletX, bulletY, reloadX, reloadY, ultimateX, ultimateY, healX, healY;
	Random r = new Random();
	boolean isGoingBack = false;
	boolean isGoingDown = false;

	public MainHero()
	{
		//Stats
		name = "Kwek";
		hitPoints = 100;
		bulletsLeft = 5;
		bulletsMax = 5;
		healAmount = 15;
		basicAttackDamage = 14;
		specAttackDamage = 20;

		hasTurn = true;

		//Sprites
		attackChoice = "";
		spriteX = 220;
		spriteY = 210;
		charImage 		= new ImageIcon("kwek.gif");
		hpBar 			= new ImageIcon("hud1.png");
		iconAlive 		= new ImageIcon("kwek_icon_alive.png");
		iconDead 		= new ImageIcon("kwek_icon_dead.png");
		charBasicAttack = new ImageIcon("kwek_basic_attack.png");
		kwekSpec 		= new ImageIcon("stick.png");
		kwekReload 		= new ImageIcon("kwek_reload.png");
		kwekHeal 		= new ImageIcon("kwek_heal.png");
		kwekUltimate 	= new ImageIcon("kwek_ultimate.png");

		basicAttackX = 105;
		basicAttackY = 105;

		bulletX = 105;
		bulletY = 200;

		reloadX = 100;
		reloadY = 250;

		healX = 100;
		healY = 105;

		ultimateX = 105;
		ultimateY = 150;
		
	}


	
	// public int heroD20()
	// {
	// 	int x;
	// 	x = r.nextInt(20) + 1;

	// 	if (x == 1) { //deal damage to self
	// 		return 1;
	// 	} else if (x > 1 && x <= 19) { //deal damage to opponent
	// 		return 2;
	// 	} else {	//deal 2x damage to opponent
	// 		return 3;
	// 	}
	// }


	public void paint(Graphics2D g)
	{	
		//HP Bar (BG)
		g.setColor(Color.GRAY);
		g.fillRect(145, 75, 200, 25);


		//HP Bar (GREEN)
		g.setColor(Color.GREEN);
		int hpBarInt;
		hpBarInt = hitPoints * 2;
		g.fillRect(145,75, hpBarInt, 25);
		
		//HP Bar HUD
		hpBar.paintIcon(this, g, 0, 0);

		

		//HP Text
		g.setFont(new Font("Verdana",Font.BOLD,10));
		g.setColor(new Color(27, 52, 74));

		int actualHp;
			if (hitPoints < 0) {
				actualHp = 0;
			} else {
				actualHp = hitPoints;
			}

		g.drawString("" + actualHp + "/" + hitPointsMax, 295, 100);

		//Character and Icon
		if (hitPoints > 0) {
			iconAlive.paintIcon(this, g, 58, 38);

			if (attackChoice.equals("basicAttack")) {
				charBasicAttack.paintIcon(this, g, basicAttackX, basicAttackY);

			} else if (attackChoice.equals("specAttack")) {
				charImage.paintIcon(this, g, 127, 105);
				kwekSpec.paintIcon(this, g, bulletX, bulletY);
				
			} else if (attackChoice.equals("finalAttack")) {
				kwekUltimate.paintIcon(this, g, ultimateX, ultimateY);

			} else if (attackChoice.equals("heal")) {
				charImage.paintIcon(this, g, 127, 105);
				kwekHeal.paintIcon(this, g, healX, healY);
			
			} else if (attackChoice.equals("reload")) {
				charImage.paintIcon(this, g, 127, 105);
				kwekReload.paintIcon(this, g, reloadX, reloadY);
			
			} else if (attackChoice.equals("")) {
				charImage.paintIcon(this, g, 127, 105);
			}

			
		} else {
			iconDead.paintIcon(this, g, 58, 38);
		}
		
		//Name Text
		g.setFont(new Font("Verdana", Font.BOLD, 20));
		g.setColor(new Color(27, 52, 74));
		g.drawString("" + name, 165, 73);

		g.setFont(new Font("Verdana", Font.BOLD, 20));
		//Moves
		if (hasTurn) {
			
			g.setColor(new Color(27, 52, 74));
		} else {
			g.setColor(new Color(69, 70, 73));
		}

		//Basic Move
		g.drawString("Holy", 100, 475);
		g.drawString("Tusok", 94, 500);

		//Basic Attack Damage
		g.drawString("" + basicAttackDamage, 90, 540);

		//Basic Attack Accuracy
		g.drawString("100", 132, 540);


		//Special Move
		g.drawString("Stick", 235, 475);
		g.drawString("Barrage", 220, 500);

		//Special Attack Damage
		g.drawString("" + specAttackDamage, 225, 540);

		//Special Attack Accuracy
		g.drawString("80", 275, 540);


		//Ultimate Move
		g.drawString("Egg", 375, 475);
		g.drawString("Xodus", 360, 500);

		//Ultimate Attack Damage
		g.drawString("KO", 355, 540);

		//Ultimate Attack Accuracy
		g.drawString("50", 407, 540);


		//Heal Move
		g.drawString("Dip In", 495, 475);
		g.drawString("Sauce", 495, 500);

		//Heal Amount
		g.drawString("" + healAmount, 515, 540);


		//Reload 
		g.drawString("Restock", 620, 475);
		g.drawString("Sticks", 630, 500);

		//Heal Amount
		g.drawString(bulletsLeft + "/" + bulletsMax, 640, 540);

		
		
	}

	public void resetCoor()
	{
		basicAttackX = 105;
		isGoingBack = false;
		bulletX = 105;
		ultimateY = 150;
		isGoingDown = false;
		reloadY = 250;
	}


	
	public void run()
	{

		while (true) {
			MarioWindow.delay(20);

			if (attackChoice.equals("basicAttack")) {

				if (basicAttackX >= 230) {
					isGoingBack = true;
				}

				if (isGoingBack == false) {
					basicAttackX = basicAttackX + 12;
				} else if (isGoingBack) {
					if (basicAttackX <= 105) {
						basicAttackX = basicAttackX;
					} else {
						basicAttackX = basicAttackX - 5;
					}
					
				}
				 

			} else if (attackChoice.equals("specAttack")) {

				bulletX = bulletX + 20;

			} else if (attackChoice.equals("finalAttack")) {

				if (ultimateY <= 110) {
					isGoingDown = true;
				}

				if (isGoingDown) {

					if (ultimateY > 150) {
						isGoingDown = false;
					} else {
						ultimateY = ultimateY + 2;
					}

				} else {
					if (ultimateY < 110) {
						isGoingDown = true;
					} else {
						ultimateY = ultimateY - 2;
					}
				}
				

			} else if (attackChoice.equals("heal")) {
				if (healY <= 110) {
					isGoingDown = true;
				}

				if (isGoingDown) {

					if (healY > 150) {
						isGoingDown = false;
					} else {
						healY = healY + 2;
					}

				} else {
					if (healY < 110) {
						isGoingDown = true;
					} else {
						healY = healY - 2;
					}
				}
			} else if (attackChoice.equals("reload")) {
				if (reloadY <= 150) {
					reloadY = -200;
				} else {
					reloadY = reloadY - 6;
				}
			}
		}
		

		// while (attackChoice.equals("basicAttack")) {
		// 	MarioWindow.delay(20);
		// 	basicAttackX = basicAttackX + 10;
		// }

		

		// if (this.attackChoice.equals("basicAttack")) {

			
		// 	basicAttackX = basicAttackX + 10;
			
			
		// 	//y = y + 1;
		// }
	}
	

	
}