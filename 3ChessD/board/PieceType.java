package board;

public enum PieceType {
    PAWN("pawn"), ROOK("rook"), KNIGHT("knight"), BISHOP("bishop"), QUEEN("queen"), KING("king");

    public final String name;

    private PieceType(String name) {
        this.name = name;
    }
}
