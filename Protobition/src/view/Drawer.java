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
	
	private final Color BLOCK_TOP = Color.LIGHT_GRAY;
	private final Color BLOCK_LEFT = Color.DARK_GRAY;
	private final Color BLOCK_RIGHT = Color.GRAY;


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
		
		double tile_width = STD_TILE_WIDTH / (2*cam.y);
		double tile_height = STD_TILE_HEIGHT / (2*cam.y);

		for(int x=0; x<map.length; x++){
			for(int z=0; z<map[x].length; z++){


				//top of the base of the block
				double xPos = (currentWidth/2 + (x-z) * tile_width + (cam.x/cam.y));
				double yPos = (currentHeight/2 + (x+z) * tile_height + (cam.z/cam.y));
				//int heightMod = (int) (map[x][z] * STD_TILE_HEIGHT * 2/cam.y);
				int heightMod = (int) -(map[x][z] * STD_TILE_HEIGHT / cam.y);
				
				//perform culling
				if(xPos + tile_width > 0 
					&& xPos - tile_width < currentWidth
					&& yPos + tile_height > 0
					&& yPos - tile_height + heightMod < currentHeight){
	
					//describes main isopoints from left to right and top to bottom
					int[] xArr = {(int) (xPos-tile_width), (int) xPos, (int) (xPos + tile_width)};
					int[] yArr = {(int) (yPos - tile_height), (int) (yPos), (int) (yPos + tile_height)};
					
					//+heightMod
					
					//draw top
					g.setColor(BLOCK_TOP);
					g.fillPolygon(new int[]{xArr[0],xArr[1],xArr[2],xArr[1]}, 
						new int[]{yArr[1]+heightMod,yArr[0]+heightMod,yArr[1]+heightMod,yArr[2]+heightMod}, 4);
					g.setColor(Color.black);
					
					g.drawPolygon(new int[]{xArr[0],xArr[1],xArr[2],xArr[1]}, 
						new int[]{yArr[1]+heightMod,yArr[0]+heightMod,yArr[1]+heightMod,yArr[2]+heightMod}, 4);
					
					if(heightMod<0){
						//draw right side
						g.setColor(BLOCK_RIGHT);
						g.fillPolygon(new int[]{xArr[1],xArr[2],xArr[2],xArr[1]}, 
								new int[]{yArr[2]+heightMod,yArr[1]+heightMod,yArr[1],yArr[2]}, 4);
						
						//draw left side
						g.setColor(BLOCK_LEFT);
						g.fillPolygon(new int[]{xArr[0],xArr[1],xArr[1],xArr[0]}, 
								new int[]{yArr[1]+heightMod,yArr[2]+heightMod,yArr[2],yArr[1]}, 4);
					}
				}
			}
		}


		display.display(image);
	}

	//private int getworldX
}
















