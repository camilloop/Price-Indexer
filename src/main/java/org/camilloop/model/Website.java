package org.camilloop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "price_indexer", type = "website", shards = 1, replicas = 0, refreshInterval = "-1")
public class Website {

    @Id
    private String name;

    public Website() {
    }

    public Website(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Website{" +
                "name='" + name + '\'' +
                '}';
    }
}
