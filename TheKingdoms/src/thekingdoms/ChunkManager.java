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
    
    public Chunk getChunk(int x, int y, int z){
        Chunk returnChunk = loadChunk(x,y,z);
        if(returnChunk==null){
            returnChunk = createChunk(x,y,z);
        }
        returnChunk.setTopTiles();
        return returnChunk;
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
        if(tmpStr==null) return null;
        MapTile[][][] tmpMap = new MapTile[16][16][16];
        
        int counter = 0;
        for(int i=0;i<tmpMap.length;i++){

            for(int j=0;j<tmpMap[i].length;j++){

                int tmpInt = 0;
                int k = 0;
                char[] tmpChar = tmpStr[counter].toCharArray();
                counter++;
                String id = "";

                while(tmpInt<tmpChar.length){

                    if(tmpChar[tmpInt]!=' '){
                        id = id+tmpChar[tmpInt];
                    }else if(tmpChar[tmpInt]==' '){
                        int tID = Integer.valueOf(id);
                        tmpMap[i][j][k] = Tiles.tiles[tID];
                        tmpMap[i][j][k].setCoords(i,j,k);
                        id = "";
                        k++;
                    }

                    tmpInt++;
                }

            }

        }
        
        Chunk tmpChunk = new Chunk(x,y,z);
        tmpChunk.setData(tmpMap);
        return tmpChunk;
        
    }
    
    public Chunk createChunk(int x, int y, int z){
        Chunk returnChunk = new Chunk(x,y,z);
        String path = "/"+x+"_"+y+"_"+z;
        fileHandler.setFolder("/levelone");
        fileHandler.createFile(path);
        WorldGenerator.getWorldGenerator().preGenerateChunk(returnChunk);
        saveChunk(returnChunk);
        return returnChunk;
    }
    
}