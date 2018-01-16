import java.util.ArrayList;

/*
 * Created by Noah Williams on 12/14/2017.
 * 
 * Purpose: The heart of the game. Handles flow, and loops. This is the game loop
 * and what processes start when and where.
 * 
 * This class also starts the application.
 */
public class MainActivity {

	//----------------------------------
	// Fields
	public static Window window; //The JFrame
	public static boolean gameOn = false; //Decides if game is on, if not system exit.
	private double FPS = 60.0; //Frames per second.
	public static boolean showFPS = false; //Show the test FPS in the console.
	public static MusicPlayer musicPlayer; //For BGM. BackGround Music.
	public static MusicPlayer soundPlayer; //For sound effects.

	
	
	//----------------------------------
	// Constructor.
	//-----------------------------------
	public MainActivity(int width, int height, String title)
	{
		window = new Window(width, height); //create our JFrame
		musicPlayer = new MusicPlayer();
		soundPlayer = new MusicPlayer();
		gameOn = true; //The game is on
		
		//---------------------------------
		//Used to manage Java's GC.
		//Flyweight pattern. Uses the same memory to constantly create the game.
		MainGame game = new MainGame(); //instantiate the mainGame once.
		MainMenu menu = new MainMenu(); //instantiate the mainMenu once.
		HowToPlayScene how = new HowToPlayScene(); //instantiate the HowToPlayScene once.
		VictoryScene victory = new VictoryScene(); //instantiate the VictoryScene once.
		SceneHandler.SceneMap.put(game.name, game); //put maingame in hashmap.
		SceneHandler.SceneMap.put(menu.name, menu); //put mainmenu in hashmap.
		SceneHandler.SceneMap.put(how.name, how); //put mainmenu in hashmap.
		SceneHandler.SceneMap.put(victory.name, victory); //put victory in hashmap.
		SceneHandler.SceneMap.get(menu.name).sceneRender = true; //make menu begin rendering.
		
		musicPlayer.loadSounds("res/Cheery_Monday.wav", "res/Balloon_Game.wav", "res/Android_Sock_Hop.wav");
		soundPlayer.loadSounds("res/Blop.wav");
		//----------------------------------------------------------
		
		SceneHandler.currentScene = SceneHandler.SceneMap.get(menu.name); //change scene to menu.
		musicPlayer.playMusic("res/Cheery_Monday.wav", true); //play music.
		
		CollisionGenerator.GetData(); //get data from IsoData.ser
		RunGame(); //Starts to run the game.
	}
	
	//---------------------
	// The game loop.
	//---------------------
	//Allows the game to run at a reasonable frame rate when animating.
	private void RunGame()
	{
	
		//----------------
		//Variables for calculating the time elasped.
		
		double nanoSec = 1000000000 / FPS; // NanoSecond within the FPS rate.
		long lastNanoSec = System.nanoTime(); // The previous counted nanosecond
		double delta = 0; //The change variable from calculus.
		
		long timer = System.currentTimeMillis(); //The computer's realtime clock miliseconds.
		int frames = 0; //Used to print how fast the game is going per frame..
		int CPS = 0; //Used to print the cycle speed per second.
		
		//---------------
		
		
		//-----------------------------------------
		// The game loop. Continues while the game is on.
		while(gameOn == true)
		{
			//-----------------------------------
			//Calculating the current elasped time
			long currentNanoSec = System.nanoTime(); //The current nano second. Renewed every cycle.
			
			//Getting the differnce between all of the variables:
			delta += (currentNanoSec - lastNanoSec) / nanoSec;
			lastNanoSec = currentNanoSec;
			//-----------------------------------
			
			
			//Updates every FRAME.
			while(delta >= 1)
			{
				frames++;
				delta--;
				RunFrame(); //updates frame.
			}
			
			//-------------------
			//Portion used for updating EVERY CYCLE.
			RunCycle(); //calls cycle update.
			CPS++;
			//-------------------
			
			//------------------------------------------
			//Updates every large elasped period of real time
			//independent of the computer's CPU.
			if(System.currentTimeMillis() - timer > 1000)
			{
				//Prints out the current frames.
				PrintFPS(frames, CPS);
				frames = 0;
				CPS = 0;
				timer += 1000;
			}
			//-------------------------------------------
				
		}//End of while loop.
		System.exit(0);
	}
	

	//------------------------
	//Updates every frame.
	private void RunFrame() {
		window.update();
	}
	
	//Updates every cycle.
	private void RunCycle() {
		
	}
	//-----------------------
	
	//Prints how fast clock per second and frame per second is.
	private void PrintFPS(int frames, int CPS) {
		
		if (showFPS == true)
			System.out.println("CPS: " + CPS + " FPS: " + frames);
		
	}
	
	
	//Starts the application.
	public static void main(String[] args)
	{
		new MainActivity(1280, 720, "Shadow Tic Tac Toe");
	}
}
