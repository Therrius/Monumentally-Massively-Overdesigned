package model;

public class ChunkService {

	int numChunks;
	private Chunk[] chunks;
	
	public ChunkService(int numChunks){
		this.numChunks = numChunks;
		chunks = new Chunk[numChunks * numChunks];
		for(int x = 0; x< numChunks; x++)
			for(int z = 0; z< numChunks; z++)
				chunks[x+(z*numChunks)] = new Chunk(x, z);
	}
	
	
	public Chunk[] getChucksFrom(double minGX, double minGZ, double maxGX, double maxGZ){
		int minX = level((int) (minGX / Chunk.CHUNK_SIZE), numChunks-1, 0);
		int minZ = level((int) (minGZ / Chunk.CHUNK_SIZE), numChunks-1, 0);
		int maxX = level((int) (maxGX / Chunk.CHUNK_SIZE), numChunks-1, 0);
		int maxZ = level((int) (maxGZ / Chunk.CHUNK_SIZE), numChunks-1, 0);
		++maxX; ++maxZ;
		
		int xSize = 1+maxX-minX;
		
		Chunk[] arr = new Chunk[(1+maxX-minX)*(1+maxZ-minZ)]; 
		
		for(int x = minX; x<=maxX; x++){
			for(int y = minZ; y<=maxZ; y++){
				arr[x + xSize] = chunks[x+y*numChunks];
			}
		}
		return arr;
	}
	
	private int level(int value, int top, int bottom){
		return Math.max(Math.min(top, value), bottom);
	}
	
	public Chunk getChunk(int chunkX, int chunkZ){
		if( !(chunkX >= 0) &&
			!(chunkZ >= 0) &&
			!(chunkX < numChunks) &&
			!(chunkZ < numChunks)){
			throw new IllegalArgumentException();
		}
		return chunks[chunkX+chunkZ*numChunks];
	}
}
