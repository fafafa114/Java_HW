public class ConsoleCheckListener implements CheckListener {
    @Override
    public void onCheck(boolean isBlack) {
        if (isBlack) {
            System.out.println("Black king is checked.");
        } else {
            System.out.println("White king is checked.");
        }
    }
}
