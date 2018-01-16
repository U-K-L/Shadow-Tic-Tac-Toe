/*
 * Created by Noah Williams on 12/16/2017
 * 
 * Purpose: Handles all scenes.
 */
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SceneHandler {
	
	public static Map<String, Scene> SceneMap = new HashMap<String, Scene>();  //HashMap of all scenes.
	public static Scene currentScene; //Current scene rendering.
	
	//---------------------------------------
	//Changes the scene.
	//---------------------------------------
	public static void changeScene(Scene newScene)
	{
		currentScene.sceneRender = false; //scene is not active.
		newScene.sceneRender = true; //new scene is active.
		//currentScene = null; //old scene is disposed.
		currentScene = newScene; //current scene is now new scene.
	}
	
	//---------------------------------------
	//Renders the current scene.
	//---------------------------------------
	public static void render(Graphics g)
	{
			if (currentScene.sceneRender == true)
			{
				currentScene.render(g); //current scene is rendered.
			}
	}

}
