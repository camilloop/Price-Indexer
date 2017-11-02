package org.camilloop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "price_indexer", type = "website", shards = 1, replicas = 0, refreshInterval = "-1")
public class Website {

    @Id
    private String name;

    private String namePattern;

    private String pricePattern;

    private int maxDepth;

    public Website() {
    }

    public Website(String name, String namePattern, String pricePattern, int maxDepth) {
        this.name = name;
        this.namePattern = namePattern;
        this.pricePattern = pricePattern;
        this.maxDepth = maxDepth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePattern() {
        return namePattern;
    }

    public void setNamePattern(String namePattern) {
        this.namePattern = namePattern;
    }

    public String getPricePattern() {
        return pricePattern;
    }

    public void setPricePattern(String pricePattern) {
        this.pricePattern = pricePattern;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public String toString() {
        return "Website{" +
                "name='" + name + '\'' +
                ", namePattern='" + namePattern + '\'' +
                ", pricePattern='" + pricePattern + '\'' +
                ", maxDepth=" + maxDepth +
                '}';
    }
}
