package main;

 

import javax.swing.JFrame;


public class Main {

    private static JFrame window;
    private static ChessPanel panel;
    
    /**
    * The main method serves as the entry point for the application.
    * It initializes the main window (JFrame) and the chess panel (ChessPanel),
    * sets various properties for the window such as non-resizable, title, and location,
    * makes the window visible, and adds the chess panel to the window.
    *
    * @param args Command line arguments (not used in this application).
    */
    public static void main(String[] args) {
        window = new JFrame();
        panel = new ChessPanel();
        
        window.setTitle("2ChessD");                 // window title
        window.setResizable(false);                 // the window cannot be resized   
        window.add(panel);                          // add JPanel to JFrame (to the window)
        window.pack();                              // set the window size according to the size of JPanel

        window.setLocation(
            (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2 - window.getWidth() / 2),
            (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2 - window.getHeight() / 2)
        );                                          // the window will be in the center of the screen
        window.setVisible(true);                  // the window will be displayed       
       
    }
}
