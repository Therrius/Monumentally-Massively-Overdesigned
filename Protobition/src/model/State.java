package model;

import util.Perlin;
import util.Vec3;

public class State {

	private static final double SCROLL_SPEED = 1;
	private static final double ZOOM_SPEED = 0.01;
	
	private ChunkService map;
	private Camera camera;


	public State(){
		camera = new Camera();
		
		Perlin p = new Perlin(32);
	
		int mapSize = 16;
		double full = mapSize * Chunk.CHUNK_SIZE;
		
		map = new ChunkService(mapSize);
		for(int x=0; x<mapSize; x++){
			for(int z=0; z<mapSize; z++){
				int[] h = map.getChunk(x, z).getHeightMap();
				
				for(int i=0; i<Chunk.CHUNK_SIZE; i++){
					for(int j=0; j<Chunk.CHUNK_SIZE; j++){
						h[i+(j*Chunk.CHUNK_SIZE)] = (int)p.getNoise((i+x*Chunk.CHUNK_SIZE)/full, 0, (j+z*Chunk.CHUNK_SIZE)/full, 3);
					}
				}
			}
		}
		
		
	}


	public Chunk[] getMap(double minGX, double minGZ, double maxGX, double maxGZ){
		return map.getChucksFrom(minGX, minGZ, maxGX, maxGZ);
	}

	public Camera getCam(){
		return camera;
	}
	
	public void addCameraMovement(){}
	
	public void tick(){
		
	}

	public void moveCamera(int left, int down, int zoom){
		if(left!=0 || down!=0){
			int x = down-left, z = down+left;
			camera.setPosition(camera.getPosition().add(new Vec3(x,0,z).scaleto(SCROLL_SPEED)));
		}
		if(zoom!=0){
			int scale = (int)(camera.getScale() + zoom*SCROLL_SPEED);
			camera.setScale((scale>0)?scale:1);
		}
		
		System.out.println(camera.getPosition() + " :: " + camera.getScale());
	}
}
