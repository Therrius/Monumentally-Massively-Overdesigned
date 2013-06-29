package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import util.Vec3;

import model.Camera;
import model.Chunk;
import model.State;

public class Drawer {

	State state;

	DisplayWindow display;
	BufferedImage image;
	int currentWidth = 0;
	int currentHeight = 0;

	//At state.getCam.y=1 this is the diwth and height of squares 
	final double WIDTH_OVER_HEIGHT = 0.5;
	
	private final Color BLOCK_TOP = Color.LIGHT_GRAY;
	private final Color BLOCK_LEFT = Color.DARK_GRAY;
	private final Color BLOCK_RIGHT = Color.GRAY;
	
	private final Sprite block;


	public Drawer(State state, DisplayWindow display){
		this.state = state;
		this.display = display;
		
		
		
		BufferedImage blockImage = new BufferedImage(24, 24, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = (Graphics2D) blockImage.getGraphics();
		
		g.setColor(BLOCK_TOP);
		g.fillPolygon(new int[]{0, 11, 23, 11}, new int[]{6, 0, 6, 11}, 4);
		
		g.setColor(BLOCK_LEFT);
		g.fillPolygon(new int[]{0,11,11,0}, new int[]{6,11,23,17}, 4);
		
		g.setColor(BLOCK_RIGHT);
		g.fillPolygon(new int[]{11,23,23,11}, new int[]{11,6,17,23}, 4);
		
		block = new Sprite(blockImage, 12.0, 0.5, 0.5);
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
		Camera cam = state.getCam();
		
		//top left, top right, bottom left, bottom right
		int[] screenPos = new int[]{0,0, currentWidth,0, 0,currentHeight, currentWidth,currentHeight};
		double[] worldPos = getWorldPoints(screenPos, cam);
		
		Chunk[] map = state.getMap(
				Math.min(worldPos[0], worldPos[2]),
				Math.min(worldPos[1], worldPos[3]),
				Math.max(worldPos[4], worldPos[6]),
				Math.max(worldPos[5], worldPos[7]));

		
		//g.drawImage(block, 10, 10, 24, 24, null);
		g.setColor(Color.BLACK);
		
		double[] positions = new double[map[map.length-1].getX()-map[0].getX() * map[map.length-1].getZ()-map[0].getZ()];
		
		int p = 0;
		for(int i=0; i<map.length; i++){
			int[] h = map[i].getHeightMap();
			for(int x=0; x<map.length; x++){
				for(int z=0; z<map.length; z++){
					h[p++] = h[x+z*Chunk.CHUNK_SIZE];
				}
			}
		}
		
		int[] points = getScreenPoints(positions, cam);
		
		for(int i=0; i<points.length; i+=2)
			block.drawOnto(g, points[i], points[i+1], cam.getScale());

		display.display(image);
	}

	private int[] getScreenPoints(double[] worldPos, Camera cam){
		if(worldPos.length%3 !=0)
			throw new IllegalArgumentException();
		
		int[] points = new int[2*worldPos.length/3];
		int halfWidth = currentWidth/2;
		int halfHeight = currentHeight/2;
		
		int scale = cam.getScale();
		Vec3 pos = cam.getPosition();
		
		
		for(int i=0, j=0; i < worldPos.length; i+=3, j+=2){
			double dx = worldPos[i]-pos.x;
			double dy = worldPos[i+1];
			double dz = worldPos[i+2]-pos.z;
			
			points[j] = (int) (halfWidth + (dx-dz) * scale);
			points[j+1] = (int) (halfHeight + WIDTH_OVER_HEIGHT * ((dx+dz)-dy) * scale);
		}
		
		return points;
	}
	
	private double[] getWorldPoints(int[] screenPos, Camera cam){
		if(screenPos.length%2 !=0)
			throw new IllegalArgumentException();
		
		double[] points = new double[screenPos.length];
		
		int halfWidth = currentWidth/2;
		int halfHeight = currentHeight/2;
		
		double scale = cam.getScale();
		double heightScale = 1/(WIDTH_OVER_HEIGHT*scale);
		Vec3 pos = cam.getPosition();
		
		
		for(int i=0, j=0; i < screenPos.length; i+=2, j+=2){
			double sx = (screenPos[i]-halfWidth)/scale; //= (dx-dz) * scale);
			double sy = (screenPos[i+1]-halfHeight)*heightScale; //= WIDTH_OVER_HEIGHT * ((dx+dz)-dy) * scale
			
			double dz = (sy-sx)/2;
			double dx = sx + dz;
			
			points[j] = (int) (dx + pos.x);
			points[j+1] = (int) (dz + pos.z);
		}
		
		return points;
	}
}
















