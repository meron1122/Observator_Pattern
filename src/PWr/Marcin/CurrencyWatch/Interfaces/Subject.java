package PWr.Marcin.CurrencyWatch.Interfaces;


import PWr.Marcin.CurrencyWatch.Interfaces.Observer;

/**
 * Created by Marcin Golonka on 20.10.15.
 */
public interface Subject {
    /**
     * Attach observer to object
     * @param ObseverInstance
     */
    public void attachObserver(Observer ObseverInstance);

    /**
     * Detach observer from observers list
     * @param ObseverInstance
     */
    public void detachObserver(Observer ObseverInstance);

    /**
     * Method to signal all observers when state changed
     */
    void notifyObservers();

}
