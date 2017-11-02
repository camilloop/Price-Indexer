package org.camilloop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import java.sql.Timestamp;

@Document(indexName = "price_indexer", type = "price_collector", shards = 1, replicas = 0, refreshInterval = "-1")
public class PriceCollector {

    @Id
    private String id;

    private String websiteName;

    private String productUrl;

    private String productName;

    private Double price;

    private Timestamp createdAt;

    public PriceCollector() {
    }

    public PriceCollector(String websiteName, String productUrl, String productName, Double price) {
        this.websiteName = websiteName;
        this.productUrl = productUrl;
        this.productName = productName;
        this.price = price;
        this.createdAt = new Timestamp(System.currentTimeMillis());
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

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PriceCollector{" +
                "id='" + id + '\'' +
                ", websiteName='" + websiteName + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}
