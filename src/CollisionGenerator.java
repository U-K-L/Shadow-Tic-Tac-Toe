import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/*
 * Created by Noah Williamas on 12/17/2017
 * 
 * --------------------------------------------
 * This class is not used upon loading.
 * This class is used only for development.
 * ---------------------------------------------
 * 
 * Purpose: Creates a bitmask for the isometric block. 
 * Loads the generated .ser file, as well as detect its contents.
 * 
 * Example: An image must be a rectangle:   ----
 * 											|/\|
 * 											----
 * Imagine a triangle inside a box. At the top corners are empty space.
 * This class finds the difference between the image rectangle, and the empty space.
 * 
 * It does this by checking which pixels aren't fully transparent. Stores data found into an array,
 * and then a .ser file.
 * 
 * NOTE: This algorithm is powerful enough to create an entire collision system for a 2D game.
 * In the end, it was only used for one file in this game. So it could be futher refined to fit this game.
 */
public class CollisionGenerator {
	
	private transient static BufferedImage image; // Image that is loaded for use.
	public static byte[][] dataMap; //The array of bytes to be turned into a .ser file.
	private transient static Color color; //Color class to check the pixels of the image.
	
	private transient static byte[][] tempMap; //Dummy map to compare pixels.
	
	//--------------------------------------------
	//Sets all variables to proper values.
	//--------------------------------------------
	public static void Start()
	{
		//Safety net in case image cannot read.
		try {
			image = ImageIO.read(new File ("res/Red.png")); //Loads the Red block image
		}catch (IOException e){e.printStackTrace();}
		
		//Creates an empty image of the same size. By making a 2D array of the same size.
		tempMap = new byte[image.getHeight()][image.getWidth()]; 
		
		
		//--------------------------------------
		//Starts the algorithm.
		int row = 0; //row for 2D array.
		generate(row); //calls the generator.
		outPutFile(); //after the generator is done. output file.
		
	}
	
	//----------------------------------------------
	//Recursive loop.
	//Goes through all the columns of an image, then moves
	//down one row.
	//If that image is not fully transparent? Put the value
	//of 1 inside the empty array same index.
	//----------------------------------------------
	private static void generate(int r)
	{
		//Goes through columns
		for(int c = 0; c < tempMap[0].length; c++)
		{
			//Checks the image pixel value.
			color = new Color(image.getRGB(c, r), true);
			
			if(color.getAlpha() > 20) //Is it visible?
				tempMap[r][c] = 0x01; //Then put a value there inside our empty array.
		}
		
		//goes to the next row.
		if(r < tempMap.length - 1) //we don't need the very last pixel.
			generate(r+1); //recursion. 
		
		dataMap = tempMap; //dataMap now takes the dummyMap's data.
	}
	
	//-----------------------------------------------
	//Generic input/output system.
	//-----------------------------------------------
	private static void outPutFile()
	{
			//Attempts all of it...
			try{
				//create file.
				FileOutputStream FileOutput =  new FileOutputStream("res/IsoData.ser");
				
				//Makes file an outputStream.
				ObjectOutputStream Output = new ObjectOutputStream(FileOutput);
				
				//Write the outputStream to the file.
				Output.writeObject(dataMap);
				
				//Flush garbage.
				Output.flush();
				
				//Closes the files.
				Output.close();
				FileOutput.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	//-----------------------------------------------
	//Generic input/output system.
	//-----------------------------------------------
	public static void GetData()
	{	
		//Tries all below...
		try{
			
			//Create input file.
			FileInputStream FileInput = new FileInputStream("res/IsoData.ser");
			
			//Turns input file into an InputStream.
			ObjectInputStream Input = new ObjectInputStream(FileInput);
			
			dataMap = (byte[][])Input.readObject();
			
			//Closes the files
			Input.close();
			FileInput.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//-----------------------------------------------
	//Checks if mouse clicks on data within the dataMap.
	//Detects object within image.
	//-----------------------------------------------
	public static boolean CheckIsoData(GameObject obj)
	{
		int compX = 0; //compare the X
		int compY = 0; //compare the Y
		compX = (int) (Window.mouse.mouse_x - obj.x); //Place mouse and object to the root coordinates.
		compY = (int) (Window.mouse.mouse_y - obj.y); //Place mouse and object to the root coordinates.
		
		//Ensure that it does not leave array.
		try{
			if(dataMap[compY][compX] == 1) //did it actually click the object?
				return true; //then return true.
		}catch(Exception e){}
		
		return false; //nothing happened? return false.
	}
}
