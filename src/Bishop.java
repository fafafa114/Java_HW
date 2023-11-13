import java.util.TreeMap;

public class Bishop extends Piece {
    public Bishop(Position position, Board board, boolean isBlack) {
        super(PieceType.BISHOP, position, board, isBlack);
    }
    @Override
    public boolean canMoveTo(int endX, int endY) {

        Position position = this.getPosition();
        int startX = position.getX();
        int startY = position.getY();

        int smallX = Math.min(startX, endX);
        int bigX = Math.max(startX, endX);
        if (endX == startX && endY == startY) {
            return false;
        }
        int key;
        TreeMap<Integer, Piece> treeMap;
        if (endX - endY == startX - startY) {
            key = endX - endY;
            treeMap = board.diagonalLeftToRight.get(key);
        }
        else if (endX + endY == startX + startY) {
            key = endX + endY;
            treeMap = board.diagonalRightToLeft.get(key);
        }
        else {
            return false;
        }
        if (treeMap == null) {
            throw new NullPointerException();
        }
        Integer ceilX = treeMap.ceilingKey(smallX+1);
        return ceilX == null || ceilX >= bigX;
    }
}
