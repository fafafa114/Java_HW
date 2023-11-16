public class WrongTurnOrderException extends Exception {
    public WrongTurnOrderException() {
        super("Wrong turn order - it is not the turn of this piece's color.");
    }
}