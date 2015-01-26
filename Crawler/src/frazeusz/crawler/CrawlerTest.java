package frazeusz.crawler;

import frazeusz.parser.Parser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CrawlerTest {

    @Mock
    Parser parser;

    @Test
    public void testEmpty() throws Exception {

        CrawlerConfigurator cfg = new CrawlerConfigurator();

        Crawler c = new Crawler(cfg, parser);
        CrawlerStatistics stats = c.getStatistics();

        c.start();

        assertEquals(0, stats.getPages());
        assertEquals(0, stats.getBytes());

        Mockito.verify(parser, Mockito.never()).process(Mockito.any(Page.class));
    }

    private void mockParserWithURLs() throws Exception
    {
        List<URL> urlsA = new ArrayList<>();
        urlsA.add(new URL("http://pl.wikipedia.org/wiki/B"));
        urlsA.add(new URL("http://pl.wikipedia.org/wiki/C"));
        urlsA.add(new URL("http://pl.wikipedia.org/wiki/D"));

        List<URL> urlsB = new ArrayList<>();
        urlsB.add(new URL("http://pl.wikipedia.org/wiki/E"));
        urlsB.add(new URL("http://pl.wikipedia.org/wiki/F"));

        List<URL> urlsE = new ArrayList<>();
        urlsE.add(new URL("http://pl.wikipedia.org/wiki/G"));
        urlsE.add(new URL("http://pl.wikipedia.org/wiki/H"));

        List<URL> urlsNone = new ArrayList<>();

        Mockito.when(parser.process(Mockito.any(Page.class)))
                .thenReturn(urlsA)
                .thenReturn(urlsB)
                .thenReturn(urlsE)
                .thenReturn(urlsNone);
    }

    private void testCrawlingDepth(int depth, int expectedPages) throws Exception
    {
        CrawlerConfigurator cfg = new CrawlerConfigurator();
        cfg.addDomain("http://pl.wikipedia.org/wiki/A");
        cfg.setDepth(depth);
        cfg.setPages(1000);

        mockParserWithURLs();

        Crawler c = new Crawler(cfg, parser);
        CrawlerStatistics stats = c.getStatistics();

        c.start();

        assertEquals(expectedPages, stats.getPages());

        Mockito.verify(parser, Mockito.times(expectedPages)).process(Mockito.any(Page.class));
    }

    @Test
    public void testCrawlingDepthZero() throws Exception {
        testCrawlingDepth(0, 1);
    }

    @Test
    public void testCrawlingDepthOne() throws Exception {
        testCrawlingDepth(1, 4);
    }

    @Test
    public void testCrawlingDepthTwo() throws Exception {
        testCrawlingDepth(2, 6);
    }

    @Test
    public void testCrawlingDepthThree() throws Exception {
        testCrawlingDepth(3, 8);
    }

    @Test
    public void testCrawlingDepthUnlimited() throws Exception {
        testCrawlingDepth(1000, 8);
    }

    private void testCrawlingPages(int pages, int expectedPages) throws Exception
    {
        CrawlerConfigurator cfg = new CrawlerConfigurator();
        cfg.addDomain("http://pl.wikipedia.org/wiki/A");
        cfg.setDepth(1000);
        cfg.setPages(pages);

        mockParserWithURLs();

        Crawler c = new Crawler(cfg, parser);
        CrawlerStatistics stats = c.getStatistics();

        c.start();

        assertEquals(expectedPages, stats.getPages());

        Mockito.verify(parser, Mockito.times(expectedPages)).process(Mockito.any(Page.class));
    }

    @Test
    public void testCrawlingPagesZero() throws Exception
    {
        testCrawlingPages(0, 0);
    }

    @Test
    public void testCrawlingPagesOne() throws Exception
    {
        testCrawlingPages(1, 1);
    }

    @Test
    public void testCrawlingPagesThree() throws Exception
    {
        testCrawlingPages(3, 3);
    }

    @Test
    public void testCrawlingPagesUnlimited() throws Exception
    {
        testCrawlingPages(1000, 8);
    }
}