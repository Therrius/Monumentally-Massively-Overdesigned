package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import util.Vec3;

import model.State;

public class Drawer {

	State state;

	DisplayWindow display;
	BufferedImage image;
	int currentWidth = 0;
	int currentHeight = 0;


	final int STD_TILE_WIDTH = 10;
	final int STD_TILE_HEIGHT = 5;


	public Drawer(State state, DisplayWindow display){
		this.state = state;
		this.display = display;
	}


	public void draw(){
		if(currentWidth != display.getDisplayWidth() || currentHeight != display.getDisplayHeight()){
			image = new BufferedImage(display.getDisplayWidth(), display.getDisplayHeight(), BufferedImage.TYPE_INT_ARGB);
			currentWidth = display.getDisplayWidth();
			currentHeight = display.getDisplayHeight();
		}



		Graphics2D g = image.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, currentWidth, currentHeight);


		Vec3 cam = state.getCam();

		int[][] map = state.getMap();
		
		double tile_width = STD_TILE_WIDTH/cam.y;
		double tile_height = STD_TILE_HEIGHT/cam.y;

		for(int x=0; x<map.length; x++){
			for(int z=0; z<map[x].length; z++){


				//top of the base of the block
				double xPos = (currentWidth/2 + (x-z) * tile_width + (cam.x/cam.y));
				double yPos = (currentHeight/2 + (x+z) * tile_height + (cam.z/cam.y));

				int[] xArr = {(int) xPos, (int) (xPos + tile_width), (int) xPos, (int) (xPos-tile_width)};
				int[] yArr = {(int) yPos, (int) (yPos + tile_height), (int) (yPos + (2*tile_height)), (int) (yPos + tile_height)};

				g.setColor(Color.gray);
				g.fillPolygon(xArr, yArr, 4);
				g.setColor(Color.black);
				g.drawPolygon(xArr, yArr, 4);

			}
		}


		display.display(image);
	}

	//private int getworldX
}
















