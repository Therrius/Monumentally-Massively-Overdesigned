package model;

import util.Vec3;

public class Camera {

	public static final int PAN_LEFT = 0;
	public static final int PAN_RIGHT = 1;
	public static final int PAN_UP = 2;
	public static final int PAN_DOWN = 3;
	
	public static final int PAN_OUT = 4;
	public static final int PAN_IN = 5;
	public static final int ROTATE_LEFT = 6;
	public static final int ROTATE_RIGHT = 7;
	
	private Vec3 position = new Vec3(0,1,0);
	
	public Camera(){
		position = new Vec3(0,1,0);
	}
	
	public Camera(Vec3 pos){
		position = pos;
	}
	
	public Vec3 getPosition(){
		return position;
	}
	
	public void setPosition(Vec3 pos){
		position = pos;
	}
}
