package org.camilloop.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.*;

@Component
public class Extractor {

    private Map<String, String> product;
    private List<Map<String, String>> productsList;
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String URL = "url";
    private final int[] count = {0};

    public Extractor() {
    }

    public List<Map<String, String>> getData(HashSet<String> links, String namePattern, String pricePattern) {
        this.productsList = new ArrayList<>();

        links.forEach(x -> {
            Document document;
            try {
                document = Jsoup.connect(x).get();
                Element productName = document.select(namePattern).first();
                Element productPrice = document.select(pricePattern).first();

                if (null != productName && null != productPrice) {
                    this.product = new HashMap<>();
                    this.product.put(URL, x);
                    this.product.put(PRODUCT_NAME, productName.text());
                    this.product.put(PRODUCT_PRICE, productPrice.text());
                    productsList.add(count[0], product);
                    this.count[0]++;
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
        return this.productsList;
    }
}
