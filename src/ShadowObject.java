/*
 * Created by Noah Williams on 12/16/2017
 * 
 * Purpose: Object for all of the shadow board.
 */
public class ShadowObject extends GameObject {

	public int color = 0; //dynamic color
	public int playerColor = 0; //Player's color
	public int originalColor = 0; //original color.
	
	//0 = RED, 1 = BLUE, 2 = GREEN, 3 = BROWN, 4 = GRAY, 5 = ORANGE, 6 = PINK,
	//7 = PURPLE, 8 = YELLOW, 9 = WHITE, 10 = BLACK.
	
	//-----------------------------------------------------------
	//Construtor
	//-----------------------------------------------------------
	public ShadowObject(int x, int y, String name, String image) {
		super(x, y, name, image);
		
		//Adds every graphic color to every shadow board
		//---------------------------------------------------
		ObjectGraphic red = new ObjectGraphic("res/SRed.png");
		GraphicMap.put("Red", red);
		
		ObjectGraphic blue = new ObjectGraphic("res/SBlue.png");
		GraphicMap.put("Blue", blue);
		
		ObjectGraphic green = new ObjectGraphic("res/SGreen.png");
		GraphicMap.put("Green", green);
		
		
		
		ObjectGraphic brown = new ObjectGraphic("res/SBrown.png");
		GraphicMap.put("Brown", brown);
		
		ObjectGraphic gray = new ObjectGraphic("res/SGray.png");
		GraphicMap.put("Gray", gray);
		
		ObjectGraphic orange = new ObjectGraphic("res/SOrange.png");
		GraphicMap.put("Orange", orange);
		
		
		
		ObjectGraphic pink = new ObjectGraphic("res/SPink.png");
		GraphicMap.put("Pink", pink);
		
		ObjectGraphic purple = new ObjectGraphic("res/SPurple.png");
		GraphicMap.put("Purple", purple);
		
		ObjectGraphic yellow = new ObjectGraphic("res/SYellow.png");
		GraphicMap.put("Yellow", yellow);
		
		
		
		ObjectGraphic black = new ObjectGraphic("res/Black.png");
		GraphicMap.put("Black", black);
		
		ObjectGraphic white = new ObjectGraphic("res/White.png");
		GraphicMap.put("White", white);
		
		//------------------------------------------------------------
		
	}
	
	//----------------------------------------------------
	//Changes color to corresponding right board.
	//----------------------------------------------------
	public boolean performColorChange(int clickedColor, BlockObject obj)
	{
		if(color == clickedColor) //If both colors match.
		{
			if(MainGame.PlayerTurn == 0) //find which player turn.
			{
				//-----------------------------------------------
				//Change it to that color and change the turn.
				if(MainGame.moveTwice == false)
					MainGame.PlayerTurn = 1;
				else
					MainGame.moveTwice = false;
				
				this.changeGraphic(this.GraphicMap.get("White"));
				this.color = 9;
				return true;
				//------------------------------
			}
			
			//----------------------------------------
			//Repeat if player 2
			if(MainGame.PlayerTurn == 1)
			{
				if(MainGame.moveTwice == false)
					MainGame.PlayerTurn = 0;
				else
					MainGame.moveTwice = false;
				this.changeGraphic(this.GraphicMap.get("Black"));
				this.color = 10;
				return true;
			}
		}
		return false;
		//-----------------------------------------------
	}
	
	//--------------------------------------
	//Sets the color of shadow board.
	//--------------------------------------
	public void setColor()
	{
		//0 = RED, 1 = BLUE, 2 = GREEN, 3 = BROWN, 4 = GRAY, 5 = ORANGE, 6 = PINK,
		//7 = PURPLE, 8 = YELLOW, 9 = WHITE, 10 = BLACK.
		//-----------------------------------------------------
		//Change graphic to the exact color shown.
		switch (color)
		{
			case 0:
				this.changeGraphic(this.GraphicMap.get("Red"));
				break;
				
			case 1:
				this.changeGraphic(this.GraphicMap.get("Blue"));
				break;
				
			case 2:
				this.changeGraphic(this.GraphicMap.get("Green"));
				break;
				
			case 3:
				this.changeGraphic(this.GraphicMap.get("Brown"));
				break;
				
			case 4:
				this.changeGraphic(this.GraphicMap.get("Gray"));
				break;
				
			case 5:
				this.changeGraphic(this.GraphicMap.get("Orange"));
				break;
				
			case 6:
				this.changeGraphic(this.GraphicMap.get("Pink"));
				break;
				
			case 7:
				this.changeGraphic(this.GraphicMap.get("Purple"));
				break;
				
			case 8:
				this.changeGraphic(this.GraphicMap.get("Yellow"));
				break;
		}//----------------------------------------------------------------
	}
	
	//------------------------------
	//Put color back to neutral.
	//------------------------------
	public void resetColor()
	{
		this.color = this.playerColor;
	}

}
