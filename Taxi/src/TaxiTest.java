import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaxiTest {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        TaxiDispatcher dispatcher = new TaxiDispatcher(random);
        Thread dispatcherThread = new Thread(dispatcher);
        dispatcherThread.start();
        List<Thread> taxiThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Taxi taxi = new Taxi("Taxi " + i, dispatcher, 100, 500, random);
            dispatcher.addTaxi(taxi);
            Thread taxiThread = new Thread(taxi);
            taxiThreads.add(taxiThread);
            taxiThread.start();
        }

        dispatcherThread.join();

        for (Thread taxiThread : taxiThreads) {
            taxiThread.join();
        }

        System.out.println("Done!!1");
    }
}
