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
public class Chunk {
    
    public MapTile[][][] map = new MapTile[16][16][256];
    public MapTile[][] topTile = new MapTile[16][16];
    public int x,y;
    GameFrame gameFrame;
    Random rand = new Random();
    
    public int[][] surfaceHeight = new int[16][16];
    
    public Chunk(GameFrame g, int X, int Y){
        gameFrame = g;
        x = X;
        y = Y;
        setSurfaceHeight(64);
        preGenerateChunk();
        setTopTiles();
    }
    
    public void renderTopTiles(int oX, int oY){
        for(int i = 0; i<topTile.length; i++){
            for(int j = 0; j<topTile[i].length; j++){
                topTile[i][j].draw(oX+i*16,oY+j*16);
            }
        }
    }
    
    public void preGenerateChunk(){
        for(int x=0;x<map.length;x++){
            for(int y=0;y<map[x].length;y++){
                int tmp = rand.nextInt(7);
                for(int z=0;z<surfaceHeight[x][y]-tmp;z++){
                    map[x][y][z] = new TileStoneIron(gameFrame,x,y);
                    
                }
                for(int z=surfaceHeight[x][y]-tmp;z<surfaceHeight[x][y];z++){
                    map[x][y][z] = new TileGrass(gameFrame,x,y);
                    
                }
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
    
    public void setSurfaceHeight(int a){
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j<map[i].length; j++){
                surfaceHeight[i][j] = a;
            }
        }
    }
    
}
