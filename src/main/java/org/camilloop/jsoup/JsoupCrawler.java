package org.camilloop.jsoup;

import org.camilloop.jsoup.api.Crawler;
import org.camilloop.model.Website;
import org.camilloop.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsoupCrawler implements Crawler {

    private ProductData productData;

    private WebsiteRepository websiteRepository;

    @Autowired
    public JsoupCrawler(ProductData productData, WebsiteRepository websiteRepository) {
        this.productData = productData;
        this.websiteRepository = websiteRepository;
    }

    @Override
    public void executeByWebsite(Website website) {
        this.productData.saveData(website);
    }

    @Override
    public void executeForAllWebsites() {
        Iterable<Website> websites = this.websiteRepository.findAll();
        for (Website website : websites) {
            this.productData.saveData(website);
        }
    }
}
