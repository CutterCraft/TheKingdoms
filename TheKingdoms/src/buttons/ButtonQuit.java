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
public class ButtonQuit extends Button{
    
    public ButtonQuit(int x, int y){
        super("Images/Buttons/Quit.jpg", x, y, 268, 48, 268, 48);

    }

    /*********
     * this method closes the program when the button is clicked from the main menu
     * @param e 
     */
    @Override
    public void buttonClicked(MouseEvent e){
        System.exit(0);
    }

    /*******
     * method not used.
     * @param X
     * @param Y 
     */
    @Override
    public void resetLabel(int X, int Y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
