/*
 * Created by Noah Willams on 12/15/2017
 * 
 * Purpose: Handles mouse actions such as movement and clicking
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MenuListener;

public class MouseController implements MouseMotionListener {

	public float mouse_x = 0; //mouse X coordinate
	public float mouse_y = 0; //mouse Y coorinate
	public mouseListen mouse; //mouse listener.
	
	//----------------------
	//create a new mouse.
	//----------------------
	public void createMouse()
	{
		mouse = new mouseListen(); //instantiate mouse listener.
	}
	
	//--------------------------------
	//Not using.
	//--------------------------------
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	//--------------------------------
	//Gets mouse location.
	//--------------------------------
	@Override
	public void mouseMoved(MouseEvent mouse) {
		mouse_x = mouse.getX(); //X coordinate
		mouse_y = mouse.getY(); //Y coordinate
		
		//Extra nesting of safety.
		try{
			if(SceneHandler.currentScene != null) //if scene isn't null
				SceneHandler.currentScene.performMove(); //scene checks mouse coordinate.
		}catch(Exception e){e.printStackTrace();}
		
	}
	
	//---------------------------------------------
	//Mouse listener for certain items.
	//---------------------------------------------
	private class mouseListen implements MouseListener
	{
		//--------------------------------
		//Every mouse click
		//--------------------------------
		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			SceneHandler.currentScene.performClick(); //perform scene's click
		}

		//--------------------------------
		//Not using.
		//--------------------------------
		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}

		//--------------------------------
		//Not using.
		//--------------------------------
		@Override
		public void mouseExited(MouseEvent arg0) {
			
		}

		//--------------------------------
		//Not using.
		//--------------------------------
		@Override
		public void mousePressed(MouseEvent arg0) {
			
		}

		//--------------------------------
		//Not using.
		//--------------------------------
		@Override
		public void mouseReleased(MouseEvent arg0) {
			
		}
	
	}

}

