/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

import java.awt.Frame;
import javax.swing.JFrame;

/**
 *
 * @author Michael
 */
public class TheKingdoms{
    public static int HEIGHT = 1080;
    public static int WIDTH = 1920;
    public static final String NAME = "TheKingdoms";
    public static boolean test = false;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(test==false){
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
            HEIGHT = 1080;
            WIDTH = 1920;
            
        }
        frame.setBounds(0,0,WIDTH,HEIGHT);
        
        GameFrame game = new GameFrame();
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setIgnoreRepaint(true);
    }
}
