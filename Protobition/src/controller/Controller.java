package controller;

import java.awt.event.KeyEvent;

import view.DisplayWindow;
import model.State;

public class Controller {

		private State state;
		private DisplayWindow window;
	
	public Controller(State state, DisplayWindow window){
		this.state = state;
		this.window = window;
	}
	
	public void tick(){
		int x = 0, y = 0, z = 0;

		if(window.getKey(KeyEvent.VK_LEFT)){x--;z++;}
		if(window.getKey(KeyEvent.VK_RIGHT)){x++;z--;}
		if(window.getKey(KeyEvent.VK_UP)){z--;x--;}
		if(window.getKey(KeyEvent.VK_DOWN)){z++;x++;}
		//y = win.pollMouseWheelClicks();
		
		if(window.getKey(KeyEvent.VK_PAGE_UP)){y++;}
		if(window.getKey(KeyEvent.VK_PAGE_DOWN)){y--;}

		if(x!=0 || y!=0 || z !=0)
			state.moveCamera(x, y, z);
		
	}
}
