/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * this method encompasses the A* algorithm used in the game
 *
 * @author Micheal
 */
public class Pathfinder {
    public int startX;
    public int startY;
    public int targetX;
    public int targetY;
    public ArrayList open = new ArrayList();
    public ArrayList closed = new ArrayList();
    public boolean targetReached = false;
    public int maximumDepth = 100000000;
    boolean ranOnce = false;
    Node[][] map;

    boolean diagonal = false;

    Node currentNode;
    Node endNode;
    Node neighbour[];


    /****************
     * this sets the pathfinder up when it is created when an order is given
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     */
    public Pathfinder(int sx, int sy, int tx, int ty, Node node[][]){
        startX=sx;
        startY=sy;
        targetX=tx;
        targetY=ty;
        map = node;
    }

    public Pathfinder(int sx, int sy, int tx, int ty, Node node[][], boolean diag){
        startX=sx;
        startY=sy;
        targetX=tx;
        targetY=ty;
        map = node;
        diagonal = diag;
    }

    /*****************
     * this finds the path to the target node and returns an array of the path
     * @return
     */
    public Node[] findPath(){
        map[startX][startY].reset(true);
        open.add(map[startX][startY]);
        neighbour = new Node [4];
        endNode = map[targetX][targetY];

        while(targetReached==false){
            sort();
            currentNode = (Node)open.get(0);
            if(currentNode.depth>=maximumDepth){
                targetReached = true;
                endNode = currentNode;
            }else{
                int tmpG = currentNode.pathCost+10;
                int tmpDepth = currentNode.depth+1;
                neighbour = getSurroundingNodes(currentNode);
                open.remove(0);
                closed.add(currentNode);
                for (int i=0; i<4;i++) {
                    if(neighbour[i]!=null){
                        if(neighbour[i].traversable == false){
                            closed.add(neighbour[i]);

                        } else if (closed.contains(neighbour[i])) {
                            if(tmpG<neighbour[i].pathCost){
                                neighbour[i].pathCost = tmpG;
                                neighbour[i].depth = tmpDepth;
                                neighbour[i].parent = currentNode;
                                neighbour[i].calculate(targetX,targetY);
                            }
                        } else if (open.contains(neighbour[i])) {
                            if(tmpG<neighbour[i].pathCost){
                                neighbour[i].pathCost = tmpG;
                                neighbour[i].parent = currentNode;
                                neighbour[i].depth = tmpDepth;
                                neighbour[i].calculate(targetX,targetY);
                                }
                        }else {
                            neighbour[i].pathCost = tmpG;
                            neighbour[i].calculate(targetX,targetY);
                            neighbour[i].parent = currentNode;
                            neighbour[i].depth = tmpDepth;
                            open.add(neighbour[i]);
                            
                        }
                        }
                }
                if(diagonal){
                    neighbour = getDiagonalNodes(currentNode);
                    tmpG+=4;
                    for (int i=0; i<4;i++) {
                        if(neighbour[i]!=null){
                            if(neighbour[i].traversable == false){
                                closed.add(neighbour[i]);
                            }else if (closed.contains(neighbour[i])) {
                                if(tmpG<neighbour[i].pathCost){
                                    neighbour[i].pathCost = tmpG;
                                    neighbour[i].parent = currentNode;
                                    neighbour[i].depth = tmpDepth;
                                    neighbour[i].calculate(targetX,targetY);
                                }
                            } else if (open.contains(neighbour[i])) {
                                if(tmpG<neighbour[i].pathCost){
                                    neighbour[i].pathCost = tmpG;
                                    neighbour[i].parent = currentNode;
                                    neighbour[i].depth = tmpDepth;
                                    neighbour[i].calculate(targetX,targetY);
                                }
                            }else {
                                neighbour[i].pathCost = tmpG;
                                neighbour[i].calculate(targetX,targetY);
                                neighbour[i].parent = currentNode;
                                neighbour[i].depth = tmpDepth;
                                open.add(neighbour[i]);
                                
                            }
                        }
                    }
                }
            }
            if(currentNode == map[targetX][targetY]){
                targetReached = true;
                endNode = currentNode;
            }
        }
        
        return getPath(endNode);

    }

    /*****************
     * this returns the horizontal and vertical nodes surrounding the current node
     * @param node
     * @return
     */
    public Node[] getSurroundingNodes(Node node){
        Node returnNodes[] = new Node[4];
        try{
                returnNodes[0] = map[node.indexX][node.indexY+1];
        }catch(Exception e){}
        try{
                returnNodes[1] = map[node.indexX+1][node.indexY];
        }catch(Exception e){}
        try{
                returnNodes[2] = map[node.indexX-1][node.indexY];
        }catch(Exception e){}
        try{
                returnNodes[3] = map[node.indexX][node.indexY-1];
        }catch(Exception e){}
        return returnNodes;

    }

    /**********************
     * this returns the diagonal nodes of the current node
     * @param node
     * @return
     */
    public Node[] getDiagonalNodes(Node node){
        Node returnNodes[] = new Node[4];
        try{
            returnNodes[0] = map[node.indexX+1][node.indexY+1];
        }catch(Exception e){}
        try{
            returnNodes[1] = map[node.indexX-1][node.indexY+1];
        }catch(Exception e){}
        try{
            returnNodes[2] = map[node.indexX+1][node.indexY-1];
        }catch(Exception e){}
        try{
            returnNodes[3] = map[node.indexX-1][node.indexY-1];
        }catch(Exception e){}
        return returnNodes;
    }

    /******************
     * this was the only way i could think of getting the list to sort as i didnt understand
     * the lists built in sort method so i improvised
     */
    public void sort(){
        Node nodeArray[] = new Node[open.size()];
        for (int i=0; i<open.size();i++){
            nodeArray[i] = (Node)open.get(i);
        }
        open.clear();
        for(int b=3; b>-1; b--){
            for(int i=nodeArray.length-b;i>0;i--){
                try{
                if(nodeArray[i].cost<nodeArray[i-1].cost){
                    Node tmpNode = nodeArray[i-1];
                    nodeArray[i-1] = nodeArray[i];
                    nodeArray[i] = tmpNode;
                }
                }catch(Exception e){}
            }
        }
        open.addAll(Arrays.asList(nodeArray));

    }

    /***************
     * this traces the path taken and then sorts it into an array from where the
     * algorithm started from so the unit can follow it
     * @param endNode
     * @return
     */
    public Node[] getPath(Node endNode){
        Node returnArray[] = new Node[endNode.depth+1];
        try{
            returnArray[endNode.depth] = endNode;
        }catch(Exception e){
        }
        Node currentNode = endNode;
        for(int i=endNode.depth-1;i>-1;i--){
            int parentPosX = 0;
            int parentPosY = 0;
            if(currentNode.parent!=null){
                parentPosX = currentNode.parent.indexX;
                parentPosY = currentNode.parent.indexY;
            }
            returnArray[i]=currentNode.parent;
            currentNode = returnArray[i];
        }
        open.clear();
        closed.clear();
        return returnArray;
    }

    /******
     * this method returns the found path.
     * @return 
     */
    public Node[] getWaypoints(){
        return getPath(endNode);
    }

}
