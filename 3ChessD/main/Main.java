package main;

import javax.swing.JFrame;

public class Main {

    static JFrame window;
    static ChessPanel panel;
    
    public static void main(String[] args) {
        window = new JFrame();
        panel = new ChessPanel();
        
        window.setResizable(false); //oknu sa nedá meniť veľkosť
        window.setTitle("3ChessD"); //názov okna
        window.setLocationRelativeTo(null); //okno sa zobrazí v strede obrazovky
        window.setVisible(true); //okno sa zobrazí
        window.add(panel); //pridanie JPanel do JFrame(do okna)
        window.pack();
    }
}
