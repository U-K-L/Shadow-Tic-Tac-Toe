
/*
 * Created by Noah Williams on 12/18/2017
 * 
 * Purpose: Mid scene between main game and title screen.
 * Used for player to pick colors.
 * 
 * This is polymorphed from Scene. All scenes are the same.
 * Therefore not much comments are needed for each indiviual scene.
 */
import java.awt.Graphics;
public class SelectionScene extends Scene {
	
	public GameObject selectionScreen; //Background.
	private MenuObject starPower; //The star power.
	private MenuObject shufflePower; //The shuffle power.
	public GameObject turn; //The turn image.
	
	private int screen = 0; //Which screen is background on.
	
	private int playerTurn = 0; //Which turn is it.
	
	public static int player1Power = 0; //Which power does the player have.
	public static int player2Power = 0; //Which power does the player have.
	
	//--------------------------------------
	//Constructor.
	//--------------------------------------
	public SelectionScene()
	{
		super("Selection"); //calls parent.
		addObjects(); //creates objects.
		moveON = true; //detects mouse movement.
	}
	
	//--------------------------------------
	//Sets up all objects on screen.
	//--------------------------------------
	public void addObjects()
	{
		//--------------------------------------------------
		//Creates the background.
		selectionScreen = new GameObject(0, 0, "SelectionScreen", "res/SelectionScreen.png"); //instanitate object.
		selectionScreen.setGraphicMap("res/SelectionShuffle.png", "res/SelectionStar.png"); //adds extra images
		
		//--------------------------------------------------
		//The powers
		starPower = new MenuObject(500, 130, "star", "res/Star1.png");
		starPower.setGraphicMap("res/Star2.png");
		
		shufflePower = new MenuObject(500, 350, "shuffle", "res/Shuffle1.png");
		shufflePower.setGraphicMap("res/Shuffle2.png");
		
		//--------------------------------------------------
		//Turn icons.
		turn = new GameObject(0,0, "turn", "res/SPlayer1.png");
		turn.setGraphicMap("res/SPlayer2.png");
			
		super.addObjects(selectionScreen, starPower, shufflePower, turn);
	}
	
	//---------------------------------
	//Renders objects.
	//---------------------------------
	public void render(Graphics g)
	{
		super.render(g);
	}
	
	//-------------------------------------
	//Decides if scene is active.
	//-------------------------------------
	public void setRender(boolean bool)
	{
		sceneRender = bool;
	}
	
	//-------------------------------------
	//Checks if scene is active.
	//-------------------------------------
	public boolean getRender()
	{
		return sceneRender;
	}
	
	//-------------------------------------
	//Perform click events.
	//-------------------------------------
	@Override
	public void performClick()
	{
		
		for(GameObject obj : SceneObjects)
		{
			if(obj.getClass() == MenuObject.class) //is object a menu object?
				if(MathFunctions.WithinRange(obj, (int)Window.mouse.mouse_x, (int)Window.mouse.mouse_y)) //mouse over object?
					if(obj.name == "star") //is it the star button?
					{
						if(playerTurn == 1) //is it player 2 turn?
						{
							player2Power = 1; //power is the star.
							MainActivity.musicPlayer.stopMusic();
							MainActivity.musicPlayer.playMusic("res/Android_Sock_Hop.wav", true);
							SceneHandler.changeScene(new MainGame()); //change scene to main game.
						}
						else
						{
							//power 1 power is the star
							playerTurn = 1; 
							player1Power = 1;
							turn.changeGraphic(turn.GraphicMap.get(turn.name + "0")); //changes turn graphic
						}
					}
					//----------------------------------------------------
					//repeat sequence for shuffle
					else if (obj.name == "shuffle")
					{
						if(playerTurn == 1)
						{
							player2Power = 2;
							MainActivity.musicPlayer.stopMusic();
							MainActivity.musicPlayer.playMusic("res/Android_Sock_Hop.wav", true);
							SceneHandler.changeScene(new MainGame());
						}
						else
						{
							playerTurn = 1;
							player1Power = 2;
							turn.changeGraphic(turn.GraphicMap.get(turn.name+ "0"));
						}
					}//-------------------------------------------------------------------
		}
	}
	
	
	//-------------------------------
	//Performed every mouse move.
	//-------------------------------
	public void performMove()
	{
		super.performMove(); //calls parent move.
		
		if(MathFunctions.WithinRange(starPower, (int)Window.mouse.mouse_x, (int)Window.mouse.mouse_y)) //mouse over star?
		{
			selectionScreen.changeGraphic(selectionScreen.GraphicMap.get(selectionScreen.name + "1")); //change background
		}
		else if(MathFunctions.WithinRange(shufflePower, (int)Window.mouse.mouse_x, (int)Window.mouse.mouse_y)) //mouse over shuffle
			selectionScreen.changeGraphic(selectionScreen.GraphicMap.get(selectionScreen.name + "0")); //change background
		else
			selectionScreen.changeGraphic(selectionScreen.GraphicMap.get(selectionScreen.name)); //neutral graphic
	}

}
