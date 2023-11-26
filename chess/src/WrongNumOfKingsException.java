public class WrongNumOfKingsException extends Exception {
    public WrongNumOfKingsException() {
        super("Wrong number of kings - there must be exactly one king of each color.");
    }
}