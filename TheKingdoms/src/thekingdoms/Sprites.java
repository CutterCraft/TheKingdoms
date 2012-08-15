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
        tiles = new Sprite[GameFrame.scales.length][][];
        for(int i=0;i<tiles.length;i++){
            tiles[i] = SpriteSheetLoader.cutTiles("/tiles.png",16,16,GameFrame.scales[i]);
        }
    }
    
    public static Sprite[] getSprite(Sprite[][][] tmp, int i, int j){
        Sprite[] returnSprite = new Sprite[tmp.length];
        for(int a=0;a<returnSprite.length;a++){
            returnSprite[a] = tmp[a][i][j];
        }
        return returnSprite;
    }
}
