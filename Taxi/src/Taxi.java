import java.util.Random;

public class Taxi implements TaxiAPI, Runnable {
    private final String name;
    private final DispatcherAPI dispatcherAPI;
    private final Random random;
    private final int minSleep;
    private final int maxSleep;
    private String currentOrder;
    private boolean hasOrder = false;

    public Taxi(String name, DispatcherAPI dispatcherAPI, int minSleep, int maxSleep, Random random) {
        this.name = name;
        this.dispatcherAPI = dispatcherAPI;
        this.minSleep = minSleep;
        this.maxSleep = maxSleep;
        this.random = random;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (this) {
                    while (!hasOrder && dispatcherAPI.isRunning()) {
                        wait();
                    }
                    if (hasOrder) {
                        fulfillOrder(currentOrder);
                        hasOrder = false;
                    } else {
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void fulfillOrder(String order) {
        this.currentOrder = order;
        System.out.println(name + " is fulfilling order: " + order);
        try {
            Thread.sleep(minSleep + random.nextInt(maxSleep - minSleep));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        dispatcherAPI.orderCompleted(this);
    }

    @Override
    public String getName() {
        return name;
    }

    public synchronized void receiveOrder(String order) {
        this.currentOrder = order;
        this.hasOrder = true;
        notify();
    }
}
