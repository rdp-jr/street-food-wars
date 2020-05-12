import java.util.*;
import java.awt.*;			
import java.awt.image.*;
import javax.swing.*;


public class EnemyLinkedQueue extends Opponent
{
	Node head, tail;
	boolean isGone, isUlti = false, isGoingDown = false;
	ImageIcon isawUlti;
	int ultimateX, ultimateY;

	
	
	public EnemyLinkedQueue()
	{
		isawUlti = new ImageIcon("isaw_ultimate.png");
		ultimateX = 500;
		ultimateY = 150;
	}

	public void enqueue(Opponent i)
	{
		Node bago = new Node(i);
		if (head == null) {
			head = bago;
			tail = bago;
		} else {
			tail.next = bago;
			tail = bago;
		}
	}

	public Opponent dequeue() //Call when head enemy is dead
	{
		if (head != null) {					//IF there is still an opponent in front 
			Opponent result = head.data;	
			head = head.next;

			if (head == null) {
				tail = null;
			}
			return result;	//Returns an Opponent object

		} else {							//If there is no enemy left

			return null;
			
		}
	}

	public Opponent peek() 
	{
		if (head != null) {
			Opponent result = head.data;
			return result;
		} else {
			return null;
		}
	}

	public void printQueue()
	{
		head.printNode();
	}

	
	public boolean isEmpty() 
	{
		return head == null;
	}

	public void emptyQueue()
	{
		while (this.isEmpty() == false) {
			this.dequeue();
		}
	}


	public void paint(Graphics2D g)
	{	
		
		if (head == null || (isGone)) {	

			// if (head == null) {
			// 	System.out.println("HEAD IS NUUUULLLL");
			// } else if (isGone) {
			// 	System.out.println("IS GOOOONEEE");
			// }
			//HP Bar (BG)
			g.setColor(Color.GRAY);
			g.fillRect(535, 75, 200, 25);

			//HP Bar HUD
			hpBar.paintIcon(this, g, 0, 0);

			//HP Text
			g.setFont(new Font("Verdana",Font.BOLD,10));
			g.setColor(new Color(27, 52, 74));
			g.drawString("0" + "/" + hitPointsMax, 685, 100);

			iconDead.paintIcon(this, g, 452, 38);

			//Name Text
			g.setFont(new Font("Verdana", Font.BOLD, 20));
			g.setColor(new Color(27, 52, 74));
			g.drawString("" + name, 560, 73);

		} else if (head != null) {		
			int hp = head.data.hitPoints;
			// System.out.println("HEAD IS NOT NULL");
			// System.out.println("HEAD'S HP: " + head.data.hitPoints);
			//Node head = new Node(head);

			//HP Bar (BG)
			g.setColor(Color.GRAY);
			g.fillRect(535, 75, 200, 25);


			//HP Bar (GREEN)
			g.setColor(Color.GREEN);
			int hpBarInt;
			hpBarInt = head.data.hitPoints * 2;
			g.fillRect(535,75, hpBarInt, 25);

			//HP Bar HUD
			hpBar.paintIcon(this, g, 0, 0);

			//HP Text
			g.setFont(new Font("Verdana",Font.BOLD,10));
			g.setColor(new Color(27, 52, 74));

			int actualHp = 0;
			
			if (hp <= 0) {
				actualHp = 0;

			} else if (hp > 0) {
				actualHp = hp;
			}

			g.drawString("" + actualHp + "/" + hitPointsMax, 685, 100);

			//Character and Icon
			if (hp > 0) {

				if (isUlti) {
					isawUlti.paintIcon(this, g, ultimateX, ultimateY);
				} else {
					charImage.paintIcon(this, g, 485, 95);
				}
				iconAlive.paintIcon(this, g, 452, 38);

			} else {
				iconDead.paintIcon(this, g, 452, 38);
			}

			//Name Text
			g.setFont(new Font("Verdana", Font.BOLD, 20));
			g.setColor(new Color(27, 52, 74));
			g.drawString("" + name, 560, 73);

		} 

	}


	public void resetCoor()
	{
		ultimateY = 150;
		isGoingDown = false;
	}


	public void run()
	{
		while (true) {
			MarioWindow.delay(20);


			if (isUlti) {
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
			}
		}
	}
}