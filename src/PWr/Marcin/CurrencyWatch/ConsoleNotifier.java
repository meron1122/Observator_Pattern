package PWr.Marcin.CurrencyWatch;

import PWr.Marcin.CurrencyWatch.Entities.Currency;
import PWr.Marcin.CurrencyWatch.Interfaces.Observer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Marcin Golonka on 22.11.15.
 */
public class ConsoleNotifier implements Observer {


    /**
     * Print to console if all varibles is set
     *
     * @param ObservatingSubject Observating Object instance
     */
    @Override
    public void stateChanged(Object ObservatingSubject) {
        if (!(ObservatingSubject instanceof Currency))
            return;

        Currency curr = (Currency) ObservatingSubject;
        if (!curr.getCode().isEmpty() && curr.getConversionRate() != 0 && !curr.getName().isEmpty() && curr.getValue() != 0) { //print if date is set
            //date format
            Date now = new Date();
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
            String date = DATE_FORMAT.format(now);
            System.out.println("[" + date + "] Waluta:" + curr.getName() + " Kod:" +
                    curr.getCode() + " Kurs:" + curr.getConversionRate() + " Wartość:" + curr.getValue());  //cast to currency and show something

        }
    }
}
