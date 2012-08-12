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
public class TileStone extends MapTile{
    public TileStone(GameFrame g,int X, int Y){
        super(g, Sprites.tiles[1][0], X, Y, 16, 16, X/16, Y/16);
        setTraversable(false);
    }
}