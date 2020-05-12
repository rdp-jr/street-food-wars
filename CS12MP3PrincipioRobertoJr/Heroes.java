import java.util.*;
import java.awt.*;			
import java.awt.image.*;
import javax.swing.*;


public class Heroes extends GameObject
{	
	Random r = new Random();
	int hitPoints, bulletsLeft, bulletsMax, 
		healAmount, basicAttackDamage, 
		specAttackDamage, spriteX, spriteY,
		hitPointsMax = 100;

	public void paint(Graphics2D g)
	{

	}

	public int basicAttack()
	{
		return basicAttackDamage;
	}

	public void heal()
	{
		hitPoints = hitPoints + healAmount;

		if (hitPoints > 100) {
			hitPoints = 100;
		}
	}

	public void reload()
	{
		bulletsLeft = bulletsMax;
	}

	public boolean specAttackHit()
	{
		int hitChance;

		hitChance = r.nextInt(100) + 1; //Random number from 1 - 100

		if (hitChance <=80) {
			return true;		//Hits!
		} else {
			return false;		//Misses!
		}

	}

	public boolean finalAttack()
	{
		
		int hitChance;

		hitChance = r.nextInt(100) + 1;	//Random number from 1 - 100

		if (hitChance <=50) {
			return true;		//Hits! Enemy dies!
		} else {
			return false;		//Misses! You die!
		}

		//Test Case
		//return true;
	}

	public void instantDeath()
	{
		hitPoints = 0;
	}

	
	public void visualize() {
		String hpstatus = "hp: (" + hitPoints + ") ";
		for (int i = 0; i <= hitPoints / 10; i++) {
			hpstatus = hpstatus + "*";
		}
		System.out.println(hpstatus);
	}
}