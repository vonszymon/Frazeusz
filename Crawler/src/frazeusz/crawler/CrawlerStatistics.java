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

    public void addPage() {
        pages++;
    }

    public void addBytes(long size) {
        bytes += size;
    }

    public long getPages() {
        return pages;
    }

    public long getBytes() {
        return bytes;
    }

}
