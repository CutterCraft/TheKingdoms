/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

/**
 *
 * @author Michael
 */
public class ChunkManager {
    
    public static ChunkManager chunkManager = new ChunkManager();
    FileHandler fileHandler = FileHandler.getFileHandler();
    
    public static ChunkManager getChunkManager(){
        return chunkManager;
    }
    
    public void saveChunk(Chunk c){
        
        String[] saveString = c.getData();
        String path = "/"+c.x+"_"+c.y+"_"+c.z;
        fileHandler.setFolder("/levelone");
        fileHandler.writeToFile(path, saveString, false);
        
    }
    
    public Chunk loadChunk(int x, int y, int z){
        fileHandler.setFolder("/levelone");
        String path = "/"+x+"_"+y+"_"+z;
        String[] tmpStr = fileHandler.readFromFile(path);
        MapTile[][][] tmpMap = new MapTile[16][16][16];
        
        for(int i=0;i<tmpMap.length;i++){
            for(int j=0;j<tmpMap[i].length;j++){
                char[] tmpChar = tmpStr[i].toCharArray();
                for(int k=0;k<tmpMap[i][j].length;k++){
                    int tmpInt = 1;
                    String id = "";
                    while(tmpChar[tmpInt]!=' '){
                        id = id+tmpChar[tmpInt];
                        tmpInt++;
                    }
                    int tID = Integer.valueOf(id);
                    tmpMap[i][j][k] = Tiles.tiles[tID];
                    tmpMap[i][j][k].setCoords(i,j,k);
                }
            }
        }
        Chunk tmpChunk = new Chunk(x,y,z);
        tmpChunk.setData(tmpMap);
        return tmpChunk;
        
    }
    
}