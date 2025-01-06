package board;

public enum PieceType {
    PAWN("pawn"), ROOK("rook"), KNIGHT("knight"), BISHOP("bishop"), QUEEN("queen"), KING("king");

    private final String name;

    PieceType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
