package board;

import input.InputHandler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Write a description of class chessBoard here.
 * 
 * @author (Lukáš Rakovan) 
 * @version (1.0.0)
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
    /**
    * Initializes the chess board by loading the board image and placing the chess pieces
    * in their starting positions.
    * The board image is loaded from the specified file path.
    * The pieces are placed in their standard initial positions for a chess game.
    */
    private void initializeBoard() {        
        try {
            this.board = ImageIO.read(new File("3ChessD/assets/boards/board_green.png"));
        } catch (Exception e) {
            System.out.println("Failed to load image for the board");                
        }    

        for (int i = 0; i < 8; i++) { //ulozi do pola na zakladne pozicie figurky
            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    switch(j) {
                        case 0:
                        this.playBoard[0][0][0] = new ChessPiece(0, 0, 0, PieceType.ROOK, false);
                        break;
                        case 1:
                        this.playBoard[1][0][0] = new ChessPiece(1, 0, 0, PieceType.KNIGHT, false);
                        break;
                        case 2:
                        this.playBoard[2][0][0] = new ChessPiece(2, 0, 0, PieceType.BISHOP, false);
                        break;
                        case 3:
                        this.playBoard[3][0][0] = new ChessPiece(3, 0, 0, PieceType.KING, false);
                        break;
                        case 4:
                        this.playBoard[4][0][0] = new ChessPiece(4, 0, 0, PieceType.QUEEN, false);
                        break;                        
                        case 5:
                        this.playBoard[5][0][0] = new ChessPiece(5, 0, 0, PieceType.BISHOP, false);
                        break;
                        case 6:
                        this.playBoard[6][0][0] = new ChessPiece(6, 0, 0, PieceType.KNIGHT, false);
                        break;
                        case 7:
                        this.playBoard[7][0][0] = new ChessPiece(7, 0, 0, PieceType.ROOK, false);
                        break;                                            
                    }
                }

                if (i == 1) {
                    this.playBoard[j][1][0] = new ChessPiece(j, 1, 0, PieceType.PAWN, false);                    
                } 

                if (i == 7) {
                    switch(j) {
                        case 0:
                        this.playBoard[0][7][0] = new ChessPiece(0, 7, 0, PieceType.ROOK, true);
                        break;
                        case 1:
                        this.playBoard[1][7][0] = new ChessPiece(1, 7, 0, PieceType.KNIGHT, true);
                        break;
                        case 2:
                        this.playBoard[2][7][0] = new ChessPiece(2, 7, 0, PieceType.BISHOP, true);
                        break;
                        case 3:
                        this.playBoard[3][7][0] = new ChessPiece(3, 7, 0, PieceType.KING, true);
                        break;
                        case 4:
                        this.playBoard[4][7][0] = new ChessPiece(4, 7, 0, PieceType.QUEEN, true);
                        break;                        
                        case 5:
                        this.playBoard[5][7][0] = new ChessPiece(5, 7, 0, PieceType.BISHOP, true);
                        break;
                        case 6:
                        this.playBoard[6][7][0] = new ChessPiece(6, 7, 0, PieceType.KNIGHT, true);
                        break;
                        case 7:
                        this.playBoard[7][7][0] = new ChessPiece(7, 7, 0, PieceType.ROOK, true);
                        break;                                            
                    }
                }

                if (i == 6) {
                    this.playBoard[j][6][0] = new ChessPiece(j, 6, 0, PieceType.PAWN, true);                    
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

    /**
     * Method, which draws every existing piece on the board, takes 1 parameter
     * @param g Graphics object, which is used to draw the pieces on the board, it is passed by the paintComponent method in class ChessPanel
     */   
    public void draw(Graphics g) { 
        if (board != null) { //if the board image is loaded
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

    /**
     * Checks if a move from one position to another is valid based on the rules of chess.
     *
     * @param x1 the x-coordinate of the piece's current position
     * @param y1 the y-coordinate of the piece's current position
     * @param z1 the z-coordinate of the piece's current position
     * @param x2 the x-coordinate of the piece's destination position
     * @param y2 the y-coordinate of the piece's destination position
     * @param z2 the z-coordinate of the piece's destination position
     * @return true if the move is valid, false otherwise
     */
    private boolean isValidMove(int x1, int y1, int z1, int x2, int y2, int z2) {
        String pieceName = this.playBoard[x1][y1][z1].getPiece().name;

        int movesClickedX = Math.abs(x1 - x2); //absolute value of the difference between the coordinates of the selected and destination tile
        int movesClickedY = Math.abs(y1 - y2);

        //VERIFICATION OF PIECE MOVEMENT
        switch (pieceName) {
            case "pawn":
            //MOVING MORE THAN 1 TILE FORWARD                    
            if (movesClickedY == 2 && y1 != 1 && y1 != 6) { //if the pawn is trying to move 2 tiles forward and it is not on the starting position                                         
                return false; 
            }    
            if (movesClickedX != 0 && movesClickedY > 1) { //if the pawn is trying to move more than 1 tile forward and also sideways
                return false;
            }
            
        
            //CAPTURING
            if (movesClickedX == 0 && movesClickedY == 1 && this.playBoard[x2][y2][z2] != null) { //if the pawn is trying to move forward whilst there is a piece
                return false;
            }
            if (movesClickedX == 1 && movesClickedY == 1 && this.playBoard[x2][y2][z2] == null) { //if the destination tile is empty
                return false;
            }
            //MOVING BACKWARDS
            if (this.playBoard[x1][y1][z1].getColor()) {
                if (y1 - y2 < 0) { //if the white pawn is trying to move backwards or sideways
                    return false;
                }
            } else if (y1 - y2 > 0) { //if the black pawn is trying to move backwards
                return false;                
            }
            //MOVING SIDEWAYS
            if (x1 - x2 != 0 && y1 - y2 == 0) { //if the pawn is trying to only move sideways
                return false;
            } 
            break;
            case "rook":
            if (movesClickedX > 0 && movesClickedY > 0) {
                return false;
            }
            //STRAIGHT MOVE COLLISION DETECTION
            if (movesClickedX != 0) {
                if (x1 > x2) {
                    for (int i = 1; i <= (x1 - x2); i++) {
                        if (this.playBoard[i+x2][y1][z1] != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = 1; i <= (x2 - x1); i++) {
                        if (this.playBoard[i+x1][y1][z1] != null) {
                            return false;
                        }
                    }
                }                
            } else if (movesClickedY != 0) {
                if (y1 > y2) {
                    for (int i = 1; i < (y1 - y2); i++) {
                        if (this.playBoard[x1][i+y2][z1] != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = 1; i < (y2 - y1); i++) {
                        if (this.playBoard[x1][i+y1][z1] != null) {
                            return false;
                        }
                    }
                }

            }         
            break;
            case "knight":
            if (movesClickedX != 2) {
                if (movesClickedY != 2) {
                    return false;
                } else {
                    if (movesClickedX != 1) {
                        return false;
                    }
                }
            } else {
                if (movesClickedY != 1) {
                    return false;
                }   

            }
            break;
            case "bishop":
            if (movesClickedX != movesClickedY) {
                return false;
            }
            //DIAGONAL MOVE COLLISION DETECTION
            if (x1 < x2) {
                if (y1 < y2) {
                    for (int i = 1; i < movesClickedY; i++) {
                        if (this.playBoard[x1+i][y1+i][z1] != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = 1; i < movesClickedY; i++) {
                        if (this.playBoard[x1+i][y1-i][z1] != null) {
                            return false;
                        }
                    }
                }                
            } else if (x1 > x2) {
                if (y1 > y2) {
                    for (int i = 1; i < movesClickedY; i++) {
                        if (this.playBoard[x1-i][y1-i][z1] != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = 1; i < movesClickedY; i++) {
                        if (this.playBoard[x1-i][y1+i][z1] != null) {
                            return false;
                        }
                    }
                }                
            }

            break;
            case "queen":
            if (movesClickedX > 0 && movesClickedY > 0 && movesClickedX != movesClickedY) {
                return false;
            }
            //STRAIGHT MOVE COLLISION DETECTION
            if (movesClickedX > movesClickedY) {
                if (x1 > x2) {
                    for (int i = 1; i <= (x1 - x2); i++) {
                        if (this.playBoard[i+x2][y1][z1] != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = 1; i <= (x2 - x1); i++) {
                        if (this.playBoard[i+x1][y1][z1] != null) {
                            return false;
                        }
                    }
                }                
            } else if (movesClickedY > movesClickedX) {
                if (y1 > y2) {
                    for (int i = 1; i < (y1 - y2); i++) {
                        if (this.playBoard[x1][i+y2][z1] != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = 1; i < (y2 - y1); i++) {
                        if (this.playBoard[x1][i+y1][z1] != null) {
                            return false;
                        }
                    }
                }

            }      
            //DIAGONAL MOVE COLLISION DETECTION
            if (x1 < x2) {
                if (y1 < y2) {
                    for (int i = 1; i < movesClickedY; i++) {
                        if (this.playBoard[x1+i][y1+i][z1] != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = 1; i < movesClickedY; i++) {
                        if (this.playBoard[x1+i][y1-i][z1] != null) {
                            return false;
                        }
                    }
                }                
            } else if (x1 > x2) {
                if (y1 > y2) {
                    for (int i = 1; i < movesClickedY; i++) {
                        if (this.playBoard[x1-i][y1-i][z1] != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = 1; i < movesClickedY; i++) {
                        if (this.playBoard[x1-i][y1+i][z1] != null) {
                            return false;
                        }
                    }
                }                
            }   
            break;
            case "king":
            if (movesClickedX > 1 || movesClickedY > 1) {
                return false;
            }
            break;
        }

        //VERIFICATION OF MOVE DESTINATION                       
        if (this.playBoard[x1][y1][z1] != null && this.playBoard[x1][y1][z1].getColor() == this.colorMove) { //if the selected tile has a piece and the color of the piece is the same as the color of the player on turn
            if (this.playBoard[x2][y2][z2] != null && this.playBoard[x1][y1][z1].getColor() == this.playBoard[x2][y2][z2].getColor()) { //if the destination tile has a piece and the color of the destination piece is different from the color of the selected piece
                return false;
            }                       
            return true;
        }
        return false;
    }

    public void getLayer(int layer) {

    }

    /**
     * Moves a chess piece from one position to another if the move is valid.
     * If the piece is a pawn and it reaches the end of the board, it is promoted to a queen.
     * After a successful move, the turn is switched to the other player.
     *
     * @param x1 the x-coordinate of the piece's current position
     * @param y1 the y-coordinate of the piece's current position
     * @param z1 the z-coordinate of the piece's current position
     * @param x2 the x-coordinate of the piece's destination position
     * @param y2 the y-coordinate of the piece's destination position
     * @param z2 the z-coordinate of the piece's destination position
     */
    private void movePiece(int x1, int y1, int z1, int x2, int y2, int z2) {
        if(isValidMove(x1, y1, z1, x2, y2, z2))
        {
            if (this.playBoard[x1][y1][z1].getPiece() == PieceType.PAWN && (y2 == 0 || y2 == 7)) { //if the piece is a pawn and it reaches the end of the board
                this.playBoard[x2][y2][z2] = new ChessPiece(x2, y2, z2, PieceType.QUEEN, this.playBoard[x1][y1][z1].getColor());  //pawn is promoted to a queen            
            } else {
                this.playBoard[x2][y2][z2] = this.playBoard[x1][y1][z1];
                this.playBoard[x2][y2][z2].setPosition(x2, y2, z2);
            }    
            this.playBoard[x1][y1][z1] = null;
            this.colorMove = !this.colorMove; //switches color that is on turn
        }        
    }
}
