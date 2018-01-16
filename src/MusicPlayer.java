/*
 * Created by Noah Williams on 12/19/2017
 * 
 * Purpose: A very generic music player.
 */
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;

public class MusicPlayer {
	

	public  AudioInputStream audio; //Stream of audio.
	public  Clip sound; //clip from java music libraries.
	public static Map<String, Clip> SoundMap = new HashMap<String, Clip>(); //Map of sounds to save memory.
	
	//---------------------------------------------------
	//Plays the music
	//---------------------------------------------------
	public void playMusic(String string, boolean continous)
	{
		this.sound = SoundMap.get(string); //get sound from hashmap.
		//Ensure safety.
		try {
			sound.setFramePosition(0); //start sound from beggining
			sound.open(); //open sound.
		}catch (LineUnavailableException e) {e.printStackTrace();}
		if(continous == true) //If it loops.
		{
			this.sound.loop(Clip.LOOP_CONTINUOUSLY); //loops forever.
			this.sound.start(); //start playing music.
		}
			else
				sound.start();//start with no loop.
	}
	
	//----------------------------------------
	//Load music paths in here.
	//----------------------------------------
	public void loadSounds(String...strings)
	{
		for(String str : strings)
		{
			loadMusic(str, false);
		}
	}
	
		//---------------------------------------------
		//Creates sound clip and stores inside hashmap.
		//---------------------------------------------
		public void loadMusic(String fileName,  boolean continous)
		{
			if (fileName != null){ //if there is a string given.
				
				//Extra net of safety.
				try{
					sound = null; //kill
					audio = null; //kill
					stopMusic(); //kill anything.
					//-----------------------------------
					//Starts creating.
					File musicFile = new File(fileName); //create file from string.
					audio = AudioSystem.getAudioInputStream(musicFile); //get input stream from file.
					AudioFormat format = audio.getFormat(); //get format from input stream.
					DataLine.Info info = new DataLine.Info(Clip.class, format); //gets data line.
					sound = (Clip) AudioSystem.getLine(info); //clip obtains data line.

					sound.open(audio); //open the audio
					SoundMap.put(fileName, sound); //put finished clip in hashmap.
					
				}catch(Exception e){e.printStackTrace();}
			}
		}
		
	//------------------------------
	//Stops music from playing.
	//------------------------------
	public void stopMusic()
	{
		if(sound != null)
		{
			try{
				sound.stop();//stop music.
				sound.flush(); //flush garbage.
				sound.setFramePosition(0); //starts from begining.
			}catch(Exception e){e.printStackTrace();}
		}
	}
	
}
