package Frazeusz.Crawler;

import Frazeusz.Parser.Parser;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


/**
 * Based on work of:
 *
 * @author Yasser Ganjisaffar <lastname at gmail dot com>
 */

public class Crawler {
    CrawlController controller;
    Parser parser;
    int numberOfCrawlers;
    CrawlerStatistics stats;

    public Crawler(CrawlerConfigurator configurator, Parser parser) {
        this.parser = parser;
        this.stats = new CrawlerStatistics(this);

        this.configure(configurator);
    }

    private void configure(CrawlerConfigurator configurator)
    {
        System.out.println("Performing Crawler configuration");

        System.out.println("Number of pages: " + configurator.getPages());
        System.out.println("Maximum depth: " + configurator.getDepth());
        System.out.println("Number of bytes: " + configurator.getBytes());
        System.out.println("List of domains: ");
        for (String domain : configurator.getDomains())
        {
            System.out.println("* " + domain);
        }
    }

    public void setup(String crawlStorageFolder, int numberOfCrawlers) throws Exception {



    /*
     * crawlStorageFolder is a folder where intermediate crawl data is
     * stored.
     */

    /*
     * numberOfCrawlers shows the number of concurrent threads that should
     * be initiated for crawling.
     */
        this.numberOfCrawlers = numberOfCrawlers;

        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);

    /*
     * Be polite: Make sure that we don't send more than 1 request per
     * second (1000 milliseconds between requests).
     */
        config.setPolitenessDelay(100);

    /*
     * You can set the maximum crawl depth here. The default value is -1 for
     * unlimited depth
     */
        config.setMaxDepthOfCrawling(4);

    /*
     * You can set the maximum number of pages to crawl. The default value
     * is -1 for unlimited number of pages
     */
        config.setMaxPagesToFetch(100);

    /*
     * Do you need to set a proxy? If so, you can use:
     * config.setProxyHost("proxyserver.example.com");
     * config.setProxyPort(8080);
     *
     * If your proxy also needs authentication:
     * config.setProxyUsername(username); config.getProxyPassword(password);
     */

    /*
     * This config parameter can be used to set your crawl to be resumable
     * (meaning that you can resume the crawl from a previously
     * interrupted/crashed crawl). Note: if you enable resuming feature and
     * want to start a fresh crawl, you need to delete the contents of
     * rootFolder manually.
     */
        config.setResumableCrawling(false);

    /*
     * Instantiate the controller for this crawl.
     */
        //  CrawlerStatistics stats = new CrawlerStatistics();
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        this.controller = new CrawlController(config, pageFetcher, robotstxtServer, parser, getStatistics());

    /*
     * For each crawl, you need to add some seed urls. These are the first
     * URLs that are fetched and then the crawler starts following links
     * which are found in these pages
     */
        controller.addSeed("http://www.moda.pl");
    }

    public void start() {
     /*
     * Start the crawl. This is a blocking operation, meaning that your code
     * will reach the line after this only when crawling is finished.
     */
        this.controller.start(BasicCrawler.class, this.numberOfCrawlers);
    }

    public CrawlerStatistics getStatistics() {
        return this.stats;
    }

    public Parser getParser() {
        return this.parser;
    }
}
