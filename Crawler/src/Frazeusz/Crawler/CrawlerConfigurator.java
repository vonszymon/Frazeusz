package frazeusz.crawler;

import java.util.ArrayList;
import java.util.List;

public class CrawlerConfigurator {
    private int pages;
    private int depth;
    private int bytes;
    private List<String> domains;

    public CrawlerConfigurator()
    {
        domains = new ArrayList<String>();
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public void addDomain(String domain)
    {
        this.domains.add(domain);
    }
}
