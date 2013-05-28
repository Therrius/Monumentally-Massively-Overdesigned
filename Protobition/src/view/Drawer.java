package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import util.Vec3;

import model.State;

public class Drawer {

	State state;

	DisplayWindow display;
	BufferedImage image;
	int currenWidth = 0;
	int currentHeight = 0;


	final int tile_width = 10;
	final int tile_height = 5;


	public Drawer(State state, DisplayWindow display){
		this.state = state;
		this.display = display;
	}


	public void draw(){
		if(currenWidth != display.getDisplayWidth() || currentHeight != display.getDisplayHeight()){
			image = new BufferedImage(display.getDisplayWidth(), display.getDisplayHeight(), BufferedImage.TYPE_INT_ARGB);
			currenWidth = display.getDisplayWidth();
			currentHeight = display.getDisplayHeight();
		}



		Graphics2D g = image.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, currenWidth, currentHeight);


		Vec3 cam = state.getCam();

		int[][] map = state.getMap();

		for(int x=0; x<map.length; x++){
			for(int y=0; y<map[x].length; y++){


				//top of the block
				int xPos = (int)(currenWidth/2 + (x-y)*tile_width + cam.x);
				int yPos = (int)((x+y)*tile_height + cam.y);

				int[] xArr = {xPos, xPos + tile_width, xPos, xPos-tile_width};
				int[] yArr = {yPos, yPos + tile_height, yPos + (2*tile_height), yPos + tile_height};


				g.setColor(Color.gray);
				g.fillPolygon(xArr, yArr, 4);
				g.setColor(Color.black);
				g.drawPolygon(xArr, yArr, 4);

			}
		}


		display.display(image);
	}
}
















