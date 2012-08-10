/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

/**
 *
 * @author Michael
 */
public class Sprite {
    
    public int width, height;
    public int[] pixels;
    
    public Sprite(int w, int h){
        this.width = w;
        this.height = h;
        this.pixels = new int[w*h];
    }
    
    public void clear(int col){
        for(int i=0;i<pixels.length;i++){
            pixels[i] = col;
        }
    }
    
    
}
