package board;

import input.InputHandler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ChessBoard {
    private BufferedImage board;
    private ChessPiece[][] playBoard;
    private InputHandler inputH;
    private MoveValidator validator;

    private int clicked;
    private int clickedX;
    private int clickedY;

    private int whiteKingX;
    private int whiteKingY;
    private int blackKingX;
    private int blackKingY;
    private boolean isCheck;

    private boolean isCastlingShort;
    private boolean isCastlingLong;

    private boolean colorMove;

    /**
     * Constructor for the ChessBoard class.
     * Initializes the chess board, input handler, move validator, and sets the initial positions of the kings.
     * @author (Lukáš Rakovan) 
     * @version (1.0.0)
     * @param inputH The input handler for managing user inputs.
     */
    public ChessBoard(InputHandler inputH) { 
        this.playBoard = new ChessPiece[8][8];
        this.inputH = inputH;
        this.validator = new MoveValidator(this); 

        this.clicked = 0;
        this.clickedX = 0;
        this.clickedY = 0;

        this.whiteKingX = 4;                        //initial positions of the kings
        this.whiteKingY = 0;
        this.blackKingX = 4;
        this.blackKingY = 7;
        this.isCheck = false;
        this.isCastlingShort = false;
        this.isCastlingLong = false;
        this.colorMove = true;                      //white starts

        this.initializeBoard();

    }

    /**
     * Gets the X coordinate of the white king.
     * @return The X coordinate of the white king.
     */
    public int getWhiteKingX() {
        return this.whiteKingX;
    }

    /**
     * Gets the Y coordinate of the white king.
     * @return The Y coordinate of the white king.
     */
    public int getWhiteKingY() {
        return this.whiteKingY;
    }

    /**
     * Gets the X coordinate of the black king.
     * @return The X coordinate of the black king.
     */
    public int getBlackKingX() {
        return this.blackKingX;
    }

    /**
     * Gets the Y coordinate of the black king.
     * @return The Y coordinate of the black king.
     */
    public int getBlackKingY() {
        return this.blackKingY;
    }

    /**
     * Checks if the current player is in check.
     * @return True if the current player is in check, false otherwise.
     */
    public boolean isCheck() {
        return this.isCheck;
    }

    /**
     * Gets the color of the player who is to move.
     * @return True if it is white's turn, false if it is black's turn.
     */
    public boolean getColorMove() {
        return this.colorMove;
    }

    /**
     * Gets the current state of the play board.
     * @return A 2D array representing the current state of the play board.
     */
    public ChessPiece[][] getPlayBoard() {
        return this.playBoard;
    }

    /**
     * Checks if castling short is possible.
     * @return True if castling short is possible, false otherwise.
     */
    public boolean isCastlingShort() {
        return this.isCastlingShort;
    }

    /**
     * Checks if castling long is possible.
     * @return True if castling long is possible, false otherwise.
     */
    public boolean isCastlingLong() {
        return this.isCastlingLong;
    }

    /**
     * Sets the state of castling short.
     *@param castlingShort True if castling short is possible, false otherwise.
     */
    public void setCastlingShort(boolean castlingShort) {
        this.isCastlingShort = castlingShort;
    }

    /**
     * Sets the state of castling long.
     * @param castlingLong True if castling long is possible, false otherwise.
     */
    public void setCastlingLong(boolean castlingLong) {
        this.isCastlingLong = castlingLong;
    }

    /**
     * Sets the color of the player who is to move.
     * @param colorMove True if it is white's turn, false if it is black's turn.
     */
    public void setColorMove(boolean colorMove) {
        this.colorMove = colorMove;
    }

    /**
     * Sets the check status of the current player.
     * @param check True if the current player is in check, false otherwise.
     */
    public void setCheck(boolean check) {
        this.isCheck = check;
    }

    /**
     * Sets the state of the play board.
     * @param playBoard A 2D array representing the new state of the play board.
     */
    public void setPlayBoard(ChessPiece[][] playBoard) {
        this.playBoard = playBoard;
    }

    public void setWhiteKingCoords(int x, int y) {
        this.whiteKingX = x;
        this.whiteKingY = y;
    }

    public void setBlackKingCoords(int x, int y) {
        this.blackKingX = x;
        this.blackKingY = y;
    }

    /**
     * Initializes the chess board by loading the board image and placing the chess pieces
     * in their starting positions.
     * The board image is loaded from the specified file path.
     * The pieces are placed in their standard initial positions for a chess game.
     */
    private void initializeBoard() {        
        try {
            this.board = ImageIO.read(new File("assets/boards/board_green.png"));
        } catch (Exception e) {
            System.out.println("Failed to load image for the board");                
        }    

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    switch (j) {
                        case 0:
                            this.playBoard[0][0] = new ChessPiece(0, 0, PieceType.ROOK, false);
                            break;
                        case 1:
                            this.playBoard[1][0] = new ChessPiece(1, 0, PieceType.KNIGHT, false);
                            break;
                        case 2:
                            this.playBoard[2][0] = new ChessPiece(2, 0, PieceType.BISHOP, false);
                            break;
                        case 3:
                            this.playBoard[3][0] = new ChessPiece(3, 0, PieceType.QUEEN, false);
                            break;
                        case 4:
                            this.playBoard[4][0] = new ChessPiece(4, 0, PieceType.KING, false);  
                            this.blackKingX = 4;
                            this.blackKingY = 0;                      
                            break;                        
                        case 5:
                            this.playBoard[5][0] = new ChessPiece(5, 0, PieceType.BISHOP, false);
                            break;
                        case 6:
                            this.playBoard[6][0] = new ChessPiece(6, 0, PieceType.KNIGHT, false);
                            break;
                        case 7:
                            this.playBoard[7][0] = new ChessPiece(7, 0, PieceType.ROOK, false);
                            break;                                            
                    }
                }

                if (i == 1) {
                    this.playBoard[j][1] = new ChessPiece(j, 1, PieceType.PAWN, false);                    
                } 

                if (i == 7) {
                    switch (j) {
                        case 0:
                            this.playBoard[0][7] = new ChessPiece(0, 7, PieceType.ROOK, true);
                            break;
                        case 1:
                            this.playBoard[1][7] = new ChessPiece(1, 7, PieceType.KNIGHT, true);
                            break;
                        case 2:
                            this.playBoard[2][7] = new ChessPiece(2, 7, PieceType.BISHOP, true);
                            break;
                        case 3:
                            this.playBoard[3][7] = new ChessPiece(3, 7, PieceType.QUEEN, true);                        
                            break;
                        case 4:
                            this.playBoard[4][7] = new ChessPiece(4, 7, PieceType.KING, true);
                            this.whiteKingX = 4;
                            this.whiteKingY = 7;
                            break;                        
                        case 5:
                            this.playBoard[5][7] = new ChessPiece(5, 7, PieceType.BISHOP, true);
                            break;
                        case 6:
                            this.playBoard[6][7] = new ChessPiece(6, 7, PieceType.KNIGHT, true);
                            break;
                        case 7:
                            this.playBoard[7][7] = new ChessPiece(7, 7, PieceType.ROOK, true);
                            break;                                            
                    }
                }

                if (i == 6) {
                    this.playBoard[j][6] = new ChessPiece(j, 6, PieceType.PAWN, true);                    
                } 
            }
        }
    }

    /**
     * Updates the state of the chess board based on user input.
     * Handles mouse clicks to select and move pieces (using InputHandler)
     */
    public void update() {
        if (this.inputH.getMouseClicked()) {
            if (this.inputH.getMouseX() > 0 && this.inputH.getMouseX() < 576 && this.inputH.getMouseY() < 576 && this.inputH.getMouseY() > 0) {
                int x = this.inputH.getMouseX() / 72;
                int y = this.inputH.getMouseY() / 72;                      
                this.clicked++;

                if (this.clicked == 1) {
                    if (this.playBoard[x][y] != null) {
                        this.clickedX = x;
                        this.clickedY = y;
                    } else {
                        this.clicked = 0;
                    }
                }
                if (this.clicked == 2) {
                    int x1 = this.clickedX;
                    int y1 = this.clickedY;

                    int x2 = x;
                    int y2 = y;

                    this.movePiece(x1, y1, x2, y2);
                    this.clicked = 0;
                }

            }    
            this.inputH.resetMouseClicked();     
        }

    }

    /**
     * Method, which draws every existing piece on the board, takes 1 parameter
     * @param g Graphics object, which is used to draw the pieces on the board, it is passed by the paintComponent method in class ChessPanel
     */   
    public void draw(Graphics g) { 
        if (this.board != null) { //if the board image is loaded
            g.drawImage(this.board, 0, 0, null); 
        }

        if (this.clicked == 1) { //if a piece is selected       
            g.drawRect(this.clickedX * 72, this.clickedY * 72, 72, 72); //draws a rectangle around the selected piece
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {                
                if (this.playBoard[i][j] != null) { //if there is a piece on the tile
                    g.drawImage(this.playBoard[i][j].getImage(), this.playBoard[i][j].getX(), //draws the piece on the tile
                        this.playBoard[i][j].getY(), this.playBoard[i][j].getSize(),
                        this.playBoard[i][j].getSize(), null); 
                }        
            }
        }
    }

    /**
     * Moves a piece from one position to another on the board.
     * Handles special moves such as castling and pawn promotion.
     * @param x1 The starting X coordinate of the piece.
     * @param y1 The starting Y coordinate of the piece.
     * @param x2 The destination X coordinate of the piece.
     * @param y2 The destination Y coordinate of the piece.
     */
    public void movePiece(int x1, int y1, int x2, int y2) {
        if (this.validator.isValidMove(x1, y1, x2, y2)) {                                   //if the move is valid             
            if (this.playBoard[x1][y1].getPiece() == PieceType.PAWN && (y2 == 0 || y2 == 7)) {                          //if the piece is a pawn and it reaches the end of the board
                this.playBoard[x2][y2] = new ChessPiece(x2, y2, PieceType.QUEEN, this.playBoard[x1][y1].getColor());    //pawn is promoted to a queen            
            } else if (this.isCastlingShort) {                                //if the king is castling short
                this.playBoard[x1 + 1][y1] = this.playBoard[x2][y2];          //the rook is moved 1 tile to the left
                this.playBoard[x1 + 1][y1].setPosition(x1 + 1, y1);
                this.playBoard[x1 + 2][y1] = this.playBoard[x1][y1];          //the king is moved 2 tiles to the right
                this.playBoard[x1 + 2][y1].setPosition(x1 + 2, y1);

                if (this.playBoard[x1 + 2][y1].getColor()) {                  //if the white king is castling short
                    this.whiteKingX = x1 + 2;                                 //updates the position of the white king in this class
                    this.whiteKingY = y1;
                } else {
                    this.blackKingX = x1 + 2;
                    this.blackKingY = y1;
                }

                this.playBoard[x2][y2] = null;            
                this.isCastlingShort = false;

            } else if (this.isCastlingLong) {                                //if the king is castling long
                this.playBoard[x1 - 1][y1] = this.playBoard[x2][y2];
                this.playBoard[x1 - 1][y1].setPosition(x1 - 1, y1);
                this.playBoard[x1 - 2][y1] = this.playBoard[x1][y1];
                this.playBoard[x1 - 2][y1].setPosition(x1 - 2, y1);

                if (this.playBoard[x1 - 2][y1].getColor()) {                  //if the white king is castling long
                    this.whiteKingX = x1 - 2;                               //updates the position of the white king in this class
                    this.whiteKingY = y1;
                } else {
                    this.blackKingX = x1 - 2;
                    this.blackKingY = y1;
                }

                this.playBoard[x2][y2] = null;
                this.isCastlingLong = false;
            } else {                                                        //normal move
                this.playBoard[x2][y2] = this.playBoard[x1][y1];
                this.playBoard[x2][y2].setPosition(x2, y2);

                if (this.playBoard[x2][y2].getPiece() == PieceType.KING) {  //if the piece is a king
                    if (this.playBoard[x2][y2].getColor()) {                //if the white king is moved
                        this.whiteKingX = x2;                               //updates the position of the white king in this class
                        this.whiteKingY = y2;
                    } else {
                        this.blackKingX = x2;
                        this.blackKingY = y2;
                    }
                }       
            }  
            this.playBoard[x1][y1] = null;

            if (this.validator.isInCheck(!this.colorMove)) {
                this.isCheck = true;
                System.out.println((!this.playBoard[x2][y2].getColor() ? "White" : "Black") + " king is in check!");

                if (this.validator.isCheckmate(!this.colorMove)) {
                    System.out.println((this.colorMove ? "White" : "Black") + " wins!");
                    System.exit(0); 
                }
            } else {
                this.isCheck = false;
            }
            this.colorMove = !this.colorMove;                               //switches color that is on turn 
        }      

    }
}

