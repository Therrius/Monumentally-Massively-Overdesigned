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
		int left = 0, down = 0, zoom = 0;

		if(window.getKey(KeyEvent.VK_LEFT)){left++;}
		if(window.getKey(KeyEvent.VK_RIGHT)){left--;}
		if(window.getKey(KeyEvent.VK_UP)){down--;}
		if(window.getKey(KeyEvent.VK_DOWN)){down++;}
		//y = win.pollMouseWheelClicks();
		
		if(window.getKey(KeyEvent.VK_PAGE_UP)){zoom++;}
		if(window.getKey(KeyEvent.VK_PAGE_DOWN)){zoom--;}

		if(left!=0 || down!=0 || zoom!=0)
			state.moveCamera(left, down, zoom);
		
	}
}
