package main;

import board.ChessBoard;
import input.InputHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ChessPanel extends JPanel implements Runnable {
    private ChessBoard board;
    private Thread thread;
    private InputHandler inputH;
    private BufferedImage gameState1;
    private BufferedImage gameState2;

    /**
     * Constructor for the ChessPanel class.
     * Initializes the panel with a preferred size, background color, and double buffering.
     * Adds a mouse listener and starts the game thread.
     */
    public ChessPanel() {       
        this.setPreferredSize(new Dimension(576, 672));             // size of JPanel
        this.setBackground(new Color(102, 66, 40));                        // wood brown color
        this.setDoubleBuffered(true);

        try {
            this.gameState1 = ImageIO.read(new File("2ChessD/assets/white/gameState1.png"));
            this.gameState2 = ImageIO.read(new File("2ChessD/assets/black/gameState2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.inputH = new InputHandler();
        this.addMouseListener(this.inputH);                                     //adds MouseListener to this JPanel      
        this.board = new ChessBoard(this.inputH);

        this.thread = new Thread(this);
        this.thread.start();                                                    //starts the game thread
    }

    /**
     * The run method for the game thread.
     * Continuously updates and repaints the game at a fixed frame rate.
     */
    @Override
    public void run() {

        while (this.thread != null) {

            this.update();                                                      //updates the game

            repaint();                                                          //draws the updated game (calls paintComponent)
            try {
                Thread.sleep(1000 / 100);                                       //60fps
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the game state by calling the update method on the ChessBoard.
     */
    public void update() {
        this.board.update();
    }

    /**
     * Paints the game components on the panel.
     * Draws the chess board, version, author, current player's move and state of the game.
     * 
     * @param g the Graphics object used for drawing
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);                                                        // calling method belonging to JPanel
        this.board.draw(g);                                                             // draws the board 
                                                                                                           
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("2ChessD 1.0.0, made by Lukáš Rakovan, 2025", 10, 653 + 12);   // prints out the version and author of the game
        
        
        if(this.board.getColorMove()) {
            g.setColor(Color.white);
                if (this.board.isCheckmate()) {
                    g.setFont(new Font("Arial", Font.BOLD, 28));
                    g.drawString("Checkmate! White wins!", 125, 600 + 28);
                    this.thread = null;                                                         // stops the game thread
                } else {               
                g.fillRect(506, 591, 50, 50);
                g.drawRect(506, 591, 50, 50);
                g.drawString("White" + "'s move ", 490, 646 + 12);                          // prints out whose move it is                
                if (this.board.isCheck()) {
                    g.drawImage(this.gameState1, 10, 591, null);                // draws the check image
                }
            }
        } else {
            g.setColor(Color.black);
            if (this.board.isCheckmate()) {
                g.setFont(new Font("Arial", Font.BOLD, 28));
                g.drawString("Checkmate! Black wins!", 125, 600 + 28); 
                this.thread = null;                                                               
            } else {                   
                    g.fillRect(506, 591, 50, 50);
                    g.drawRect(506, 591, 50, 50);   
                    g.drawString("Black" + "'s move ", 490, 646 + 12);              
                    if (this.board.isCheck()) {
                        g.drawImage(this.gameState2, 10, 591, null);
                    }
                }
            }  
        g.dispose();                                                                    // releases resources that g occupies in memory (no longer needed)
    }
}

