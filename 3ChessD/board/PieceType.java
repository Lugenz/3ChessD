package board;

public enum PieceType {
    PAWN(1, 0), ROOK(8, 0), KNIGHT(3, 1), BISHOP(8, 0), QUEEN(8, 0), KING(1, 0);
    private int movesDirOne;
    private int movesDirTwo;
    private PieceType(int movesDirOne, int movesDirTwo) {
        this.movesDirOne = movesDirOne;
        this.movesDirTwo = movesDirTwo;
    }
}
