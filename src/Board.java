import java.util.*;
public class Board implements API {

    protected Map<Position, Piece> pieces;


    protected Map<Integer, TreeMap<Integer, Piece>> rows;
    protected Map<Integer, TreeMap<Integer, Piece>> columns;
    protected Map<Integer, TreeMap<Integer, Piece>> diagonalLeftToRight;
    protected Map<Integer, TreeMap<Integer, Piece>> diagonalRightToLeft;


    private Set<Position> megaPawnPositions;


    private List<CheckListener> checkListeners;


    private boolean currentPlayerIsBlack;

    private Piece blackKing;
    private Piece whiteKing;
    public Board() {
        pieces = new HashMap<>();
        rows = new HashMap<>();
        columns = new HashMap<>();
        diagonalLeftToRight = new HashMap<>();
        diagonalRightToLeft = new HashMap<>();
        megaPawnPositions = new HashSet<>();
        checkListeners = new ArrayList<>();
        currentPlayerIsBlack = false;
    }

    @Override
    public void createBoard(List<Position> megaPawnPositions) {
        this.megaPawnPositions.clear();
        this.megaPawnPositions.addAll(megaPawnPositions);
    }

    @Override
    public void addPiece(PieceType type, int x, int y, boolean isBlack) {
        Position position = new Position(x, y);
        Piece piece = PieceFactory.createPiece(type, position, this, isBlack);


        if (type == PieceType.KING) {
            if (isBlack) {
                blackKing = piece;
            } else {
                whiteKing = piece;
            }
        }
        pieces.put(position, piece);


        rows.computeIfAbsent(x, k -> new TreeMap<>()).put(y, piece);


        columns.computeIfAbsent(y, k -> new TreeMap<>()).put(x, piece);


        int LtoRKey = x - y;
        diagonalLeftToRight.computeIfAbsent(LtoRKey, k -> new TreeMap<>()).put(x, piece);


        int RtoLKey = x + y;
        diagonalRightToLeft.computeIfAbsent(RtoLKey, k -> new TreeMap<>()).put(x, piece);

    }
    private void putPiece() {

    }

    @Override
    public Piece getPiece(int x, int y) {

        return pieces.get(new Position(x, y));
    }

    @Override
    public void movePiece(int startX, int startY, int endX, int endY) throws NoPieceException, IllegalMoveException, MoveCheckException, WrongTurnOrderException {

        Piece piece = getPiece(startX, startY);
        Piece targetPiece = getPiece(endX, endY);

        if (piece == null) {
            throw new NoPieceException();
        }
//        System.out.println("movePiece: " + piece.getType());

        if (piece.isBlack() != currentPlayerIsBlack) {
            throw new WrongTurnOrderException();
        }
        if (targetPiece != null && targetPiece.isBlack() == piece.isBlack()) {
            throw new IllegalMoveException();
        }



        if (!piece.canMoveTo(endX, endY)) {
            throw new IllegalMoveException();
        }

        if (piece.getType() == PieceType.KING) {
            if (piece.isBlack()) {
                blackKing.setPosition(new Position(endX, endY));
            } else {
                whiteKing.setPosition(new Position(endX, endY));
            }
        }
        if (piece.getType() == PieceType.PAWN) {
            if (megaPawnPositions.contains(new Position(endX, endY))) {
                convertType(PieceType.MEGAPAWN, startX, startY);
            }
        }
        doMove(startX, startY, endX, endY);
        if (isChecked(piece.isBlack())) {
            throw new MoveCheckException();
        }
        if (isChecked(!piece.isBlack())) {
            for (CheckListener listener : checkListeners) {
                listener.onCheck(piece.isBlack());
            }
        }
        currentPlayerIsBlack = !currentPlayerIsBlack;
    }

    @Override
    public void addCheckListener(CheckListener listener) {
        checkListeners.add(listener);
    }

    private void deletePiece(int x, int y) {
        Position position = new Position(x, y);
        pieces.remove(position);


        rows.get(x).remove(y);
        if (rows.get(x).isEmpty()) {
            rows.remove(x);
        }


        columns.get(y).remove(x);
        if (columns.get(y).isEmpty()) {
            columns.remove(y);
        }


        int LtoRKey = x - y;
        diagonalLeftToRight.get(LtoRKey).remove(x);
        if (diagonalLeftToRight.get(LtoRKey).isEmpty()) {
            diagonalLeftToRight.remove(LtoRKey);
        }


        int RtoLKey = x + y;
        diagonalRightToLeft.get(RtoLKey).remove(x);
        if (diagonalRightToLeft.get(RtoLKey).isEmpty()) {
            diagonalRightToLeft.remove(RtoLKey);
        }
    }
    private void doMove(int startX, int startY, int endX, int endY) {
        Piece piece = getPiece(startX, startY);
        deletePiece(startX, startY);
        if (getPiece(endX, endY) != null) {
            deletePiece(endX, endY);
        }
        addPiece(piece.getType(), endX, endY, piece.isBlack());
    }

    private boolean isChecked(boolean isBlack) {
        Piece king = isBlack ? blackKing : whiteKing;


        Collection<Piece> tempPieces = new ArrayList<>(pieces.values());
        for (Piece piece : tempPieces) {
            if (piece.isBlack() != isBlack && piece.canMoveTo(king.getPosition().getX(), king.getPosition().getY())) {
                return true;
            }
        }
        return false;
    }
    protected Piece convertType(PieceType type, int x, int y) {
        Piece piece = getPiece(x, y);
        deletePiece(x, y);
        addPiece(type, x, y, piece.isBlack());
//        System.out.println("convertType: " + piece.getType() + " to " + type);
        return getPiece(x, y);
    }


    public Map<Position, Piece> getPieces() {
        return pieces;
    }

}
