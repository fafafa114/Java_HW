public class Pawn extends Piece {
    public Pawn(Position position, Board board, boolean isBlack) {
        super(PieceType.PAWN, position, board, isBlack);
    }

    @Override
    public boolean canMoveTo(int endX, int endY) {
        Position position = this.getPosition();
        int startX = position.getX();
        int startY = position.getY();
        int diffX = endX - startX;
        int fwdY = startY + (isBlack ? -1 : 1);//the direction the pawn, here I assume white is at the bottom
        if (fwdY != endY || Math.abs(diffX) > 1) {
            return false;
        }
        if (diffX == 0) {
            return board.getPiece(endX, endY) == null;
        } else {
            return board.getPiece(endX, endY) != null;//eat, the case of same color is handled in Board
        }
    }
}
