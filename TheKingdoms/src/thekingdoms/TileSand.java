/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

/**
 *This class is a holder for the MapTile class that determines what the MapTile looks like and whether it is
 * traversable or not.
 * @author Micheal
 */
public class TileSand extends MapTile{
    public TileSand(int X, int Y, int Z){
        super(Sprites.getSprite(Sprites.tiles,3,0), X, Y, Z, 16, 16, X/16, Y/16);
        setTraversable(true);
    }
    
    public int getTileID(){
        return 3;
    }
}
