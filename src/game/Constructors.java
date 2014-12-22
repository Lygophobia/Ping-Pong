package game;


import game.client.Client;
import game.client.ClippingLogic;

public interface Constructors {
	
	//Attempting to make it Resizeable.
	
	public final Client Client = new Client();
	public final ClippingLogic ClippingLogic = new ClippingLogic();
	
	public static Client getClient()
	{
		return Client;
	}
	
	public static ClippingLogic getClippingLogic()
	{
		return ClippingLogic;
	}
	
	public static final byte frameMovement = 10;
	public static final byte maxSpeed = 15;
	
	public boolean debugMessages = false;
	
	public static void debugMessage(String s)
	{
		if(debugMessages)
		{
			System.out.println("DEBUG: " + s);
		}
	}
}
