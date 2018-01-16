/*
 * Created by Noah Williams on 12/15/2017
 * 
 * Purpose: Every image uses this class.
 */
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.*;
public class GameObject {
	
	public int x = 0; //The x coordinate.
	public int y = 0; //The y coordinate
	public String name; //Name of the object.
	public ObjectGraphic texture; //image that it uses.
	public Map<String, ObjectGraphic> GraphicMap = new HashMap<String, ObjectGraphic>(); //A hashmap of images it may use.
	protected int ENUM = 0; //Enumerator for the hashkey.
	protected boolean showObject = true; //Decides if object should be visible or not.
	
	//---------------------------------------------------------
	//Constructor. Needs a position, a name, and an image.
	//---------------------------------------------------------
	public GameObject(int x, int y, String name, String image)
	{
		this.x = x;
		this.y = y;
		this.name = name;
		texture = new ObjectGraphic(image); //creates a new image from the string given.
		GraphicMap.put(name, texture); //puts that image in the hashmap.
	}
	
	//------------------------------------------------
	//Renders the image.
	//------------------------------------------------
	public void render(Graphics g, int x, int y)
	{
		if (texture.image != null) //is there an image?
			g.drawImage(texture.image, x, y, null); //draw it.
	}
	
	
	//------------------------------------------------
	//Change the graphic.
	//------------------------------------------------
	public void changeGraphic(ObjectGraphic text)
	{
		texture = text; //this graphic becomes the new graphic given.
	}
	
	//------------------------------------------------
	//Puts images inside hashMap.
	//------------------------------------------------
	public void setGraphicMap(String ...strings)
	{
		for(String str : strings) //for every string given.
		{
			ObjectGraphic text = new ObjectGraphic(str); //instantiate a graphic
			GraphicMap.put(name + ENUM, text); //put it in map.
			ENUM++; //enumerate hashKey.
		}
	}
	
	//------------------------------------------------
	//Setter/getter for showObject
	//------------------------------------------------
	public boolean getShow()
	{
		return showObject;
	}
	
	public void setShow(boolean bool)
	{
		showObject = bool;
	}
	
	//------------------------------------------------
	//Event Handlers.
	//------------------------------------------------
	//General perform.
	public void perform()
	{
		
	}
	
	//------------------------------------------------
	//If clicked
	//------------------------------------------------
	public void performClick()
	{
		
	}
	
	//------------------------------------------------
	//If mouse moved
	//------------------------------------------------
	public void performMove()
	{
		
	}
	
	//------------------------------------------------
	//If a color was changed.
	//------------------------------------------------
	public boolean performColorChange(int color, BlockObject blockObject)
	{
		return true;
		
	}
	
	public void resetColor()
	{
		
	}
}
