// write in a separate file
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
public class MyButtons extends GameObject {
	String action = "";
	boolean hasTurn = true, isOver = false;
	
	public void paint(Graphics2D g) 
	{
		

	}
	
	public void mouseClicked(int xmouse,int ymouse,int b) 
	{	
		
		if ((ymouse >= 440) && (ymouse <= 575)) {

			if ((xmouse >= 75) && (xmouse <= 185)) {
				System.out.println("You clicked Basic Attack");
				action = "basicAttack";

			} else if ((xmouse >= 209) && (xmouse <= 318)) {
				System.out.println("You clicked Special Attack");
				action = "specAttack";

			} else if ((xmouse >= 342) && (xmouse <= 451)) {
				System.out.println("You clicked Final Attack");
				action = "finalAttack";

			} else if ((xmouse >= 477) && (xmouse <= 584)) {
				System.out.println("You clicked Heal");
				action = "heal";

			} else if ((xmouse >= 610) && (xmouse <= 718)) {
				System.out.println("You clicked Reload");
				action = "reload";
			}
		}

		if (this.isOver) {
			if ((xmouse >= 342) && (xmouse <= 452) && (ymouse >= 254) && (ymouse <= 338)) {
				System.out.println("You clicked Play Again");
				action = "playAgain";
			}
		}

		


		
				
	}
	
	/*
	public void keyPressed(String s) {
		System.out.println("You pressed " + s);
		if (s.equals("G")) {
			action = "green";
		} else if (s.equals("Y")) {
			action = "yellow";
		} else if (s.equals("B")) {
			action = "blue";
		}
		
	}
	*/
	
	
	
}