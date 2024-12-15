package board;

public enum PieceType {
    PAWN(1, 0, "pawn"), ROOK(8, 0, "rook"), KNIGHT(3, 0, "knight"), BISHOP(0, 8, "bishop"), QUEEN(8, 8, "queen"), KING(1, 1, "king");
    public final int movesDiagonal;
    public final int movesStraight;
    public final String name;

    private PieceType(int movesStraight, int movesDiagonal, String name) {
        this.movesDiagonal = movesDiagonal;
        this.movesStraight = movesStraight;
        this.name = name;
    }
}
