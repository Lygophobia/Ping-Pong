package game.client;

import game.Constructors;
import game.client.GameScreen;

public class ClippingLogic extends GameScreen 
{
	public static boolean ballLive = false;
	
	public void moveBall(int x, int y)
	{
		GameScreen.ballX = x;
		GameScreen.ballY = y;
	}
	
	public void startGame()
	{
		new Thread() {
			public void run() 
			{
				int ballSpeedX = 2;
				int ballSpeedY = 1;
				while(ballLive)
				{
					try 
					{
						Constructors.debugMessage("BallX: " + GameScreen.ballX + " BallY: " + GameScreen.ballY);
						Constructors.debugMessage("BallX Clip: "+ Constructors.frameMovement  + " - "+ (Constructors.windowWidth - 15)
												  + " BallY Clip: " + Constructors.frameMovement + " - " + (Constructors.windowWidth - 15));
						
						while(GameScreen.ballX > 5 && GameScreen.ballX < (Constructors.windowWidth - 40) //Left Right
							  && GameScreen.ballY > 4 && GameScreen.ballY < (Constructors.windowHeight - 60)) //Up Down
						{
							moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY + ballSpeedY);
							Thread.sleep(10);
						}
						while(GameScreen.ballX > 5 && GameScreen.ballX < (Constructors.windowWidth - 40) 
							  && GameScreen.ballY > 5 && GameScreen.ballY < (Constructors.windowHeight - 59))
						{
							moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY - ballSpeedY);
							Thread.sleep(10);
						}
						
						while(GameScreen.ballX > 5 && GameScreen.ballX < (Constructors.windowWidth - 35) 
							  && GameScreen.ballY > 5 && GameScreen.ballY < (Constructors.windowHeight - 60))
						{
							moveBall(GameScreen.ballX - ballSpeedX, GameScreen.ballY - ballSpeedY);
							Thread.sleep(10);
						}
						
						while(GameScreen.ballX > 4 && GameScreen.ballX < (Constructors.windowWidth - 35) 
							  && GameScreen.ballY > 5 && GameScreen.ballY < (Constructors.windowHeight - 60))
						{
							moveBall(GameScreen.ballX + ballSpeedX, GameScreen.ballY - ballSpeedY);
							Thread.sleep(10);
						}
						

						
						
					} 
					catch (Exception e) 
					{
						
					}
				}
			}
		}.start();
	}
}
