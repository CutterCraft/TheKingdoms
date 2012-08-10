/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

/**
 *
 * @author Michael
 */
public class Screen {
    
    public int width,height;
    private int xOffset = 0, yOffset = 0;
    public int[] pixels;
    private int spriteSheetTileSize = 16;
    
    public Screen(int w, int h, int tileSize){
        this.width = w;
        this.height = h;
        this.spriteSheetTileSize = tileSize;
        
        pixels = new int[w*h];
    }

    public void renderSprite(int xPos, int yPos, Sprite sprite){
        
        int h = (int)(sprite.height*GameFrame.scale);
        int w = (int)(sprite.width*GameFrame.scale);
        
        xPos -= xOffset;
        yPos -= yOffset;
        xPos *=GameFrame.scale;
        yPos *=GameFrame.scale;
        
        for(int y=0;y<h;y++){
            if(yPos + y < 0 || yPos + y >= height) continue;
            for(int x=0;x<w;x++){
                if(xPos + x < 0 || xPos + x >= width) continue;
                int col = sprite.pixels[x+(y*h)];
                if(col != -65281&&col<0) pixels[(x + xPos) + (y + yPos)*width] = col;
                
            }
            
        }
        
    }
    
}
