package game;

import java.util.HashMap;

import game.Constructors;

public class Main implements Constructors
{

	public static HashMap<Integer, Integer> Key = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) 
	{
		Constructors.getClient().notify();
	}
	
}
