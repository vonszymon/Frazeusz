package frazeusz.crawler;

import frazeusz.parser.Parser;
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
    final int CRAWLERS = 4;
    final String CRAWLERDIR = "crawlerdata";

    private CrawlController controller;
    private Parser parser;
    private CrawlerStatistics stats;

    public Crawler(CrawlerConfigurator configurator, Parser parser) throws Exception {
        this.parser = parser;
        this.stats = new CrawlerStatistics(this);

        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(CRAWLERDIR);

        config.setPolitenessDelay(100);

        config.setMaxDepthOfCrawling(configurator.getDepth());

        config.setMaxPagesToFetch(configurator.getPages());

        config.setMaxDownloadedBytes(configurator.getBytes());

        config.setResumableCrawling(false);

        config.setIncludeHttpsPages(true);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        this.controller = new CrawlController(config, pageFetcher, robotstxtServer, parser, getStatistics());

        for (String domain : configurator.getDomains())
        {
            controller.addSeed(domain);
        }
    }

    public void start() {
        this.controller.start(BasicCrawler.class, CRAWLERS);
    }

    public CrawlerStatistics getStatistics() {
        return this.stats;
    }

    public Parser getParser() {
        return this.parser;
    }

    public void stopCrawling(){ this.controller.shutdown(); }

    public void suspendCrawling() { this.controller.suspend(); }

    public void resumeCrawling() { this.controller.resume(); }
}
