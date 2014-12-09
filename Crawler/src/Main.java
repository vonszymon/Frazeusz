import frazeusz.crawler.Crawler;
import frazeusz.crawler.CrawlerConfigurator;
import frazeusz.crawler.CrawlerGUI;
import frazeusz.GUI.GUI;
import frazeusz.parser.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    CrawlerGUI crawlerGui;

    public void init() {
        System.out.println("Initializing");

        ActionListener onStart = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    start();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };

        GUI gui = new GUI(onStart);
        crawlerGui = new CrawlerGUI();

        gui.addPane("crawler", crawlerGui.getRootPanel());

        gui.showForm();

        System.out.println("GUI shown");
    }

    public void start() throws Exception {
        System.out.println("Start");

        CrawlerConfigurator cfg = crawlerGui.getConfigurator();

        Parser parser = new Parser();
        Crawler crawler = new Crawler(cfg, parser);

        System.out.println("Setting up...");
        crawler.setup("data", 2);

        System.out.println("Starting...");
        crawler.start();

        System.out.println("All done");
    }

    public static void main(String[] args) throws Exception {

        Main m = new Main();
        m.init();
    }
}