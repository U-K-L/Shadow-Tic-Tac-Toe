/*
 * Created by Noah Williams on 12/19/2017
 * 
 * Purpose: The Shadow Tic Tac Board Scene.
 * This handles the shadow tic tac toe board
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class MainGame extends Scene {

	private ArrayList <ShadowObject> ShadowObjects = new ArrayList<ShadowObject>(); //Shadow Board
	private ArrayList <BlockObject> BlockObjects = new ArrayList<BlockObject>(); //Regular Board.
	private ArrayList <Integer> colors = new ArrayList<Integer>(); //Colors for shuffling.
	
	public static boolean moveTwice = false; //Can they move twice? (Special power)
	public static byte PlayerTurn = 0; //Which turn is it?
	private int player1Power = 0; //What special power does player 1 have?
	private int player2Power = 0; //What special power does player 2 have?
	
	public MenuObject retry; //Retry button.
	public MenuObject quit; // quit button.
	public GameObject turn; //turn buttons.
	public MenuObject starPower; //star power button.
	public MenuObject shufflePower; //shuffle power button.
	
	//-----------------------------------------
	//Constructor.
	//-----------------------------------------
	public MainGame()
	{
		super("maingame"); //puts name in.
		
		this.PlayerTurn = 0; //turn is 0.
		moveON = true; //this scene listens to mouse.
		moveTwice = false; //moveTwice is false.

		this.player1Power = SelectionScene.player1Power; //Gets the player's powers
		this.player2Power = SelectionScene.player2Power; //Gets the player's powers
		
		//------------------------------------------------------------
		//create color array.
		Integer[] colorList = new Integer[] {0,1, 2, 3, 4, 5,6,7,8};
		//-----------------
		colors.addAll(Arrays.asList(colorList));
		//add and start objects.
		addObjects();
		CreateShadowObjects();
	}
	
	//----------------------------------------
	//Restarts the board
	//----------------------------------------
	public void RestartBoard()
	{
		moveTwice = false; //cannot move twice.
		
		//obtain powers.
		this.player1Power = SelectionScene.player1Power;
		this.player2Power = SelectionScene.player2Power;
		PlayerTurn = 0;
		
		//Changes if the power button is visible.
		if(player1Power == 1)//If the player 1 power is the star.
		{
			starPower.showObject = true;
			shufflePower.showObject = false;
		}
		//If player1 power is shuffle.
		else if (player1Power == 0)
		{
			shufflePower.showObject = true;
			starPower.showObject = false;
		}
		
		
		//Change blocks back to original color
		for(GameObject block : SceneObjects)
		{
			if( (block.getClass() == BlockObject.class) || (block.getClass() == ShadowObject.class)) //Is it a block or shadow?
			{
				block.resetColor();
			}
		}
		
		ShuffleObjects(); //Shuffle shadow board objects.
	}

	public void addObjects()
	{
		GameObject background = new GameObject(0,0, "background", "res/MainMenuBack.png"); //create background
		
		//Create turn button.
		turn = new GameObject(0,0, "turn", "res/Player1.png");
		turn.setGraphicMap("res/Player2.png");
		
		//-------------------------------------
		//create various buttons.
		retry = new MenuObject(10, 615, "Retry", "res/SReplay1.png");
		quit = new MenuObject(980, 615, "Quit", "res/SQuit1.png");
		retry.setGraphicMap("res/SReplay2.png");
		quit.setGraphicMap("res/SQuit2.png");
		
		starPower = new MenuObject(470, 500, "Star", "res/Star1.png");
		starPower.setGraphicMap("res/Star2.png");
		starPower.showObject = false;
		
		shufflePower = new MenuObject(470, 500, "shuffle", "res/Shuffle1.png");
		shufflePower.setGraphicMap("res/Shuffle2.png");
		shufflePower.showObject = false;
		//---------------------------------------
		
		//Changes if the power button is visible.
		if(player1Power == 1)//If the player 1 power is the star.
		{
			starPower.showObject = true;
			shufflePower.showObject = false;
		}
		//If player1 power is shuffle.
		else if (player1Power == 0)
		{
			shufflePower.showObject = true;
			starPower.showObject = false;
		}
		
		updatePowerBlocks(); //update the powers button
		super.addObjects(background, turn, retry, quit, starPower, shufflePower); //adds in arraylist.
		addBlocks(); //adds all the blocks
	}
	
	//--------------------------------
	//Creates all blocks needed for board.
	//--------------------------------
	private void addBlocks()
	{
		//First ROW
		BlockObject Red = new BlockObject(50, 200, "Red", "res/Red.png", 0);
		BlockObjects.add(Red);
		BlockObject Blue = new BlockObject(159, 159, "Blue", "res/Blue.png", 1);
		BlockObjects.add(Blue);
		BlockObject Green = new BlockObject(268, 119, "Green", "res/Green.png",2);
		BlockObjects.add(Green);
		
		//-------------
		//Second ROW
		BlockObject Pink = new BlockObject(50, 330, "Pink", "res/Pink.png", 6);
		BlockObjects.add(Pink);
		BlockObject Purple = new BlockObject(159, 290, "Purple", "res/Purple.png", 7);
		BlockObjects.add(Purple);
		BlockObject Orange = new BlockObject(268, 250, "Orange", "res/Orange.png", 5);
		BlockObjects.add(Orange);
		
		//-------------
		//Third ROW
		BlockObject Brown = new BlockObject(50, 460, "Brown", "res/Brown.png", 3);
		BlockObjects.add(Brown);
		BlockObject Gray = new BlockObject(159, 420, "Gray", "res/Gray.png", 4);
		BlockObjects.add(Gray);
		BlockObject Yellow = new BlockObject(268, 380, "Yellow", "res/Yellow.png", 8);
		BlockObjects.add(Yellow);
		
		super.addObjects(Red, Blue, Green, Pink, Purple, Orange, Brown, 
				Gray, Yellow); //add those objects.
	}
	
	//-------------------------------------------
	//Creates all blocks needed for shadow board.
	//-------------------------------------------
	private void CreateShadowObjects()
	{
		ShadowObject block1 = new ShadowObject(1130, 200, "one", "res/Red.png");
		ShadowObject block2 = new ShadowObject(1021, 159, "two", "res/Red.png");
		ShadowObject block3 = new ShadowObject(912, 119, "three", "res/Red.png");
		
		ShadowObject block4 = new ShadowObject(1130, 330, "four", "res/Red.png");
		ShadowObject block5 = new ShadowObject(1021, 290, "five", "res/Red.png");		
		ShadowObject block6 = new ShadowObject(912, 250, "six", "res/Red.png");
		
		ShadowObject block7 = new ShadowObject(1130, 460, "seven", "res/Red.png");
		ShadowObject block8 = new ShadowObject(1021, 420, "eight", "res/Red.png");
		ShadowObject block9 = new ShadowObject(912, 380, "nine", "res/Red.png");
		
		addShadowObjects(block1, block2, block3, block4, block5, block6,
						 block7, block8, block9); //adds blocks to an array list
		
		super.addObjects(block1, block2, block3, block4, block5, block6,
				 block7, block8, block9);
		
		ShuffleObjects(); //Shuffle all objects colors.
	}
	
	//---------------------------------------
	//Adds shadow boards to a list.
	//---------------------------------------
	public void addShadowObjects(ShadowObject ...gameObjects)
	{
		for(ShadowObject obj : gameObjects)
		{
			ShadowObjects.add(obj);
		}
	}
	
	//-------------------------------------
	//Shuffle board.
	//-------------------------------------
	public void ShuffleObjects()
	{
		Collections.shuffle(colors); //Shuffle colors.
		for(int i = 0; i < 9; i++)
		{
			//---------------------------
			//Adds those colors to the board.
			ShadowObjects.get(i).originalColor = colors.get(i); //first ever color.
			ShadowObjects.get(i).color = colors.get(i); //dynamic color that changes.
			ShadowObjects.get(i).setColor(); //sets the graphic.
		}
		
	}
	
	//-----------------------------
	//Performs every click.
	//-----------------------------
	public void performClick()
	{
		super.performClick();
		
			
		//Checks both boards for winning conditions
		CheckVictory();
		
		//--------------------------------------
		//Performs every action within SceneObjects.
		for(GameObject obj : SceneObjects)
		{
			if(obj.getClass() == MenuObject.class) //If it is a menuObject
				if(MathFunctions.WithinRange(obj, (int)Window.mouse.mouse_x, (int)Window.mouse.mouse_y))//If mouse is over it?
					
					//--------------------------
					//If it is the retry button
					if(obj.name == "Retry")
					{
						RestartBoard();
					}
					//If it is the quit button
					else if (obj.name == "Quit")
					{
						MainActivity.gameOn = false;
						System.exit(0);
					}
					//If it is the star button.
					else if(obj.name == "Star" && obj.showObject == true)
					{
						PerformMoveTwice(); //Perform move twice.
						starPower.showObject = false; //Object is no longer shown.
						if(PlayerTurn == 1)
							player2Power = 0;
						else
							player1Power = 0;
					}
					//if it is the shuffle button.
					else if(obj.name == "shuffle" && obj.showObject == true)
					{
						PerformPowerShuffle();
						shufflePower.showObject = false;
						if(PlayerTurn == 1)
							player2Power = 0;
						else
							player1Power = 0;
					}
		}
		
		updatePowerBlocks(); //Update the power buttons.
			
	}
	
	//---------------------------------------
	//Handles victory sequence.
	//---------------------------------------
	public void CheckVictory()
	{
		//-------------------------------------------------
		//Calls various functions to check if board is won.
		//--------
		//Shadow Board
		if(MathFunctions.ShadowCheckWin(ShadowObjects) == 27 ) //Is it filled with white?
		{
			MainActivity.musicPlayer.stopMusic(); //Stop music
			MainActivity.musicPlayer.playMusic("res/Balloon_Game.wav", true); //Play music
			SceneHandler.changeScene(SceneHandler.SceneMap.get("Victory")); //change scene to victory.
			SceneHandler.SceneMap.get("Victory").whoWon(0); //check who won P1 or P2?
		}
		else if(MathFunctions.ShadowCheckWin(ShadowObjects) == 30) //Is it filled with black?
		{
			MainActivity.musicPlayer.stopMusic();
			MainActivity.musicPlayer.playMusic("res/Balloon_Game.wav", true);
			SceneHandler.changeScene(SceneHandler.SceneMap.get("Victory"));
			SceneHandler.SceneMap.get("Victory").whoWon(1);
		}
		
		
		//-----------------------------
		//Normal Board.
		//---------------------------
		//Calls a function that gets the sum of a column.
		if(MathFunctions.BlockCheckWin(BlockObjects) == 27)
		{
			MainActivity.musicPlayer.stopMusic();
			MainActivity.musicPlayer.playMusic("res/Balloon_Game.wav", true);
			SceneHandler.changeScene(SceneHandler.SceneMap.get("Victory"));
			SceneHandler.SceneMap.get("Victory").whoWon(0);
		}
		else if(MathFunctions.BlockCheckWin(BlockObjects) == 30)
		{
			MainActivity.musicPlayer.stopMusic();
			MainActivity.musicPlayer.playMusic("res/Balloon_Game.wav", true);
			SceneHandler.changeScene(SceneHandler.SceneMap.get("Victory"));
			SceneHandler.SceneMap.get("Victory").whoWon(1);
		}
	}
	
	//--------------------------------
	//Changes the powers graphics
	//--------------------------------
	public void updatePowerBlocks()
	{
		
		
		//Changes the turn graphic.
		if(PlayerTurn == 1)
		{
			turn.changeGraphic(turn.GraphicMap.get(turn.name+ "0"));
			if(player2Power == 1)
			{
				starPower.showObject = true;
				shufflePower.showObject = false;
			}
			else if (player2Power == 2)
			{
				shufflePower.showObject = true;
				starPower.showObject = false;
			}
		}
		else
		{
			turn.changeGraphic(turn.GraphicMap.get(turn.name));
			if(player1Power == 1)
			{
				starPower.showObject = true;
				shufflePower.showObject = false;
			}
			else if (player1Power == 2)
			{
				shufflePower.showObject = true;
				starPower.showObject = false;
			}
		}
		
	}
	
	//----------------------------------------
	//Special power. Allows player to shuffle colors on shadow board.
	//----------------------------------------
	public void PerformPowerShuffle()
	{
		Collections.shuffle(colors); //Shuffles the colors
		ArrayList <Integer> notUsableColors = new ArrayList<Integer>(); //creates an arraylist
		
		//--------------------------------
		//Checks if color is already used
		for(int i = 0; i < 9; i++)
		{
			if(ShadowObjects.get(i).color >= 9)
			{
				notUsableColors.add(ShadowObjects.get(i).originalColor); //stores it in an arrayList.
			}
		}
		
		//-------------------------------------------
		//Does the shuffling.
		for(int i = 0; i < 9; i++) //For every color
		{
			if(MathFunctions.InsideArray(colors.get(i), notUsableColors)) //Is that color not usable.
			{
				if(ShadowObjects.get(i).color < 9) //Is that object a player color?
				{
					ShadowObjects.get(i).color = colors.get(i); //object's color = to shuffled color.
					ShadowObjects.get(i).setColor();
				}
			}
		}
	}
	
	//-------------------------------
	//Special power.
	//Allows Player to move twice 
	//-----------------------------
	public void PerformMoveTwice()
	{
		moveTwice = true;
	}
	
	//----------------------------
	//Renders objects
	//----------------------------
	public void render(Graphics g)
	{
		super.render(g);
	}
	
	
	//-------------------------------------
	//Set the if scene is visible
	//-------------------------------------
	public void setRender(boolean bool)
	{
		sceneRender = bool;
	}
	
	//-------------------------------------
	//Get visible scene.
	//-------------------------------------
	public boolean getRender()
	{
		return sceneRender;
	}
}
