/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

/**
 *
 * @author Michael
 */
public class TileStoneIron extends MapTile{
    public TileStoneIron(int X, int Y, int Z){
        super(Sprites.getSprite(Sprites.tiles,1,1), X, Y, Z, 16, 16, X/16, Y/16);
        setTraversable(true);
    }
    
    public int getTileID(){
        return 5;
    }
}