import java.util.List;
public interface API {
    void createBoard(List<Position> megaPawnPositions);
    void addPiece(PieceType type, int x, int y, boolean isBlack);
    Piece getPiece(int x, int y);
    void movePiece(int startX, int startY, int endX, int endY) throws NoPieceException, IllegalMoveException, MoveCheckException, WrongTurnOrderException;
    void addCheckListener(CheckListener listener);
}
