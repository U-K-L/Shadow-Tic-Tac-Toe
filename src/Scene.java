
/*
 * Created by Noah Williams on 12/15/2017
 * 
 * Purpose: The parent class of all other scenes.
 * Handles what GameObjects are rendered.
 */

import java.awt.Graphics;
import java.util.ArrayList;

public class Scene {
	
	public ArrayList <GameObject> SceneObjects = new ArrayList(); //ArrayList of objects.
	protected boolean sceneRender = false; //Decides if scene is active.
	public String name; //the scene's name.
	
	protected boolean moveON = false; //decides to detect mouse movement
	
	//------------------------------
	//Constructor.
	//Create a scene
	//------------------------------
	public Scene(String name)
	{
		this.name = name;
	}
	
	//------------------------------
	//Renders the objects in a scene.
	//------------------------------
	public void render(Graphics g)
	{
		for(GameObject obj : SceneObjects)
		{
			if(obj.showObject == true) //if the object is visible.
				obj.render(g, obj.x, obj.y); //draw that object.
		}
	}
	
	//-----------------------------------------------
	//Add objects to scene.
	//-----------------------------------------------
	public void addObjects(GameObject ...gameObjects)
	{
		for(GameObject obj : gameObjects)
		{
			SceneObjects.add(obj);
		}
	}
	
	//-----------------------------------------------
	//Every mouse click
	//-----------------------------------------------
	public void performClick()
	{
		for(GameObject obj : SceneObjects)
		{
			if(obj.showObject == true)
				obj.performClick();
		}
	}
	
	//-----------------------------------------------
	//every mouse movement.
	//-----------------------------------------------
	public void performMove()
	{
		if(moveON == true)
		{
			for(GameObject obj : SceneObjects)
			{
				if(obj.showObject == true)
					obj.performMove();
			}
		}
	}
	
	//-----------------------------------------------
	//Every color change.
	//-----------------------------------------------
	public void performColorChange(int color, BlockObject blockObject)
	{
		for(GameObject obj : SceneObjects)
		{
			if(obj.showObject == true)
				obj.performColorChange(color, blockObject);
		}
	}
	
	//---------------------------
	//Polymorphism
	//---------------------------
	public void whoWon(int player)
	{
		
	}
	
	//---------------------------
	//Polymorphism
	//---------------------------
	public void RestartBoard()
	{
		
	}

}
