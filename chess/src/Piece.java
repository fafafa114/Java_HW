public abstract class Piece {
    protected PieceType type;
    protected boolean isBlack;
    protected Position position;
    protected Board board;

    public Piece(PieceType type, Position position, Board board, boolean isBlack) {
        this.type = type;
        this.isBlack = isBlack;
        this.position = position;
        this.board = board;
    }

    public PieceType getType() {
        return type;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract boolean canMoveTo(int endX, int endY);
}
