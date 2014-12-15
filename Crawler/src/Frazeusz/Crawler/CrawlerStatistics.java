package frazeusz.crawler;

public class CrawlerStatistics {
    Crawler crawler;

    private long pages;

    private long bytes;

    public CrawlerStatistics(Crawler crawler) {
        this.crawler = crawler;
        pages = 0;
        bytes = 0;
    }

    public synchronized void addPage() {
        pages++;
    }

    public synchronized void addBytes(long size) {
        bytes += size;
    }

    public synchronized long getPages() {
        return pages;
    }

    public synchronized long getBytes() {
        return bytes;
    }

}
