/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

/**
 *
 * @author Michael
 */
public class Tiles {
    
    public static Tiles tilesInstance = new Tiles();
    public static MapTile[] tiles;
    
    {
        tiles = new MapTile[1024];
        tiles[0] = new TileAir(0,0,0);
        tiles[1] = new TileDirt(0,0,0);
        tiles[2] = new TileGrass(0,0,0);
        tiles[3] = new TileSand(0,0,0);
        tiles[4] = new TileStone(0,0,0);
        tiles[5] = new TileStoneIron(0,0,0);
    }
}
