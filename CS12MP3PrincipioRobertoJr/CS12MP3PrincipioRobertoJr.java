import java.util.Random;

public class CS12MP3PrincipioRobertoJr
{
	public static void main(String args[])
	{

		MainHero player 		= new MainHero();
		Opponent firstEnemy 	= new Opponent();
		//Scanner input 			= new Scanner(System.in);
		Random r 				= new Random();
		GameText gametext 		= new GameText();
		Gfx gfx 				= new Gfx();
		EnemyLinkedQueue queue 	= new EnemyLinkedQueue();
		Projectiles proj 		= new Projectiles();

		int newEnemyD20;
		//String choice;

		MyButtons click			= new MyButtons();

		//MarioWindow
		final MarioWindow mario = new MarioWindow();
		
		mario.add(gfx);
		mario.add(click);
		mario.add(queue);
		mario.add(player);
		mario.add(proj);
		mario.add(gametext);
		

		(new Thread() {

			public void run() {
				mario.startGame();
			}
		}
		).start();

		Opponent enemy = new Opponent();

		//GAME TEXT INITIALIZATION
		gametext.playerBasicDamage 	= player.basicAttackDamage;
		gametext.playerSpecDamage 	= player.specAttackDamage;
		gametext.playerSpriteX 		= player.spriteX;
		gametext.playerSpriteY 		= player.spriteY;
		gametext.playerHealAmount 	= player.healAmount;
		gametext.enemyHealAmount	= enemy.healAmount;
		gametext.enemyBasicDamage 	= enemy.basicAttackDamage;
		gametext.enemySpecDamage 	= enemy.specAttackDamage;
		gametext.enemySpriteX 		= enemy.spriteX;
		gametext.enemySpriteY 		= enemy.spriteY;

		//INITIALIZATION
		gametext.isOver = false;

		//First Enemy
		queue.enqueue(firstEnemy);
		enemy = queue.peek();

		//GAME TEXT
		gametext.winner = "none";
		gametext.nEnemy = 1;
		gfx.isGameOver = false;


		//MAIN LOOP
		while (true) {

			//GAME LOOP
			while (!queue.isEmpty()) {

				if (player.hitPoints <= 0) {
					gametext.winner = "enemy";
					break;
				}

				gametext.hasNewEnemy = false;
				enemy = queue.peek();

				gfx.hasTurn = true;
				player.hasTurn = true;
				
				gametext.choice = "turn";

				mario.delay(50);
				player.attackChoice = "";
				proj.choice = "";
				proj.resetCoor();

				if (click.action.length() > 0) {
					
					if (click.action.equals("basicAttack") && player.hasTurn) {
						gfx.hasTurn = false;
						player.hasTurn = false;
						
						
						int basicAttackChance = r.nextInt(20) + 1;

						//Hits Self
						if (basicAttackChance == 1) {
							gametext.choice = "aa";
							player.hitPoints = player.hitPoints - player.basicAttackDamage;
							System.out.println("> Player is confused! Player takes self-damage.");

						//Hits Opponent
						} else if (basicAttackChance > 1 && basicAttackChance <= 19) {
							player.attackChoice = "basicAttack";	
							gametext.choice = "ab";
							enemy.hitPoints = enemy.hitPoints - player.basicAttackDamage;
							System.out.println("> Player is normal. Opponent takes basic damage.");

						//Hits Opponent 2x
						} else if (basicAttackChance == 20) {
							player.attackChoice = "basicAttack";	
							gametext.choice = "ac";
							enemy.hitPoints = enemy.hitPoints - (player.basicAttackDamage * 2);
							System.out.println("> Player is strong! Opponent takes (2x) basic damage.");
						}


						if (enemy.hitPoints <= 0) {
							queue.isGone = true;
							queue.dequeue();				//moved dequeue before delay
							MarioWindow.delay(1750);
							queue.isGone = false;										
							gametext.choice = "";
							
							gametext.nEnemy = gametext.nEnemy - 1;

							if (queue.isEmpty()) {
								MarioWindow.delay(20);
								System.out.println("> No more opponents left.\nYou Win!");
								gametext.winner = "player";
								break;
						
							} else {
								
								gametext.choice = "";
								gametext.enemyChoice = "newEnemy";
								System.out.println("> A new Enemy has arrived.");
							}

						} else {
						
						}

						
						click.action = "";
						player.resetCoor();
						gametext.resetCoor();
						

					} else if (click.action.equals("specAttack") && player.hasTurn) {

						gfx.hasTurn = false;
						player.hasTurn = false;
						

						if (player.bulletsLeft != 0) {

							if (player.specAttackHit()) {
								player.attackChoice = "specAttack";	
								gametext.choice = "bHit";
								System.out.println("> Special Attack Hits!");
								enemy.hitPoints = enemy.hitPoints - player.specAttackDamage;
								player.bulletsLeft = player.bulletsLeft - 1;

								if (enemy.hitPoints <= 0) {

									queue.isGone = true;
									queue.dequeue();				//moved dequeue before delay
									MarioWindow.delay(1750);
									queue.isGone = false;	
									gametext.choice = "";
							
									
									gametext.nEnemy = gametext.nEnemy - 1;


								if (queue.isEmpty()) {
									MarioWindow.delay(20);
									System.out.println("> No more opponents.\nYou Win!");
									gametext.winner = "player";
									break;

								} else {
									//MarioWindow.delay(1750);
									gametext.choice = "";
									gametext.enemyChoice = "newEnemy";
									System.out.println("> A new Enemy has arrived.");
									}

								}

							} else {
								gametext.choice = "bMiss";
								System.out.println("> Special Attack Misses!");
								player.bulletsLeft = player.bulletsLeft - 1;

							}

						} else {
							gametext.choice = "bNoBullets";
							System.out.println("> No more bullets left!");
						}


						click.action = "";
						player.resetCoor();
						gametext.resetCoor();

						} else if (click.action.equals("finalAttack")) {
						gfx.hasTurn = false;
						player.hasTurn = false;
						player.attackChoice = "finalAttack";

						gametext.choice = "c";

						MarioWindow.delay(1750);
						
						

						if (player.finalAttack()) {
							gametext.choice = "cHit";
							
							enemy.instantDeath();
							System.out.println("> Final Attack Hits!");
							
							
							queue.isGone = true;
							queue.dequeue();				//moved dequeue before delay
							MarioWindow.delay(1750);
							queue.isGone = false;	


							gametext.choice = "";
							//MarioWindow.delay(1750);
							//queue.dequeue();
							gametext.nEnemy = gametext.nEnemy - 1;


							if (queue.isEmpty()) {
								MarioWindow.delay(20);
								System.out.println("> No more opponents left.\nYou Win!");
								gametext.winner = "player";
								break;

							} else {
								
								//MarioWindow.delay(1750);		//TRY TO ADD
								gametext.choice = "";
								//MarioWindow.delay(1750);
								gametext.enemyChoice = "newEnemy";
								System.out.println("> A new Enemy has arrived.");
							} 

						} else {
							//MarioWindow.delay(1750);			...

							gametext.choice = "cMiss";

							player.instantDeath();
							System.out.println("> Final Attack Misses! You die!\nYou Lost.");
							gametext.winner = "enemy";
							break;
						}

						click.action = "";
						gametext.resetCoor();
						player.resetCoor();
						

					} else if (click.action.equals("heal") && player.hasTurn) {
						player.attackChoice = "heal";
						gfx.hasTurn = false;
						player.hasTurn = false;
						

						gametext.choice = "d";
						player.heal();
						System.out.println("> +15 HP");

						click.action = "";
						gametext.resetCoor();
						player.resetCoor();

					} else if (click.action.equals("reload") && player.hasTurn) {
						player.attackChoice = "reload";
						gfx.hasTurn = false;
						player.hasTurn = false;
						

						gametext.choice = "e";
						player.reload();

						click.action = "";
						gametext.resetCoor();
						player.resetCoor();

						}

						//opponent's turn
						System.out.println("\n+++++++++++++++++++++++OPPONENT'S TURN+++++++++++++++++++++++\n");

						int enemyChoice;
						


						MarioWindow.delay(1750);
						player.attackChoice = "";

						mario.delay(50);
						proj.choice = "";
						gametext.choice = "wait";
				
						enemy = queue.peek();

					//AI DECISION LOOP

					while (true) {
						//Low HP use ULTIMATE
						if (enemy.hitPoints <= 30) {
							enemyChoice = 3;
							break;

						//HP is 26 - 50, use HEAL
						} else if ((enemy.hitPoints >= 31) && (enemy.hitPoints <= 50)) {
							enemyChoice = 4;
							break;

						//CHOOSES BETWEEN BASIC ATTACK AND SPEC ATTACK IF HP IS > 50 AND THERE ARE STILL BULLETS LEFT
						} else if (enemy.hitPoints > 50 && enemy.bulletsLeft != 0) {
							enemyChoice = r.nextInt(2) + 1;
							break;
						
						//RELOADS IF HP > 50 AND THERE ARE NO MORE BULLETS
						} else if (enemy.hitPoints > 50 && enemy.bulletsLeft == 0) {
							enemyChoice = 5;
							break;
						}

						//Notes: In the case of the Enemy getting 26 - 50 HP left and 0 Bullets, AI will choose to heal first 
					} //AI DECISION LOOP

					gametext.resetCoor();
					proj.resetCoor();

					//TEST CASE
					//enemyChoice = 3;

					MarioWindow.delay(50);
					proj.choice = "";
					
					//ENEMY BASIC ATTACK
					if (enemyChoice == 1) {
						gametext.enemyChoice = "1";
						proj.choice = "1";


						System.out.println("> Enemy uses Basic Attack.");
						player.hitPoints = player.hitPoints - enemy.basicAttack();

						if (player.hitPoints <= 0) {
							System.out.println("> Basic Attack kills you.");
							gametext.winner = "enemy";
							break;

						} else {
							System.out.println("> You take Basic Attack Damage");
						}

					//ENEMY SPECIAL ATTACK
					} else if (enemyChoice == 2) {
						System.out.println("> Enemy uses Special Attack.");
						if (enemy.bulletsLeft != 0) {
							if (enemy.specAttackHit()) {
								gametext.enemyChoice = "2hit";
								proj.choice = "2";
						
								player.hitPoints = player.hitPoints - enemy.specAttackDamage;
								enemy.bulletsLeft = enemy.bulletsLeft - 1;

								if (player.hitPoints <= 0) {
									System.out.println("> Enemy's special attack kills you. \nYou Lost.");
									gametext.winner = "enemy";
									break;
								} else {
									System.out.println("> You take Special Attack Damage.");
								}

							} else {
								gametext.enemyChoice = "2miss";
								System.out.println("> Enemy's Special Attack misses!");
								enemy.bulletsLeft = enemy.bulletsLeft - 1;
							}
						} else {
							gametext.enemyChoice = "noBullets";
						}
						

						
						//proj.resetCoor();

					//ENEMY ULTIMATE ATTACK
					} else if (enemyChoice == 3) {
						System.out.println("> Enemy uses Final Attack!");
						proj.choice = "3";
						queue.isUlti = true;

						gametext.enemyChoice = "3";
						
						MarioWindow.delay(1750);

						//Success
						if (enemy.finalAttack()) {
							gametext.enemyChoice = "3hit";
							player.instantDeath();
							System.out.println("> Enemy's Final Attack Hits!\nYou Lost.");
							gametext.winner = "enemy";
							break;

						//Miss
						} else {
							gametext.enemyChoice = "3miss";
							enemy.instantDeath();
							MarioWindow.delay(20);
							System.out.println("> Enemy's Final Attack Misses! It dies!");

							queue.isGone = true;
							queue.dequeue();				//moved dequeue before delay
							MarioWindow.delay(1750);
							queue.isGone = false;	
							gametext.choice = "";
							
							gametext.nEnemy = gametext.nEnemy - 1;

							if (queue.isEmpty()) {
								MarioWindow.delay(20);
								System.out.println("> No more opponents left.\nYou Win!");
								gametext.winner = "player";
								break;

							} else {
								//MarioWindow.delay(1750);
								gametext.choice = "";
								gametext.enemyChoice = "newEnemy";
								System.out.println("> A new Enemy has arrived.");
							} 

						
						}	

						queue.isUlti = false;
						queue.resetCoor();

						
						//proj.resetCoor();

					//ENEMY HEAL
					} else if (enemyChoice == 4) {
						gametext.enemyChoice = "4";
						proj.choice = "4";
						System.out.println("Enemy uses Heal."); 
						enemy.heal();
						//proj.resetCoor();

					//ENEMY RELOAD
					} else if (enemyChoice == 5) {
						gametext.enemyChoice = "5";
						proj.choice = "5";
						
						System.out.println("Enemy reloads.");
						enemy.reload();
						//proj.resetCoor();
					}

					gametext.resetCoor();
					proj.resetCoor();

					//SPAWN NEW ENEMY
					newEnemyD20 = r.nextInt(20) + 1;

					//Test Case
					//newEnemyD20 = 1;

					if (newEnemyD20 == 1) {
						MarioWindow.delay(1750);
						gametext.choice = "";
						gametext.enemyChoice = "";
						gametext.hasNewEnemy = true;
						Opponent newEnemy = new Opponent();

						queue.enqueue(newEnemy);
						gametext.nEnemy = gametext.nEnemy + 1;
						MarioWindow.delay(1750);

					} else {
						MarioWindow.delay(1750);
						gametext.choice = "turn";
						gametext.enemyChoice = "";
						System.out.println("--------------------------------------------------------------");

					}

				} //end If
				
				click.action = "";
				gametext.resetCoor();
				queue.isUlti = false;
				//proj.resetCoor();

			}	//GAME LOOP

			MarioWindow.delay(1750);
			gametext.choice = "";
			gametext.enemyChoice = "";
			gametext.isOver = true;

			gfx.isGameOver = true;
			click.isOver = true;

			//RESETS THE GAME
			if (click.action.equals("playAgain")) {

				MarioWindow.delay(10);
				click.isOver = false;
				queue.emptyQueue();

				//First Enemy
				queue.enqueue(firstEnemy);
				MarioWindow.delay(10);
				//continue;

				//INITIALIZATION
				gametext.isOver = false;

				

				//GAME TEXT
				gametext.winner = "none";

				player.hitPoints = 100;
				player.bulletsLeft = 5;
				gametext.nEnemy = 1;
				gfx.isGameOver = false;
				
				click.action = "";

			}

		}

	}


	
}