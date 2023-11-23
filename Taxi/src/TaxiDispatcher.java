import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;

public class TaxiDispatcher implements DispatcherAPI, Runnable {
    private final BlockingQueue<Taxi> availableTaxis = new LinkedBlockingQueue<>();
    private final Random random;
    private volatile boolean keepRunning = true;
    private int orderCount = 0;
    private static final int MAX_ORDERS = 50;

    public TaxiDispatcher(Random random) {
        this.random = random;
    }
    @Override
    public void run() {
        while (keepRunning && orderCount < MAX_ORDERS) {
            try {
                Taxi taxi = availableTaxis.take();
                String order = "Order " + (++orderCount) + " for " + taxi.getName();
                taxi.receiveOrder(order);

                if (orderCount >= MAX_ORDERS) {
                    keepRunning = false;
                } else {
                    Thread.sleep(random.nextInt(100));
                }
            } catch (InterruptedException e) {
                keepRunning = false;
                Thread.currentThread().interrupt();
            }
        }

        for (Taxi taxi : availableTaxis) {
            synchronized (taxi) {
                taxi.notifyAll();
            }
        }
    }

    @Override
    public void addTaxi(TaxiAPI taxiAPI) {
        availableTaxis.add((Taxi) taxiAPI);
    }

    @Override
    public void orderCompleted(TaxiAPI taxiAPI) {
        if (keepRunning) {
            availableTaxis.add((Taxi) taxiAPI);
        }
    }

    @Override
    public boolean isRunning() {
        return keepRunning;
    }
}
