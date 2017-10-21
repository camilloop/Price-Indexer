package org.camilloop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "price_indexer", type = "price_collector", shards = 1, replicas = 0, refreshInterval = "-1")
public class PriceCollector {

    @Id
    private String id;

    private String websiteName;

    private String productName;

    private Double price;

    public PriceCollector() {
    }

    public PriceCollector(String websiteName, String productName, Double price) {
        this.websiteName = websiteName;
        this.productName = productName;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PriceCollector{" +
                "id='" + id + '\'' +
                ", websiteName='" + websiteName + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
