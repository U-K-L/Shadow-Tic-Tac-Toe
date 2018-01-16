/*
 * Creadted by Noah Williams on 12/18/2017
 * 
 * Purpose: The starting menu.
 * 
 * This is polymorphed from Scene. All scenes are the same.
 * Therefore not much comments are needed for each indiviual scene.
 */
import java.awt.Graphics;

public class MainMenu extends Scene {

	public GameObject backGround; //the background.
	public GameObject sideBars; //The circle side bars.
	public MenuObject play; //the play button.
	public MenuObject quit; //the quit button.
	public MenuObject how; //the how to play button.
	
	//-----------------------------------------
	//Constructor.
	//-----------------------------------------
	public MainMenu()
	{
		super("mainmenu"); //calls parent class and gives name.
		addObjects(); //creates all objects.
		moveON = true; //this scene detects mouse movement.
	}
	
	//-----------------------------------------
	//Creates objects for scene.
	//-----------------------------------------
	public void addObjects()
	{
		//-------------------------------------------------------------------
		//Creates unanimated objects.
		backGround = new GameObject(0, 0, "BackGround", "res/Background.png");
		sideBars = new GameObject(0, 0, "sidebars", "res/SideBars.png");
		
		//-------------------------------------------------------------------
		//Creates Menu Objects
		play = new MenuObject(218, 88, "play", "res/Play1.png");
		quit = new MenuObject(218, 480, "quit", "res/Quit1.png");
		how = new MenuObject(218, 280, "how", "res/HowTo1.png");
		//-------------------------------------------------------------------
		//Gives extra graphics.
		how.setGraphicMap("res/HowTo2.png");
		play.setGraphicMap("res/Play2.png");
		quit.setGraphicMap("res/Quit2.png");
		
		
		super.addObjects(backGround, sideBars, play, quit, how); //add all objects to ArrayList.
	}
	
	//-----------------------------------
	//Render objects
	//-----------------------------------
	public void render(Graphics g)
	{
		super.render(g);
	}
	
	//-----------------------------------
	//Set if scene is active
	//-----------------------------------
	public void setRender(boolean bool)
	{
		sceneRender = bool;
	}
	
	//-----------------------------------
	//Check if scene is active
	//-----------------------------------
	public boolean getRender()
	{
		return sceneRender;
	}
	
	//-----------------------------------
	//Perform every click.
	//-----------------------------------
	@Override
	public void performClick()
	{
		for(GameObject obj : SceneObjects)
		{
			if(obj.getClass() == MenuObject.class)//is it a menu object.
				if(MathFunctions.WithinRange(obj, (int)Window.mouse.mouse_x, (int)Window.mouse.mouse_y)) //is mouse over it?
					if(obj.name == "play") //is the object the play button.
						SceneHandler.changeScene(new SelectionScene()); //change scene.
					else if (obj.name == "quit") //is the object the quit button?
					{
						//Exit game.
						MainActivity.gameOn = false;
						System.exit(0);
					}
					else if (obj.name == "how") //is the object the how to play button?
					{
						SceneHandler.changeScene(SceneHandler.SceneMap.get("how")); //change scene.
					}
		}
	}
}
