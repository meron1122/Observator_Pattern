package PWr.Marcin.CurrencyWatch.Interfaces;

/**
 * Created by Marcin Golonka on 20.10.15.
 */
public interface Observer {
    /**
     * Triggered by subject when changed...
     * @param ObservatingSubject Observating Object instance
     */
    public void stateChanged(Object ObservatingSubject);

}
