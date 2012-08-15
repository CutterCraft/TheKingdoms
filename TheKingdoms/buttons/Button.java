/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buttons;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
/**
 *
 * @author Micheal
 */
abstract public class Button {

    Panel panel;
    GameFrame gameFrame;

    int x,y,width,height,imageW,imageH;
    public Rectangle rect = new Rectangle(0, 0, 1, 1);
    
    protected Sprite sprite;

    public Button(String ref, int X, int Y, int w, int h, int imgW, int imgH){
        this.sprite = ImageLoader.get().getSprite(ref);
        x = X;
        y = Y;
        width = w;
        height = h;
        rect.x = X;
        rect.y = Y;
        rect.width = w;
        rect.height = h;
        imageW=imgW;
        imageH=imgH;
    }

    /*****
     * this is used to draw the button
     * @param g 
     */
    public void scaleDraw(Graphics g){
        sprite.scaleDraw(g,x,y,x+width,y+height,0,0,imageW,imageH);
    }

    /*****
     * this is an abstract method for use in the subclasses for when the button is clicked on
     * @param e 
     */
    abstract public void buttonClicked(MouseEvent e);
    
    /****
     * this is an abstract method for use in the TextBox subclasses
     * @param X
     * @param Y 
     */
    abstract public void resetLabel(int X, int Y);

    /*****
     * this gives a new position to the button
     * @param X
     * @param Y 
     */
    public void setPosition(int X, int Y){
        x = X;
        y = Y;
        rect.x = x;
        rect.y = y;
    }

    /*****
     * this sets the parent variables for this button so it can call upon the gameFrame or panel
     * @param g
     * @param p 
     */
    public void addLink(GameFrame g, Panel p){
        panel = p;
        gameFrame = g;
    }

    /*****
     * this checks to see if a button has been clicked on by providing the coordinates of a mouse click
     * @param pixel_x
     * @param pixel_y
     * @return 
     */
    public boolean isInPoint(int pixel_x, int pixel_y) {
        if (rect.contains(pixel_x, pixel_y)) {
            return true;
        } else {
            return false;
        }
    }

}
