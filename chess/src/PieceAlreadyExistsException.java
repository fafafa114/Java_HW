public class PieceAlreadyExistsException extends Exception {
    public PieceAlreadyExistsException() {
        super("Piece already exists at this position.");
    }
}
