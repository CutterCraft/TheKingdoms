/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buttons;

import java.awt.event.MouseEvent;
/**
 *
 * @author Michael
 */
public class ButtonNewGame extends Button {
    
    public ButtonNewGame(int x, int y){
        super("Images/Buttons/NewGame.jpg", x, y, 268, 48,268,48);

    }

    /********
     * this method creates a new game by setting the gameFrame state to game.
     * @param e 
     */
    @Override
    public void buttonClicked(MouseEvent e){
        gameFrame.setGameState(4);
    }

    /*****
     * method not used.
     * @param X
     * @param Y 
     */
    @Override
    public void resetLabel(int X, int Y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
