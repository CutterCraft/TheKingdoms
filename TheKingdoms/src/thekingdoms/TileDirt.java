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
    public TileDirt(GameFrame g,int X, int Y){
        super(g, Sprites.tiles[2][0], X, Y, 16, 16, X/16, Y/16);
        setTraversable(true);
    }
}
