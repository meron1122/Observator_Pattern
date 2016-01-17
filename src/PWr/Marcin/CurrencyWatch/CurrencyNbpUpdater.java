package PWr.Marcin.CurrencyWatch;


import PWr.Marcin.CurrencyWatch.Entities.Currency;
import PWr.Marcin.CurrencyWatch.Exceptions.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.net.*;

/**
 * Created by Marcin Golonka on 22.11.15.
 *
 * @See http://www.nbp.pl/home.aspx?f=/kursy/instrukcja_pobierania_kursow_walut.html
 */
public class CurrencyNbpUpdater {

    private static final String currencyListUrl = "http://www.nbp.pl/Kursy/xml/LastA.xml";
    private static final String fileDownloadName = "temp.xml";


    private void downloadCurrencyList() throws Exception {
        try {
            //create new url
            URL currencyList = new URL(this.currencyListUrl); //a list parse
            //file write
            ReadableByteChannel rbc = Channels.newChannel(currencyList.openStream());
            FileOutputStream fos = new FileOutputStream(fileDownloadName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * TODO REFACTOR!
     * @param currencyToUpdate
     * @return
     * @throws Exception
     */
    public Currency updateCurrency(Currency currencyToUpdate) throws Exception {

        try {
            this.downloadCurrencyList(); //download xml file
            Document tree = parseTree(fileDownloadName); //parse xml file to tree
            NodeList nList = tree.getElementsByTagName("pozycja"); //find list of nodes

            for (int i = 0; i < nList.getLength(); i++) { //iterate over currencies
                Node nNode = nList.item(i);  //get item
                if (nNode.getNodeType() == Node.ELEMENT_NODE) { //oh, subnode
                    Element eElement = (Element) nNode; //cast it to  Element
                    String tempCode = eElement.getElementsByTagName("kod_waluty").item(0).getTextContent();

                    if (tempCode.equals(currencyToUpdate.getCode().toUpperCase())) { //match currency
                        //fill entity
                        currencyToUpdate.setName(eElement.getElementsByTagName("nazwa_waluty").item(0).getTextContent());
                        currencyToUpdate.setConversionRate(Double.parseDouble(eElement.getElementsByTagName("przelicznik").item(0).getTextContent().replace(",", ".")));
                        currencyToUpdate.setValue(Double.parseDouble(eElement.getElementsByTagName("kurs_sredni").item(0).getTextContent().replace(",", ".")));
                        return currencyToUpdate; ///return updated
                    }
                }
            }
        } catch (Exception e) { //rethrow exception, is Valid?
            throw e;
        }
        throw new NoMatchingCurrencyException();
    }

    private Document parseTree(String fileName) throws Exception {
        File fXmlFile = new File(fileName); //open file
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); //create builder from factory
        Document doc = dBuilder.parse(fXmlFile);  //parse
        return doc;
    }
}