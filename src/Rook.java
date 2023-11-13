import java.util.Map;
import java.util.TreeMap;
public class Rook extends Piece {
    public Rook(Position position, Board board, boolean isBlack) {
        super(PieceType.ROOK, position, board, isBlack);
    }
    @Override
    public boolean canMoveTo(int endX, int endY) {
        Position position = this.getPosition();
        int startX = position.getX();
        int startY = position.getY();

        if (endX == startX && endY == startY) {
            return false;
        }
        int key;
        TreeMap<Integer, Piece> treeMap;

        int small;
        int big;

        if (endX == startX) {
            key = startX;
            treeMap = board.rows.get(key);
            small = Math.min(startY, endY);
            big = Math.max(startY, endY);
        }

        else if (endY == startY) {
            key = startY;
            treeMap = board.columns.get(key);
            small = Math.min(startX, endX);
            big = Math.max(startX, endX);
        }
        else {
            return false;
        }

        if (treeMap == null) {
            throw new NullPointerException();
        }
        Integer ceilV = treeMap.ceilingKey(small + 1);
//        System.out.println("small: " + small);
//        System.out.println("ceilV: " + ceilV);
        return ceilV == null || ceilV >= big;
    }
}
