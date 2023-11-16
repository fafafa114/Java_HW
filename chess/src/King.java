public class King extends Piece {
    public King(Position position, Board board, boolean isBlack) {
        super(PieceType.KING, position, board, isBlack);
    }

    @Override
    public boolean canMoveTo(int endX, int endY) {
//        System.out.println("King canMoveTo");
        Position position = this.getPosition();
        int startX = position.getX();
        int startY = position.getY();
        boolean canMove = board.convertType(PieceType.MEGAPAWN, startX, startY).canMoveTo(endX, endY);
        board.convertType(PieceType.KING, startX, startY);
        return canMove;
    }
}
