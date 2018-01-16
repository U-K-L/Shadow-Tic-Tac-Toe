import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/*
 * Created by Noah Williams on 12/14/2017
 * 
 * Purpose: Used to manage the rendering of many objects.
 * Handles graphics.
 */
//-----------------------------
//Static class...No constructor
public class Render {
	
	public static Graphics2D g; //Global graphics variable for all classes to manipulate
	
	//------------------------------------
	//Draws the graphics to the screen.
	//------------------------------------
	public static void Draw()
	{
		//Safety net in case render goes wrong.
		try{
			//Checks if the buffer has created buffers.
			if (Window.buffer != null)
			{
				//----------------------------------------------
				//Begins to render.
				g = (Graphics2D) Window.buffer.getDrawGraphics();
			
				g.setColor(Color.BLUE); //Creates a large background.
				g.fillRect(0, 0, MainActivity.window.width, MainActivity.window.height);
				
				
				SceneHandler.render(g);
				
				//Dispose every update.
				g.dispose();
				//Shows next g
				Window.buffer.show();
			}
		}catch(Exception e){}
	}

}
