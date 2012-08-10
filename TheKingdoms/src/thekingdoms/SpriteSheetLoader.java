/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.imageio.ImageIO;

/**
 *
 * @author Michael
 */
public class SpriteSheetLoader {
    
    public int[] sheetPixels;
    public int[] pixels;
    int x,y,sheetWidth;
    
    public SpriteSheetLoader(){
        
    }
    
    public static Sprite[][] cutTiles(String fileName, int w, int h){
        
        return cutTiles(fileName,w,h,0,0);
        
    }
    
    public static Sprite[][] cutTiles(String fileName, int w, int h, int xOffset, int yOffset){
        
        try{
            BufferedImage image = ImageIO.read(GameFrame.class.getResource(fileName));
            
            int xTiles = (image.getWidth()-xOffset)/w;
            int yTiles = (image.getHeight()-yOffset)/h;
            
            Sprite[][] result = new Sprite[xTiles][yTiles];
            
            for(int x=0;x<xTiles;x++){
                for(int y=0;y<yTiles;y++){
                    result[x][y] = new Sprite(w,h);
                    
                    image.getRGB(xOffset+x*w,yOffset+y*h,w,h,result[x][y].pixels,0,w);
                }
            }
            
            return result;
        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        
        return null;
        
    }
    
}
