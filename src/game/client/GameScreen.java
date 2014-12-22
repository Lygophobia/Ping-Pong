package game.client;

import game.Constructors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameScreen extends JPanel
{
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
			drawField(g2d);
	        drawPaddles(g2d);
	        
	    	repaint();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void drawField(Graphics2D g2d)
	{
		g2d.setColor(Color.WHITE);
		g2d.draw(Constructors.Field);
	}
	
	public void drawPaddles(Graphics2D g2d)
	{
		g2d.setColor(Color.GREEN);
		g2d.fillRect(Constructors.playerOne.x, Constructors.playerOne.y,
					 Constructors.playerOne.width, Constructors.playerOne.height);
		
		g2d.fillRect(Constructors.playerTwo.x, Constructors.playerTwo.y,
				 Constructors.playerTwo.width, Constructors.playerTwo.height);
	}
	
	public void cleanScreen(Graphics2D g2d)
	{
		g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Constructors.windowWidth, Constructors.windowHeight);
	}
}
