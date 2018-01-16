/*
 * Created by Noah Williams on 12/15/2017
 * 
 * Purpose: Handles the graphic's texture. Handles predefined shapes as well
 * as images.
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectGraphic {
	
	private String path; //the file path
	public BufferedImage image; //the image.
	
	//--------------------------------
	//Reads give file and makes it an image.
	//--------------------------------
	public ObjectGraphic(String path)
	{
		this.path = path;
		
		//Safety net in case image cannot read.
		try {
			image = ImageIO.read(new File (path)); //gets the image from the path.
		}catch (IOException e){}
	}
}
