/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *  this is used in the pathfinding algorithm
 * @author Micheal
 */
public class Node {
    public int indexX,indexY;
    public int x;
    public int y;
    public int width,height,cX,cY;
    public int cost;
    public int pathCost;
    public int heuristic;
    public boolean traversable = true;
    public Node parent;
    public int depth = 0;
    Rectangle rect;
    public int tmpCost;
    public int tmpPathCost;

    public Color color = new Color(255,0,0);

    /************
     * this takes the index of the node and creates its position as the centre of its 1/4 of a tile
     * @param x
     * @param y
     */
    public Node(int X, int Y, int W, int H, int iX, int iY){
        x = X;
        y = Y;
        width = W;
        height = H;
        indexX = iX;
        indexY = iY;
        cX = x+(width/2);
        cY = y+(height/2);
        rect = new Rectangle(x,y,width,height);
    }

    /******************
     * this calculates the cost of this node to the target destination
     * @param tx
     * @param ty
     */
    public void calculate(int tx, int ty){
        workHeuristic(tx,ty);
        cost = pathCost+heuristic;
    }

    /*****************
     * this is how i work the heuristic out
     *this is the Manhattan method
     * @param tx
     * @param ty
     */
    public void workHeuristic(int tx, int ty){
        heuristic = (int)Math.round(/*10**/(Math.sqrt(Math.pow(tx-indexX, 2)+Math.pow(ty-indexY, 2))));
    }

    /*****************
     * this method resets the nodes values after the pathfinding has been completed
     */
    public void reset(boolean b){
        cost = 0;
        depth = 0;
        pathCost = 0;
        if(b){
            parent = null;
        }
        heuristic = 0;
    }

    /***********
     * this method is used when debugging the pathfinder. it shows all data that the node uses
     * to the subclass maptile that uses it.
     * @param g 
     */
    public void drawData(Graphics g){
        if(parent!=null){
            g.setColor(color);
            g.drawLine(this.cX,this.cY,parent.cX,parent.cY);
        }
        g.setColor(Color.BLACK);
        g.drawString(""+cost,(x+1),(y+12));
        g.drawString(""+pathCost,x+1,y+60);
        g.drawString(""+heuristic,x+32,y+60);
    }

    /********
     * this sets the colour of the text that is used when the drawData method is uses.
     * @param c 
     */
    public void setColor(Color c){
        this.color = c;
    }
}

