/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

/**
 *
 * @author Michael
 */
public class TileDirt extends MapTile{
    public TileDirt(int X, int Y, int Z){
        super(Sprites.tiles[2][0], X, Y, Z, 16, 16, X/16, Y/16);
        setTraversable(true);
    }
    
    public int getTileID(){
        return 1;
    }
    
}
