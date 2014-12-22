package game.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Constructors;
import game.Main;
import game.client.ClippingLogic;
import game.client.GameScreen;

import javax.swing.JFrame;

public class Client implements KeyListener
{
	JFrame client = new JFrame();
	//Attempting to make it Resizeable.
	
	public Client()
	{
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setTitle("Ping Pong - Client");
		client.setSize((int)GameScreen.windowWidth, (int)GameScreen.windowHeight);
		client.setLocationRelativeTo(null);
		client.setResizable(false);
		client.addKeyListener(this);
		client.add(new GameScreen());

		client.addKeyListener(null);

		//client.pack();
		client.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent event)
	{

		Constructors.debugMessage("Key pressed : '" + event.getKeyChar() + "'");
		Constructors.debugMessage("Location X: " + GameScreen.playerOne.x + " Location Y:" + GameScreen.playerOne.y);
		Constructors.debugMessage("Clip X: "+ (Constructors.frameMovement) + " Clip Y: " + (((int)GameScreen.windowHeight - (Constructors.frameMovement*3)) - GameScreen.playerOne.height));
		
		int key = event.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE)
		{
			ClippingLogic.ballLive = true;
			Constructors.getClippingLogic().startGame();
			return;
		}
		
		Main.Key.put(key, key);
		
		new Thread() 
		{
			public void run() 
			{
				try 
				{
					if(Main.Key.containsValue(KeyEvent.VK_W))
					{
						if(GameScreen.playerOne.y > (Constructors.frameMovement))
						{
							GameScreen.playerOne.setLocation(GameScreen.playerOne.x, GameScreen.playerOne.y - Constructors.frameMovement);
						}
					}
					if(Main.Key.containsValue(KeyEvent.VK_S))
					{
						if(GameScreen.playerOne.y < (((int)GameScreen.windowHeight - (Constructors.frameMovement*4)) - GameScreen.playerOne.height))
						{
							GameScreen.playerOne.setLocation(GameScreen.playerOne.x, (GameScreen.playerOne.y + Constructors.frameMovement));
						}
					}

					if(Main.Key.containsValue(KeyEvent.VK_UP))
					{
						if(GameScreen.playerTwo.y > (Constructors.frameMovement))
						{
							GameScreen.playerTwo.setLocation(GameScreen.playerTwo.x, GameScreen.playerTwo.y - Constructors.frameMovement);
						}
					}
					if(Main.Key.containsValue(KeyEvent.VK_DOWN))
					{
						if(GameScreen.playerTwo.y < (((int)GameScreen.windowHeight - (Constructors.frameMovement*4)) - GameScreen.playerTwo.height))
						{
							GameScreen.playerTwo.setLocation(GameScreen.playerTwo.x, (GameScreen.playerTwo.y + Constructors.frameMovement));
						}
					}
					
					Thread.sleep(500);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{
		Main.Key.remove(event.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent event) 
	{

	}

}
