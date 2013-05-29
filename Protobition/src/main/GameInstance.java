package main;

import java.awt.event.KeyEvent;

import controller.Controller;

import model.State;
import view.DisplayWindow;
import view.Drawer;

public class GameInstance {
	
	DisplayWindow win;
	Drawer draw;
	State state;
	Controller cont;
	
	
	public GameInstance(){
		win = DisplayWindow.create(800, 600);
		win.setVisible(true);
		
		state = new State();
		
		draw = new Drawer(state, win);
		cont = new Controller(state, win);
	}
	
	
	public void run() throws Exception{


		long tick = System.currentTimeMillis();
		while (!win.getKey(KeyEvent.VK_Q)){
			//sleep and draw
			//Thread.sleep(Math.max(tick+20-System.currentTimeMillis(), 0));
			draw.draw();
			cont.tick();
			tick = System.currentTimeMillis();
		}
		win.dispose();
	}
}
