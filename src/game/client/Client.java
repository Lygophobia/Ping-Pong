package game.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Constructors;
import game.Main;
import game.client.ClippingLogic;

import javax.swing.JFrame;

public class Client implements KeyListener
{
	public Client()
	{
		JFrame client = new JFrame();
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setTitle("Ping Pong - Client");
		client.setSize(Constructors.windowWidth, Constructors.windowHeight);
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
		Constructors.debugMessage("Location X: " + Constructors.playerOne.x + " Location Y:" + Constructors.playerOne.y);
		Constructors.debugMessage("Clip X: "+ (Constructors.frameMovement) + " Clip Y: " + ((Constructors.windowHeight - (Constructors.frameMovement*3)) - Constructors.playerOne.height));
		
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
						if(Constructors.playerOne.y > (Constructors.frameMovement))
						{
							Constructors.playerOne.setLocation(Constructors.playerOne.x, Constructors.playerOne.y - Constructors.frameMovement);
						}
					}
					if(Main.Key.containsValue(KeyEvent.VK_S))
					{
						if(Constructors.playerOne.y < ((Constructors.windowHeight - (Constructors.frameMovement*4)) - Constructors.playerOne.height))
						{
							Constructors.playerOne.setLocation(Constructors.playerOne.x, (Constructors.playerOne.y + Constructors.frameMovement));
						}
					}

					if(Main.Key.containsValue(KeyEvent.VK_UP))
					{
						if(Constructors.playerTwo.y > (Constructors.frameMovement))
						{
							Constructors.playerTwo.setLocation(Constructors.playerTwo.x, Constructors.playerTwo.y - Constructors.frameMovement);
						}
					}
					if(Main.Key.containsValue(KeyEvent.VK_DOWN))
					{
						if(Constructors.playerTwo.y < ((Constructors.windowHeight - (Constructors.frameMovement*4)) - Constructors.playerTwo.height))
						{
							Constructors.playerTwo.setLocation(Constructors.playerTwo.x, (Constructors.playerTwo.y + Constructors.frameMovement));
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
	public void keyTyped(KeyEvent arg0) 
	{

	}

}
