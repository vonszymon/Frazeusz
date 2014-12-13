package Frazeusz.Crawler;

public class CrawlerStatistics {
    Crawler crawler;

    private long pages;

    private long bytes;

    public CrawlerStatistics() {
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
