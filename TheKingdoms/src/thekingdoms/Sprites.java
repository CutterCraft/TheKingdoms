/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

/**
 *
 * @author Michael
 */
public class Sprites {
    public static Sprites spritesInstance = new Sprites();
    public static Sprite[][][] tiles;
    
    {
    tiles = new Sprite[5][][];
    tiles[0] = SpriteSheetLoader.cutTiles("/tiles.png",16,16,0.25);
    tiles[1] = SpriteSheetLoader.cutTiles("/tiles.png",16,16,0.5);
    tiles[2] = SpriteSheetLoader.cutTiles("/tiles.png",16,16);
    tiles[3] = SpriteSheetLoader.cutTiles("/tiles.png",16,16,2);
    tiles[4] = SpriteSheetLoader.cutTiles("/tiles.png",16,16,4);
    }
    
    public static Sprite[] getSprite(Sprite[][][] tmp, int i, int j){
        Sprite[] returnSprite = new Sprite[tmp.length];
        for(int a=0;a<returnSprite.length;a++){
            returnSprite[a] = tmp[a][i][j];
        }
        return returnSprite;
    }
}
