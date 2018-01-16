/*
 * Created by Noah Williams on 12/17/2017.
 * 
 *  Purpose: Action handler. Handles generic action such as:
 *  menu graphic shifts, changing tic tac toe block's colors.
 *  
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {

	//----------------------------------------------------
	//Generic action for LEFT SIDE BOARD. Obtains a ---BlockObject--- 
	//and changes color to player's color when the player clicks a block.
	//----------------------------------------------------
	public void BlockActionPerformed(BlockObject o) {
		
		//Checks which player turn it is.
		if(MainGame.PlayerTurn == 0)
		{
			//changes to the color of the player.
			o.changeGraphic(o.GraphicMap.get("White")); //gets the graphic from hashMap.
			o.playerColor = 9; //playerColor is changed to "white" index color. this is used to listen to other classes.
		}
		
		//is it player two's turn?
		if(MainGame.PlayerTurn == 1)
		{
			o.changeGraphic(o.GraphicMap.get("Black")); //change color to black.
			o.playerColor = 10; //change listen variable to index of black.
		}
	}
	
	//------------------------------------------------
	//Generic action for all menu objects.
	//Lights up the menu object when mouse hovers over it.
	//------------------------------------------------
	public void MenuActionPerformed(GameObject o) {
		
		//Ensure game does not crash.
		try{
			//Change the graphic to the next graphic in HashMap.
			//if it is not the second graphic?
			if(o.texture != o.GraphicMap.get(o.name + "0"))
			{
				//Change it to the second graphic.
				o.changeGraphic(o.GraphicMap.get(o.name + "0"));
				MainActivity.soundPlayer.playMusic("res/Blop.wav", false); //play the sound effect WITH SOUNDPLAYER.
			}
		}catch(Exception e){e.printStackTrace();} //Print the errors, as this should not silently fail.
	}

	
	//------------------------------------------------
	//Not Used.
	//------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
