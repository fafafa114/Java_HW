public interface DispatcherAPI {
    void addTaxi(TaxiAPI taxiAPI);

    void orderCompleted(TaxiAPI taxiAPI);

    boolean isRunning();
}
