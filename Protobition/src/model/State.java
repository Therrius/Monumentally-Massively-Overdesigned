package model;

import util.Vec3;

public class State {

	private static final double SCROLL_SPEED = 5;
	private static final double ZOOM_SPEED = 0.01;
	
	private int[][] map;
	private Vec3 cameraPos;


	public State(){
		map = new int[100][100];
		cameraPos = new Vec3(0,1,0);
	}


	public int[][] getMap(){
		return map;
	}

	public Vec3 getCam(){
		return cameraPos;
	}

	public void moveCamera(int x, int zoom, int z){
		if(x!=0 || z!=0)
			cameraPos = cameraPos.add(new Vec3(x,0,z).scaleto(SCROLL_SPEED * cameraPos.y));
		if(zoom!=0)
			cameraPos = cameraPos.add(cameraPos.mul(ZOOM_SPEED * zoom));
		
		System.out.println(cameraPos);
	}

}
