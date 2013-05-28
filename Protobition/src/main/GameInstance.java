package main;

import java.awt.event.KeyEvent;

import model.State;
import view.DisplayWindow;
import view.Drawer;

public class GameInstance {
	
	DisplayWindow win;
	Drawer draw;
	State state;
	
	
	
	public GameInstance(){
		win = DisplayWindow.create(800, 600);
		win.setVisible(true);
		
		state = new State();
		
		draw = new Drawer(state, win);
	}
	
	
	public void run() throws Exception{


		long tick = System.currentTimeMillis();
		while (!win.getKey(KeyEvent.VK_Q)){
			
			int x = 0, y = 0, z = 0;

			if(win.getKey(KeyEvent.VK_LEFT)){x++;}
			if(win.getKey(KeyEvent.VK_RIGHT)){x--;}
			if(win.getKey(KeyEvent.VK_UP)){z++;}
			if(win.getKey(KeyEvent.VK_DOWN)){z--;}
			//y = win.pollMouseWheelClicks();
			
			if(win.getKey(KeyEvent.VK_PAGE_UP)){y++;}
			if(win.getKey(KeyEvent.VK_PAGE_DOWN)){y--;}

			if(x!=0 || y!=0 || z !=0)
				state.moveCamera(x, y, z);
			
			
			
			//sleep and draw
			Thread.sleep(Math.max(tick+20-System.currentTimeMillis(), 0));
			draw.draw();
			tick = System.currentTimeMillis();
		}

		win.dispose();
		System.exit(1);
	}
}
