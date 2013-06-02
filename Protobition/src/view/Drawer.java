package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import util.Vec3;

import model.State;

public class Drawer {

	State state;

	DisplayWindow display;
	BufferedImage image;
	int currentWidth = 0;
	int currentHeight = 0;

	//At state.getCam.y=1 this is the diwth and height of squares 
	final int STD_TILE_WIDTH = 10;
	final int STD_TILE_HEIGHT = 5;
	
	private final Color BLOCK_TOP = Color.LIGHT_GRAY;
	private final Color BLOCK_LEFT = Color.DARK_GRAY;
	private final Color BLOCK_RIGHT = Color.GRAY;
	
	private final Sprite block;


	public Drawer(State state, DisplayWindow display){
		this.state = state;
		this.display = display;
		
		
		block = new Sprite(24, 24, 0.5, 0.5);
		Graphics2D g = (Graphics2D) block.getGraphics();
		
		g.setColor(BLOCK_TOP);
		g.fillPolygon(new int[]{0, 11, 23, 11}, new int[]{6, 0, 6, 11}, 4);
		
		g.setColor(BLOCK_LEFT);
		g.fillPolygon(new int[]{0,11,11,0}, new int[]{6,11,23,17}, 4);
		
		g.setColor(BLOCK_RIGHT);
		g.fillPolygon(new int[]{11,23,23,11}, new int[]{11,6,17,23}, 4);
		
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
		
		//g.drawImage(block, 10, 10, 24, 24, null);
		g.setColor(Color.BLACK);
		

		double positions[] = new double[3*map.length*map[0].length];
		
		for(int x=0; x<map.length; x++){
			for(int z=0; z<map[x].length; z++){
				positions[3*(z+x*map[x].length)] = x;
				positions[3*(z+x*map[x].length) + 1] = map[x][z];
				positions[3*(z+x*map[x].length) + 2] = z;
			}
		}
		
		int[] points = getScreenPoints(positions, cam);
		
		for(int i=0; i<points.length; i+=2){
			block.drawOnto(g, points[i], points[i+1], cam.y);
			//g.drawImage(block, points[i], points[i+1], 24, 24, null);
			
			//g.fillOval(points[i], points[i+1], 3, 3);
		}
		
		
//		int[] points = getScreenPoints(new double[]{0,0,0}, cam);
//		g.drawImage(block, points[0], points[1], 24, 24, null);
		
		
		
//
//
//				//top of the base of the block
//				double xPos = (currentWidth/2 + (x-z) * tile_width + (cam.x/cam.y));
//				double yPos = (currentHeight/2 + (x+z) * tile_height + (cam.z/cam.y));
//				//int heightMod = (int) (map[x][z] * STD_TILE_HEIGHT * 2/cam.y);
//				int heightMod = (int) -(map[x][z] * STD_TILE_HEIGHT / cam.y);
//				
//				//perform culling
//				if(xPos + tile_width > 0 
//					&& xPos - tile_width < currentWidth
//					&& yPos + tile_height > 0
//					&& yPos - tile_height + heightMod < currentHeight){
//	
//					//describes main isopoints from left to right and top to bottom
//					int[] xArr = {(int) (xPos-tile_width), (int) xPos, (int) (xPos + tile_width)};
//					int[] yArr = {(int) (yPos - tile_height), (int) (yPos), (int) (yPos + tile_height)};
//					
//					//+heightMod
//					
//					//draw top
//					g.setColor(BLOCK_TOP);
//					g.fillPolygon(new int[]{xArr[0],xArr[1],xArr[2],xArr[1]}, 
//						new int[]{yArr[1]+heightMod,yArr[0]+heightMod,yArr[1]+heightMod,yArr[2]+heightMod}, 4);
//					g.setColor(Color.black);
//					
//					g.drawPolygon(new int[]{xArr[0],xArr[1],xArr[2],xArr[1]}, 
//						new int[]{yArr[1]+heightMod,yArr[0]+heightMod,yArr[1]+heightMod,yArr[2]+heightMod}, 4);
//					
//					if(heightMod<0){
//						//draw right side
//						g.setColor(BLOCK_RIGHT);
//						g.fillPolygon(new int[]{xArr[1],xArr[2],xArr[2],xArr[1]}, 
//								new int[]{yArr[2]+heightMod,yArr[1]+heightMod,yArr[1],yArr[2]}, 4);
//						
//						//draw left side
//						g.setColor(BLOCK_LEFT);
//						g.fillPolygon(new int[]{xArr[0],xArr[1],xArr[1],xArr[0]}, 
//								new int[]{yArr[1]+heightMod,yArr[2]+heightMod,yArr[2],yArr[1]}, 4);
//					}
//				}
//			}
//		}


		display.display(image);
	}

	private int[] getScreenPoints(double[] worldPos, Vec3 cam){
		if(worldPos.length%3 !=0)
			throw new IllegalArgumentException();
		
		int[] points = new int[2*worldPos.length/3];
		int halfWidth = currentWidth/2;
		int halfHeight = currentHeight/2;
		
		for(int i=0, j=0; i < worldPos.length; i+=3, j+=2){
			double dx = worldPos[i]-cam.x;
			double dy = worldPos[i+1];
			double dz = worldPos[i+2]-cam.z;
			
			points[j] = (int) (halfWidth + STD_TILE_WIDTH * (dx-dz) * cam.y);
			points[j+1] = (int) (halfHeight + STD_TILE_HEIGHT * ((dx+dz)-dy) * cam.y);
		}
		
		return points;
	}
}
















