package main;

import board.ChessBoard;
import input.InputHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ChessPanel extends JPanel implements Runnable{
    ChessBoard board;
    JButton button;
    private Thread thread;
    private InputHandler inputH;

    public ChessPanel() {       
        this.setPreferredSize(new Dimension(576, 672)); //Veľkosť okna rodičovskej triedy
        this.setBackground(Color.white); //Farba
        this.setDoubleBuffered(true);

        this.button = new JButton("Zmena vrstvy");

        this.setLayout(null);

        this.button.setBounds(213, 604, 150, 40);
        this.add(button);

        this.inputH = new InputHandler();
        this.addMouseListener(inputH); //JPanel teraz registruje kliky myšou
        this.board = new ChessBoard(3, this.inputH);

        this.thread = new Thread(this);

        this.thread.start();
    }

    @Override
    public void run() {

        while (thread != null) {
            //UPDATE: pozicia charakteru
            update();
            //DRAW: vykresli na obrazovku aktualizované informácie
            repaint(); //volá paintComponent
            try {
                Thread.sleep(1000/100); //60fps
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        this.board.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //volanie metódy patriacej rodičovskej triede triedy JPanel
        this.board.draw(g);
        g.dispose(); //uvolni prostriedky, ktoré g2 zaberá v pamäti (už ich nepotrebuje)
    }
}
