package model;

import util.Vec3;

public class State {

	int[][] map;
	Vec3 cameraPos;


	public State(){
		map = new int[100][100];
		cameraPos = Vec3.zero;
	}


	public int[][] getMap(){
		return map;
	}

	public Vec3 getCam(){
		return cameraPos;
	}

	public void moveCamera(Vec3 add){
		cameraPos = cameraPos.add(add);

		System.out.println(cameraPos);
	}

}
