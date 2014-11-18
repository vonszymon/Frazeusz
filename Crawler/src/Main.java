import Frazeusz.Crawler.Crawler;
import Frazeusz.Crawler.CrawlerGUI;
import Frazeusz.Parser.Parser;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("CRAWLER");

        Parser parser = new Parser();

        Crawler crawler = new Crawler(parser);

        CrawlerGUI gui = crawler.getGUI();
        gui.showForm();


        System.out.println("Setting up...");
        crawler.setup("data", 2);

        System.out.println("Starting...");
        crawler.start();

        System.out.println("All done");
    }
}