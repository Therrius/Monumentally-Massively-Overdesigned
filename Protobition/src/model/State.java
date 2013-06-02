package model;

import util.Perlin;
import util.Vec3;

public class State {

	private static final double SCROLL_SPEED = 3;
	private static final double ZOOM_SPEED = 0.01;
	
	private int[][] map;
	private Camera camera;


	public State(){
		map = new int[100][100];
		camera = new Camera();
		
		Perlin p = new Perlin(32);
		
		for (int i=0; i< 100; i++)
			for (int j=0; j< 100; j++)
				map[i][j] = (int)(20*p.getNoise(i/100.0, j/100.0, 0, 3))+10;
	}


	public int[][] getMap(){
		return map;
	}

	public Vec3 getCam(){
		return camera.getPosition();
	}
	
	public void addCameraMovement(){}
	
	public void tick(){
		
	}

	public void moveCamera(int x, int zoom, int z){
		if(x!=0 || z!=0)
			camera.setPosition(camera.getPosition().add(new Vec3(x,0,z).scaleto(SCROLL_SPEED / camera.getPosition().y)));
		if(zoom!=0)
			camera.setPosition(camera.getPosition().add(camera.getPosition().mul(ZOOM_SPEED * zoom)));
		
		System.out.println(camera.getPosition());
	}
}
