package PWr.Marcin.CurrencyWatch.Entities;

import PWr.Marcin.CurrencyWatch.Exceptions.*;
import PWr.Marcin.CurrencyWatch.Interfaces.Observer;
import PWr.Marcin.CurrencyWatch.Interfaces.Subject;

import java.util.ArrayList;


/**
 * Created by Marcin Golonka on 14.12.15.
 */
public class Currency implements Subject {

    /**
     * Currency code
     */
    private String code;

    /**
     * Currency name
     */
    private String name;
    /**
     * Representate conversion rate related with PLN
     */
    private double conversionRate;

    private double value;

    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public Currency(String code) throws EmptyCurrencyCodeException {
        if (code == null || code.isEmpty())
            throw new EmptyCurrencyCodeException();

        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.notifyObservers();
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
        this.notifyObservers();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        this.notifyObservers();
    }

    @Override
    public void attachObserver(Observer ObseverInstance) {
        observers.add(ObseverInstance);
    }

    @Override
    public void detachObserver(Observer ObseverInstance) {
        observers.remove(ObseverInstance);
    }

    @Override
    public void notifyObservers() {
        for (Observer actualyObserver : observers) {
            actualyObserver.stateChanged(this);
        }
    }
}
