public class MegaPawn extends Piece {
    public MegaPawn(Position position, Board board, boolean isBlack) {
        super(PieceType.MEGAPAWN, position, board, isBlack);
    }
    @Override
    public boolean canMoveTo(int endX, int endY) {
        Position position = this.getPosition();
        int startX = position.getX();
        int startY = position.getY();
        int diffX = Math.abs(endX - startX);
        int diffY = Math.abs(endY - startY);
//        System.out.println("diffX: " + diffX + " diffY: " + diffY);
        return Math.max(diffX, diffY) == 1;
    }
}
