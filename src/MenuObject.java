/*
 * Created by Noah Williams on 12/17/2017
 * 
 * Purpose: Polymorphed class derived from GameObject.
 * Creates values for all button/menu objects.
 */
public class MenuObject extends GameObject {

	public ActionHandler action; //ActionListener.
	
	//---------------------------------------------------
	//Constructor.
	//---------------------------------------------------
	public MenuObject(int x, int y, String name, String image) {
		super(x, y, name, image);
		action = new ActionHandler();//creates new actionlistener.
	}
	
	
	//-----------------------------
	//Perform ever mouse move.
	//-----------------------------
	public void performMove()
	{
		if(MathFunctions.WithinRange(this, (int)Window.mouse.mouse_x, (int)Window.mouse.mouse_y)) //mouse over object?
		{
			action.MenuActionPerformed(this); //perform generic action.
			if(this.name == "play" || this.name == "quit" || this.name == "how" ) //if these buttons perform special action.
				this.x = 230;
		}
		else
		{
			this.changeGraphic(this.GraphicMap.get(this.name)); //if these buttons perform special action.
			if(this.name == "play" || this.name == "quit" || this.name == "how" )
				this.x = 218;
		}
	}
}
