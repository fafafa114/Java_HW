public class MoveCheckException extends Exception {
    public MoveCheckException() {
        super("Moving the piece is not allowed due to check/checkmate.");
    }
}