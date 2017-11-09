package org.camilloop.jsoup;

import org.camilloop.model.PriceCollector;
import org.camilloop.model.Website;
import org.camilloop.repository.PriceCollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
public class ProductData {

    private Extractor extractor;

    private PriceCollectorRepository priceCollectorRepository;

    private List<PriceCollector> list;

    @Autowired
    public ProductData(Extractor extractor, PriceCollectorRepository priceCollectorRepository) {
        this.extractor = extractor;
        this.priceCollectorRepository = priceCollectorRepository;
    }

    public void saveData(Website website) {
        WebCrawler webCrawler = new WebCrawler(website.getUrl(), website.getMaxDepth());
        HashSet<String> pageLinks = webCrawler.getPageLinks();
        List<Map<String, String>> data = this.extractor.getData(
                pageLinks,
                website.getNamePattern(),
                website.getPricePattern()
        );

        list = new ArrayList<>();

        for (Map map : data) {
            Double productPrice = this.preparePriceFormat(map.get(Extractor.PRODUCT_PRICE).toString());
            PriceCollector priceCollector = new PriceCollector(
                    website.getName(),
                    map.get(Extractor.URL).toString(),
                    map.get(Extractor.PRODUCT_NAME).toString(),
                    productPrice
            );
            list.add(priceCollector);
        }

        this.deleteData(website.getName());
        this.saveData(list);
    }

    private void deleteData(String websiteName) {
        if (!list.isEmpty()) {
            priceCollectorRepository.deleteAllByWebsiteName(websiteName);
        }
    }

    private void saveData(List<PriceCollector> list) {
        if (!list.isEmpty()) {
            priceCollectorRepository.saveAll(list);
        }
    }

    private Double preparePriceFormat(String price) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        try {
            Number number = format.parse(price);
            return number.doubleValue();
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
