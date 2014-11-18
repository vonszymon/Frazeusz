import Frazeusz.Crawler.Crawler;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("CRAWLER");

        Crawler crawler = new Crawler();

        System.out.println("Setting up...");
        crawler.setup("data", 2);

        System.out.println("Starting...");
        crawler.start();

        System.out.println("All done");
    }
}