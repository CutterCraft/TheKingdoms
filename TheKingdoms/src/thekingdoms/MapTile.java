/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

import java.awt.Rectangle;
import pathfinder.Node;
/**
 *
 * @author Micheal
 */
public abstract class MapTile extends Node {
    public Rectangle recte = new Rectangle(0, 0, 1, 1);
    protected Sprite sprite;
    int imgWidth,imgHeight;
    String mapTileType;
    GameFrame gameFrame;

    public MapTile(GameFrame g, Sprite spr, int X, int Y, int w, int h, int iX, int iY){
        super(X, Y, w, h, iX, iY);
        sprite = spr;
        imgWidth = 32;
        imgHeight = 32;
        gameFrame = g;
        
    }

    /**********
     * this method draws the MapTile to the screen.
     * @param g 
     */
    public void draw(int drawX, int drawY){
        gameFrame.screen.renderSprite(drawX, drawY, sprite);
    }

    /*********
     * this method returns the x coordinate of the tile.
     * @return 
     */
    public int getX(){
        return x;
    }

    /***********
     * this method returns the y coordinate of the tile.
     * @return 
     */
    public int getY(){
        return y;
    }

    /************
     * this method returns the width of the tile.
     * @return 
     */
    public int getWidth(){
        return width;
    }

    /******
     * this method returns the height of the tile.
     * @return 
     */
    public int getHeight(){
        return height;
    }

    /***********
     * this method sets the x coordinate of the tile.
     * @param tmpX 
     */
    public void setX(int tmpX){
        x = tmpX;
    }

    /************
     * this method sets the y coordinate of the tile.
     * @param tmpY 
     */
    public void setY(int tmpY){
        y = tmpY;
    }

    /**************
     * this method sets whether the tile can be used to move upon in the pathfinding class.
     * if it is false this means that units cannot move onto it.
     * @param b 
     */
    public void setTraversable(boolean b){
        this.traversable = b;
    }

}
