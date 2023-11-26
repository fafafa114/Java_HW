import java.util.List;
public interface API {
    void createBoard(List<Position> megaPawnPositions);
    void addPiece(PieceType type, int x, int y, boolean isBlack) throws PieceAlreadyExistsException;
    Piece getPiece(int x, int y);
    void movePiece(int startX, int startY, int endX, int endY) throws NoPieceException, IllegalMoveException, MoveCheckException, WrongTurnOrderException, WrongNumOfKingsException;
    void addCheckListener(CheckListener listener);
}
