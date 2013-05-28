package main;

import java.awt.event.KeyEvent;

import util.Vec3;
import view.DisplayWindow;
import view.Drawer;
import model.State;

public class Main {


	public static final double MAX_CAM_SPEED = 2;

	public static void main(String[] args) throws Exception{
		State state = new State();
		DisplayWindow win = DisplayWindow.create(800, 600);
		win.setVisible(true);
		Drawer draw = new Drawer(state, win);


		long tick = System.currentTimeMillis();
		while (!win.getKey(KeyEvent.VK_Q)){

			draw.draw();

			Thread.sleep(Math.max(tick+20-System.currentTimeMillis(), 0));
			tick = System.currentTimeMillis();


			int x = 0;
			int y = 0;

			if(win.getKey(KeyEvent.VK_LEFT)){
				x++;
			}
			if(win.getKey(KeyEvent.VK_RIGHT)){
				x--;
			}
			if(win.getKey(KeyEvent.VK_UP)){
				y++;
			}
			if(win.getKey(KeyEvent.VK_DOWN)){
				y--;
			}

			if(x!=0 || y!=0)
				state.moveCamera(new Vec3(x,y,0).scaleto(MAX_CAM_SPEED));


		}

		win.dispose();
		System.exit(1);
	}
}