import java.util.*;
import java.awt.*;			
import java.awt.image.*;
import javax.swing.*;


public class GameText extends GameObject
{
	String choice, enemyChoice, winner;
	int playerBasicDamage, playerSpecDamage, 
		playerSpriteX,  playerSpriteY,
		playerHealAmount, enemyHealAmount,
		enemySpriteX, enemySpriteY,
		enemyBasicDamage, enemySpecDamage, nEnemy, enemyCrit, playerCrit;
	boolean hasNewEnemy, isOver;

	ImageIcon iconEnemy = new ImageIcon("isaw_icon_alive.png");
	ImageIcon kwekConfused = new ImageIcon("kwek_confused.png");


	
		

	public GameText()
	{
		choice = "";
		enemyChoice = "";
		playerSpriteY = 350;
		enemySpriteY = 350;
		enemyCrit = 315;
		
		
	}

	

	public void paint(Graphics2D g)
	{	
		g.setFont(new Font("Verdana",Font.BOLD,12));
		g.setColor(Color.WHITE);
		
		int playerTextX, playerTextY;

		int statusBarX, statusBarY;

		statusBarX = 300;
		statusBarY = 20;

		playerTextX = 75;
		playerTextY = 400;


		int counter = nEnemy - 1;

		if (nEnemy > 1) {
			iconEnemy.paintIcon(this, g, 718, 112);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.drawString("" + counter, 730, 135);
		}

		g.setFont(new Font("Verdana",Font.BOLD,12));
		g.setColor(Color.WHITE);	

		if (choice.equals("aa")) {
			kwekConfused.paintIcon(this, g, 180, 210);
			g.drawString("     Kwek got confused!", statusBarX, statusBarY);
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);
			g.drawString("- " + playerBasicDamage, playerSpriteX, playerSpriteY);

			
		} else if (choice.equals("ab")) {
			g.drawString("   Kwek used Holy Tusok!", statusBarX, statusBarY);
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);
			g.drawString("- " + playerBasicDamage, enemySpriteX, enemySpriteY);

		} else if (choice.equals("ac")) {
			g.drawString("   Kwek used Holy Tusok!", statusBarX, statusBarY);
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);
			int doubleDamage = playerBasicDamage * 2;
			g.drawString("- " + doubleDamage, enemySpriteX, enemySpriteY);
			g.drawString("CRITICAL", enemySpriteX - 35, enemyCrit);


		} else if (choice.equals("bHit")) {
			g.drawString("Kwek used Stick Barrage!", statusBarX, statusBarY);
			
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);
			g.drawString("- " + playerSpecDamage, enemySpriteX, enemySpriteY);
			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.setColor(Color.WHITE);	

		} else if (choice.equals("bMiss")) {
			g.drawString("Kwek used Stick Barrage!", statusBarX, statusBarY);
			
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);
			g.drawString("Miss", enemySpriteX, enemySpriteY);
			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.setColor(Color.WHITE);

		} else if (choice.equals("c")) {
			g.drawString("  Kwek used EGGXODUS!", statusBarX, statusBarY);
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);

	    } else if (choice.equals("cHit")) {
			g.drawString("  Kwek used EGGXODUS!", statusBarX, statusBarY);
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);

			g.drawString("FATALITY", enemySpriteX - 80, enemySpriteY);

			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.setColor(Color.WHITE);

		} else if (choice.equals("cMiss")) {
			g.drawString("  Kwek used EGGXODUS!", statusBarX, statusBarY);
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);

			g.drawString("Miss", enemySpriteX, enemySpriteY);
			g.drawString("FATALITY", playerSpriteX, playerSpriteY);
			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.setColor(Color.WHITE);

		} else if (choice.equals("d")) {
			g.drawString("   Kwek used Dip In Sauce!", statusBarX, statusBarY);
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.GREEN);
			g.drawString("+ " + playerHealAmount, playerSpriteX, playerSpriteY);
			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.setColor(Color.WHITE);	
			
		} else if (choice.equals("e")) {
			g.drawString("    Kwek used Restock Sticks!", statusBarX, statusBarY);
			
		} else if (choice.equals("turn")) {
			
			g.drawString("                Your Turn", statusBarX, statusBarY);

		} else if (choice.equals("wait")) {
			g.drawString("", playerTextX, playerTextY);
						
		} else if (choice.equals("bNoBullets")) {
			g.drawString("                No Sticks left!", statusBarX, statusBarY);
		} else if (choice.equals("")) {
			g.drawString("", 0, 0);
		}

		int enemyTextX, enemyTextY;

		enemyTextX = 450;
		enemyTextY = 400;

		if (enemyChoice.equals("1")) {
			g.drawString("     Isaw used Shitball!", statusBarX, statusBarY);

			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);
			g.drawString("- " + enemyBasicDamage, playerSpriteX, playerSpriteY);
			
			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.setColor(Color.WHITE);

			
		} else if (enemyChoice.equals("2hit")) {
			g.drawString("    Isaw used Giant Shit!", statusBarX, statusBarY);
			
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);
			g.drawString("- " + enemySpecDamage, playerSpriteX, playerSpriteY);
			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.setColor(Color.WHITE);

		} else if (enemyChoice.equals("2miss")) {
			g.drawString("    Isaw used Giant Shit!", statusBarX, statusBarY);
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);
			g.drawString("Miss", playerSpriteX, playerSpriteY);
			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.setColor(Color.WHITE);

		} else if (enemyChoice.equals("3hit")) {
			g.drawString("Isaw used RAYQUAZA FORM!", statusBarX, statusBarY);
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);
			g.drawString("FATALITY", playerSpriteX, playerSpriteY);
			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.setColor(Color.WHITE);

		} else if (enemyChoice.equals("3miss")) {
			g.drawString("Isaw used RAYQUAZA FORM!", statusBarX, statusBarY);
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.setColor(Color.RED);
			g.drawString("Miss", playerSpriteX, playerSpriteY);

			g.setColor(Color.RED);
			g.drawString("FATALITY", enemySpriteX - 80, enemySpriteY);
			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.setColor(Color.WHITE);

		} else if (enemyChoice.equals("4")) {
			g.drawString("         Isaw used Grill!", statusBarX, statusBarY);
			g.setColor(Color.GREEN);
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.drawString("+ " + enemyHealAmount, enemySpriteX, enemySpriteY);
			g.setFont(new Font("Verdana",Font.BOLD,12));
			g.setColor(Color.WHITE);
			
		} else if (enemyChoice.equals("5")) {
			g.drawString("     Isaw used Shit Again!", statusBarX, statusBarY);
			
		} else if (enemyChoice.equals("newEnemy")) {
			g.drawString("		A new enemy has arrived!", statusBarX, statusBarY);

		} else if (enemyChoice.equals("noBullets")) {
			g.drawString("        Isaw has no shit left!", statusBarX, statusBarY);

	    } else if (enemyChoice.equals("")) {
			g.drawString("",enemyTextX, enemyTextY);
		} else if (enemyChoice.equals("3")) {
			g.drawString("Isaw used RAYQUAZA FORM!", statusBarX, statusBarY);
		}

		if (hasNewEnemy) {
			g.drawString("  A new enemy has appeared!", statusBarX, statusBarY);
		}

		
		

		if (isOver) {
			if (winner.equals("player")) {
				g.setFont(new Font("Verdana",Font.BOLD,130));
				g.setColor(Color.GREEN);
				g.drawString("YOU WIN", 70, 220);
			} else if (winner.equals("enemy")) {
				g.setFont(new Font("Verdana",Font.BOLD,130));
				g.setColor(Color.RED);
				g.drawString("YOU LOSE", 35, 220);
			} 
		}
		

	}

	public void resetCoor()
	{
		playerSpriteY = 350;
		enemySpriteY = 350;
		enemyCrit = 315;
	}


	public void run()
	{
		while (true) {
			MarioWindow.delay(20);

			if (choice.equals("aa") || choice.equals("d") || enemyChoice.equals("1") || enemyChoice.equals("2hit") || enemyChoice.equals("2miss") || enemyChoice.equals("3hit")) {
				if (playerSpriteY <= 180) {
					playerSpriteY = -100;
				} else {
					playerSpriteY = playerSpriteY - 6;
				}

			} else if (choice.equals("ab") || choice.equals("bHit") || choice.equals("bMiss") || choice.equals("cHit")  || enemyChoice.equals("4")) {
				if (enemySpriteY <= 180) {
					enemySpriteY = -100;
				} else {
					enemySpriteY = enemySpriteY - 6;
				}
				
			} else if (choice.equals("ac")) {
				if (enemySpriteY <= 180) {
					enemySpriteY = -100;
				} else {
					enemySpriteY = enemySpriteY - 6;
				}

				if (enemyCrit <= 180) {
					enemyCrit = -100;
				} else {
					enemyCrit = enemyCrit - 6;
				}

			} else if (choice.equals("cMiss") || enemyChoice.equals("3miss")) {
				if (enemySpriteY <= 180) {
					enemySpriteY = -100;
				} else {
					enemySpriteY = enemySpriteY - 6;
				}

				if (playerSpriteY <= 180) {
					playerSpriteY = -100;
				} else {
					playerSpriteY = playerSpriteY - 6;
				}

			}
		}
	}
}