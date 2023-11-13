public class IllegalMoveException extends Exception {
    public IllegalMoveException() {
        super("The piece can't move to the target position.");
    }
}
