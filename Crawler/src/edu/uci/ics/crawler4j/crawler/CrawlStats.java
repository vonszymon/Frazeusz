package edu.uci.ics.crawler4j.crawler;

public class CrawlStats {
	
	private long pages;
	
	private long bytes;
	
	public CrawlStats(){
		pages = 0;
		bytes = 0;
	}
	
	public void addPage(){
		pages++;
	}
	
	public void addBytes(long size){
		bytes += size;
	}
	
	public long getPages(){
		return pages;
	}
	
	public long getBytes(){
		return bytes;
	}
	
}
