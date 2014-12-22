package game;

import java.awt.Rectangle;

import game.client.Client;

public interface Constructors {
	
	public final Client Client = new Client();
	
	public static Client getClient()
	{
		return Client;
	}
	
	public static final int windowHeight = 480;
	public static final int windowWidth = 640;
	public static final byte frameMovement = 10;
	
	public static Rectangle Field = new Rectangle(5, 5, (windowWidth - 15), (windowHeight - 40));
	
	public static Rectangle playerOne = new Rectangle(10, 10, 10, 100);
	public static Rectangle playerTwo = new Rectangle((windowWidth - 25), 10, 10, 100);
	
	public boolean debugMessages = true;
	
	public static void debugMessage(String s)
	{
		if(debugMessages)
		{
			System.out.println(s);
		}
	}
}
