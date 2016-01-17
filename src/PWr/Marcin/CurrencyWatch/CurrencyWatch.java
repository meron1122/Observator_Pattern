package PWr.Marcin.CurrencyWatch;

import PWr.Marcin.CurrencyWatch.Entities.Currency;
import PWr.Marcin.CurrencyWatch.Exceptions.*;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Marcin Golonka on 14.12.15.
 */
public class CurrencyWatch {

    public static void main(String[] args) {
        CurrencyNbpUpdater currencyUpdater = new CurrencyNbpUpdater();

        try {
            Scanner textReader = new Scanner(System.in); //init scanner

            System.out.print("Podaj kod waluty do obserwacji:");
            ConsoleNotifier consoleNotif = new ConsoleNotifier(); //new kind of observer

            Currency euro = new Currency(textReader.nextLine()); //construct currency
            euro.attachObserver(consoleNotif); //attach observer

            currencyUpdater.updateCurrency(euro); //update currency
            euro.detachObserver(consoleNotif);// detatach observer
        } catch (EmptyCurrencyCodeException e) {
            System.out.println("Nie podałeś kodu waluty!");
        } catch (NoMatchingCurrencyException e) {
            System.out.print("Nie znaleziono waluty o takim kodzie");
        } catch (Exception e) {
            System.out.print(e.toString());
        } //TODO Catch more exception, eg. issue with download xml file, or error in parsing
        finally {
            //remove temp file after all, if exist
            File temp = new File("temp.xml");
            if (temp.exists() && !temp.isDirectory()) {
                temp.delete();
            }
        }

    }
}
