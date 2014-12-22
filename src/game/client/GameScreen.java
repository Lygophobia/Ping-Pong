package game.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class GameScreen extends JPanel
{
	public static double windowHeight = 480;
	public static double windowWidth = 640;
	
	public static int ballX = 90;
	public static int ballY = ((int)windowHeight / 2);
	
	public static Rectangle Field = new Rectangle(5, 5, ((int)GameScreen.windowWidth - 15), ((int)GameScreen.windowHeight - 40));
	public static Rectangle Field2 = new Rectangle(4, 4, ((int)GameScreen.windowWidth - 15), ((int)GameScreen.windowHeight - 40));
	public static Rectangle Fancy1 = new Rectangle((((int)GameScreen.windowWidth / 2) - 10), 5, 1, ((int)GameScreen.windowHeight - 40));
	
	public static Rectangle playerOne = new Rectangle(10, 10, 10, 100);
	public static Rectangle playerTwo = new Rectangle(((int)GameScreen.windowWidth - 25), 10, 10, 100);
	
	//Attempting to make it Resizeable.
	
	@Override
    public void paintComponent(Graphics g) 
	{
        super.paintComponents(g);
        drawGame(g);
    }
	
	private void drawGame(Graphics g)
	{
		try
		{
			Graphics2D g2d = (Graphics2D) g;
			
			cleanScreen(g2d);
			refreshVariables((int)windowWidth, (int)windowHeight, Field, Field2, Fancy1, playerTwo);
			drawField(g2d);
	        drawPaddles(g2d);
	        drawBall(g2d);
	        
	    	repaint();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void drawBall(Graphics2D g2d)
	{
		g2d.setColor(Color.cyan);
		g2d.fillOval(ballX, ballY, 25, 25);
	}
	
	public void drawField(Graphics2D g2d)
	{
		g2d.setColor(Color.WHITE);
		g2d.draw(GameScreen.Field);
		g2d.draw(GameScreen.Field2);
		g2d.draw(GameScreen.Fancy1);
	}
	
	public void refreshVariables(int windowWidth, int windowHeight, Rectangle Field, Rectangle Field2, Rectangle Fancy1, Rectangle playerTwo)
	{
		GameScreen.Field = Field;
		GameScreen.Field2 = Field2;
		GameScreen.Fancy1 = Fancy1;
		GameScreen.playerTwo = playerTwo;
	}
	
	public void drawPaddles(Graphics2D g2d)
	{
		g2d.setColor(Color.GREEN);
		g2d.fillRect(GameScreen.playerOne.x, GameScreen.playerOne.y,
					 GameScreen.playerOne.width, GameScreen.playerOne.height);
		
		g2d.fillRect(GameScreen.playerTwo.x, GameScreen.playerTwo.y,
				 GameScreen.playerTwo.width, GameScreen.playerTwo.height);
	}
	
	public void cleanScreen(Graphics2D g2d)
	{
		g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, (int)windowWidth, (int)windowHeight);
	}
}
