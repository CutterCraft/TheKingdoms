/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buttons;

import java.awt.event.MouseEvent;
/**
 *
 * @author Micheal
 */
public class ButtonOptions extends Button{
    
    public ButtonOptions(int x, int y){
        super("Images/Buttons/Options.jpg", x, y, 268, 48, 268, 48);

    }

    /*******
     * this method sets the option state in the gameFrame by being clicked on in the main menu.
     * @param e 
     */
    @Override
    public void buttonClicked(MouseEvent e){
        gameFrame.setGameState(2);
    }

    /*********
     * method not used.
     * @param X
     * @param Y 
     */
    @Override
    public void resetLabel(int X, int Y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
