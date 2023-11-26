public class NoPieceException extends Exception {
    public NoPieceException() {
        super("No piece on the given cell.");
    }
}
