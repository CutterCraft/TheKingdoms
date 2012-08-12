/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

import java.util.Random;

/**
 *
 * @author Michael
 */
public class WorldGenerator {
    
    public static WorldGenerator worldGen = new WorldGenerator();
    Random rand = new Random();
    
    public WorldGenerator(){
        
    }
    
    public static WorldGenerator getWorldGenerator(){
        return worldGen;
    }
    
    public void preGenerateChunk(Chunk c){
        for(int x=0;x<c.map.length;x++){
            for(int y=0;y<c.map[x].length;y++){
                for(int z=0;z<c.map[x][y].length;z++){
                    c.map[x][y][z] = new TileStone(x,y,z);
                    
                }
            }
        }
    }
    
    public void createChunk(int i, int j, int k){
        
    }
    
}
