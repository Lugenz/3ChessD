package board;

import input.InputHandler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * for (int i = 0; i < 3; i++) {//z
for (int j = 0; j < this.size; j++) {//y
for (int k = 0; k < this.size; k++) {//x
this.board[k][j][i] = new Rectangle();
this.board[k][j][i].changeSize(48, 48);
if(i%2 == 0 && j%2 == 0 && k%2 == 0)  {
this.board[k][j][i].changeColor("black");
this.board[k][j][i].changePosition(k*48, j*48);
} else if(i%2 == 0 && j%2 == 0 && k%2 == 1){
this.board[k][j][i].changeColor("white");
this.board[k][j][i].changePosition(k*48, j*48);
}else if(i%2 == 0 && j%2 == 1 && k%2 == 0){
this.board[k][j][i].changeColor("white");
this.board[k][j][i].changePosition(k*48, j*48);
}else if(i%2 == 0 && j%2 == 1 && k%2 == 1){
this.board[k][j][i].changeColor("black");
this.board[k][j][i].changePosition(k*48, j*48);
}

else if(i%2 == 1&& j%2 == 1 && k%2 == 1)  {
this.board[k][j][i].changeColor("black");
this.board[k][j][i].changePosition(k*48, j*48);
} else if(i%2 == 1 && j%2 == 1 && k%2 == 0){
this.board[k][j][i].changeColor("white");
this.board[k][j][i].changePosition(k*48, j*48);
}else if(i%2 == 1 && j%2 == 0 && k%2 == 1){
this.board[k][j][i].changeColor("white");
this.board[k][j][i].changePosition(k*48, j*48);
}else if(i%2 == 1 && j%2 == 0 && k%2 == 0){
this.board[k][j][i].changeColor("black");
this.board[k][j][i].changePosition(k*48, j*48);
}                                       
}
}
}

for (int j = 0; j < this.size; j++) {//x
for (int k = 0; k < this.size; k++) {//y
this.board[j][k][1].makeVisible();          
}
}
 * Write a description of class chessBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChessBoard {
    private BufferedImage board;
    private ChessPiece[][][] playBoard;
    private InputHandler inputH;

    
    private int size;
    private int currentLayer;
    
    private int clicked = 0;
    private int clickedX = 0;
    private int clickedY = 0;
    private boolean colorMove = true;

    public ChessBoard(int size, InputHandler inputH) { //suppose it is 6

        this.playBoard = new ChessPiece[8][8][size];
        this.size = size;
        this.inputH = inputH;

        initializeBoard();
        this.currentLayer = 0;
        
    }

    private void initializeBoard() {        
        try {
            this.board = ImageIO.read(new File("assets/boards/board_green.png"));
        } catch (Exception e) {
            System.out.println("Failed to load image for the board");                
        }    
        
        

        for (int i = 0; i < 8; i++) { //ulozi do pola na zakladne pozicie figurky
            for (int j = 0; j < 8; j++) {
            if (i == 0) {
                switch(j) {
                case 0:
                this.playBoard[0][0][0] = new ChessPiece(0, 0, 0, "rook", false);
                break;
                case 1:
                this.playBoard[1][0][0] = new ChessPiece(1, 0, 0, "knight", false);
                break;
                case 2:
                this.playBoard[2][0][0] = new ChessPiece(2, 0, 0, "bishop", false);
                break;
                case 3:
                this.playBoard[3][0][0] = new ChessPiece(3, 0, 0, "king", false);
                break;
                case 4:
                this.playBoard[4][0][0] = new ChessPiece(4, 0, 0, "queen", false);
                break;                        
                case 5:
                this.playBoard[5][0][0] = new ChessPiece(5, 0, 0, "bishop", false);
                break;
                case 6:
                this.playBoard[6][0][0] = new ChessPiece(6, 0, 0, "knight", false);
                break;
                case 7:
                this.playBoard[7][0][0] = new ChessPiece(7, 0, 0, "rook", false);
                break;                                            
                }
            }
            
            if (i == 1) {
                this.playBoard[j][1][0] = new ChessPiece(j, 1, 0, "pawn", false);                    
            } 
            
            if (i == 7) {
                switch(j) {
                case 0:
                this.playBoard[0][7][0] = new ChessPiece(0, 7, 0, "rook", true);
                break;
                case 1:
                this.playBoard[1][7][0] = new ChessPiece(1, 7, 0, "knight", true);
                break;
                case 2:
                this.playBoard[2][7][0] = new ChessPiece(2, 7, 0, "bishop", true);
                break;
                case 3:
                this.playBoard[3][7][0] = new ChessPiece(3, 7, 0, "king", true);
                break;
                case 4:
                this.playBoard[4][7][0] = new ChessPiece(4, 7, 0, "queen", true);
                break;                        
                case 5:
                this.playBoard[5][7][0] = new ChessPiece(5, 7, 0, "bishop", true);
                break;
                case 6:
                this.playBoard[6][7][0] = new ChessPiece(6, 7, 0, "knight", true);
                break;
                case 7:
                this.playBoard[7][7][0] = new ChessPiece(7, 7, 0, "rook", true);
                break;                                            
                }
            }
            
            if (i == 6) {
                this.playBoard[j][6][0] = new ChessPiece(j, 6, 0, "pawn", true);                    
            } 
            }
        }
    }
    
    public void update() {
        
        if (inputH.getMouseClicked()) {
            if (inputH.getMouseX() > 0 && inputH.getMouseX() < 576 && inputH.getMouseY() < 576 && inputH.getMouseY() > 0) {
            int x = inputH.getMouseX() / 72;
            int y = inputH.getMouseY() / 72;                      
            this.clicked++;

            if (clicked == 1) {
                if (this.playBoard[x][y][currentLayer] != null) {
                clickedX = x;
                clickedY = y;
                }
                else {
                    this.clicked = 0;
                }
            }
            if (clicked == 2) {
                int x1 = clickedX;
                int y1 = clickedY;
                int z1 = currentLayer;

                int x2 = x;
                int y2 = y;
                int z2 = currentLayer;

                movePiece(x1, y1, z1, x2, y2, z2);
                this.clicked = 0;
            }
        
        }    
        inputH.resetMouseClicked();     
    }
    
    }

    public void draw(Graphics g) { //nakresli hraciu plochu(kresli ju zbytocne vzdy pri aktualizacii Threadu)
        if (board != null) {
            g.drawImage(board, 0, 0, null); 
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {                
                    if (this.playBoard[i][j][0] != null) {
                        g.drawImage(this.playBoard[i][j][0].getImage(), this.playBoard[i][j][0].getX(), this.playBoard[i][j][0].getY(),this.playBoard[i][j][0].getSize(), this.playBoard[i][j][0].getSize(), null);
                    }        
            }
        }
    }
    
    private boolean isValidMove(int x1, int y1, int z1, int x2, int y2, int z2) {
        if (this.playBoard[x1][y1][z1] != null && this.playBoard[x1][y1][z1].getColor() == this.colorMove) {
            if (this.playBoard[x2][y2][z2] != null && this.playBoard[x1][y1][z1].getColor() == this.playBoard[x2][y2][z2].getColor()) {
                return false;
            }

            
            this.colorMove = !this.colorMove;
            return true;
        }
        return false;
    }

    public void getLayer(int layer) {

    }

    private void movePiece(int x1, int y1, int z1, int x2, int y2, int z2) {
        if(isValidMove(x1, y1, z1, x2, y2, z2))
        {
            this.playBoard[x2][y2][z2] = this.playBoard[x1][y1][z1];
            this.playBoard[x2][y2][z2].setPosition(x2, y2, z2);

            this.playBoard[x1][y1][z1] = null;
        }        
    }
}
