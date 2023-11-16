public class PieceFactory {
    public static Piece createPiece(PieceType type, Position position, Board board, boolean isBlack) {
        switch (type) {
            case KING:
                return new King(position, board, isBlack);
            case QUEEN:
                return new Queen(position, board, isBlack);
            case ROOK:
                return new Rook(position, board, isBlack);
            case BISHOP:
                return new Bishop(position, board, isBlack);
            case KNIGHT:
                return new Knight(position, board, isBlack);
            case PAWN:
                return new Pawn(position, board, isBlack);
            case MEGAPAWN:
                return new MegaPawn(position, board, isBlack);
            default:
                throw new IllegalArgumentException("Invalid piece type");
        }
    }
}