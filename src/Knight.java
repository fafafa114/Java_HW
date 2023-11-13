public class Knight extends Piece {
    public Knight(Position position, Board board, boolean isBlack) {
        super(PieceType.KNIGHT, position, board, isBlack);
    }

    @Override
    public boolean canMoveTo(int endX, int endY) {
        Position position = this.getPosition();
        int startX = position.getX();
        int startY = position.getY();
        int diffX = Math.abs(endX - startX);
        int diffY = Math.abs(endY - startY);
        return (diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1);
    }
}
