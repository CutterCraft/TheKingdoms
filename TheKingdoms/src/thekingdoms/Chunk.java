/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Michael
 */
public class Chunk {
    
    public MapTile[][][] map = new MapTile[16][16][16];
    public MapTile[][] topTile = new MapTile[16][16];
    public int x,y,z;
    Random rand = new Random();
    public boolean renderable = false;
    
    public ArrayList surroundingChunks = new ArrayList();
    
    public Chunk(int X, int Y, int Z){
        x = X;
        y = Y;
        z = Z;
    }
    
    public void renderTopTiles(int oX, int oY){
        for(int i = 0; i<topTile.length; i++){
            for(int j = 0; j<topTile[i].length; j++){
                if(topTile[i][j]!=null)topTile[i][j].draw(oX+i*16,oY+j*16);
            }
        }
    }
    
    public void setTopTiles(){
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j<map[i].length; j++){
                for(int k = 0; k<map[i][j].length; k++){
                    if(map[i][j][k]!=null) topTile[i][j] = map[i][j][k];
                }
            }
        }
    }
    
    public void setRenderable(boolean b){
        renderable = b;
    }
    
    public Chunk[] getSurroundingChunks(){
        return (Chunk[])surroundingChunks.toArray();
    }
    
    public Chunk getSurroundingChunk(int i, int j, int k){
        
        Chunk[] tmpChunks = (Chunk[])surroundingChunks.toArray();
        for(Chunk tmp: tmpChunks){
            if((tmp.x == this.x+i)&&(tmp.y == this.y+j)&&(tmp.z == this.z+k)){
                return tmp;
            }
        }
        
        return null;
    }
    
    public void setSurroundingChunk(Chunk tmp){
        if(!this.surroundingChunks.contains(tmp)){
            surroundingChunks.add(tmp);
        }
    }
    
    public String[] getData(){
        
        ArrayList arrayList = new ArrayList();
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                String tmpLine = "";
                for(int k=0;k<map[i][j].length;k++){
                    if(map[i][j][k]!=null){
                        tmpLine = tmpLine+" "+map[i][j][k].getTileID();
                    }else{
                        tmpLine = tmpLine+" "+0;
                    }
                }
                arrayList.add(tmpLine);
            }
        }
        String[] returnString = new String[arrayList.size()];
        arrayList.toArray(returnString);
        return returnString;
        
    }
    
    public void setData(MapTile[][][] m){
        map = m;
    }
    
}
