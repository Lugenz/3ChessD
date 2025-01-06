package board;

public class MoveValidator {
    private ChessBoard board;

    /**
     * Constructor for MoveValidator.
     * 
     * @param board The chessboard to be used for validation.
     */
    public MoveValidator(ChessBoard board) {
        this.board = board;
    }

    /**
     * Checks if the king of the given color is in check.
     * 
     * @param color The color of the king to check.
     * @return true if the king is in check, false otherwise.
     */
    public boolean isInCheck(boolean color) {
        int kingX;
        int kingY;
        if (color) {                                                                    //if the king is white
            kingX = this.board.getWhiteKingX();
            kingY = this.board.getWhiteKingY();
        } else {
            kingX = this.board.getBlackKingX();
            kingY = this.board.getBlackKingY();
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {               
                if (this.board.getPlayBoard()[i][j] != null
                    && this.board.getPlayBoard()[i][j].getColor() != color) {                       //if the tile has a piece and the color of the piece is different from the color of the king
                    if (this.isValidMove(i, j, kingX, kingY)) {                                      //if the piece can "capture" the king
                        System.out.print("Check! By ");
                        System.out.println(this.board.getPlayBoard()[i][j].getPiece().getName());
                        this.board.setCheck(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Simulates a move on the chessboard and checks if the move removes a check condition.
     * 
     * @param x1 The starting x-coordinate of the piece to be moved.
     * @param y1 The starting y-coordinate of the piece to be moved.
     * @param x2 The ending x-coordinate of the piece to be moved.
     * @param y2 The ending y-coordinate of the piece to be moved.
     * @return true if the move removes the check condition, false otherwise.
     */
    public boolean removesCheck(int x1, int y1, int x2, int y2) {
        ChessPiece[][] tempBoard = new ChessPiece[8][8];                    //create a temporary board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.board.getPlayBoard()[i][j] != null) {              //if the tile has a piece
                    tempBoard[i][j] = new ChessPiece(i, j, this.board.getPlayBoard()[i][j].getPiece(), this.board.getPlayBoard()[i][j].getColor());
                }
            }
        }
        boolean color = this.board.getColorMove();                                 //save the color of the player on turn
        this.board.setCheck(false);                                        //set the check to false for the simulation
        this.board.movePiece(x1, y1, x2, y2);                                    //simulate moving the piece

        if (this.isInCheck(color)) {                                 //if the move puts the king in check again
            this.board.setPlayBoard(tempBoard);                                  //reset the board to the previous state
            this.board.setCheck(true);  
            this.board.setColorMove(color);                                   //set the check back to original state

            return false;
        } else {
            this.board.setPlayBoard(tempBoard);
            this.board.setCheck(true);
            this.board.setColorMove(color); 

            return true;
        }

    }

    public boolean isCheckmate(boolean color) {

        this.board.setColorMove(color);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.board.getPlayBoard()[i][j] != null && this.board.getPlayBoard()[i][j].getColor() == color) {               //if the tile has a piece and the color of the piece is the same as the color of the king
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {

                            if (this.isValidMove(i, j, k, l)) { 
                                System.out.println("33333");
                                System.out.println(this.board.getPlayBoard()[i][j].getPiece().getName());
                                System.out.println(i + " " + j + " to " + k + " " + l);  
                                this.board.setColorMove(!color); 

                                return false;                                                                       
                            }
                        }
                    }
                }
            }
        }

        this.board.setColorMove(!color); 
        return true;
    }

    /**
     * Validates if a move from (x1, y1) to (x2, y2) is valid based on the piece's movement rules and the current state of the board.
     *
     * @param x1 The x-coordinate of the starting position.
     * @param y1 The y-coordinate of the starting position.
     * @param x2 The x-coordinate of the destination position.
     * @param y2 The y-coordinate of the destination position.
     * @return true if the move is valid, false otherwise.
     */
    public boolean isValidMove(int x1, int y1, int x2, int y2) { 
        String pieceName = this.board.getPlayBoard()[x1][y1].getPiece().getName();

        int movesClickedX = Math.abs(x1 - x2);                                              //absolute value of the difference between the coordinates of the selected and destination tile
        int movesClickedY = Math.abs(y1 - y2);

        //VERIFICATION OF PIECE MOVEMENT
        switch (pieceName) {
            case "pawn":
                //MOVING MORE THAN 1 TILE FORWARD                    
                if (movesClickedY == 2) {                                       //if the pawn is trying to move 2 tiles forward                                       
                    if (y1 != 1) {                                              //if the pawn isn't on the starting position
                        if (y1 != 6) {                                          //if the pawn isn't on the starting position
                            return false;
                        }   
                    } 
                }  
                if (movesClickedY > 2) {                                        //if the pawn is trying to move more than 2 tiles forward                                                       
                    return false;                
                } 
                if (movesClickedX != 0 && movesClickedY > 1) {                  //if the pawn is trying to move more than 1 tile forward and also sideways
                    return false;
                }

                //CAPTURING
                if (movesClickedX == 0 && movesClickedY == 1 && this.board.getPlayBoard()[x2][y2] != null) {             //if the pawn is trying to move forward whilst there is a piece
                    return false;
                }
                if (movesClickedX == 1 && movesClickedY == 1 && this.board.getPlayBoard()[x2][y2] == null) {             //if the destination tile is empty
                    return false;
                }

                //MOVING BACKWARDS
                if (this.board.getPlayBoard()[x1][y1].getColor()) {                  //if the pawn is white
                    if (y1 - y2 < 0) {                                          //if the white pawn is trying to move backwards
                        return false;
                    }
                } else if (y1 - y2 > 0) {                                       //if the black pawn is trying to move backwards
                    return false;                
                }

                //MOVING SIDEWAYS
                if (x1 - x2 != 0 && y1 - y2 == 0) {                             //if the pawn is trying to only move sideways
                    return false;
                } 

                if (movesClickedX > 1) {                                        //if the pawn is trying to move more than 1 tile sideways                             
                    return false;                
                }
                break;

            case "rook":
                if (movesClickedX > 0 && movesClickedY > 0) {
                    return false;
                }

                //STRAIGHT MOVE COLLISION DETECTION
                if (movesClickedX != 0) {                                       //if the rook is trying to move only horizontally
                    if (x1 > x2) {                                              //if the rook is trying to move left
                        for (int i = 1; i < (x1 - x2); i++) {
                            if (this.board.getPlayBoard()[x1 - i][y1] != null) {       //if there is a piece in the way
                                return false;
                            }
                        }
                    } else {
                        for (int i = 1; i < (x2 - x1); i++) { 
                            if (this.board.getPlayBoard()[x1 + i][y1] != null) {
                                return false;
                            }
                        }
                    }                
                } else if (movesClickedY != 0) {                                //if the rook is trying to move only vertically
                    if (y1 > y2) {                                              //if the rook is trying to move up
                        for (int i = 1; i < (y1 - y2); i++) {
                            if (this.board.getPlayBoard()[x1][y1 - i] != null) {       //if there is a piece in the way
                                return false;
                            }
                        }
                    } else {
                        for (int i = 1; i < (y2 - y1); i++) {
                            if (this.board.getPlayBoard()[x1][y1 + i] != null) {
                                return false;
                            }
                        }
                    }
                }      
                break;

            case "knight":
                if (movesClickedX != 2) {                                       //if the knight isn't trying to move 2 tiles horizontally
                    if (movesClickedY != 2) {                                   
                        return false;
                    } else {
                        if (movesClickedX != 1) {                               //if the knight isn't trying to move 1 tile horizontally
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
                if (movesClickedX != movesClickedY) {                           //if the bishop isn't trying to move diagonally (the X and Y must to be the same)
                    return false;
                }

                //DIAGONAL MOVE COLLISION DETECTION
                if (x1 < x2) {                                                  //if the bishop is trying to move right
                    if (y1 < y2) {                                              //if the bishop is trying to move down
                        for (int i = 1; i < movesClickedY; i++) {
                            if (this.board.getPlayBoard()[x1 + i][y1 + i] != null) {     //if there is a piece in the way
                                return false;
                            }
                        }
                    } else {
                        for (int i = 1; i < movesClickedY; i++) {
                            if (this.board.getPlayBoard()[x1 + i][y1 - i] != null) {
                                return false;
                            }
                        }
                    }                
                } else if (x1 > x2) {
                    if (y1 > y2) {
                        for (int i = 1; i < movesClickedY; i++) {
                            if (this.board.getPlayBoard()[x1 - i][y1 - i] != null) {
                                return false;
                            }
                        }
                    } else {
                        for (int i = 1; i < movesClickedY; i++) {
                            if (this.board.getPlayBoard()[x1 - i][y1 + i] != null) {
                                return false;
                            }
                        }
                    }                
                }
                break;

            case "queen":
                if (movesClickedX > 0 && movesClickedY > 0
                    && movesClickedX != movesClickedY) {                            //if the queen isn't trying to move diagonally or straight
                    return false;
                }

                //STRAIGHT MOVE COLLISION DETECTION
                if (movesClickedX > movesClickedY) {                            //if the queen is trying to move more tiles horizontally than vertically
                    if (x1 > x2) {                                              //if the queen is trying to move left
                        for (int i = 1; i < (x1 - x2); i++) {
                            if (this.board.getPlayBoard()[x1 - i][y1] != null) {       //if there is a piece in the way
                                return false;
                            }
                        }
                    } else {
                        for (int i = 1; i < (x2 - x1); i++) {
                            if (this.board.getPlayBoard()[x1 + i][y1] != null) {
                                return false;
                            }
                        }
                    }                
                } else if (movesClickedX < movesClickedY) {
                    if (y1 > y2) {
                        for (int i = 1; i < (y1 - y2); i++) {
                            if (this.board.getPlayBoard()[x1][y1 - i] != null) {
                                return false;
                            }
                        }
                    } else {
                        for (int i = 1; i < (y2 - y1); i++) {
                            if (this.board.getPlayBoard()[x1][y1 + i] != null) {
                                return false;
                            }
                        }
                    }
                }    

            //DIAGONAL MOVE COLLISION DETECTION
                if (x1 < x2) {                                                  //if the queen is trying to move right
                    if (y1 < y2) {                                              //if the queen is trying to move down
                        for (int i = 1; i < movesClickedY; i++) {
                            if (this.board.getPlayBoard()[x1 + i][y1 + i] != null) {     //if there is a piece in the way
                                return false;
                            }
                        }
                    } else {
                        for (int i = 1; i < movesClickedY; i++) {
                            if (this.board.getPlayBoard()[x1 + i][y1 - i] != null) {
                                return false;
                            }
                        }
                    }                
                } else if (x1 > x2) {
                    if (y1 > y2) {
                        for (int i = 1; i < movesClickedY; i++) {
                            if (this.board.getPlayBoard()[x1 - i][y1 - i] != null) {
                                return false;
                            }
                        }
                    } else {
                        for (int i = 1; i < movesClickedY; i++) {
                            if (this.board.getPlayBoard()[x1 - i][y1 + i] != null) {
                                return false;
                            }
                        }
                    }                
                }   
                break;

            case "king":

                if (movesClickedX > 1 || movesClickedY > 1) {                                           //if the king is trying to move more than 1 tile (castle)
                    if (this.board.isCheck()) {                                                              //if the king is in check
                        return false;
                    }

                //STRAIGHT MOVE COLLISION DETECTION (for the case the king is castling)
                    if (movesClickedX != 0) {                                                           //if the king is trying to move only horizontally
                        if (x1 > x2) {                                                                  //if the king is trying to move left
                            for (int i = 1; i < (x1 - x2); i++) {
                                if (this.board.getPlayBoard()[x1 - i][y1] != null) {
                                    return false;
                                }
                            }
                        } else {
                            for (int i = 1; i < (x2 - x1); i++) {
                                if (this.board.getPlayBoard()[x1 + i][y1] != null) {
                                    return false;
                                }
                            }
                        }                
                    }

                //CASTLING
                    if ((y1 != 0 && y1 != 7) || x1 != 4) {                                                      //if the king isn't on the starting position
                        return false;   
                    } else if (movesClickedX != 3) {                                                            //if the king isn't trying to castle short
                        if (movesClickedX != 4) {                                                               //if the king isn't trying to castle long
                            return false;
                        } else if (this.board.getPlayBoard()[x2][y2].getPiece() != PieceType.ROOK) {             //if the destination tile doesn't have a rook
                            return false; 
                        } else if (this.board.getPlayBoard()[x2][y2].getColor() != this.board.getColorMove()) {   //if the destination tile doesn't have a rook of the same color as the king
                            return false;
                        } else {              
                            this.board.setCastlingLong(true);      
                            System.out.println("I am castling long!");  
                        }
                    } else if (this.board.getPlayBoard()[x2][y2].getPiece() != PieceType.ROOK) {                 //if the destination tile doesn't have a rook
                        return false; 
                    } else if (this.board.getPlayBoard()[x2][y2].getColor() != this.board.getColorMove()) {       //if the destination tile doesn't have a rook of the same color as the king
                        return false;
                    } else {             
                        this.board.setCastlingShort(true);    
                        System.out.println("I am castling short!");
                    }
                } 
                break;
        }

        // VERIFICATION OF MOVE DESTINATION                       
        if (this.board.getPlayBoard()[x1][y1] != null
            && this.board.getPlayBoard()[x1][y1].getColor() == this.board.getColorMove()) { // if the selected tile has a piece and the color of the piece is the same as the color of the player on turn

            if (this.board.getPlayBoard()[x2][y2] != null) { // if the destination tile has a piece
                if (this.board.getPlayBoard()[x1][y1].getColor() == this.board.getPlayBoard()[x2][y2].getColor()) { // if the destination piece is the same color as the selected piece
                    if (!this.board.isCastlingLong() && !this.board.isCastlingShort()) { // if the move is castling
                        return false;
                    } 
                }
            }

        } else {
            return false; // if the selected tile does not have a piece or the color does not match the player's turn
        }

        //VERIFICATION OF UNCHECKING THE KING
        if (this.board.isCheck()) {       
            System.out.println("pipik");                                                //if is in check            
            if (!this.removesCheck(x1, y1, x2, y2)) {                                              //verify if the move removes the check               
                return false;
            }
        }      
        return true;
    }
}
