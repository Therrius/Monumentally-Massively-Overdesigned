package model;

public class Chunk {

	public static final int CHUNK_SIZE = 16;
	
	private int[] heightMap;
	private int x, z;
	
	public Chunk(int x, int z){
		this.x = x;
		this.z = z;
		heightMap = new int[CHUNK_SIZE * CHUNK_SIZE];
	}
	
	public int[] getHeightMap(){
		return heightMap;
	}
	
	public int getX(){
		return x;
	}
	public int getZ(){
		return z;
	}
}
