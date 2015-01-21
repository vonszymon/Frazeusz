import frazeusz.crawler.Crawler;
import frazeusz.crawler.CrawlerConfigurator;
import frazeusz.crawler.CrawlerGUI;
import frazeusz.crawler.CrawlerStatistics;
import frazeusz.GUI.GUI;
import frazeusz.parser.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    CrawlerGUI crawlerGui;
    Crawler crawler;

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

        ActionListener onStop = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    crawler.stopCrawling();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };

        ActionListener onSuspend = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    crawler.suspendCrawling();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };

        ActionListener onResume = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    crawler.resumeCrawling();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };

        GUI gui = new GUI(onStart,onStop,onSuspend,onResume);
        crawlerGui = new CrawlerGUI();

        gui.addPane("Crawler", crawlerGui.getRootPanel());

        gui.showForm();

        System.out.println("GUI shown");
    }

    public void start() throws Exception {
        System.out.println("Start");

        CrawlerConfigurator cfg = crawlerGui.getConfigurator();

        Parser parser = new Parser();
        crawler = new Crawler(cfg, parser);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                crawler.start();
            }
        });
        t.start();
        System.out.println("All done");
    }

    public static void main(String[] args) throws Exception {

        Main m = new Main();
        m.init();
    }
}