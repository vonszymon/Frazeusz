package Frazeusz.Parser;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public synchronized List<URL> process(Page page) throws MalformedURLException {
        System.out.println("Parser parsing page");

        List<URL> adresses = new ArrayList<URL>();

        URL url1 = new URL("http://www.moda.pl/odziez/bluzki/");
        URL url2 = new URL("http://www.moda.pl/odziez/kurtki/");
        URL url3 = new URL("http://www.koty.pl/");
        adresses.add(url1);
        adresses.add(url2);
        adresses.add(url3);

        return adresses;
    }

}
