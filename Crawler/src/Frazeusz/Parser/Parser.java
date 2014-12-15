package frazeusz.parser;

import frazeusz.crawler.Page;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public synchronized List<URL> process(Page page) throws MalformedURLException {
        System.out.println("Parser parsing page");

        List<URL> addresses = new ArrayList<URL>();

        URL url1 = new URL("http://www.moda.pl/odziez/bluzki/");
        URL url2 = new URL("http://www.moda.pl/odziez/kurtki/");
        URL url3 = new URL("http://www.koty.pl/");
        if(page.getWebURL().getURL().equals("http://www.koty.pl/")){
            addresses.add(new URL("http://www.psy.pl/"));
        }
        addresses.add(url1);
        addresses.add(url2);
        addresses.add(url3);

        return addresses;
    }

}
