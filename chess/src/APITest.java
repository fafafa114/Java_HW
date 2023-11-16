import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class APITest {

    @org.junit.jupiter.api.Test
    void createBoard() {
        Board board = new Board();
        List<Position> megaPawnPositions = Arrays.asList(new Position(1, 1), new Position(2, 2));
        board.createBoard(megaPawnPositions);
        assertTrue(board.getMegaPawnPositions().containsAll(megaPawnPositions));
    }

    @org.junit.jupiter.api.Test
    public void addAndGetPiece() throws Exception {
        Board board = new Board();
        board.addPiece(PieceType.KING, 0, 0, false);
        board.addPiece(PieceType.KING, 10000, 10000, true);
        board.addPiece(PieceType.ROOK, 0, 3, true);
        Piece piece = board.getPiece(0, 0);
        assertNotNull(piece);
        assertEquals(PieceType.KING, piece.getType());
        assertFalse(piece.isBlack());
        piece = board.getPiece(10000, 10001);
        assertNull(piece);
    }

    @org.junit.jupiter.api.Test
    void movePiece() throws Exception {
        Board board = new Board();
        ConsoleCheckListener listener = new ConsoleCheckListener();
        board.addCheckListener(listener);
        board.addPiece(PieceType.KING, 0, 0, false);
        board.addPiece(PieceType.KING, 10000, 10001, true);
        board.addPiece(PieceType.ROOK, 4, 3, true);
        board.addPiece(PieceType.QUEEN, 3, 3, false);
        board.movePiece(3, 3, 114514, 114514);
        assertNull(board.getPiece(3, 3));
        assertNotNull(board.getPiece(114514, 114514));
        assertEquals(PieceType.QUEEN, board.getPiece(114514, 114514).getType());
        board.movePiece(4, 3, 0, 3);
        assertThrows(MoveCheckException.class, () -> board.movePiece(114514, 114514, 114514, 114513));
    }

    @org.junit.jupiter.api.Test
    void promotePawn() throws Exception {
        Board board = new Board();
        List<Position> megaPawnPositions = Arrays.asList(new Position(1, 1), new Position(2, 2));
        board.createBoard(megaPawnPositions);
        board.addPiece(PieceType.KING, -1, -1, false);
        board.addPiece(PieceType.KING, -5, -5, true);
        board.addPiece(PieceType.PAWN, 2, 1, false);
        board.movePiece(2, 1, 2, 2);
        assertEquals(PieceType.MEGAPAWN, board.getPiece(2, 2).getType());

        board.addPiece(PieceType.PAWN, 1, 1, false);//initial position is in megaPawnPositions
        assertEquals(PieceType.MEGAPAWN, board.getPiece(1, 1).getType());
    }

    @org.junit.jupiter.api.Test
    void eatTest() throws Exception {
        Board board = new Board();
        ConsoleCheckListener listener = new ConsoleCheckListener();
        board.addCheckListener(listener);
        board.addPiece(PieceType.KING, 0, 0, false);
        board.addPiece(PieceType.KING, 9999, 10001, true);
        board.addPiece(PieceType.ROOK, 4, 3, true);
        board.addPiece(PieceType.QUEEN, 3, 3, false);
        board.movePiece(3, 3, 4, 3);
        assertEquals(PieceType.QUEEN, board.getPiece(4, 3).getType());
        assertNull(board.getPiece(3, 3));
    }
}