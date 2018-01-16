import java.util.ArrayList;

/*
 * Created by Noah Williams on 12/17/2017.
 * 
 * Purpose: A class to quickly do mathmatical based events.
 * 
 */
public class MathFunctions {
	
	//--------------------------------------
	//Checks if value is NOT inside array.
	//--------------------------------------
	public static boolean InsideArray(int value, ArrayList <Integer> comparray)
	{
		for(Integer val : comparray)
			{
				if(value == val)
					return false;
			}
		return true;
		
	}
	//--------------------------------------------
	//Checks if value is over an object.
	//--------------------------------------------
	public static boolean WithinRange(GameObject obj, int compX, int compY)
	{
		//Checks heigh/width and x/y to determine if the value is
		//over the object.
		if (compX >= obj.x && //is it too far to the right?
			compX <= obj.texture.image.getWidth() + obj.x  && //is it too far to the left?
			compY >= obj.y && //is it too low?
			compY <= obj.texture.image.getHeight() + obj.y ) //is it too high?
				return true; //if none of those, return true.
		return false; //else return false.
		
	}
	
	//-----------------------------------------
	//Used to find winner of the game.
	//Shadow Board
	//------------------------------------------
	public static int ShadowCheckWin(ArrayList<ShadowObject> objects)
	{
		int sum = 0;
		//Checks horizontal
		sum = ShadowSumMatrix(objects);
		//Return sum if someone won.
		if(sum == 27 || sum == 30)
			return sum;
		
		return sum;
	}
	
	//--------------------------------------------
	//Used to find winner of the game.
	//Regular Board
	//--------------------------------------------
	public static int BlockCheckWin(ArrayList<BlockObject> objects)
	{
		int sum = 0;
		//Checks horizontal
		sum = BlockSumMatrix(objects);
		//Return sum if someone won.
		if(sum == 27 || sum == 30)
			return sum;
		
		return sum;
	}
	
	//----------------------------------------
	//Shadow Board victor check
	//Returns the sum of the matrix. If it is 27 that is 9*3(P1 wins)
	// If it is 30 that is 3*10 (P2 wins)
	//----------------------------------------
	public static int ShadowSumMatrix(ArrayList<ShadowObject> objects)
	{
		int sum = 0;
		int increment = 1;
		int min = 0;
		int max = 2;
		int iterations = 0;
		while(iterations <= 7)
		{
			//Sets the values based on what
			//iteration it is.
			switch (iterations)
			{
				//First row horizontal
				case 0:
					break;
				//Second row horizontal
				case 1:
					min = 3;
					max = 5;
					break;
				//Third row horizontal
				case 2:
					min = 6;
					max = 8;
					break;
				//First row vertical
				case 3:
					min = 0;
					max = 6;
					increment = 3;
					break;
				//Second row vertical
				case 4:
					min = 1;
					max = 7;
					increment = 3;
					break;
				//Third row vertical
				case 5:
					min = 2;
					max = 8;
					increment = 3;
					break;
				//First row diagonal
				case 6:
					min = 0;
					max = 8;
					increment = 4;
					break;
				//Second row diagonal
				case 7:
					min = 2;
					max = 6;
					increment = 2;
					break;
			}
			
			//---------------------------------------------------
			//Gets the sum of the board.
			for(int i = min; i <= max; i += increment)
			{
				if(objects.get(i).color == 10 || objects.get(i).color == 9 ) //if the object is black or white.
					sum += objects.get(i).color; //add sum
				else
					break;
			}
			//Return sum if someone won.
			if(sum == 27 || sum == 30)
				return sum;
			
			//Start the process again...
			sum = 0;
			iterations++;
		}
		return sum;
	}
	
	//----------------------------------------------------------------
	//Block Board victor check
	//Returns the sum of the matrix. If it is 27 that is 9*3(P1 wins)
	//If it is 30 that is 3*10 (P2 wins)
	//----------------------------------------------------------------
	public static int BlockSumMatrix(ArrayList<BlockObject> objects)
	{
		int sum = 0;
		int increment = 1;
		int min = 0;
		int max = 2;
		int iterations = 0;
		while(iterations <= 7)
		{
			//Sets the values based on what
			//iteration it is.
			switch (iterations)
			{
				//First row horizontal
				case 0:
					break;
				//Second row horizontal
				case 1:
					min = 3;
					max = 5;
					break;
				//Third row horizontal
				case 2:
					min = 6;
					max = 8;
					break;
				//First row vertical
				case 3:
					min = 0;
					max = 6;
					increment = 3;
					break;
				//Second row vertical
				case 4:
					min = 1;
					max = 7;
					increment = 3;
					break;
				//Third row vertical
				case 5:
					min = 2;
					max = 8;
					increment = 3;
					break;
				//First row diagonal
				case 6:
					min = 0;
					max = 8;
					increment = 4;
					break;
				//Second row diagonal
				case 7:
					min = 2;
					max = 6;
					increment = 2;
					break;
			}
			
			//---------------------------------------------------
			//Gets the sum of the board.
			for(int i = min; i <= max; i += increment)
			{
				if(objects.get(i).playerColor == 10 || objects.get(i).playerColor == 9 ) //if black or white
					sum += objects.get(i).playerColor; //add sum.
				else
					break;
			}
			//Return sum if someone won.
			if(sum == 27 || sum == 30)
				return sum;
			
			//Start the process again...
			sum = 0;
			iterations++;
		}
		return sum;
	}
}