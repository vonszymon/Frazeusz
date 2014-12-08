package Frazeusz.Parser;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public synchronized List<String> process(Page page) {
        System.out.println("Parser parsing page");

        List<String> adresses = new ArrayList<String>();

        adresses.add("http://www.koty.pl");

        return adresses;
    }

}
