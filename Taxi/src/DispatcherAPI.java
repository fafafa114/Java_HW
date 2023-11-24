public interface DispatcherAPI {
    void addTaxi(TaxiAPI taxiAPI);

    void placeOrder(TaxiAPI taxiAPI);

    boolean isRunning();
}
