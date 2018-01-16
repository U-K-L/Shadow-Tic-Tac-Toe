/*
 * Created by Noah Williams on 12/17/2017
 * 
 * Purpose: Polymorhped class derived from GameObject.
 * Used to do spefic actions to the LEFT SIDE BOARD.
 * 
 * Actions such as: call actionListener, special fields,
 * get extra graphics.
 */
public class BlockObject extends GameObject {
	
	public ActionHandler action; //Each object has its own ActionListener. Improves
	//time complexity, but at a memory cost. 
	
	public int color = 0; //The color of this object.
	public int playerColor = 0;//Color used for a player's click
	
	//0 = RED, 1 = BLUE, 2 = GREEN, 3 = BROWN, 4 = GRAY, 5 = ORANGE, 6 = PINK,
	//7 = PURPLE, 8 = YELLOW, 9 = WHITE, 10 = BLACK. //INDEX COLORS

	//----------------------------------------------------
	//Constructor. Calls it's parent class.
	//----------------------------------------------------
	public BlockObject(int x, int y, String name, String image, int color) {
		super(x, y, name, image); //parent class constructor.	
		this.color = color;
		
		action = new ActionHandler();//instantiate an ActionListener.
		
		//---------------------------------------
		//Adds black and white graphic to hashMap.
		ObjectGraphic text = new ObjectGraphic("res/Black.png");
		GraphicMap.put("Black", text);
		ObjectGraphic text2 = new ObjectGraphic("res/White.png");
		GraphicMap.put("White", text2);
		//--------------------------------------
	}
	
	//----------------------------------------------------
	//Whenever the player clicks, this is performed.
	//Checks if the mouse is on the object AND that no color
	//is already in place.
	//----------------------------------------------------
	public void performClick()
	{
		if(this.playerColor < 9) //Is it below the player's index color (BLACK AND WHITE)
			if(MathFunctions.WithinRange(this, (int)Window.mouse.mouse_x, (int)Window.mouse.mouse_y)) //is mouse on the image?
				if(CollisionGenerator.CheckIsoData(this)) //Is it on the visible graphic, or the empty edges of the rectangle?
				{
					//Change it's color
					action.BlockActionPerformed(this);
					//And perform all events that respond to this color change.
					SceneHandler.currentScene.performColorChange(this.color, this);
				}
	}
	
	public void resetColor()
	{
		this.playerColor = this.color;
		changeGraphic(this.GraphicMap.get(this.name));
	}

}
