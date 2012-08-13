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
        
        return cutTiles(fileName,w,h,0,0,1);
        
    }
    
    public static Sprite[][] cutTiles(String fileName, int w, int h, double scale){
        
        return cutTiles(fileName,w,h,0,0,scale);
        
    }
    
    public static Sprite[][] cutTiles(String fileName, int w, int h, int xOffset, int yOffset, double scale){
        
        try{
            BufferedImage image = ImageIO.read(GameFrame.class.getResource(fileName));
            
            int xTiles = (image.getWidth()-xOffset)/w;
            int yTiles = (image.getHeight()-yOffset)/h;
            
            Sprite[][] result = new Sprite[xTiles][yTiles];
            
            for(int x=0;x<xTiles;x++){
                for(int y=0;y<yTiles;y++){
                    result[x][y] = new Sprite(w,h);
                    
                    image.getRGB(xOffset+x*w, yOffset+y*h, w, h, result[x][y].pixels, 0, w);
                }
            }
            
            for(int x=0;x<xTiles;x++){
                for(int y=0;y<yTiles;y++){
                    int[] tmpHolder = result[x][y].pixels;
                    int newW = (int)(w*scale);
                    int newH = (int)(h*scale);
                    int[] modifiedHolder = new int[newW*newH];
                    
                    for(int i=0;i<modifiedHolder.length;i++){
                        modifiedHolder[i] = tmpHolder[(int)(i/scale/scale)];
                    }
                    
                    System.out.println("ran");
                    result[x][y] = new Sprite(newW,newH);
                    result[x][y].pixels = modifiedHolder;
                }
            }
            
            return result;
        }catch(Exception e){
            System.err.println("error in spriteSheetLoader: "+e.getLocalizedMessage());
        }
        
        return null;
        
    }
    
}
