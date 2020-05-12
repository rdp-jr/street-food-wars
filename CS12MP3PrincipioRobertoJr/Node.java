public class Node 
{
	Opponent data;
	Node next;

	public Node(Opponent pasok)
	{
		data = new Opponent();
		data.name = pasok.name;
		data.hitPoints = pasok.hitPoints;
		data.bulletsLeft = pasok.bulletsMax;
		data.healAmount = pasok.healAmount;
		data.basicAttackDamage = pasok.basicAttackDamage;
		data.specAttackDamage = pasok.specAttackDamage;
	}

	
	public void printNode()
	{
		if (data != null) {
			System.out.println(data.name + "HP: " + data.hitPoints);	
		}
	}
	
	
}