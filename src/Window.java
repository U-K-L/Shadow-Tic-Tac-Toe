/*
 * Created by Noah Williams on 12/11/2017
 * 
 * Purpose: JFrame...constantly renders all objects to panel.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;

import javax.swing.*;

public class Window extends JFrame {
	
	public static BufferStrategy buffer; //The buffered image.
	public static MouseController mouse; //Mouse class.
	
	public int width = 0; //JFrame width
	public int height = 0; //JFrame height
	
	//----------------------------------
	//Constructor.
	//----------------------------------
	public Window(int width, int height)
	{
		//---------------
		//sets width/height
		this.width = width;
		this.height = height;
		setSize((int)width,(int)height);
		
		//--------------------------
		setResizable(false); //not resizable
		setTitle("Shadow Tic Tac Toe"); //title of game
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes and releases memory
		mouse = new MouseController(); //new mouse controller
		mouse.createMouse(); //create a mouse.
		addMouseMotionListener(mouse); //add motion listener.
		addMouseListener(mouse.mouse); //add mouse listener
		setVisible(true); //display frame.
	}
	
    //----------------------------
    //Updates every frame.
	//---------------------------
    public void update()
    {
    	//Create BufferStrategy for stopping flickering.
    	buffer = getBufferStrategy();
    	//if null create more.
    	if(buffer == null)
    	{
    		//Draws 3 frames beforehand.
    		createBufferStrategy(3);
    		return;
    	}

    	Render.Draw();//Call render
    }
	

}
