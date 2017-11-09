package org.camilloop.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;

public class WebCrawler {
    private String url;
    private int maxDepth;
    private HashSet<String> links;
    private static final Logger logger = LoggerFactory.getLogger(WebCrawler.class);

    public WebCrawler(String url, int maxDepth) {
        this.url = url;
        this.maxDepth = maxDepth;
        this.links = new HashSet<>();
    }

    public HashSet<String> getPageLinks() {
        this.getPageLinks(url, 0);
        return this.links;
    }

    private void getPageLinks(String url, int depth) {
        if (!this.links.contains(url) && depth <= this.maxDepth) {
            //System.out.println(">> Depth: " + depth + " [" + url + "]");
            try {
                this.links.add(url);

                Document document = Jsoup.connect(url).get();
                Elements linksOnPage = document.select("a[href~=(" + this.url + ")|(^/)]");

                depth++;
                for (Element page : linksOnPage) {
                    this.getPageLinks(page.attr("abs:href"), depth);
                }
            } catch (IOException | IllegalArgumentException e) {
                logger.warn("For '" + url + "': " + e);
            }
        }
    }

    @Override
    public String toString() {
        return "WebCrawler{" +
                "links=" + links +
                '}';
    }
}
