import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws WrongTurnOrderException, NoPieceException, IllegalMoveException, MoveCheckException {
        List<Position> megaPawnPositions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            megaPawnPositions.add(new Position(i, i));
        }
        Board board = new Board();
        board.createBoard(megaPawnPositions);
        ConsoleCheckListener listener = new ConsoleCheckListener();
        board.addCheckListener(listener);
        board.addPiece(PieceType.KING, 0, 0, false);
        board.addPiece(PieceType.KING, 5, 5, true);
//        board.addPiece(PieceType.PAWN, 2, 1, false);
        board.addPiece(PieceType.QUEEN, 1, 1, false);
//        board.addPiece(PieceType.ROOK, 1, 5000, true);

        //protected Map<Position, Piece> pieces;
        //output all pieces

        for (Map.Entry<Position, Piece> entry : board.pieces.entrySet()) {
            System.out.println(entry.getKey().getX() + " " + entry.getKey().getY() + " " + entry.getValue().getType() + " " + entry.getValue().isBlack());
        }
        System.out.println(board.getPiece(0, 0).getType());

        board.movePiece(1, 1, 5, 1);
        for (Map.Entry<Position, Piece> entry : board.pieces.entrySet()) {
            System.out.println(entry.getKey().getX() + " " + entry.getKey().getY() + " " + entry.getValue().getType() + " " + entry.getValue().isBlack());
        }
    }
}
