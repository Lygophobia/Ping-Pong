package game.client;

import game.Constructors;
import game.client.GameScreen;

public class ClippingLogic extends GameScreen 
{
	public static boolean ballLive = false;
	public static int ballSpeedX = 2;
	public static int ballSpeedY = 1;
	public static int movingDirectionLR = 2; //0 = not moving, 1 = Left, 2 = Right
	public static int movingDirectionUD = 0; //0 = not moving, 1 = up, 2 = down
	public static int bounce = 1;
	boolean nearWall = false;
	
	public void moveBall(int x, int y)
	{
		if(GameScreen.ballX < x) //Increments
		{
			movingDirectionLR = 2; //Right
		} 
		else if(GameScreen.ballX > x) //Decrements
		{
			movingDirectionLR = 1; //Left
		}
		GameScreen.ballX = x;
		if(GameScreen.ballY < y) //Increments
		{
			movingDirectionUD = 2; //Down
		} 
		else if(GameScreen.ballY > y) //Decrements
		{
			movingDirectionUD = 1; //Up
		}
		GameScreen.ballY = y;
	}
	
	public void calculateBounce()
	{
		Constructors.debugMessage("Moving " + (movingDirectionLR == 1 ? "Left" : "Right") + " + " + (movingDirectionUD == 1 ? "Up" : "Down"));
		
		nearWall = ((GameScreen.ballX > 4 && GameScreen.ballX < 6 || 
				GameScreen.ballX <= (Constructors.windowWidth - 39) && GameScreen.ballX >= (Constructors.windowWidth - 41)) ? true : false);
		
		if(nearWall)
		{
			if(movingDirectionLR == 1) //Left
			{
				if(movingDirectionUD == 1) //up
				{
					bounce = 2; //right up
					Constructors.debugMessage("Next Movement: right up");
					moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY - ballSpeedY); //Right up
				}
				else if(movingDirectionUD == 2) //down
				{
					bounce = 1; //Right down
					Constructors.debugMessage("Next Movement: Right down");
					moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY + ballSpeedY); //Right Down
				}
			}
			else if(movingDirectionLR == 2) //Right
			{
				if(movingDirectionUD == 1) //up
				{
					bounce = 4; //Left up
					Constructors.debugMessage("Next Movement: Left up");
					moveBall(GameScreen.ballX - ballSpeedX, GameScreen.ballY - ballSpeedY); //Left up
				}
				else if(movingDirectionUD == 2) //down
				{
					bounce = 3; //Left Down
					Constructors.debugMessage("Next Movement: Left Down");
					moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY - ballSpeedY); //Left down
				}
			}
		}
		else //Not Near Wall
		{
			if(movingDirectionLR == 1) //Left
			{
				if(movingDirectionUD == 1) //up
				{
					bounce = 3; //1 = Right Down, 2 = Right up, 3 = left Down, 4 = Left up
					Constructors.debugMessage("Next Movement: Left Down");
					moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY - ballSpeedY); //Left down
				}
				else if(movingDirectionUD == 2) //down
				{
					bounce = 4; //1 = Right Down, 2 = Right up, 3 = left Down, 4 = Left up
					Constructors.debugMessage("Next Movement: Left up");
					moveBall(GameScreen.ballX - ballSpeedX, GameScreen.ballY - ballSpeedY); //Left up
				}
			} 
			else if(movingDirectionLR == 2) //Right
			{
				if(movingDirectionUD == 1) //up
				{
					bounce = 1; //1 = Right Down, 2 = Right up, 3 = left Down, 4 = Left up
					Constructors.debugMessage("Next Movement: Right down");
					moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY + ballSpeedY); //Right Down
				}
				else if(movingDirectionUD == 2) //down
				{
					bounce = 2; //1 = Right Down, 2 = Right up, 3 = left Down, 4 = Left up
					Constructors.debugMessage("Next Movement: right up");
					moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY - ballSpeedY); //Right up
				}
			}
		}
	}
	
	public void startGame()
	{
		new Thread() {
			public void run() 
			{
				while(ballLive)
				{
					try 
					{
						Constructors.debugMessage("BallX: " + GameScreen.ballX + " BallY: " + GameScreen.ballY 
						+ " Bounces off wall? " + (nearWall ? "True" : "False"));
						
						//Clip Bounds: X = 5 - 600
						//Clip Bounds: Y = 5 - 420
						switch(bounce)
						{ 
							case 1:
									while(GameScreen.ballX > 5 && GameScreen.ballX < (Constructors.windowWidth - 40) //Left Right
										  && GameScreen.ballY > 5 && GameScreen.ballY < (Constructors.windowHeight - 60)) //Up Down
									{
										moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY + ballSpeedY); //Right Down
										Thread.sleep(10);
									}
								break;
							case 2:
									while(GameScreen.ballX > 5 && GameScreen.ballX < (Constructors.windowWidth - 40) 
										  && GameScreen.ballY > 5 && GameScreen.ballY < (Constructors.windowHeight - 60))
									{
										moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY - ballSpeedY); //Right up
										Thread.sleep(10);
									}
								break;
							case 3:
									while(GameScreen.ballX >= 5 && GameScreen.ballX < (Constructors.windowWidth - 40) 
										  && GameScreen.ballY > 5 && GameScreen.ballY < (Constructors.windowHeight - 60))
									{
										moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY - ballSpeedY); //Left down
										Thread.sleep(10);
									}
								break;
							case 4:
									while(GameScreen.ballX >= 5 && GameScreen.ballX < (Constructors.windowWidth - 40) 
									  && GameScreen.ballY > 5 && GameScreen.ballY < (Constructors.windowHeight - 60))
									{
										moveBall(GameScreen.ballX - ballSpeedX, GameScreen.ballY - ballSpeedY); //Left up
										Thread.sleep(10);
									}
								break;
						}
						calculateBounce();
					} 
					catch (Exception e) 
					{
						
					}
				}
			}
		}.start();
	}
}
