import java.awt.Graphics;

/*
 * Created by Noah Williams on 12/18/2017
 * 
 * Purpose: This is the how to play scene.
 * 
 *  This is polymorphed from Scene. All scenes are the same.
 * Therefore not much comments are needed for each indiviual scene.
 * 
 */
public class HowToPlayScene extends Scene{
	

	public GameObject backGround; //The background image
	public MenuObject prev; //The previous image
	public MenuObject next; //The next image.
	
	private int sceneFrame = 0; //Determines what picture it is currently on.
	
	//------------------------------------
	//Constructor.
	//------------------------------------
	public HowToPlayScene()
	{
		super("how"); //Gives name to parent class constructor.
		addObjects(); //adds all the objects.
		moveON = true; //This scene listens to mouse movement.
	}
	
	//---------------------------------------------------
	//Adds all objects and set up the scene.
	//---------------------------------------------------
	public void addObjects()
	{

		backGround = new GameObject(0, 0, "BackGround", "res/HOW1.png"); //Make the background.
		backGround.setGraphicMap("res/HOW2.png", "res/HOW3.png"); //Give it extra graphics.
		
		//-----------------------------------------------------
		//MenuObjects
		prev = new MenuObject(20, 50, "prev", "res/Prev1.png"); //Create the previous button
		next = new MenuObject(1100, 50, "next", "res/Next1.png"); //Create the next button
		prev.setGraphicMap("res/Prev2.png"); //give it extra graphics
		next.setGraphicMap("res/Next2.png");
		
		
		super.addObjects(backGround, prev, next); //Add all objects to arrayList.
	}
	
	
	//------------------------------------
	//render graphics
	//------------------------------------
	public void render(Graphics g)
	{
		super.render(g);
	}
	
	
	//------------------------------------
	//Decides if scene is still active.
	//------------------------------------
	public void setRender(boolean bool)
	{
		sceneRender = bool;
	}
	
	//------------------------------------
	//checks if scene is active.
	//------------------------------------
	public boolean getRender()
	{
		return sceneRender;
	}
	
	//------------------------------------
	//Perform every click.
	//------------------------------------
	@Override
	public void performClick()
	{
		for(GameObject obj : SceneObjects) //for all objects in scene.
		{
			if(obj.getClass() == MenuObject.class) //is it a menuObject?
				if(MathFunctions.WithinRange(obj, (int)Window.mouse.mouse_x, (int)Window.mouse.mouse_y)) //mouse hovers over it?
					if(obj.name == "next") //is it the next object?
					{
						switch (sceneFrame) //which frame is it currently on?
						{
							//If 0, go to frame 1. Change background.
							case 0:
								sceneFrame = 1;
								backGround.changeGraphic(backGround.GraphicMap.get(backGround.name + "0")); 
								break;
								//If 1, go to frame 2. Change background.
							case 1:
								sceneFrame = 2;
								backGround.changeGraphic(backGround.GraphicMap.get(backGround.name + "1"));
								break;
								//If 2 change scene.
							case 2:
								SceneHandler.changeScene(SceneHandler.SceneMap.get("mainmenu"));
								break;
						}

					}
					//If it is the previous object?
					else if (obj.name == "prev")
					{
						switch (sceneFrame)
						{
						    //change scene.
							case 0:
								SceneHandler.changeScene(SceneHandler.SceneMap.get("mainmenu"));
								break;
							//go backwards
							case 1:
								sceneFrame = 0;
								backGround.changeGraphic(backGround.GraphicMap.get(backGround.name));
								break;
							//go backwards.
							case 2:
								sceneFrame = 1;
								backGround.changeGraphic(backGround.GraphicMap.get(backGround.name + "0"));
								break;
						}
					}//End if statement.
		}//End for loop.
	}//end method.

}//end class
