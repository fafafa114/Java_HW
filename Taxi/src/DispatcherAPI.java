public interface DispatcherAPI {
    void addTaxi(TaxiAPI taxiAPI);

    void placeOrder(Taxi taxi, String order);

    boolean isRunning();
}
