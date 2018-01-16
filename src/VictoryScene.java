import java.awt.Graphics;

/*
 * Created by Noah Williams on 12/18/2017.
 * 
 * Purpose: Victory screen upon winning the game.
 * 
 * This is polymorphed from Scene. All scenes are the same.
 * Therefore not much comments are needed for each indiviual scene.
 */
public class VictoryScene extends Scene {
	
	public GameObject backGround; //Make background
	public MenuObject retry; //retry button
	public MenuObject quit; //quit button
	
	//---------------------
	//constructor
	//---------------------
	public VictoryScene()
	{
		super("Victory");
		addObjects();
		moveON = true; //detects mouse
	}

	
	public void addObjects()
	{
		
		//---------------------------
		//Create background.
		backGround = new GameObject(0, 0, "BackGround", "res/Victory1.png");
		backGround.setGraphicMap("res/Victory2.png");
		
		//-----------------------------
		//Create buttons
		retry = new MenuObject(10, 535, "Retry", "res/VReplay1.png");
		quit = new MenuObject(720, 535, "Quit", "res/VQuit1.png");
		retry.setGraphicMap("res/VReplay2.png");
		quit.setGraphicMap("res/VQuit2.png");
		
		
		super.addObjects(backGround, retry, quit);
	}
	
	//---------------------------------
	//Decides checks who won.
	//---------------------------------
	public void whoWon(int player)
	{
		if(player > 0)
			backGround.changeGraphic(backGround.GraphicMap.get(backGround.name + "0")); //changes the victory screen.
	}
	
	//--------------------------------------------
	//Perform every click
	//Activate retry or quit button.
	//--------------------------------------------
	@Override
	public void performClick()
	{
		for(GameObject obj : SceneObjects) //for every object.
		{
			if(obj.getClass() == MenuObject.class) //is it a button.
				if(MathFunctions.WithinRange(obj, (int)Window.mouse.mouse_x, (int)Window.mouse.mouse_y)) //is mouse over it?
					if(obj.name == "Retry") //is it retry button?
					{
						//Change scene, reset music, restart board.
						MainActivity.musicPlayer.stopMusic();
						MainActivity.musicPlayer.playMusic("res/Android_Sock_Hop.wav", true);
						SceneHandler.changeScene(SceneHandler.SceneMap.get("maingame"));
						SceneHandler.SceneMap.get("maingame").RestartBoard();
					}
					else if (obj.name == "Quit")
					{
						MainActivity.gameOn = false;
						System.exit(0);
					}
		}
	}
	
	//---------------------------------
	//Render
	//---------------------------------
	public void render(Graphics g)
	{
		super.render(g);
	}
	
	//---------------------------------
	//set active.
	//---------------------------------
	public void setRender(boolean bool)
	{
		sceneRender = bool;
	}
	
	//---------------------------------
	//is it avtive
	//---------------------------------
	public boolean getRender()
	{
		return sceneRender;
	}

}
