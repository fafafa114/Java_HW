public class Queen extends Piece {
    public Queen(Position position, Board board, boolean isBlack) {
        super(PieceType.QUEEN, position, board, isBlack);
    }

    public boolean canMoveTo(int endX, int endY) {
        Position position = this.getPosition();
        int startX = position.getX();
        int startY = position.getY();
        boolean canMove = board.convertType(PieceType.ROOK, startX, startY).canMoveTo(endX, endY);
        canMove = canMove || board.convertType(PieceType.BISHOP, startX, startY).canMoveTo(endX, endY);
        board.convertType(PieceType.QUEEN, startX, startY);
        return canMove;
    }
}
